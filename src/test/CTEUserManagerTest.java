package test;
import handler.*;
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
        _userManager = new CTEUserManager();
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
            CTEUserManager _manager = new CTEUserManager();
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
            CTEUserManager _manager = new CTEUserManager();
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

    public void testUpdateBeyond ( ) {
        try {
            _userManager.addUser("frank", InetAddress.getLocalHost());
            _userManager.setCursorForUser("frank", new TextPosition(41));
            _userManager.addUser("foo", InetAddress.getLocalHost());
            _userManager.setCursorForUser("foo", new TextPosition(42));
            _userManager.addUser("bar", InetAddress.getLocalHost());
            _userManager.setCursorForUser("bar", new TextPosition(43));
            TextPosition pivot = new TextPosition(42);
            _userManager.updateBeyond(pivot, 10);
            assertEquals(_userManager.getUser("frank").getPosition(), new TextPosition(41));
            assertEquals(_userManager.getUser("foo").getPosition(), new TextPosition(42));
            assertEquals(_userManager.getUser("bar").getPosition(), new TextPosition(53));
        }
        catch (Exception e) {
            out.println("Exception caught:");
            out.println(e.getMessage());
        }
    }

    public void testUpdateBetween ( ) {
        try {
            _userManager.addUser("manuel", InetAddress.getLocalHost());
            _userManager.setCursorForUser("manuel", new TextPosition(21));
            _userManager.addUser("pedro", InetAddress.getLocalHost());
            _userManager.setCursorForUser("pedro", new TextPosition(22));
            _userManager.addUser("bonehead", InetAddress.getLocalHost());
            _userManager.setCursorForUser("bonehead", new TextPosition(23));
            _userManager.addUser("frank", InetAddress.getLocalHost());
            _userManager.setCursorForUser("frank", new TextPosition(41));
            _userManager.addUser("foo", InetAddress.getLocalHost());
            _userManager.setCursorForUser("foo", new TextPosition(42));
            _userManager.addUser("bar", InetAddress.getLocalHost());
            _userManager.setCursorForUser("bar", new TextPosition(43));
            TextPosition front = new TextPosition(22);
            TextPosition back = new TextPosition(42);
            _userManager.updateBetween(front, back);
            assertEquals(_userManager.getUser("manuel").getPosition(), new TextPosition(21));
            assertEquals(_userManager.getUser("pedro").getPosition(), new TextPosition(22));
            assertEquals(_userManager.getUser("bonehead").getPosition(), new TextPosition(22));
            assertEquals(_userManager.getUser("frank").getPosition(), new TextPosition(22));
            assertEquals(_userManager.getUser("foo").getPosition(), new TextPosition(22));
            assertEquals(_userManager.getUser("bar").getPosition(), new TextPosition(43));
        }
        catch (Exception e) {
            out.println("Exception caught:");
            out.println(e.getMessage());
        }
    }
}
