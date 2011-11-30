package gui;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.File;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.MappedByteBuffer;
import java.nio.charset.Charset;
import java.util.Observer;
import java.util.Observable;
import commands.*;
import handler.*;
import user.*;
import java.rmi.RemoteException;

public class EditorGUI implements Observer {

    
    
    JTextPane _textPane;
    JFrame _frame;
    protected Client _client;
    private CollaborateDialog _dialog;
    private static final boolean DEBUG = true;

    public EditorGUI(Client client){
        _client = client;
    }

   /*
    * Update the frame with the specified docText.
    */
    public void update ( Observable observable, Object docText ) {
        if (docText instanceof String){
            String docTextString = (String)docText;
            _textPane.setText(docTextString);
        }
    }

    private void print ( String message ) { if (DEBUG) { System.out.println(message); } }

    
    /*
     * Launch the GUI.
     */
    public void launch ( ) {
        /*
        javax.swing.SwingUtilities.invokeLater(new Runnable ( ) {
            public void run ( ) { createAndShow(); }
        });
         */
        createAndShow();
        _dialog = new CollaborateDialog(_frame, _client);
        _dialog.hideDialog();
    }

    private void createAndShow ( ) {
        _frame = new JFrame("Collaborative Text Editor");
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add the menubar
        _frame.setJMenuBar(createMenuBar());

        //Add the text area
        _textPane = new JTextPane();
        JScrollPane scrollPane = new JScrollPane(_textPane);
        /*FIX ME: JTEXTPANE NEEDS CUSTOM CURSORS...
         * probably need to extend JTextPane... we could then add a
         * blinking pipe ("|") or something to be the cursor.
         * As it is right now, there is no cursor showing because I had to make
         * the editable option false*
         */
        _textPane.setEditable(false); // This might end up being set so we can manually keep track of what is displayed
        _textPane.addKeyListener(new KeystrokeListener());
        _frame.getContentPane().add(scrollPane);

        //Display the window.
        _frame.pack();
        _frame.setSize(800, 600);
        _frame.setVisible(true);
    }

    //Creates and returns a JMenuBar with the appropriate JMenu items
    private JMenuBar createMenuBar(){
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        //Create menu items
        JMenuItem changeID = new JMenuItem("Change UserID");
        JMenuItem newFile = new JMenuItem("New");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem saveAs = new JMenuItem("Save As...");
        JMenuItem collab = new JMenuItem("Collaborate");
        JMenuItem exitCollab = new JMenuItem("Exit Collaboration");
        JMenuItem exitApp = new JMenuItem("Exit Application");

        //Add menu items to JMenu
        fileMenu.add(changeID);
        fileMenu.add(newFile);
        fileMenu.add(open);
        fileMenu.add(save);
        fileMenu.add(saveAs);
        fileMenu.add(collab);
        fileMenu.add(exitCollab);
        fileMenu.add(exitApp);

        //Register menu items with listeners
        changeID.addActionListener(new ChangeClientIDListener());
        newFile.addActionListener(new NewFileListener());
        open.addActionListener(new OpenFileListener());
        saveAs.addActionListener(new SaveAsListener());
        collab.addActionListener(new CollabListener());
        exitApp.addActionListener(new ExitListener());

        result.add(fileMenu);
        return result;
    }

    /*************************************
    * Listeners for specific JMenuItems *
    *************************************/

    private class ChangeClientIDListener implements ActionListener {
        public void actionPerformed ( ActionEvent e ) {
            try { _client.changeClientName("OTHER"); }
            catch (InvalidUserIDException iuide) { iuide.printStackTrace(); }
            catch (RemoteException re) { re.printStackTrace(); }
            catch (UserNotFoundException  unfe) { unfe.printStackTrace(); }
            catch (OutOfBoundsException oobe) { oobe.printStackTrace(); }
        }
    }

    private class NewFileListener implements ActionListener {
        public void actionPerformed ( ActionEvent e ) {
            _textPane.setText("");
            //Also have to reset the model:
            //After selecting "New File", begin to type and you'll see characters from "last" document
        }
    }

