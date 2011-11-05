package test;

import handler.*;
import user.*;
import junit.framework.TestCase;
import static java.lang.System.out;
import java.net.*;
import java.awt.Color;

public class CTEUserManagerTest extends TestCase {

    private CTEUserManager _userManager;

    public static void main(String[] args) {
        out.println("Begin Main");
        CTEUserManagerTest test = new CTEUserManagerTest();
        test.setUp();
        test.testUser();
        test.testCTEUserManager();
        test.testCTEUserManagerColors();
    }

    protected void setUp() {
        _userManager = new CTEUserManager();
    }

    public void testUser() {
        Color _inputColor0 = Color.black;
        String _inputUserID0 = "mlbrinle";
        InetAddress _IPAddress0 = null;
        try {
            _IPAddress0 = InetAddress.getLocalHost();
        } catch (UnknownHostException uhe) {
            out.println("Exception caught + uhe.getMessage()");
        }
        try {
            CTEUser testUser0 = new CTEUser(_inputUserID0, _IPAddress0, _inputColor0);
            assertEquals(testUser0.getCursorColor(), _inputColor0);
            assertEquals(testUser0.getUserID(), _inputUserID0);
            assertEquals(testUser0.getIPAddress(), InetAddress.getLocalHost());
            assertEquals(testUser0.getPosition().getPosition(), 0);
        } catch (Exception e) {
            out.println(e.getMessage());
        }

        //Test create user with null userID
        try {
            User testUser1 = new CTEUser("", _IPAddress0, _inputColor0);
            assertEquals(testUser1.getCursorColor(), _inputColor0);
            assertEquals(testUser1.getUserID(), "");
        } catch (InvalidUserIDException e) {
            String exceptionMessage = e.getMessage();
            assertEquals("User ID can not be null", exceptionMessage);
        }
    }

    public void testCTEUserManager() {
        try {
            CTEUserManager _manager = new CTEUserManager();
            assertEquals(_manager.getNumberOfUsers(), 0);
            CTEUser mbrinle = new CTEUser("mlbrinle", InetAddress.getLocalHost(), ColorList.getColor(0));
            _manager.addUser(mbrinle);
            assertEquals(_manager.getNumberOfUsers(), 1);
            //_manager.addUser("mlbrinle", InetAddress.getLocalHost());
            CTEUser myUser0 = _manager.getUser("mlbrinle");
            assertEquals(myUser0.toString(), mbrinle.toString());
            assertEquals(myUser0, mbrinle);
            //see if a user can be added with the same user ID as one contained
            try {
                _manager.addUser(myUser0);
            } catch (InvalidUserIDException e) {
                String exceptionMessage = e.getMessage();
                assertEquals("User ID is not unique: mlbrinle", exceptionMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testCTEUserManagerColors() {
        try {
            CTEUserManager _manager = new CTEUserManager();
            String baseName = "user";
            int i = 1;
            while (i < 15) {
                String userName = baseName + i;
                CTEUser temp = new CTEUser(userName, InetAddress.getLocalHost(), ColorList.getColor(i));
                _manager.addUser(temp);
                i++;
            }
            int k = 14;
            while (k > 0) {
                String userName = baseName + k;
                _manager.removeUser(userName);
                k--;
            }
        } catch (Exception e) {
            out.println("Exception caught:");
            out.println(e.getMessage());
        }
    }

    public void testUpdateBeyond() {
        try {
            CTEUser frank = new CTEUser("frank", InetAddress.getLocalHost(), ColorList.getColor(0));
            _userManager.addUser(frank);
            _userManager.setCursorForUser("frank", new TextPosition(41));
            CTEUser foo = new CTEUser("foo", InetAddress.getLocalHost(), ColorList.getColor(1));
            _userManager.addUser(foo);
            _userManager.setCursorForUser("foo", new TextPosition(42));
            CTEUser bar = new CTEUser("bar", InetAddress.getLocalHost(), ColorList.getColor(2));
            _userManager.addUser(bar);
            _userManager.setCursorForUser("bar", new TextPosition(43));
            TextPosition pivot = new TextPosition(42);
            _userManager.updateBeyond(pivot, 10);
            assertEquals(_userManager.getUser("frank").getPosition(), new TextPosition(41));
            assertEquals(_userManager.getUser("foo").getPosition(), new TextPosition(42));
            assertEquals(_userManager.getUser("bar").getPosition(), new TextPosition(53));
        } catch (Exception e) {
            out.println("Exception caught:");
            out.println(e.getMessage());
        }
    }

    public void testUpdateBetween() {
        try {
            CTEUser manuel = new CTEUser("manuel", InetAddress.getLocalHost(), ColorList.getColor(0));
            _userManager.addUser(manuel);
            _userManager.setCursorForUser("manuel", new TextPosition(21));
            CTEUser pedro = new CTEUser("pedro", InetAddress.getLocalHost(), ColorList.getColor(0));
            _userManager.addUser(pedro);
            _userManager.setCursorForUser("pedro", new TextPosition(22));
            CTEUser bonehead = new CTEUser("bonehead", InetAddress.getLocalHost(), ColorList.getColor(0));
            _userManager.addUser(bonehead);
            _userManager.setCursorForUser("bonehead", new TextPosition(23));
            CTEUser frank = new CTEUser("frank", InetAddress.getLocalHost(), ColorList.getColor(0));
            _userManager.addUser(frank);
            _userManager.setCursorForUser("frank", new TextPosition(41));
            CTEUser foo = new CTEUser("foo", InetAddress.getLocalHost(), ColorList.getColor(0));
            _userManager.addUser(foo);
            _userManager.setCursorForUser("foo", new TextPosition(42));
            CTEUser bar = new CTEUser("bar", InetAddress.getLocalHost(), ColorList.getColor(0));
            _userManager.addUser(bar);
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
        } catch (Exception e) {
            out.println("Exception caught:");
            out.println(e.getMessage());
        }
    }
}
