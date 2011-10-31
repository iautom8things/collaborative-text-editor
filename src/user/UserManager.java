/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import handler.OutOfBoundsException;
import handler.TextPosition;
import java.net.InetAddress;

/**
 *
 * @author mlalford
 */
public interface UserManager {

    /*
     * Add a new user with the specified userID and IPAddress to the container
     * @Requires
     *      userID != null
     *      userID is unique - not contained in _users already
     *      IPAddress != null
     * @Ensures
     *      user will be added
     */    
    void addUser(String userID, InetAddress IPAddress) throws UserIDNotUniqueException;

    /*
     * Returns the number of Users contained in this UserManager
     */    
    int getNumberOfUsers();

    /*
     * Returns the User with the specified userID
     * @Requires
     *      a User with the specified userID is contained in this UserManager
     */    
    User getUser(String userID) throws UserNotFoundException;

    /*
     * Remove the user with the specified userID from the collection
     * @Requires
     *      the user is contained in this UserManager
     * @Ensures
     *      the user is not conatined in this UserManager
     */    
    void removeUser(String userID) throws UserNotFoundException;

    /*
     * Set the position of the user with the userID to the given cursorPosition
     * @Requires
     *      User with userID is contained in this UserManager
     * @Ensures
     *      the value of the cursor position for this user is the same as cursorPosition
     */    
    void setCursorForUser(String userID, TextPosition cursorPosition) throws UserNotFoundException, OutOfBoundsException;

}
