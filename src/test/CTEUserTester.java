package test;

import user.*;
import handler.*;
import java.net.*;
import java.awt.Color;
import junit.framework.*;

/**
 * AUTHOR: Kyle Whittington
 * CREATION DATE: 11/29/11
 * UPDATED: 12/1/11 by Manuel
 *
 * This is a class to test CTEUser.java
 */
public class CTEUserTester extends TestCase {
    /*
     * DEFAULTUSER's INSTANCE VARIABLES
     * These are the what a default CTEUser's
     * instance variables should equal.
     */
    //defaultUser._name = "Bob"
    private static final String defaultUser_NAME = "Bob";
    //defaultUser._IPAddress = www.uno.edu
    private InetAddress defaultUser_IP;
    //defaultUser._cursorColor = Color.red
    private static final Color defaultUser_COLOR = Color.red;
    //defaultUser._uniqueID = "808"
    private static final String defaultUser_ID = "808";

    /*UNIQUEUSERONE's INSTANCE VARIABLES*/
    //uniqueUserOne._name = George
    private static final String uniqueUserOne_NAME = "George";
    //uniqueUserOne._IPAddress = www.google.com
    private InetAddress uniqueUserOne_IP;
    //uniqueUserOne._cursorColor = Color.black
    private static final Color uniqueUserOne_COLOR = Color.black;
    //uniqueUserOne._uniqueID = UN1QUJ3US3R0N3
    private static final String uniqueUserOne_ID = "UN1QU3US3R0N3";
    //UNIQUEUSERTWO's INSTANCE VARIABLES//
    //uniqueUserTwo._name = Zelda
    private static final String uniqueUserTwo_NAME = "Zelda";
    //uniqueUserTwo._IPAddress = www.yahoo.com
    private InetAddress uniqueUserTwo_IP;
    //uniqueUserTwo._cursorColor = Color.white
    private static final Color uniqueUserTwo_COLOR = Color.white;
    //uniqueUserTwo._uniqueID = un1qu3us3rtw0
    private static final String uniqueUserTwo_ID = "un1qu3us3rtw0";
    /*UNIQUEUSERTWO's INSTANCE VARIABLES*/
    //likeUserXXX._name = "Gob"
    private static final String likeUser_NAME = "Gob";
    //likeUserXXX._IPAddress = www.uno.edu
    private InetAddress likeUser_IP;
    //likeUserXXX._cursorColor = Color.blue
    private static final Color likeUser_COLOR = Color.blue;
    //likeUserXXX._uniqueID = L1K3US3R1D
    private static final String likeUser_ID = "L1K3US3R1D";

    //will hold the default values for a CTEUser
    private CTEUser defaultUser;
    //will hold unique values to the other CTEUsers
    private CTEUser uniqueUserOne;
    //will hold unique values to the other CTEUsers
    private CTEUser uniqueUserTwo;
    //will hold similar values to likeUserTwo
    private CTEUser likeUserOne;
    //will hold similar values to likeUserOne
    private CTEUser likeUserTwo;

    //will be run before any other method, establishing
    //the users' values for each subsequent test
    @Override
    public void setUp ( ) {
        /*INITIALIZE IP ADDRESSES*/
        try {
            defaultUser_IP = InetAddress.getByName("www.uno.edu");
            uniqueUserOne_IP = InetAddress.getByName("www.google.com");
            uniqueUserTwo_IP = InetAddress.getByName("www.yahoo.com");
            likeUser_IP = InetAddress.getByName("www.uno.edu");
        }
        catch (UnknownHostException e) { fail("USER IP's NOT SET PROPERLY"); }

        //uses the default constructor to initialize
        //defaultUser = new CTEUser();
        //NOTE: Default constructor not written yet
        //until written, use this initialization
        try {
            defaultUser = new CTEUser(defaultUser_NAME, defaultUser_IP,
                    defaultUser_COLOR, defaultUser_ID);
        }
        catch (InvalidUserIDException e) { fail("defaultUser Name Invalid"); }
        try {
            //uses the uniqueUserOne static variables
            uniqueUserOne = new CTEUser(uniqueUserOne_NAME, uniqueUserOne_IP,
                    uniqueUserOne_COLOR, uniqueUserOne_ID);
        }
        catch (InvalidUserIDException e) { fail("uniqueUserOne Name Invalid"); }
        try {
            //uses the uniqueUserTwo static variables
            uniqueUserTwo = new CTEUser(uniqueUserTwo_NAME, uniqueUserTwo_IP,
                    uniqueUserTwo_COLOR, uniqueUserTwo_ID);
        }
        catch (InvalidUserIDException e) { fail("uniqueUserTwo Name Invalid"); }
        try {
            //uses the likeUserXXX static variables
            likeUserOne = new CTEUser(likeUser_NAME, likeUser_IP,
                    likeUser_COLOR, likeUser_ID);
        }
        catch (InvalidUserIDException e) { fail("likeUserOne Name Invalid"); }
        try {
            //uses the likeUserXXX static variables
            likeUserTwo = new CTEUser(likeUser_NAME, likeUser_IP,
                    likeUser_COLOR, likeUser_ID);
        }
        catch (InvalidUserIDException e) { fail("likeUserTwo Name Invalid"); }
    }

