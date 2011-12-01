package gui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.JTextComponent;
import user.CTEUser;
import user.CTEUserManager;

/*
 * Extends the DefaultCaret so that the caret can be placed in many positions in 
 * the Document. Instead of painting one carot for one position, paint separate
 * carots for each different position
 */
public class MultiCaret extends DefaultCaret {

    private CTEUserManager _userManager;

    public MultiCaret(CTEUserManager userManager) {
        _userManager = userManager;
    }

    /*
     * Overrides the paint method of DefaultCaret
     */
    @Override
    public void paint(Graphics g) {
        JTextComponent component = getComponent();
        if (component == null) {
            return;
        }
        //Create an Iterator to go through all users and update the characters
        Iterator<CTEUser> userIterator = _userManager.getUsers().iterator();
        while(userIterator.hasNext()){
            CTEUser user = userIterator.next();

            //get the position of the user
            int carotPosition = user.getPosition().getPosition();
            Rectangle carotShape = null;
            try {
                carotShape = component.modelToView(carotPosition);
            } 
            catch (BadLocationException e) {
                return;
            }
            if (carotShape == null) {
                return;
            }
            int rectangleHeight = carotShape.height * 4 / 5 - 3; // will be distance from y to top

            if ((this.x != carotShape.x) || (this.y != carotShape.y + rectangleHeight)) {
                // paint() has been called directly, without a previous call to
                // damage(), so do some cleanup. (This happens, for example, when
                // the text component is resized.)
                repaint(); // erase previous location of caret
                this.x = carotShape.x; // set new values for x,y,width,height
                this.y = carotShape.y + rectangleHeight;
                width = 5;
                height = 5;
            }
            //Set the color of the cursor to the user's color
            g.setColor(user.getCursorColor());
            //Draw the carot
            g.drawLine(carotShape.x, carotShape.y, carotShape.x, carotShape.y + 14);
        }
    }

    /*
     * Sets the _userManager
     */
    public void setUserManager(CTEUserManager userManager) {
        _userManager = userManager;
    }
}