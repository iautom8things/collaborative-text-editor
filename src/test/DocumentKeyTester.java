package test;

import handler.*;
import junit.framework.*;

/**
 * AUTHOR: Kyle Whittington
 * CREATION DATE: 12/2/11
 * UPDATED:  12/2/11 By Manuel
 *
 * This is a class to test DocumentKey.java
 */
public class DocumentKeyTester extends TestCase {
    /*
     * DEFAULTUSER's INSTANCE VARIABLES
     * These are what a default DocumentKey's
     * instance variables should equal.
     */
    //defaultDocKey._documentName = "default.txt"
    private static final String defaultDocKey_DOCNAME = "default.txt";
    //defaultDocKey._pass = "default"
    private static final String defaultDocKey_PASS = "default";

    /*UNIQUEDOCKEYONE's INSTANCE VARIABLES*/
    //uniqueDocKeyOne._documentName = "grades.txt"
    private static final String uniqueDocKeyOne_DOCNAME = "grades.txt";
    //uniqueDocKeyOne._pass = "teachercreature"
    private static final String uniqueDocKeyOne_PASS = "teachercreature";

    /*UNIQUEDOCKEYTWO's INSTANCE VARIABLES*/
    //uniqueDocKeyTwo._documentName = "ENGL1157_HW1.txt"
    private static final String uniqueDocKeyTwo_DOCNAME = "ENGL1157_HW1";
    //uniqueDocKeyTwo._pass = "charlesTwain"
    private static final String uniqueDocKeyTwo_PASS = "charlesTwain";

    /*LIKEUSERS' INSTANCE VARIABLES*/
    //likeDocKey._documentName = "War of the Worlds.txt"
    private static final String likeDocKey_DOCNAME = "War of the Worlds.txt";
    //likeDocKey._pass = "theinvisibleman"
    private static final String likeDocKey_PASS = "theinvisibleman";

    //will hold the default values for a DocumentKey
    private DocumentKey defaultDocKey;
    //will hold unique values to the other DocumentKeys
    private DocumentKey uniqueDocKeyOne;
    //will hold unique values to the other DocumentKeys
    private DocumentKey uniqueDocKeyTwo;
    //will hold similar values to likeDocKeyTwo
    private DocumentKey likeDocKeyOne;
    //will hold similar values to likeDocKeyOne
    private DocumentKey likeDocKeyTwo;

    //will be run before any other method, establishing
    //the DocKeys' values for each subsquent test
    @Override
    public void setUp ( ) {
        //uses the default constructor to initialize
        //defaultDocKey = new DocumentKey();
        //NOTE: default constructor not written yet
        //until written, use this initialization:
        defaultDocKey = new DocumentKey(defaultDocKey_DOCNAME,
                                        defaultDocKey_PASS);
        //uses the uniqueDocKeyOne static variables
        uniqueDocKeyOne = new DocumentKey(uniqueDocKeyOne_DOCNAME,
                                        uniqueDocKeyOne_PASS);
        //uses the uniqueDocKeyTwo static variables
        uniqueDocKeyTwo = new DocumentKey(uniqueDocKeyTwo_DOCNAME,
                uniqueDocKeyTwo_PASS);
        //uses the likeUserXXX static variables
        likeDocKeyOne = new DocumentKey(likeDocKey_DOCNAME,
                                        likeDocKey_PASS);
        //uses the likeUserXXX static variables
        likeDocKeyTwo = new DocumentKey(likeDocKey_DOCNAME,
                                        likeDocKey_PASS);
    }

    /*
     * Tests getName()
     */
    public void testGetName ( ) {
        //test default document name
        assertEquals(defaultDocKey_DOCNAME, defaultDocKey.getName());
        //NOTE: default constructor not written

        //test unique document name
        assertEquals(uniqueDocKeyOne_DOCNAME, uniqueDocKeyOne.getName());
        assertEquals(uniqueDocKeyTwo_DOCNAME, uniqueDocKeyTwo.getName());
        assertFalse(uniqueDocKeyOne.getName().equals(uniqueDocKeyTwo.getName()));
        assertFalse(uniqueDocKeyTwo.getName().equals(uniqueDocKeyOne.getName()));

        //test like document name
        assertEquals(likeDocKey_DOCNAME, likeDocKeyOne.getName());
        assertEquals(likeDocKey_DOCNAME, likeDocKeyTwo.getName());
        assertEquals(likeDocKeyTwo.getName(), likeDocKeyOne.getName());
        assertEquals(likeDocKeyOne.getName(), likeDocKeyTwo.getName());
    }

