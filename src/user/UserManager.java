/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.net.InetAddress;

/**
 *
 * @author mlalford
 */
public interface UserManager {
    
    public void addUser(String userID, InetAddress IPAddress) throws Exception;
    
    public int getNumberOfUsers();
}
