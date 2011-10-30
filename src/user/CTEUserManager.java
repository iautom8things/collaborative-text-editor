package user;

import java.awt.Color;
import java.util.*;
import java.net.Inet4Address;

/**
 * A container and manager for CTEUsers.
 */
public class CTEUserManager implements UserManager{

    /*
     * Create a new empty CTEUserManager.
     */
    public CTEUserManager (){
        //need to be able to rapidly call functions to the user container 
        //hashmap?
         _users = new HashMap();
    }
    
    
    HashMap _users; //Container for all CTEUsers that this manages
    
    @Override
    public void addUser(String userID, Inet4Address IPAddress) {
        Color cursorColor = getNextColor();
        CTEUser newUser = new CTEUser(userID, IPAddress, cursorColor);
         _users.put(userID, newUser);        
    }
    
    public Color getNextColor(){
        Random random = new Random();
        return new Color(1);
    }
    
    public void setCursorForUser(String userID, int cursorPosition){
        //_CTEUserList.get(_CTEUserList.indexof());
    }
    }
    

