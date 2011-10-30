/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.awt.Color;
import java.net.InetAddress;
import handler.*;

/**
 *
 * @author mlalford
 */
public class CTEUser implements User {

    /**
     * @Requires
     * String userID != null;
     * Color cursorColor != null;
     * InetAddress IPAdress != null;
     */
    public CTEUser(String userID, InetAddress IPAddress, Color cursorColor){
        _cursorPosition = new TextPosition();
        _IPAddress = IPAddress;
        _userID = userID;
        _cursorColor = cursorColor;
    }

    private InetAddress _IPAddress;
    private String _userID;
    private Color _cursorColor;
    private TextPosition _cursorPosition; 

    public InetAddress getIPAddress() {
        return _IPAddress;
    }

    public String getUserID() {
        return _userID;
    }
    
    public TextPosition getPosition() {
        return _cursorPosition;
    }
    
    public void setPosition(int position) throws OutOfBoundsException {
        _cursorPosition.setPosition(position);
    }    
    
    public Color getCursorColor() {
        return _cursorColor;
    }      
    
    public String toString(){
        String returnString = getUserID() + "{" + getPosition().getPosition() + ", " + getCursorColor().toString() +", " + getIPAddress() + "}";
        return returnString;
    }
}
