package test;

import user.*;
import junit.framework.TestCase;
import static java.lang.System.out;
import java.net.*;
import java.awt.Color;

public class CTEUserManagerTest extends TestCase {
    private CTEUserManager _userManager;

    public static void main ( String[] args ) {
        out.println("Begin Main");
        CTEUserManagerTest test = new CTEUserManagerTest();
        test.setUp();
        test.testUser();
        test.testCTEUserManager();
        test.testCTEUserManagerColors();
    }

    protected void setUp ( ) {
        _userManager = CTEUserManager.getInstance();
    }

    public void testUser ( ) {
        Color _inputColor0 = Color.black;
        String _inputUserID0 = "mlbrinle";
        InetAddress _IPAddress0 = null;
        try {
            _IPAddress0 = InetAddress.getLocalHost();
        }
        catch (UnknownHostException uhe) {
            out.println("Exception caught + uhe.getMessage()");
        }
        try {
            CTEUser testUser0 = new CTEUser(_inputUserID0, _IPAddress0, _inputColor0);
            assertEquals(testUser0.getCursorColor(), _inputColor0);
            assertEquals(testUser0.getUserID(), _inputUserID0);
            assertEquals(testUser0.getIPAddress(), InetAddress.getLocalHost());
            out.println("position: " + testUser0.getPosition().getPosition());
            assertEquals(testUser0.getPosition().getPosition(), 0);
        }
        catch (Exception e) {
            out.println(e.getMessage());
        }

        //Test create user with null userID
        try {
            User testUser1 = new CTEUser("", _IPAddress0, _inputColor0);
            assertEquals(testUser1.getCursorColor(), _inputColor0);
            assertEquals(testUser1.getUserID(), "");
        }
        catch (Exception e) {
            out.println(e.getMessage());
        }
    }

    public void testCTEUserManager ( ) {
        try {
            CTEUserManager _manager = CTEUserManager.getInstance();
            assertEquals(_manager.getNumberOfUsers(), 0);
            _manager.addUser("mlbrinle", InetAddress.getLocalHost());
            assertEquals(_manager.getNumberOfUsers(), 1);
            //_manager.addUser("mlbrinle", InetAddress.getLocalHost());
            CTEUser myUser0 = _manager.getUser("mlbrinle");
            out.println(myUser0.toString());
            out.println("myuser: " + myUser0.toString());
            //CTEUser myUser1 = _manager.getUser("mlbrinl");
            _manager.addUser("malford", InetAddress.getLocalHost());

        }
        catch (Exception e ) {
            out.println("Exception caught:");
            out.println(e.getMessage());
        }
    }

        public void testCTEUserManagerColors ( ) {
        try {
            CTEUserManager _manager = CTEUserManager.getInstance();
            String baseName = "user";
            int i = 1;
            while(i<15){
                String userName = baseName + i;
                _manager.addUser(userName, InetAddress.getLocalHost());
                i++;
            }
            int k = 14;
            while (k>0) {
                String userName = baseName + k;
                _manager.removeUser(userName);
                k--;
            }
         }
        catch (Exception e) {
            out.println("Exception caught:");
            out.println(e.getMessage());
        }
    }
}
