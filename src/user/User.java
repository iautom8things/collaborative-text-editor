/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.awt.Color;
import handler.*;

/**
 *
 * @author mlalford
 */
public interface User {

    public String getUserID();
    
    public Color getCursorColor();
    
    public TextPosition getPosition();
    
    public String toString();
    
}
