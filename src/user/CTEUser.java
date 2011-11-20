package user;

import java.awt.Color;
import java.net.InetAddress;
import handler.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * A User of a Collaborative Text Editor
 */
public class CTEUser implements User, Serializable {

    private String _userID;
    private Color _cursorColor;
    private TextPosition _cursorPosition;
    private InetAddress _IPAddress;

    /**
     * @Requires
     * String userID != null;
     * Color cursorColor != null;
     * InetAddress IPAdress != null;
     */
    public CTEUser ( String userID, InetAddress IPAddress, Color cursorColor ) throws InvalidUserIDException {
        if(userID == "") throw new InvalidUserIDException();
        _cursorPosition = new TextPosition();
        _userID = userID;
        _cursorColor = cursorColor;
        _IPAddress = IPAddress;
    }

    /*
     * The InetAddress of this User.
     */
    public InetAddress getIPAddress ( ) { return _IPAddress; }

    /*
     * The user ID.
     * @Ensures
     *      Result.length() > 0
     */
    public String getUserID ( ) { return _userID; }


    /*
     * The position of the cursor.
     * @Ensures
     *      Result != null
     */
    public TextPosition getPosition ( ) { return _cursorPosition; }

    /*
     * Set the position of this User.
     * @Requires
     *      TextPosition != null
     */
    public void setPosition ( TextPosition position ) throws OutOfBoundsException { _cursorPosition = position; }

    /*
     * The cursor color.
     * @Ensures
     *      Result != null
     */
    public Color getCursorColor ( ) { return _cursorColor; }

    /*
     * A String representation of this User.
     */
    public String toString ( ) {
        String returnString = getUserID() + "{" + getPosition().getPosition() + "}";
        return returnString;
    }

    private void writeObject ( ObjectOutputStream out ) throws IOException {
        ObjectOutputStream.PutField fields = out.putFields();
        fields.put("_userID", _userID);
        fields.put("_cursorColor", _cursorColor);
        fields.put("_cursorPosition", _cursorPosition);
        fields.put("_IPAddress", _IPAddress);
        out.writeFields();
    }

    private void readObject ( ObjectInputStream in ) throws IOException, ClassNotFoundException {
        ObjectInputStream.GetField fields = in.readFields();
        _userID= (String) fields.get("_userID", null);
        _cursorColor= (Color) fields.get("_cursorColor", null);
        _cursorPosition = (TextPosition) fields.get("_cursorPosition", null);
        _IPAddress = (InetAddress) fields.get("_IPAddress", null);
    }
}
