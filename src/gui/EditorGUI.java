package gui;

import javax.swing.*;
import java.awt.event.*;

public class EditorGUI{
  
  public EditorGUI(){
  }
  
  public void launch(){
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShow();
      }
    });
  }
  
  private void createAndShow(){
    JFrame frame = new JFrame("Collaborative Text Editor");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //Add the menubar
    frame.setJMenuBar(createMenuBar());
    
    //Add the text area
    JTextPane textPane = new JTextPane();
    JScrollPane scrollPane = new JScrollPane(textPane);
    textPane.addKeyListener(new KeystrokeListener());
    frame.getContentPane().add(scrollPane);
    
    //Display the window.
    frame.pack();
    frame.setSize(800, 600);
    frame.setVisible(true);
  }
  
  //Creates and returns a JMenuBar with the appropriate JMenu items
  private JMenuBar createMenuBar(){
    JMenuBar result = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    
    //Create menu items
    JMenuItem open = new JMenuItem("Open");
    JMenuItem save = new JMenuItem("Save");
    JMenuItem saveAs = new JMenuItem("Save As...");
    JMenuItem collab = new JMenuItem("Collaborate");
    JMenuItem exitCollab = new JMenuItem("Exit Collaboration");
    JMenuItem exitApp = new JMenuItem("Exit Application");
    
    //Add menu items to JMenu
    fileMenu.add(open);
    fileMenu.add(save);
    fileMenu.add(saveAs);
    fileMenu.add(collab);
    fileMenu.add(exitCollab);
    fileMenu.add(exitApp);
    
    //Register menu items with listeners
    open.addActionListener(new OpenListener());
    exitApp.addActionListener(new ExitListener());
    
    result.add(fileMenu);
    return result;
  }
  
  /*************************************
   * Listeners for specific JMenuItems *
   *************************************/
  
  //Display the dialog to open a file
  private class OpenListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
      System.out.println("Display the open file dialog.");
    }
  }
  
  //Exit the application
  private class ExitListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
      System.exit(0);
    }
  }
  
  /**************************************/
  
  private class KeystrokeListener implements KeyListener{
    public void keyPressed(KeyEvent e){
      //System.out.println("Key pressed.");
    }
    
    public void keyReleased(KeyEvent e){
      //System.out.println("Key released.");
    }
    
    public void keyTyped(KeyEvent e){
      System.out.println("Key typed: " + e.getKeyChar() + "(" + e.getKeyCode() + ")");
    }
  }//End KeystrokeListener
  
}//End CollaborativeEditorGUI