    /*
     * Tests getPass()
     */
    public void testGetPass ( ) {
        //test default document pass
        assertEquals(defaultDocKey_PASS, defaultDocKey.getPass());
        //NOTE: default constructor not written

        //test unique document pass
        assertEquals(uniqueDocKeyOne_PASS, uniqueDocKeyOne.getPass());
        assertEquals(uniqueDocKeyTwo_PASS, uniqueDocKeyTwo.getPass());
        assertFalse(uniqueDocKeyOne.getPass().equals(uniqueDocKeyTwo.getPass()));
        assertFalse(uniqueDocKeyTwo.getPass().equals(uniqueDocKeyOne.getPass()));

        //test like document pass
        assertEquals(likeDocKey_PASS, likeDocKeyOne.getPass());
        assertEquals(likeDocKey_PASS, likeDocKeyTwo.getPass());
        assertEquals(likeDocKeyTwo.getPass(), likeDocKeyOne.getPass());
        assertEquals(likeDocKeyOne.getPass(), likeDocKeyTwo.getPass());
    }

    /*
     * Tests toString()
     */
    public void testToString ( ) {
        //String Variable Set Up
        String defaultString = "DocumentKey{" + "name: " +
                defaultDocKey_DOCNAME + ", password: " +
                defaultDocKey_PASS + " }";
        String uniqueStringOne = "DocumentKey{" + "name: " +
                uniqueDocKeyOne_DOCNAME + ", password: " +
                uniqueDocKeyOne_PASS + " }";
        String uniqueStringTwo = "DocumentKey{" + "name: " +
                uniqueDocKeyTwo_DOCNAME + ", password: " +
                uniqueDocKeyTwo_PASS + " }";
        String likeString = "DocumentKey{" + "name: " +
                likeDocKey_DOCNAME + ", password: " +
                likeDocKey_PASS + " }";

        //test default document key toString
        assertEquals(defaultString, defaultDocKey.toString());
        //NOTE: default constructor not written

        //test unique document key toString
        assertEquals(uniqueStringOne, uniqueDocKeyOne.toString());
        assertEquals(uniqueStringTwo, uniqueDocKeyTwo.toString());

        //test like document keys toString
        assertEquals(likeString, likeDocKeyOne.toString());
        assertEquals(likeString, likeDocKeyTwo.toString());
        assertEquals(likeDocKeyTwo.toString(), likeDocKeyOne.toString());
    }

    /*
     * Tests hashCode()
     */
    public void testHashCode ( ) {
        //test default document key hashCode
        assertEquals(defaultDocKey.hashCode(), defaultDocKey.hashCode());
        //NOTE: default constructor not written

        //test unique document key hashCode
        //test if the same class will repeatedly hash to the same value
        assertEquals(uniqueDocKeyOne.hashCode(), uniqueDocKeyOne.hashCode());
        assertEquals(uniqueDocKeyTwo.hashCode(), uniqueDocKeyTwo.hashCode());
        //test if different classes will hash to different values
        assertFalse(uniqueDocKeyOne.hashCode() == uniqueDocKeyTwo.hashCode());
        assertFalse(uniqueDocKeyTwo.hashCode() == uniqueDocKeyOne.hashCode());

        //test like document key hashcode
        //test if the same class will repeately hash to the same value
        assertEquals(likeDocKeyOne.hashCode(), likeDocKeyOne.hashCode());
        assertEquals(likeDocKeyTwo.hashCode(), likeDocKeyTwo.hashCode());
        //test if similar classes will hash to the same value
        assertEquals(likeDocKeyTwo.hashCode(), likeDocKeyOne.hashCode());
        assertEquals(likeDocKeyOne.hashCode(), likeDocKeyTwo.hashCode());
    }

