import javax.swing.*;
import java.awt.event.*;

public class EditorGUI{
  
  private JMenuBar _menuBar;
  private JTextArea _textArea;
  
  //Initialize all components of the GUI and register with listeners
  public EditorGUI(){
    _menuBar = createMenuBar();
    _textArea = createTextArea();
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
    frame.setJMenuBar(_menuBar);
    
    //Add the text area
    JScrollPane scrollPane = new JScrollPane(_textArea);
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
  
  //Creates and returns a JTextArea with the approriate settings
  private JTextArea createTextArea(){
    JTextArea result = new JTextArea();
    result.setLineWrap(true);
    return result;
  }
  
  private static class ExitListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
      System.exit(0);
    }
  }//End ExitListener
  
}//End CollaborativeEditorGUI