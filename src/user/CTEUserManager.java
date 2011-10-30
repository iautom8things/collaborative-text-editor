package user;

import java.awt.Color;
import java.util.*;
import java.net.InetAddress;
import handler.*;

/**
 * A container and manager for CTEUsers.
 */
public class CTEUserManager implements UserManager{

    /*
     * Create a new empty CTEUserManager.
     */
    public CTEUserManager (){
         _users = new HashMap();
    }
    
    HashMap _users; //Container for all CTEUsers that this manages
    
    /*
     * Add a new user with the specified userID and IPAddress to the container
     * @Requires
     *      userID != null
     *      userID is unique - not contained in _users already
     *      IPAddress != null
     * @Ensures
     *      user will be added
     */
    public void addUser(String userID, InetAddress IPAddress) throws UserIDNotUniqueException{
        Color cursorColor = ColorList.getColor(this.getNumberOfUsers());
        if(_users.containsKey(userID)){
            throw new UserIDNotUniqueException(userID);
        }
        CTEUser newUser = new CTEUser(userID, IPAddress, cursorColor);
        _users.put(userID, newUser);  
    }
    
    /*
     * Remove the user with the specified userID from the collection
     * @Requires
     *      the user is contained in this CTEUserManager
     * @Ensures
     *      the user is not conatined in this CTEUserManager
     */
    public void removeUser(String userID) throws UserNotFoundException{
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
     *      cursorPosition < TextPosition.MAXPOSITION
     *      cursorPosition > TextPosition.MINPOSITION
     * @Ensures
     *      the value of the cursor position for this user is the same as cursorPosition
     */
    public void setCursorForUser(String userID, int cursorPosition) throws UserNotFoundException, OutOfBoundsException{
        CTEUser currentUser = getUser(userID);
        currentUser.setPosition(cursorPosition);
    }
    
    public int getNumberOfUsers(){
        return _users.size();
    }
    
    
    public CTEUser getUser(String userID) throws UserNotFoundException{ 
        if(_users.containsKey(userID)){
            Object user = _users.get(userID);
            return (CTEUser)user;
        }
        else throw new UserNotFoundException(userID);
    }

}