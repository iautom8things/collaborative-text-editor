import javax.swing.*;

public class CollaborativeEditorGUI{
  
   public static void main(String[] args) {
        //Create and show this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
   
   /**
     * Create the GUI and show it.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Collaborative Text Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add the menubar
        JMenuBar menuBar = createMenuBar();
        frame.setJMenuBar(menuBar);
                
        //Add the text area
        JTextArea textArea = new JTextArea();
        frame.getContentPane().add(textArea);

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
      fileMenu.add("Exit");
      result.add(fileMenu);
      return result;
    }
  
}
