package user;

import java.awt.Color;
import java.net.InetAddress;
import handler.*;

/**
 * A User of a Collaborative Text Editor
 */
public class IndividualUser extends AbstractUser {

    /**
     * @Requires
     * String userID != null;
     * Color cursorColor != null;
     */
    public IndividualUser ( String userID, Color cursorColor ) throws InvalidUserIDException {
        super(userID, cursorColor);
    }

}
