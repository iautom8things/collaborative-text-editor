/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.awt.Color;
import java.util.*;

/**
 *
 * @author Melissa
 */
public class CTEUserManager implements UserManager{

    public CTEUserManager (){
        //need to be able to rapidly call functions to the user container 
        //hashmap?
         _CTEUserList = new LinkedList<CTEUser>();    
    }
    
    LinkedList<CTEUser> _CTEUserList; 
    
    @Override
    public void addUser(String userID, String IPAddress) {
        Color cursorColor = getNextColor();
        CTEUser newUser = new CTEUser(userID, IPAddress, cursorColor);
        _CTEUserList.add(newUser);
    }
    
    public Color getNextColor(){
        Random random = new Random();
        return new Color(1);
    }
    
    public void setCursorForUser(String userID, int cursorPosition){
        //_CTEUserList.get(_CTEUserList.indexof());
    }
    }
    

