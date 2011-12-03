package user;

import java.awt.Color;
import java.util.*;
import java.util.concurrent.*;
import java.net.InetAddress;
import handler.*;
import java.io.Serializable;
import java.lang.Cloneable;
import java.lang.CloneNotSupportedException;

/**
 * A container and manager for CTEUsers.
 */
public class CTEUserManager implements Serializable, Cloneable {

    private volatile ConcurrentHashMap<String, CTEUser> _users; //Container for all CTEUsers that this manages

    /**
     * Create the CTEUserManager.
     *  @Ensures
     *      this.getNumberOfUsers() == 0
     */
    public CTEUserManager ( ) { _users = new ConcurrentHashMap<String, CTEUser>(); }


    /***********
     * Queries *
     **********/


    /**
     * Returns the number of CTEUsers contained in this CTEUserManager.
     */
    public synchronized int getNumberOfUsers ( ) { return _users.size(); }

    /**
     * Checks if the given CTEUser is contained in the CTEUserManager.
     *
     * Requires:
     *      CTEUser != null
     */
    public synchronized boolean contains ( CTEUser user ) { return _users.containsKey(user.getUniqueID()); }

    /**
     * Return an Iterable Collection of the CTEUsers this CTEUserManager
     * manages.
     */
    public synchronized Collection<CTEUser> getUsers ( ) { return _users.values(); }


    /************
     * Commands *
     ***********/


    /**
     * Add a new user with the specified userID and IPAddress to the
     * container. Get the size of the container and the corresponding color
     * to that size - according to ColorList - and set the CTEUser's color
     * to that
     *
     * @Requires
     *      userID != null
     *      userID is unique - not contained in _users already
     *      IPAddress != null
     * @Ensures
     *      user will be added
     *      user.getCursorColor == ColorList.getColor(_users.size())
     */
    public synchronized void addUser ( CTEUser user ) { 
        user.setCursorColor(ColorList.getColor(_users.size()));
        _users.put(user.getUniqueID(), user); 
        
    }

    /**
     * Remove the user with the specified userID from the collection.
     *
     * @Requires
     *      the user is contained in this CTEUserManager
     * @Ensures
     *      the user is not contained in this CTEUserManager
     */
    public synchronized void removeUser ( CTEUser user ) throws UserNotFoundException {
        if (_users.containsKey(user.getUniqueID())) { _users.remove(user.getUniqueID()); }
        else { throw new UserNotFoundException(user.getName()); }
    }

    /*
     * Returns the CTEUser with specified userID.
     */
    public synchronized CTEUser getUser( String userID ) throws UserNotFoundException {
        if (_users.containsKey(userID)) { return _users.get(userID); }
        else { throw new UserNotFoundException(userID); }
    }

    /*
     * Set the position of the CTEuser to the specified TextPosition.
     *  @Requires
     *      user != null
     *      pos != null
     *  @Ensures
     *      user.getPosition().getPosition() == pos.getPosition()
     */
    public synchronized void setCursorForUser ( CTEUser user, TextPosition pos ) throws OutOfBoundsException, UserNotFoundException {
        getUser(user.getUniqueID()).setPosition(pos);
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
    public synchronized void updateBeyond ( TextPosition pivot, int amount ) throws OutOfBoundsException {
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
    public synchronized void updateBetween ( TextPosition front, TextPosition back ) throws OutOfBoundsException, UserNotFoundException {
        for (CTEUser user : _users.values()) {
            TextPosition tp = user.getPosition();
            if (tp.isBeyond(front) && !tp.isBeyond(back)) { user.setPosition(front); }
        }
    }

    /**
     * Change a User's name.
     *  @Requires
     *      user != null
     *      name != null
     *  @Ensures
     *      user.getName() == name
     */
    public synchronized void setUserName ( CTEUser user, String name ) throws InvalidUserIDException, UserNotFoundException {
        String key = user.getUniqueID();
        if (_users.containsKey(key)) {
            CTEUser value = _users.get(key);
            value.setName(name);
        }
        else { throw new UserNotFoundException(user.getName()); }
    }

    @Override
    public synchronized Object clone ( ) throws CloneNotSupportedException {
        CTEUserManager clone = new CTEUserManager();

        for (CTEUser user: _users.values()) { clone.addUser((CTEUser) user.clone()); }

        return clone;
    }

    /**
     * Return a String representation of the CTEUserManager.
     */
    public synchronized String toString ( ) {
        String result = "UserManager{ ";
        for (CTEUser user: _users.values()) { result += user + ", "; }
        result = result.substring(0, result.length()-2);
        result += " }";
        return result;
    }

}
