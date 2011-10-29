/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.awt.Color;
import java.net.Inet4Address;

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
    public CTEUser(String userID, Inet4Address IPAddress, Color cursorColor) {
        _cursorPosition = 0;
        _IPAddress = IPAddress;
        _userID = userID;
        _cursorColor = cursorColor;
    }

    private Inet4Address _IPAddress;
    private String _userID;
    private Color _cursorColor;
    private int _cursorPosition;

    protected Inet4Address getIPAddress() {
        return _IPAddress;
    }

    public String getUserID() {
        return _userID;
    }
    
    public int getPosition() {
        return _cursorPosition;
    }
    
    public Color getCursorColor() {
        return _cursorColor;
    }      
    
}
