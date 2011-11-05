package user;

import java.awt.Color;
import java.util.*;
import java.util.concurrent.*;
import java.net.InetAddress;
import handler.*;

/**
 * A container and manager for CTEUsers.
 */
public class CTEUserManager implements UserManager {

    private volatile ConcurrentMap<String, User> _users; //Container for all CTEUsers that this manages

    /*
     * Create the CTEUserManager.
     */
    public CTEUserManager ( ) {
         _users = new ConcurrentHashMap<String, User>();
    }

    /*
     * Add a new user with the specified userID and IPAddress to the container
     * @Requires
     *      userID != null
     *      userID is unique - not contained in _users already
     *      IPAddress != null
     * @Ensures
     *      user will be added
     */
    public synchronized void addUser ( User user ) throws InvalidUserIDException {
        Color cursorColor = ColorList.getColor(this.getNumberOfUsers());
        if(_users.containsValue(user)){
            throw new InvalidUserIDException(user.getUserID());
        }
        _users.put(user.getUserID(), user);
    }

    /*
     * Remove the user with the specified userID from the collection
     * @Requires
     *      the user is contained in this CTEUserManager
     * @Ensures
     *      the user is not conatined in this CTEUserManager
     */
    public synchronized void removeUser ( String userID ) throws UserNotFoundException {
        if(_users.containsKey(userID)){
            _users.remove(userID);
        }
        else{
          throw new UserNotFoundException(userID);
        }
    }

    /*
     * Set the position of the user with the userID to the given cursorPosition
     * @Requires
     *      CTEUser with userID is contained in this CTEUserManager
     * @Ensures
     *      the value of the cursor position for this user is the same as cursorPosition
     */
    public synchronized void setCursorForUser ( String userID, TextPosition cursorPosition ) throws UserNotFoundException, OutOfBoundsException {
        CTEUser currentUser = getUser(userID);
        currentUser.setPosition(cursorPosition);
    }

    /*
     * Returns the number of CTEUsers contained in this CTEUserManager
     */
    public synchronized int getNumberOfUsers ( ) { return _users.size(); }

    public synchronized boolean contains ( String name ) { return _users.containsKey(name); }

    /*
     * Returns the CTEUser with the specified userID
     * @Requires
     *      a CTEUser with the specified userID is contained in this CTEUserManager
     */
    public synchronized CTEUser getUser ( String userID ) throws UserNotFoundException {
        if(_users.containsKey(userID)){
            Object user = _users.get(userID);
            return (CTEUser)user;
        }
        
        else throw new UserNotFoundException(userID);
    }

    /*
     * Given a pivot point, every TextPosition of a User that is beyond the
     * pivot will be incremented (if amount > 0) or decremented (if amount < 0)
     * by the amount specified.
     */
    public synchronized void updateBeyond ( TextPosition pivot, int amount ) throws OutOfBoundsException {
        for (User user : _users.values()) {
            TextPosition tp = user.getPosition();
            if (tp.isBeyond(pivot)) {
                if (amount < 0) {
                    amount = Math.abs(amount);
                    tp.decrementBy(amount);
                }
                else { tp.incrementBy(amount); }
            }
        }
    }

    /*
     *
     */
    public synchronized void updateBetween ( TextPosition front, TextPosition back ) throws OutOfBoundsException, UserNotFoundException {
        for (User user : _users.values()) {
            TextPosition tp = user.getPosition();
            if (tp.isBeyond(front) && !tp.isBeyond(back)) {
                this.setCursorForUser(user.getUserID(), front);
            }
        }
    }
}
