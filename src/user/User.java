package user;

import java.awt.Color;
import handler.*;
import java.net.InetAddress;

/**
 * A User of a Shared Editor.
 */
public interface User {

    /*
     * The user ID.
     * @Ensures
     *      Result.length() > 0
     */
    public String getUserID ( );

    /*
     * The cursor color.
     * @Ensures
     *      Result != null
     */
    public Color getCursorColor ( );

    /*
     * The position of the cursor.
     * @Ensures
     *      Result != null
     */
    public TextPosition getPosition ( );

    /*
     * A String representation of this User.
     */
    public String toString ( );

    /*
     * Set the position of this User.
     * @Requires
     *      TextPosition != null
     */
    public void setPosition ( TextPosition position ) throws OutOfBoundsException;

}
