package gui;

import handler.*;

public class EditorLauncher {
//this is nice
  public static void main ( String[] args ) {
      EditorGUI gui = new EditorGUI(new Client());
      gui.launch();
  }

}
