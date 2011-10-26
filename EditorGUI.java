import javax.swing.*;
import java.awt.event.*;

public class EditorGUI{
  
  public EditorGUI(){
    //constructor
  }
  
  public void launch(){
    //Create and show this application's GUI
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShow();
      }
    });
  }
  
  //Create the GUI and show it
  private void createAndShow(){
    //Create and set up the window
    JFrame frame = new JFrame("Collaborative Text Editor");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //Add the menubar
    JMenuBar menuBar = createMenuBar();
    frame.setJMenuBar(menuBar);
    
    //Add the text area
    JTextArea textArea = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(textArea);
    frame.getContentPane().add(scrollPane);
    
    //Display the window.
    frame.pack();
    frame.setSize(800, 600);
    frame.setVisible(true);
  }
  
  //Creates and returns a JMenuBar with the appropriate JMenu items
  private static JMenuBar createMenuBar(){
    JMenuBar result = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    fileMenu.add("Open");
    fileMenu.add("Save");
    fileMenu.add("Save As...");
    fileMenu.add("Collaborate");
    fileMenu.add("Exit Collaboration");
    JMenuItem exitMenuItem = new JMenuItem("Exit Application");
    ExitListener exitListener = new ExitListener();
    exitMenuItem.addActionListener(exitListener);
    fileMenu.add(exitMenuItem);
    result.add(fileMenu);
    return result;
  }
  
  private static class ExitListener implements ActionListener{
    
    public void actionPerformed(ActionEvent e){
      System.exit(0);
    }
    
  }
  
}//End CollaborativeEditorGUI class