    /*
     * Tests getUniqueID()
     */
    public void testGetUniqueID ( ) {
        //test default user id
        assertEquals(defaultUser_ID, defaultUser.getUniqueID());
        //NOTE default constructor not written

        //test unique user id
        assertEquals(uniqueUserOne_ID, uniqueUserOne.getUniqueID());
        assertEquals(uniqueUserTwo_ID, uniqueUserTwo.getUniqueID());

        //test like user name
        assertEquals(likeUser_ID, likeUserOne.getUniqueID());
        assertEquals(likeUser_ID, likeUserOne.getUniqueID());
    }

    /*
     * Tests getIPAddress()
     */
    public void testGetIPAddress ( ) {
        //test default user IP
        assertEquals(defaultUser_IP, defaultUser.getIPAddress());
        //NOTE: default constructor not written

        //test unique user ip
        assertEquals(uniqueUserOne_IP, uniqueUserOne.getIPAddress());
        assertEquals(uniqueUserTwo_IP, uniqueUserTwo.getIPAddress());

        //test like user ip
        assertEquals(likeUser_IP, likeUserOne.getIPAddress());
        assertEquals(likeUser_IP, likeUserTwo.getIPAddress());
    }

    /*
     * Tests getName()
     */
    public void testGetName ( ) {
        //test default user name
        assertEquals(defaultUser_NAME, defaultUser.getName());
        //NOTE: default constructor not written

        //test unique user name
        assertEquals(uniqueUserOne_NAME, uniqueUserOne.getName());
        assertEquals(uniqueUserTwo_NAME, uniqueUserTwo.getName());

        //test like user name
        assertEquals(likeUser_NAME, likeUserOne.getName());
        assertEquals(likeUser_NAME, likeUserTwo.getName());
    }

    /*
     * Tests getCursorColor()
     */
    public void testGetCursorColor ( ) {
        //test default user cursor
        assertEquals(defaultUser_COLOR, defaultUser.getCursorColor());
        //NOTE: default constructor not written

        //test unique user color
        assertEquals(uniqueUserOne_COLOR, uniqueUserOne.getCursorColor());
        assertEquals(uniqueUserTwo_COLOR, uniqueUserTwo.getCursorColor());

        //test like user color
        assertEquals(likeUser_COLOR, likeUserOne.getCursorColor());
        assertEquals(likeUser_COLOR, likeUserTwo.getCursorColor());
    }

    /*
     * Tests getPosition() where cursor has not moved yet
     */
    public void testInitialGetPosition ( ) {
        //test default user cursor
        assertEquals(0, defaultUser.getPosition().getPosition());
        //NOTE: default constructor not written

        //test unique user position
        assertEquals(0, uniqueUserOne.getPosition().getPosition());
        assertEquals(0, uniqueUserTwo.getPosition().getPosition());

        //test like user color
        assertEquals(0, likeUserOne.getPosition().getPosition());
        assertEquals(0, likeUserTwo.getPosition().getPosition());
    }

