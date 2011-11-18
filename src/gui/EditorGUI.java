package gui;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.MappedByteBuffer;
import java.nio.charset.Charset;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.EditorKit;
import javax.swing.text.DefaultEditorKit;
import java.util.Observer;
import java.util.Observable;
import handler.*;
import user.*;
import static java.lang.System.out;
import network.*;

public class EditorGUI implements Observer {

    JTextPane _textPane;
    JFrame _frame;
    protected Client _client;
    
    public EditorGUI(Client client){
        _client = client;
    }
    /*
     * 
     */
    public void update ( Observable obs, Object docText ) {
        if (docText instanceof String){
            String docTextString = (String)docText;
            _textPane.setText(docTextString);
        }
    }
    public void launch ( ) {
        /*
        javax.swing.SwingUtilities.invokeLater(new Runnable ( ) {
            public void run ( ) { createAndShow(); }
        });
         */
        createAndShow();
    }

    private void createAndShow ( ) {
        _frame = new JFrame("Collaborative Text Editor");
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add the menubar
        _frame.setJMenuBar(createMenuBar());

        //Add the text area
        _textPane = new JTextPane();
        JScrollPane scrollPane = new JScrollPane(_textPane);
        /*FIX ME: JTEXTPANE NEEDS CUSTOM CURSORS */
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
        JMenuItem newFile = new JMenuItem("New");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem saveAs = new JMenuItem("Save As...");
        JMenuItem collab = new JMenuItem("Collaborate");
        JMenuItem exitCollab = new JMenuItem("Exit Collaboration");
        JMenuItem exitApp = new JMenuItem("Exit Application");

        //Add menu items to JMenu
        fileMenu.add(newFile);
        fileMenu.add(open);
        fileMenu.add(save);
        fileMenu.add(saveAs);
        fileMenu.add(collab);
        fileMenu.add(exitCollab);
        fileMenu.add(exitApp);

        //Register menu items with listeners
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

    private class NewFileListener implements ActionListener {
        public void actionPerformed ( ActionEvent e ) {
            _textPane.setText("");
        }
    }

    private class CollabListener implements ActionListener {
        public void actionPerformed ( ActionEvent e ) {
            try{
                new CreateCollaboration(null).execute(null, null);
            }
            catch(Exception e1){e1.printStackTrace();}
        }
    }
    
    //Display the dialog to open a file
    private class OpenFileListener implements ActionListener {
        public void actionPerformed ( ActionEvent e ) {
            System.out.println("Display the open file dialog.");
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
                    //System.out.println(contents + "\n<<<File has been read");
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
                System.out.println("Saved file as: " + _fileChooser.getSelectedFile().getName());
            }
            else { System.out.println("Save command cancelled by user."); }
        }
    }

    //Exit the application
    private class ExitListener implements ActionListener {
        public void actionPerformed ( ActionEvent e ) { System.exit(0); }
    }

    /**************************************/

    private class KeystrokeListener implements KeyListener {
        public void keyPressed ( KeyEvent e ) {
            //System.out.println("Key pressed.");
        }

        public void keyReleased ( KeyEvent e ) {
            //System.out.println("Key released.");
            //_client.setDocument(_textPane.getText());
        }

        public void keyTyped ( KeyEvent e ) {
            _client.passKeyEvent(e);
        }
    }//End KeystrokeListener

}//End CollaborativeEditorGUI