    private class CollabListener implements ActionListener {
        public void actionPerformed ( ActionEvent e ) {
            try {
                _dialog.showDialog();
                String documentName = "TestDoc1"; //Dumb info for testing
                String pass = "password"; //Dumb password for testing
                DocumentKey documentKey = new DocumentKey(documentName, pass);

                //_client.initiateCollaboration();
            }
            catch (Exception e1) { e1.printStackTrace(); }
        }
    }

    //Display the dialog to open a file
    private class OpenFileListener implements ActionListener {
        public void actionPerformed ( ActionEvent e ) {
            print("Display the open file dialog.");
            //Setup FileChooser
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showOpenDialog(_frame);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                File f = chooser.getSelectedFile();
                try {
                    FileInputStream stream = new FileInputStream(f);
                    FileChannel fChannel = stream.getChannel();
                    MappedByteBuffer byteBuff = fChannel.map(FileChannel.MapMode.READ_ONLY, 0, fChannel.size());
                    String contents = Charset.defaultCharset().decode(byteBuff).toString();

                    //_textPane.setText(contents);
                    //update(this, contents);
                    _client.setDocument(contents);
                    //print(contents + "\n<<<File has been read");
                    stream.close();
                }
                catch (Exception ioe) {
                    ioe.printStackTrace();
                    System.out.println(ioe.getMessage());
                }
            }

        }
    }

    private class SaveAsListener implements ActionListener {
        public void actionPerformed ( ActionEvent e) {
            JFileChooser _fileChooser = new JFileChooser();
            int returnVal = _fileChooser.showSaveDialog(_frame);
            if (returnVal == JFileChooser.APPROVE_OPTION){
                try {
                    File f = _fileChooser.getSelectedFile();
                    Writer out = new OutputStreamWriter(new FileOutputStream(f));
                    out.write(_textPane.getText());
                    out.close();
                }
                catch(IOException ioe) {
                    ioe.printStackTrace();
                    System.out.println(ioe.getMessage());
                }
                catch(Exception ex) {
                    ex.printStackTrace();
                    System.out.println(ex.getMessage());
                }
                print("Saved file as: " + _fileChooser.getSelectedFile().getName());
            }
            else { print("Save command cancelled by user."); }
        }
    }

    //Exit the application
    private class ExitListener implements ActionListener {
        public void actionPerformed ( ActionEvent e ) { System.exit(0); }
    }

    /**************************************/
    private class KeystrokeListener implements KeyListener {

        public void keyPressed(KeyEvent e) {
            try {
               

                Command command = null;
                CTEUser user = _client.getUser();
                TextPosition tp = (TextPosition) user.getPosition().clone();
                print("Text Position: " + tp);
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    print("Down pressed");
                    _client.passCommand(command);
                    
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    print("UP pressed");
                    
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    print("LEFT pressed");
                    command = new MoveCursorPositionCommand(user, -1);
                    _client.passCommand(command);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    print("RIGHT pressed");
                    command = new MoveCursorPositionCommand(user, 1);
                    _client.passCommand(command);
                }

                tp = (TextPosition) user.getPosition().clone();
                print("Text Position: " + tp);  
                                
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        public void keyReleased ( KeyEvent e ) {
            print("Key released.");
        }

        /*
         * When a key is typed, create a Command and pass it to the _client.
         */
        public void keyTyped ( KeyEvent e ) {
            try {
                Command command;
                CTEUser user = _client.getUser();
                TextPosition tp = (TextPosition) user.getPosition().clone();
                if (e.getKeyChar() != KeyEvent.VK_BACK_SPACE) { command = new InsertTextCommand(user, Character.toString(e.getKeyChar())); }
                else {
                    tp.decrement();
                    command = new RemoveTextCommand(user, tp);
                }
                _client.passCommand(command);
            }
            catch (UserNotFoundException iue) { iue.printStackTrace(); }
            catch (InvalidUserIDException iuide) { iuide.printStackTrace(); }
            catch (OutOfBoundsException oobe) { oobe.printStackTrace(); }
            catch (Exception ex) { ex.printStackTrace(); }
        }//End keyTyped()
        
    }//End KeystrokeListener

}//End EditorGUI
