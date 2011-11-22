package user;

import java.awt.Color;
import java.net.InetAddress;
import handler.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.Cloneable;
import java.lang.CloneNotSupportedException;

/**
 * A User of a Collaborative Text Editor
 */
public class CTEUser implements Serializable, Cloneable {

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
     *      Tex)Position != null
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

    @Override
    public Object clone ( ) throws CloneNotSupportedException {
        CTEUser clone = null;
        try {
            clone = new CTEUser(_userID, _IPAddress, _cursorColor);
            clone.setPosition(_cursorPosition);
        }
        catch (InvalidUserIDException iuede) { iuede.printStackTrace(); }
        catch (OutOfBoundsException oobe) { oobe.printStackTrace(); }

        return clone;
    }

    @Override
    public boolean equals ( Object other ) {
        if (other == null) { return false; }
        if (other == this) { return true; }
        if (other.getClass() != this.getClass()) { return false; }

        CTEUser otherUser = (CTEUser) other;

        return _cursorPosition.equals(otherUser.getPosition())
            && _cursorColor.equals(otherUser.getCursorColor())
            && _userID.equals(otherUser.getUserID())
            && _IPAddress.equals(otherUser.getIPAddress());
    }
    @Override
    public int hashCode ( ) {
        String result = "" + _userID + _IPAddress + _cursorPosition + _cursorColor;
        return result.hashCode();
    }
}