    /*
     * Tests clone()
     */
    public void testClone ( ) {
        //CLONE VARIABLE SET UP
        try {
            CTEUser defaultClone = (CTEUser) defaultUser.clone();
            //test default user clone
            assertEquals(defaultUser.getUniqueID(), defaultClone.getUniqueID());
            assertEquals(defaultUser.getIPAddress(), defaultClone.getIPAddress());
            assertEquals(defaultUser.getName(), defaultClone.getName());
            assertEquals(defaultUser.getCursorColor(), defaultClone.getCursorColor());
            assertEquals(defaultUser.getPosition(), defaultClone.getPosition());
            assertEquals(0, defaultClone.compareTo(defaultUser));
            assertEquals(true, defaultClone.equals(defaultUser));
            assertEquals(defaultUser.toString(), defaultClone.toString());
            assertEquals(defaultUser.hashCode(), defaultClone.hashCode());
            //NOTE: default constructor not written
        }
        catch (CloneNotSupportedException e) { fail("defaultClone Not Supported"); }
        try {
            CTEUser uniqueCloneOne = (CTEUser) uniqueUserOne.clone();
            //test unique user clones
            assertEquals(uniqueUserOne.getUniqueID(), uniqueCloneOne.getUniqueID());
            assertEquals(uniqueUserOne.getIPAddress(), uniqueCloneOne.getIPAddress());
            assertEquals(uniqueUserOne.getName(), uniqueCloneOne.getName());
            assertEquals(uniqueUserOne.getCursorColor(), uniqueCloneOne.getCursorColor());
            assertEquals(uniqueUserOne.getPosition(), uniqueCloneOne.getPosition());
            assertEquals(0, uniqueCloneOne.compareTo(uniqueUserOne));
            assertEquals(true, uniqueCloneOne.equals(uniqueUserOne));
            assertEquals(uniqueUserOne.toString(), uniqueCloneOne.toString());
            assertEquals(uniqueUserOne.hashCode(), uniqueCloneOne.hashCode());
        }
        catch (CloneNotSupportedException e) { fail("uniqueCloneOne Not Supported"); }
        try {
            CTEUser uniqueCloneTwo = (CTEUser) uniqueUserTwo.clone();
            assertEquals(uniqueCloneTwo.getUniqueID(), uniqueUserTwo.getUniqueID());
            assertEquals(uniqueCloneTwo.getIPAddress(), uniqueUserTwo.getIPAddress());
            assertEquals(uniqueCloneTwo.getName(), uniqueUserTwo.getName());
            assertEquals(uniqueCloneTwo.getCursorColor(), uniqueUserTwo.getCursorColor());
            assertEquals(uniqueCloneTwo.getPosition(), uniqueUserTwo.getPosition());
            assertEquals(uniqueCloneTwo.compareTo(uniqueUserTwo), 0);
            assertEquals(uniqueCloneTwo.equals(uniqueUserTwo), true);
            assertEquals(uniqueCloneTwo.toString(), uniqueUserTwo.toString());
            assertEquals(uniqueCloneTwo.hashCode(), uniqueUserTwo.hashCode());
        } catch (CloneNotSupportedException e) { fail("uniqueCloneTwo Not Supported"); }
        try {
            CTEUser likeCloneOne = (CTEUser) likeUserOne.clone();
            assertEquals(likeUserOne.getUniqueID(), likeCloneOne.getUniqueID());
            assertEquals(likeUserOne.getIPAddress(), likeCloneOne.getIPAddress());
            assertEquals(likeUserOne.getName(), likeCloneOne.getName());
            assertEquals(likeUserOne.getCursorColor(), likeCloneOne.getCursorColor());
            assertEquals(likeUserOne.getPosition(), likeCloneOne.getPosition());
            assertEquals(0, likeCloneOne.compareTo(likeUserOne));
            assertEquals(true, likeCloneOne.equals(likeUserOne));
            assertEquals(likeUserOne.toString(), likeCloneOne.toString());
            assertEquals(likeUserOne.hashCode(), likeCloneOne.hashCode());
        } catch (CloneNotSupportedException e) { fail("likeCloneOne Not Supported"); }
        try {
            CTEUser likeCloneTwo = (CTEUser) likeUserTwo.clone();
            assertEquals(likeUserTwo.getUniqueID(), likeCloneTwo.getUniqueID());
            assertEquals(likeUserTwo.getIPAddress(), likeCloneTwo.getIPAddress());
            assertEquals(likeUserTwo.getName(), likeCloneTwo.getName());
            assertEquals(likeUserTwo.getCursorColor(), likeCloneTwo.getCursorColor());
            assertEquals(likeUserTwo.getPosition(), likeCloneTwo.getPosition());
            assertEquals(0, likeCloneTwo.compareTo(likeUserTwo));
            assertEquals(true, likeCloneTwo.equals(likeUserTwo));
            assertEquals(likeUserTwo.toString(), likeCloneTwo.toString());
            assertEquals(likeUserTwo.hashCode(), likeCloneTwo.hashCode());
        } catch (CloneNotSupportedException e) { fail("likeCloneTwo Not Supported"); }
    }

