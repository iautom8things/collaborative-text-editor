package user;

import java.awt.Color;
import java.net.InetAddress;
import handler.*;

/**
 * A User of a Collaborative Text Editor
 */
public class CTEUser extends AbstractUser {

    /**
     * @Requires
     * String userID != null;
     * Color cursorColor != null;
     * InetAddress IPAdress != null;
     */
    public CTEUser ( String userID, InetAddress IPAddress, Color cursorColor ) throws InvalidUserIDException {
        super(userID, cursorColor);
        _IPAddress = IPAddress;
    }

    private InetAddress _IPAddress;

    /*
     * The InetAddress of this User.
     */
    public InetAddress getIPAddress ( ) { return _IPAddress; }

}
