package user;

import handler.OutOfBoundsException;
import handler.TextPosition;
import java.net.InetAddress;
import java.util.Collection;

/**
 *
 * @author mlalford
 */
public interface UserManager {

    /**
     * Add a new user with the specified userID and IPAddress to the container
     * @Requires
     *      userID != null
     *      userID is unique - not contained in _users already
     *      IPAddress != null
     * @Ensures
     *      user will be added
     */
    void addUser ( User user ) throws InvalidUserIDException;

    /**
     * Remove the user with the specified userID from the collection
     * @Requires
     *      the user is contained in this CTEUserManager
     * @Ensures
     *      the user is not conatined in this CTEUserManager
     */
    void removeUser ( String userID ) throws UserNotFoundException;

    /**
     * Returns the number of Users contained in this UserManager
     */
    int getNumberOfUsers ( );

    /**
     * Returns the User with the specified userID
     * @Requires
     *      a User with the specified userID is contained in this UserManager
     */
    User getUser ( String userID ) throws UserNotFoundException;

    /**
     * Set the position of the user with the userID to the given cursorPosition
     * @Requires
     *      User with userID is contained in this UserManager
     * @Ensures
     *      the value of the cursor position for this user is the same as cursorPosition
     */
    void setCursorForUser ( String userID, TextPosition cursorPosition ) throws UserNotFoundException, OutOfBoundsException;

    /**
     * Given a pivot point, every TextPosition of a User that is beyond the
     * pivot will be incremented (if amount > 0) or decremented (if amount < 0)
     * by the amount specified.
     * @Requires
     *      pivot != null
     *      amount != null
     *      amount != 0
     * @Ensures
     *      Any User whose TextPosition is beyond the pivot
     *      will have their Position Incremented by amount if amount > 0
     *      or Decremented by Math.abs(amount) if amount < 0.
     */
    void updateBeyond ( TextPosition pivot, int amount ) throws OutOfBoundsException;

    /**
     * This is used when a selection of text is deleted. All users within the
     * selected text should be updated to the front TextPosition.
     * @Requires
     *      front != null
     *      back != null
     * @Ensures
     *      Any user whose TextPosition is between the two TextPositions, front and back,
     *      will have their TextPosition updated to front.
     */
    void updateBetween ( TextPosition front, TextPosition back ) throws OutOfBoundsException, UserNotFoundException;

    Collection<User> getUsers ( );
}