    /*
     * Tests compareTo()
     */
    public void testCompareTo ( ) {
        //test default user compareTo
        assertEquals(0, defaultUser.compareTo(defaultUser));
        //NOTE: default constructor not written

        //test unique user compareTo
        //uniqueUserOne.getPosition() == 10
        //uniqueUserTwo.getPosition() == 0
        try {
            uniqueUserOne.setPosition(new TextPosition(10));
            assertTrue(uniqueUserOne.compareTo(uniqueUserTwo) > 0);
            assertTrue(uniqueUserTwo.compareTo(uniqueUserOne) < 0);
        }
        catch (OutOfBoundsException e) { fail("uniqueUserOne's Position Out of Bounds"); }

        //test like user compareTo
        //likeUserOne.getPosition() == likeUserTwo
        assertTrue(likeUserOne.compareTo(likeUserTwo) == 0);
        assertTrue(likeUserTwo.compareTo(likeUserOne) == 0);
    }

    /*
     * Tests equals()
     */
    public void testEquals ( ) {
        //test default user equals
        //assertTrue(defaultUser.equals(new CTEUser()));
        //NOTE: default constructor not initialized

        //test unique user equals
        assertFalse(uniqueUserOne.equals(uniqueUserTwo));
        assertFalse(uniqueUserTwo.equals(uniqueUserOne));

        //test like user equals
        assertTrue(likeUserOne.equals(likeUserTwo));
        assertTrue(likeUserTwo.equals(likeUserOne));
    }

    /*
     * Tests toString()
     */
    public void testToString() {
        //String Variable Set Up
        //all positions should be zero at this point
        String defaultString = defaultUser_NAME + "{ id: " + defaultUser_ID + " pos: [" + 0 + "] }";
        String uniqueStringOne = uniqueUserOne_NAME + "{ id: " + uniqueUserOne_ID + " pos: [" + 0 + "] }";
        String uniqueStringTwo = uniqueUserTwo_NAME + "{ id: " + uniqueUserTwo_ID + " pos: [" + 0 + "] }";
        String likeString = likeUser_NAME + "{ id: " + likeUser_ID + " pos: [" + 0 + "] }";
        //test default user toString
        assertEquals(defaultString, defaultUser.toString());
        //NOTE: default constructor not written

        //test unique users toString
        assertEquals(uniqueStringOne, uniqueUserOne.toString());
        assertEquals(uniqueStringTwo, uniqueUserTwo.toString());

        //test like users toString
        assertEquals(likeString, likeUserOne.toString());
        assertEquals(likeString, likeUserTwo.toString());
        assertEquals(likeUserTwo.toString(), likeUserOne.toString());
    }

    /*
     * Tests hashCode()
     */
    public void testHashCode ( ) {
        //test default user hashCode
        assertEquals(defaultUser.hashCode(), defaultUser.hashCode());
        //NOTE: default constructor not written

        //test unique user hashCode
        assertEquals(uniqueUserOne.hashCode(), uniqueUserOne.hashCode());
        assertEquals(uniqueUserTwo.hashCode(), uniqueUserTwo.hashCode());
        assertFalse(uniqueUserOne.hashCode() == uniqueUserTwo.hashCode());

        //test like user hashCode
        assertEquals(likeUserOne.hashCode(), likeUserTwo.hashCode());
        assertEquals(likeUserTwo.hashCode(), likeUserOne.hashCode());
    }

    /*
     * Tests setPosition()
     */
    public void testSetPosition ( ) {
        //test with default user
        try {
            defaultUser.setPosition(new TextPosition(10));
            assertEquals(new TextPosition(10), defaultUser.getPosition());

            defaultUser.setPosition(new TextPosition(20));
            assertEquals(new TextPosition(20), defaultUser.getPosition());

            defaultUser.setPosition(new TextPosition(42));
            assertEquals(new TextPosition(42), defaultUser.getPosition());

            defaultUser.setPosition(new TextPosition(101));
            assertEquals(new TextPosition(101), defaultUser.getPosition());
        }
        catch (OutOfBoundsException e) { fail("defaultUser setPosition TextPosition invalid"); }

        //NOTE: default constructor not written
    }

    /*
     * Tests setName()
     */
    public void testSetName ( ) {
        //test with default user
        try {
            defaultUser.setName("Gob");
            assertEquals("Gob", defaultUser.getName());

            defaultUser.setName("St. Francis");
            assertEquals("St. Francis", defaultUser.getName());

            defaultUser.setName("Zelda");
            assertEquals("Zelda", defaultUser.getName());
        }
        catch (InvalidUserIDException e) { fail("defaultUser setName Name invalid"); }
    }
}
