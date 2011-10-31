package user;

import java.awt.Color;
import java.net.InetAddress;
import handler.*;

/**
 * A User of a Collaborative Text Editor
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

    /*
     * The InetAddress of this User.
     */    
    public InetAddress getIPAddress() {
        return _IPAddress;
    }

    /*
     * The user ID.
     * @Ensures
     *      Result.length() > 0
     */
    public String getUserID() {
        return _userID;
    }

    
    /*
     * The position of the cursor.
     * @Ensures
     *      Result != null
     */    
    public TextPosition getPosition() {
        return _cursorPosition;
    }
    
    /*
     * Set the position of this User.
     * @Requires 
     *      TextPosition != null
     */    
    public void setPosition(TextPosition position) throws OutOfBoundsException {
        _cursorPosition = position;
    }    
    
    /*
     * The cursor color.
     * @Ensures
     *      Result != null
     */    
    public Color getCursorColor() {
        return _cursorColor;
    }      
    
    /*
     * A String representation of this User.
     */    
    public String toString(){
        String returnString = getUserID() + "{" + getPosition().getPosition() + ", " + getCursorColor().toString() +", " + getIPAddress() + "}";
        return returnString;
    }
}
