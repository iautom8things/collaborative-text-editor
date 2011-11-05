package user;

import java.awt.Color;
import java.net.InetAddress;
import handler.*;

/**
 * A User of a Collaborative Text Editor
 */
public abstract class AbstractUser implements User {

    /**
     * @Requires
     * String userID != null;
     * Color cursorColor != null;
     * InetAddress IPAdress != null;
     */
    public AbstractUser ( String userID, Color cursorColor ) throws InvalidUserIDException {
        if(userID == "") throw new InvalidUserIDException();
        _cursorPosition = new TextPosition();
        _userID = userID;
        _cursorColor = cursorColor;
    }

    private String _userID;
    private Color _cursorColor;
    private TextPosition _cursorPosition;

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
}
