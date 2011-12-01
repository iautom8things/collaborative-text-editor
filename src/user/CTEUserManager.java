package user;

import java.awt.Color;
import java.util.*;
import java.util.concurrent.*;
import java.net.InetAddress;
import handler.*;
import java.io.Serializable;
import java.lang.Cloneable;
import java.lang.CloneNotSupportedException;
import java.util.ArrayList;

/**
 * A container and manager for CTEUsers.
 */
public class CTEUserManager implements Serializable, Cloneable {

    private static final boolean DEBUG = true;
    private final Object _lock = new Object();

    private volatile ConcurrentMap<String, CTEUser> _users; //Container for all CTEUsers that this manages

    /**
     * Create the CTEUserManager.
     */
    public CTEUserManager ( ) { _users = new ConcurrentSkipListMap<String, CTEUser>(); }


    /***********
     * Queries *
     **********/


    /**
     * Returns the number of CTEUsers contained in this CTEUserManager.
     */
    public int getNumberOfUsers ( ) {
        int size;
        synchronized (_lock) { size = _users.size(); }
        return size;
    }

    /**
     * Checks if the given CTEUser is contained in the CTEUserManager.
     *
     * Requires:
     *      CTEUser != null
     */
    public boolean contains ( CTEUser user ) {
        boolean result = false;
        synchronized (_lock) { _users.containsKey(user.getUniqueID()); }
        return result;
    }

    /**
     * Return an Iterable Collection of the CTEUsers this CTEUserManager
     * manages.
     */
    public Collection<CTEUser> getUsers ( ) {
        Collection<CTEUser> users;
        synchronized (_lock) { users = _users.values(); }
        return users;
    }


    /************
     * Commands *
     ***********/


    /**
     * Add a new user with the specified userID and IPAddress to the
     * container.
     *
     * Requires:
     *      userID != null
     *      userID is unique - not contained in _users already
     *      IPAddress != null
     * Ensures:
     *      user will be added
     */
    public void addUser ( CTEUser user ) {
        synchronized (_lock) { _users.put(user.getUniqueID(), user); }
    }

    /**
     * Remove the user with the specified userID from the collection.
     *
     * Requires:
     *      the user is contained in this CTEUserManager
     * Ensures:
     *      the user is not conatined in this CTEUserManager
     */
    public void removeUser ( CTEUser user ) throws UserNotFoundException {
        synchronized (_lock) {
            if (_users.containsKey(user.getUniqueID())) { _users.remove(user.getUniqueID()); }
            else { throw new UserNotFoundException(user.getName()); }
        }
    }

    public CTEUser getUser( String userID ) throws UserNotFoundException {
        synchronized (_lock) {
            if (_users.containsKey(userID)) { return _users.get(userID); }
            else { throw new UserNotFoundException(userID); }
        }
    }

    public void setCursorForUser ( CTEUser user, TextPosition pos ) throws OutOfBoundsException, UserNotFoundException {
        synchronized (_lock) { getUser(user.getUniqueID()).setPosition(pos); }
    }

    /**
     * Given a pivot point, every TextPosition of a User that is beyond the
     * pivot will be incremented (if amount > 0) or decremented (if amount < 0)
     * by the amount specified.
     *
     * @Requires
     *      pivot != null
     *      amount != null
     *      amount != 0
     * @Ensures
     *      Any User whose TextPosition is beyond the pivot
     *      will have their Position Incremented by amount if amount > 0
     *      or Decremented by Math.abs(amount) if amount < 0.
     */
    public void updateBeyond ( TextPosition pivot, int amount ) throws OutOfBoundsException {
        synchronized (_lock) {
            for (CTEUser user : _users.values()) {
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
    }

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
    public void updateBetween ( TextPosition front, TextPosition back ) throws OutOfBoundsException, UserNotFoundException {
        if (DEBUG) { System.out.println("updating textpositions between " + front + " and " + back); }
        ArrayList<String> keys = new ArrayList<String>();
        synchronized (_lock) {
            for (String key : _users.keySet()) { keys.add(key); }
            for (String key : keys) {
                CTEUser user = _users.get(key);
                if (DEBUG) { System.out.println("...checking " + user); }
                TextPosition tp = user.getPosition();
                if (DEBUG) { System.out.println(tp); }
                if (tp.isBeyond(front) && back.isBeyond(tp)) {
                    if (DEBUG) { System.out.println("...is between"); }
                    user.setPosition(front);
                }
            }
        }
    }

    /**
     * Change a User's name.
     */
    public void setUserName ( CTEUser user, String name ) throws InvalidUserIDException, UserNotFoundException {
        String key = user.getUniqueID();
        synchronized (_lock) {
            if (_users.containsKey(key)) {
                CTEUser value = _users.get(key);
                value.setName(name);
            }
            else { throw new UserNotFoundException(user.getName()); }
        }
    }

    @Override
    public Object clone ( ) throws CloneNotSupportedException {
        CTEUserManager clone = new CTEUserManager();

        synchronized (_lock) {
            for (CTEUser user: _users.values()) { clone.addUser((CTEUser) user.clone()); }
        }

        return clone;
    }

    /**
     * Return a String representation of the CTEUserManager.
     */
    public String toString ( ) {
        String result = "UserManager{ ";

        synchronized (_lock) {
            for (CTEUser user: _users.values()) { result += user + ", "; }
        }
        result = result.substring(0, result.length()-2);
        result += " }";

        return result;
    }

}
