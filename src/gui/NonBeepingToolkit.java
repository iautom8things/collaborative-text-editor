package gui;

import sun.awt.windows.*;

/*
 * This is an Extension of sun.awt.windows.WToolkit. Because the JTextPane was 
 * set to not editable, the application would beep when the backspace was pressed.
 * Since this application does not need to beep, the does nothing.
 */
public class NonBeepingToolkit extends WToolkit {  

    @Override
    //Do Nothing
    public void beep() {}
      
      public NonBeepingToolkit(){
          super();
      }
}
