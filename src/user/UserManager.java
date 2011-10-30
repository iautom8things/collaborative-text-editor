/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.net.Inet4Address;

/**
 *
 * @author mlalford
 */
public interface UserManager {
    
    public void addUser(String userID, Inet4Address IPAddress);
    
}
