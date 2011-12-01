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
    public void setUp() {
        /*INITIALIZE IP ADDRESSES*/
        try {
            defaultUser_IP = InetAddress.getByName("www.uno.edu");
            uniqueUserOne_IP = InetAddress.getByName("www.google.com");
            uniqueUserTwo_IP = InetAddress.getByName("www.yahoo.com");
            likeUser_IP = InetAddress.getByName("www.uno.edu");
        } catch (UnknownHostException e) {
            System.out.println("USER IP's NOT SET PROPERLY");
        }

        //uses the default constructor to initialize
        //defaultUser = new CTEUser();
        //NOTE: Default constructor not written yet
        //until written, use this initialization
        try {
            defaultUser = new CTEUser(defaultUser_NAME, defaultUser_IP,
                    defaultUser_COLOR, defaultUser_ID);
        } catch (InvalidUserIDException e) {
            System.out.println("defaultUser Name Invalid");
        }
        try {
            //uses the uniqueUserOne static variables
            uniqueUserOne = new CTEUser(uniqueUserOne_NAME, uniqueUserOne_IP,
                    uniqueUserOne_COLOR, uniqueUserOne_ID);
        } catch (InvalidUserIDException e) {
            System.out.println("uniqueUserOne Name Invalid");
        }
        try {
            //uses the uniqueUserTwo static variables
            uniqueUserTwo = new CTEUser(uniqueUserTwo_NAME, uniqueUserTwo_IP,
                    uniqueUserTwo_COLOR, uniqueUserTwo_ID);
        } catch (InvalidUserIDException e) {
            System.out.println("uniqueUserTwo Name Invalid");
        }
        try {
            //uses the likeUserXXX static variables
            likeUserOne = new CTEUser(likeUser_NAME, likeUser_IP,
                    likeUser_COLOR, likeUser_ID);
        } catch (InvalidUserIDException e) {
            System.out.println("likeUserOne Name Invalid");
        }
        try {
            //uses the likeUserXXX static variables
            likeUserTwo = new CTEUser(likeUser_NAME, likeUser_IP,
                    likeUser_COLOR, likeUser_ID);
        } catch (InvalidUserIDException e) {
            System.out.println("likeUserTwo Name Invalid");
        }
    }

    /*
     * Tests getUniqueID()
     */
    public void testGetUniqueID() {
        //test default user id
        assertEquals(defaultUser.getUniqueID(),defaultUser_ID);
        //NOTE default constructor not written

        //test unique user id
        assertEquals(uniqueUserOne.getUniqueID(), uniqueUserOne_ID);
        assertEquals(uniqueUserTwo.getUniqueID(), uniqueUserTwo_ID);

        //test like user name
        assertEquals(likeUserOne.getUniqueID(), likeUser_ID);
        assertEquals(likeUserOne.getUniqueID(), likeUser_ID);
    }

    /*
     * Tests getIPAddress()
     */
    public void testGetIPAddress() {
        //test default user IP
        assertEquals(defaultUser.getIPAddress(),defaultUser_IP);
        //NOTE: default constructor not written

        //test unique user ip
        assertEquals(uniqueUserOne.getIPAddress(), uniqueUserOne_IP);
        assertEquals(uniqueUserTwo.getIPAddress(), uniqueUserTwo_IP);

        //test like user ip
        assertEquals(likeUserOne.getIPAddress(), likeUser_IP);
        assertEquals(likeUserTwo.getIPAddress(), likeUser_IP);
    }

    /*
     * Tests getName()
     */
    public void testGetName() {
        //test default user name
        assertEquals(defaultUser.getName(),defaultUser_NAME);
        //NOTE: default constructor not written

        //test unique user name
        assertEquals(uniqueUserOne.getName(), uniqueUserOne_NAME);
        assertEquals(uniqueUserTwo.getName(), uniqueUserTwo_NAME);

        //test like user name
        assertEquals(likeUserOne.getName(), likeUser_NAME);
        assertEquals(likeUserTwo.getName(), likeUser_NAME);
    }

    /*
     * Tests getCursorColor()
     */
    public void testGetCursorColor() {
        //test default user cursor
        assertEquals(defaultUser.getCursorColor(),defaultUser_COLOR);
        //NOTE: default constructor not written

        //test unique user color
        assertEquals(uniqueUserOne.getCursorColor(), uniqueUserOne_COLOR);
        assertEquals(uniqueUserTwo.getCursorColor(), uniqueUserTwo_COLOR);

        //test like user color
        assertEquals(likeUserOne.getCursorColor(), likeUser_COLOR);
        assertEquals(likeUserTwo.getCursorColor(), likeUser_COLOR);
    }

    /*
     * Tests getPosition() where cursor has not moved yet
     */
    public void testInitialGetPosition() {
        //test default user cursor
        assertEquals(defaultUser.getPosition().getPosition(),0);
        //NOTE: default constructor not written

        //test unique user position
        assertEquals(uniqueUserOne.getPosition().getPosition(), 0);
        assertEquals(uniqueUserTwo.getPosition().getPosition(), 0);

        //test like user color
        assertEquals(likeUserOne.getPosition().getPosition(), 0);
        assertEquals(likeUserTwo.getPosition().getPosition(), 0);
    }

    /*
     * Tests clone()
     */
    public void testClone() {
        //CLONE VARIABLE SET UP
        try {
            CTEUser defaultClone = (CTEUser) defaultUser.clone();
            //test default user clone
            assertEquals(defaultClone.getUniqueID(),defaultUser.getUniqueID());
            assertEquals(defaultClone.getIPAddress(),defaultUser.getIPAddress());
            assertEquals(defaultClone.getName(),defaultUser.getName());
            assertEquals(defaultClone.getCursorColor(),defaultUser.getCursorColor());
            assertEquals(defaultClone.getPosition(),defaultUser.getPosition());
            assertEquals(defaultClone.compareTo(defaultUser),0);
            assertEquals(defaultClone.equals(defaultUser),true);
            assertEquals(defaultClone.toString(),defaultUser.toString());
            assertEquals(defaultClone.hashCode(),defaultUser.hashCode());
            //NOTE: default constructor not written
        } catch (CloneNotSupportedException e) {
            System.out.println("defaultClone Not Supported");
        }
        try {
            CTEUser uniqueCloneOne = (CTEUser) uniqueUserOne.clone();
            //test unique user clones
            assertEquals(uniqueCloneOne.getUniqueID(), uniqueUserOne.getUniqueID());
            assertEquals(uniqueCloneOne.getIPAddress(), uniqueUserOne.getIPAddress());
            assertEquals(uniqueCloneOne.getName(), uniqueUserOne.getName());
            assertEquals(uniqueCloneOne.getCursorColor(), uniqueUserOne.getCursorColor());
            assertEquals(uniqueCloneOne.getPosition(), uniqueUserOne.getPosition());
            assertEquals(uniqueCloneOne.compareTo(uniqueUserOne), 0);
            assertEquals(uniqueCloneOne.equals(uniqueUserOne), true);
            assertEquals(uniqueCloneOne.toString(), uniqueUserOne.toString());
            assertEquals(uniqueCloneOne.hashCode(), uniqueUserOne.hashCode());
        } catch (CloneNotSupportedException e) {
            System.out.println("uniqueCloneOne Not Supported");
        }
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
        } catch (CloneNotSupportedException e) {
            System.out.println("uniqueCloneTwo Not Supported");
        }
        try {
            CTEUser likeCloneOne = (CTEUser) likeUserOne.clone();
            assertEquals(likeCloneOne.getUniqueID(), likeUserOne.getUniqueID());
            assertEquals(likeCloneOne.getIPAddress(), likeUserOne.getIPAddress());
            assertEquals(likeCloneOne.getName(), likeUserOne.getName());
            assertEquals(likeCloneOne.getCursorColor(), likeUserOne.getCursorColor());
            assertEquals(likeCloneOne.getPosition(), likeUserOne.getPosition());
            assertEquals(likeCloneOne.compareTo(likeUserOne), 0);
            assertEquals(likeCloneOne.equals(likeUserOne), true);
            assertEquals(likeCloneOne.toString(), likeUserOne.toString());
            assertEquals(likeCloneOne.hashCode(), likeUserOne.hashCode());
        } catch (CloneNotSupportedException e) {
            System.out.println("likeCloneOne Not Supported");
        }
        try {
            CTEUser likeCloneTwo = (CTEUser) likeUserTwo.clone();
            assertEquals(likeCloneTwo.getUniqueID(), likeUserTwo.getUniqueID());
            assertEquals(likeCloneTwo.getIPAddress(), likeUserTwo.getIPAddress());
            assertEquals(likeCloneTwo.getName(), likeUserTwo.getName());
            assertEquals(likeCloneTwo.getCursorColor(), likeUserTwo.getCursorColor());
            assertEquals(likeCloneTwo.getPosition(), likeUserTwo.getPosition());
            assertEquals(likeCloneTwo.compareTo(likeUserTwo), 0);
            assertEquals(likeCloneTwo.equals(likeUserTwo), true);
            assertEquals(likeCloneTwo.toString(), likeUserTwo.toString());
            assertEquals(likeCloneTwo.hashCode(), likeUserTwo.hashCode());
        } catch (CloneNotSupportedException e) {
            System.out.println("likeCloneTwo Not Supported");
        }
    }

    /*
     * Tests compareTo()
     */
    public void testCompareTo() {
        //test default user compareTo
        assertEquals(defaultUser.compareTo(defaultUser),0);
        //NOTE: default constructor not written

        //test unique user compareTo
        //uniqueUserOne.getPosition() == 10
        //uniqueUserTwo.getPosition() == 0
        try {
            uniqueUserOne.setPosition(new TextPosition(10));
            assertTrue(uniqueUserOne.compareTo(uniqueUserTwo) > 0);
            assertTrue(uniqueUserTwo.compareTo(uniqueUserOne) < 0);
        } catch (OutOfBoundsException e) {
            System.out.println("uniqueUserOne's Position Out of Bounds");
        }

        //test like user compareTo
        //likeUserOne.getPosition() == likeUserTwo
        assertTrue(likeUserOne.compareTo(likeUserTwo) == 0);
        assertTrue(likeUserTwo.compareTo(likeUserOne) == 0);
    }

    /*
     * Tests equals()
     */
    public void testEquals() {
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
        assertEquals(defaultUser.toString(), defaultString);
        //NOTE: default constructor not written

        //test unique users toString
        assertEquals(uniqueUserOne.toString(),uniqueStringOne);
        assertEquals(uniqueUserTwo.toString(),uniqueStringTwo);

        //test like users toString
        assertEquals(likeUserOne.toString(),likeString);
        assertEquals(likeUserTwo.toString(),likeString);
        assertEquals(likeUserOne.toString(),likeUserTwo.toString());
    }

    /*
     * Tests hashCode()
     */
    public void testHashCode() {
        //test default user hashCode
        assertEquals(defaultUser.hashCode(),defaultUser.hashCode());
        //NOTE: default constructor not written

        //test unique user hashCode
        assertEquals(uniqueUserOne.hashCode(),uniqueUserOne.hashCode());
        assertEquals(uniqueUserTwo.hashCode(),uniqueUserTwo.hashCode());
        assertFalse(uniqueUserOne.hashCode() == uniqueUserTwo.hashCode());

        //test like user hashCode
        assertEquals(likeUserOne.hashCode(),likeUserTwo.hashCode());
        assertEquals(likeUserTwo.hashCode(),likeUserOne.hashCode());
    }

    /*
     * Tests setPosition()
     */
    public void testSetPosition() {
        //test with default user
        try {
            defaultUser.setPosition(new TextPosition(10));
            assertEquals(defaultUser.getPosition(),new TextPosition(10));

            defaultUser.setPosition(new TextPosition(20));
            assertEquals(defaultUser.getPosition(),new TextPosition(20));

            defaultUser.setPosition(new TextPosition(42));
            assertEquals(defaultUser.getPosition(),new TextPosition(42));

            defaultUser.setPosition(new TextPosition(101));
            assertEquals(defaultUser.getPosition(),new TextPosition(101));
        } catch (OutOfBoundsException e) {
            System.out.println("defaultUser setPosition TextPosition invalid");
        }

        //NOTE: default constructor not written
    }

    /*
     * Tests setName()
     */
    public void testSetName() {
        //test with default user
        try {
            defaultUser.setName("Gob");
            assertEquals(defaultUser.getName(),"Gob");

            defaultUser.setName("St. Francis");
            assertEquals(defaultUser.getName(),"St. Francis");

            defaultUser.setName("Zelda");
            assertEquals(defaultUser.getName(),"Zelda");
        } catch (InvalidUserIDException e) {
            System.out.println("defaultUser setName Name invalid");
        }
    }
}