    /*
     * Tests clone()
     */
    public void testClone ( ) {
        //Clone Variable Set Up
        try {
            DocumentKey defaultClone = (DocumentKey) defaultDocKey.clone();
            //test default document key clone
            assertEquals( defaultDocKey.getName(), defaultClone.getName());
            assertEquals( defaultDocKey.getPass(), defaultClone.getPass());
            assertTrue(defaultClone.equals(defaultDocKey));
            assertTrue(defaultDocKey.equals(defaultClone));
        }
        catch (CloneNotSupportedException e) { fail("defaultClone Not Supported"); }
        try {
            DocumentKey uniqueCloneOne = (DocumentKey)uniqueDocKeyOne.clone();
            //test unique document key clone
            assertEquals(uniqueDocKeyOne.getName(), uniqueCloneOne.getName());
            assertEquals(uniqueDocKeyOne.getPass(), uniqueCloneOne.getPass());
            assertTrue(uniqueCloneOne.equals(uniqueDocKeyOne));
            assertTrue(uniqueDocKeyOne.equals(uniqueCloneOne));
        }
        catch (CloneNotSupportedException e) { fail("uniqueCloneOne Not Supported"); }
        try {
            DocumentKey uniqueCloneTwo = (DocumentKey)uniqueDocKeyTwo.clone();
            //test unique document key clone
            assertEquals(uniqueDocKeyTwo.getName(), uniqueCloneTwo.getName());
            assertEquals(uniqueDocKeyTwo.getPass(), uniqueCloneTwo.getPass());
            assertTrue(uniqueCloneTwo.equals(uniqueDocKeyTwo));
            assertTrue(uniqueDocKeyTwo.equals(uniqueCloneTwo));
        }
        catch (CloneNotSupportedException e) { fail("uniqueCloneTwo Not Supported"); }
        try {
            DocumentKey likeCloneOne = (DocumentKey)likeDocKeyOne.clone();
            //test like document key clone
            assertEquals(likeDocKeyOne.getName(), likeCloneOne.getName());
            assertEquals(likeDocKeyOne.getPass(), likeCloneOne.getPass());
            assertTrue(likeCloneOne.equals(likeDocKeyOne));
            assertTrue(likeDocKeyOne.equals(likeCloneOne));
        }
        catch (CloneNotSupportedException e) { fail("likeCloneOne Not Supported"); }
        try {
            DocumentKey likeCloneTwo = (DocumentKey)likeDocKeyTwo.clone();
            //test like document key clone
            assertEquals(likeDocKeyTwo.getName(), likeCloneTwo.getName());
            assertEquals(likeDocKeyTwo.getPass(), likeCloneTwo.getPass());
            assertTrue(likeCloneTwo.equals(likeDocKeyTwo));
            assertTrue(likeDocKeyTwo.equals(likeCloneTwo));
        }
        catch (CloneNotSupportedException e) { fail("likeCloneTwo Not Supported"); }
    }

    /*
     * Tests equals()
     */
    public void testEquals ( ) {
        //test default user equals
        //assertTrue(defaultDocKey.equals(new DocumentKey());
        //NOTE: default constructor not initialized
        //Until written, use this assert:
        assertTrue(defaultDocKey.equals(new DocumentKey(defaultDocKey_DOCNAME,
                defaultDocKey_PASS)));
        //test unique document key equals
        //test for equality with self
        assertTrue(uniqueDocKeyOne.equals(uniqueDocKeyOne));
        assertTrue(uniqueDocKeyTwo.equals(uniqueDocKeyTwo));
        //test for inequality with an unequal object
        assertFalse(uniqueDocKeyOne.equals(uniqueDocKeyTwo));
        assertFalse(uniqueDocKeyTwo.equals(uniqueDocKeyOne));

        //test like document key equals
        //test for equality with self
        assertTrue(likeDocKeyOne.equals(likeDocKeyOne));
        assertTrue(likeDocKeyTwo.equals(likeDocKeyTwo));
        //test for equality with an equal object
        assertTrue(likeDocKeyOne.equals(likeDocKeyTwo));
        assertTrue(likeDocKeyTwo.equals(likeDocKeyOne));
    }
}
