package test;

import handler.*;
import junit.framework.*;

/**
 * AUTHOR: Kyle Whittington
 * CREATION DATE: 12/12/11
 * UPDATED: n/a
 * 
 * This is a class to test DocumentKey.java
 */
public class DocumentKeyTester extends TestCase{
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
    public void setUp() {
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
    public void getNameTest() {
        //test default document name
        assertEquals(defaultDocKey.getName(),defaultDocKey_DOCNAME);
        //NOTE: default constructor not written
        
        //test unique document name
        assertEquals(uniqueDocKeyOne.getName(),uniqueDocKeyOne_DOCNAME);
        assertEquals(uniqueDocKeyTwo.getName(),uniqueDocKeyTwo_DOCNAME);
        assertFalse(uniqueDocKeyOne.getName().equals(uniqueDocKeyTwo.getName()));
        assertFalse(uniqueDocKeyTwo.getName().equals(uniqueDocKeyOne.getName()));
        
        //test like document name
        assertEquals(likeDocKeyOne.getName(),likeDocKey_DOCNAME);
        assertEquals(likeDocKeyTwo.getName(),likeDocKey_DOCNAME);
        assertEquals(likeDocKeyOne.getName(),likeDocKeyTwo.getName());
        assertEquals(likeDocKeyTwo.getName(),likeDocKeyOne.getName());
    }
    
    /*
     * Tests getPass()
     */
    public void getPassTest() {
        //test default document pass
        assertEquals(defaultDocKey.getPass(),defaultDocKey_PASS);
        //NOTE: default constructor not written
        
        //test unique document pass
        assertEquals(uniqueDocKeyOne.getPass(),uniqueDocKeyOne_PASS);
        assertEquals(uniqueDocKeyTwo.getPass(),uniqueDocKeyTwo_PASS);
        assertFalse(uniqueDocKeyOne.getPass().equals(uniqueDocKeyTwo.getPass()));
        assertFalse(uniqueDocKeyTwo.getPass().equals(uniqueDocKeyOne.getPass()));
        
        //test like document pass
        assertEquals(likeDocKeyOne.getPass(),likeDocKey_PASS);
        assertEquals(likeDocKeyTwo.getPass(),likeDocKey_PASS);
        assertEquals(likeDocKeyOne.getPass(),likeDocKeyTwo.getPass());
        assertEquals(likeDocKeyTwo.getPass(),likeDocKeyOne.getPass());
    }
    
    /*
     * Tests toString()
     */
    public void toStringTest() {
        //String Variable Set Up
        String defaultString = "DocumentKey{" + "name: " + 
                defaultDocKey_DOCNAME + ", password: " + 
                defaultDocKey_PASS + " }";
        String uniqueStringOne = "DocuemtnKey{" + "name: " +
                uniqueDocKeyOne_DOCNAME + ", password: " + 
                uniqueDocKeyOne_PASS + " }";
        String uniqueStringTwo = "DocumentKey{" + "name: " +
                uniqueDocKeyTwo_DOCNAME + ", password: " +
                uniqueDocKeyTwo_PASS + " }";
        String likeString = "DocumentKey{" + "name: " +
                likeDocKey_DOCNAME + ", password: " +
                likeDocKey_PASS + " }";
        
        //test default document key toString
        assertEquals(defaultDocKey.toString(),defaultString);
        //NOTE: default constructor not written
        
        //test unique document key toString
        assertEquals(uniqueDocKeyOne.toString(),uniqueStringOne);
        assertEquals(uniqueDocKeyTwo.toString(),uniqueStringTwo);
        
        //test like document keys toString
        assertEquals(likeDocKeyOne.toString(),likeString);
        assertEquals(likeDocKeyTwo.toString(),likeString);
        assertEquals(likeDocKeyOne.toString(),likeDocKeyTwo.toString());
    }
    
    /*
     * Tests hashCode()
     */
    public void hashCodeTest() {
        //test default document key hashCode
        assertEquals(defaultDocKey.hashCode(),defaultDocKey.hashCode());
        //NOTE: default constructor not written
        
        //test unique document key hashCode
        //test if the same class will repeatedly hash to the same value
        assertEquals(uniqueDocKeyOne.hashCode(),uniqueDocKeyOne.hashCode());
        assertEquals(uniqueDocKeyTwo.hashCode(),uniqueDocKeyTwo.hashCode());
        //test if different classes will hash to different values
        assertFalse(uniqueDocKeyOne.hashCode() == uniqueDocKeyTwo.hashCode());
        assertFalse(uniqueDocKeyTwo.hashCode() == uniqueDocKeyOne.hashCode());
        
        //test like document key hashcode
        //test if the same class will repeately hash to the same value
        assertEquals(likeDocKeyOne.hashCode(),likeDocKeyOne.hashCode());
        assertEquals(likeDocKeyTwo.hashCode(),likeDocKeyTwo.hashCode());
        //test if similar classes will hash to the same value
        assertEquals(likeDocKeyOne.hashCode(),likeDocKeyTwo.hashCode());
        assertEquals(likeDocKeyTwo.hashCode(),likeDocKeyOne.hashCode());
    }
    
    /*
     * Tests clone()
     */
    public void cloneTest() {
        //Clone Variable Set Up
        try {
            DocumentKey defaultClone = (DocumentKey) defaultDocKey.clone();
            //test default document key clone
            assertEquals(defaultClone.getName(), defaultDocKey.getName());
            assertEquals(defaultClone.getPass(), defaultDocKey.getPass());
            assertTrue(defaultClone.equals(defaultDocKey));
            assertTrue(defaultDocKey.equals(defaultClone));
        } catch (CloneNotSupportedException e) {
            System.out.println("defaultClone Not Supported");
        }
        try {
            DocumentKey uniqueCloneOne = (DocumentKey)uniqueDocKeyOne.clone();
            //test unique document key clone
            assertEquals(uniqueCloneOne.getName(),uniqueDocKeyOne.getName());
            assertEquals(uniqueCloneOne.getPass(),uniqueDocKeyOne.getPass());
            assertTrue(uniqueCloneOne.equals(uniqueDocKeyOne));
            assertTrue(uniqueDocKeyOne.equals(uniqueCloneOne));
        } catch (CloneNotSupportedException e) {
            System.out.println("uniqueCloneOne Not Supported");
        }
        try {
            DocumentKey uniqueCloneTwo = (DocumentKey)uniqueDocKeyTwo.clone();
            //test unique document key clone
            assertEquals(uniqueCloneTwo.getName(),uniqueDocKeyTwo.getName());
            assertEquals(uniqueCloneTwo.getPass(),uniqueDocKeyTwo.getPass());
            assertTrue(uniqueCloneTwo.equals(uniqueDocKeyTwo));
            assertTrue(uniqueDocKeyTwo.equals(uniqueCloneTwo));
        } catch (CloneNotSupportedException e) {
            System.out.println("uniqueCloneTwo Not Supported");
        }
        try {
            DocumentKey likeCloneOne = (DocumentKey)likeDocKeyOne.clone();
            //test like document key clone
            assertEquals(likeCloneOne.getName(),likeDocKeyOne.getName());
            assertEquals(likeCloneOne.getPass(),likeDocKeyOne.getPass());
            assertTrue(likeCloneOne.equals(likeDocKeyOne));
            assertTrue(likeDocKeyOne.equals(likeCloneOne));
        } catch (CloneNotSupportedException e) {
            System.out.println("likeCloneOne Not Supported");
        }
        try {
            DocumentKey likeCloneTwo = (DocumentKey)likeDocKeyTwo.clone();
            //test like document key clone
            assertEquals(likeCloneTwo.getName(),likeDocKeyTwo.getName());
            assertEquals(likeCloneTwo.getPass(),likeDocKeyTwo.getPass());
            assertTrue(likeCloneTwo.equals(likeDocKeyTwo));
            assertTrue(likeDocKeyTwo.equals(likeCloneTwo));
        } catch (CloneNotSupportedException e) {
            System.out.println("likeCloneTwo Not Supported");
        }
    }
    
    /*
     * Tests equals()
     */
    public void equalsTest() {
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