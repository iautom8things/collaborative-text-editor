import junit.framework.TestCase;

/**
 * TODO
 *      - Test Concurrency of StringHandler
 *
 *  To help understand the tests below, I will use the following formats for
 *  my comments:
 *
 *  ^ indicates the cursor position of writer #0
 *  | indicates the cursor position of writer #1
 *
 *  When a StringHandler is instantiated, all cursors are positioned at the
 *  start of the String buffer.
 *
 */

public class StringHandlerTester extends TestCase {
    private StringHandler sh;
    private String initialStr = "This is only a test";
    private int initialSize = 2;

    protected void setUp ( ) {
        sh = new StringHandler(initialStr, initialSize);
    }

    public void testStringHandlerConstructor ( ) {
        String testStr = "42";
        int testNumWriters = 42;

        sh = new StringHandler(testStr, testNumWriters);
        assertEquals(sh.toString(), testStr);
        assertEquals(sh.numWriters(), testNumWriters);
        assertEquals(sh.getLength(), testStr.length());
        for (int i = 0; i < testNumWriters; i++) {
            assertEquals(sh.getIndex(i), 0);
        }
    }

    public void testMoveCursorTo ( ) {
        assertEquals(sh.getIndex(0), 0);    // "^|This is only a test"
        sh.moveCursorTo(0,10);
        assertEquals(sh.getIndex(0), 10);   // "|This is on^ly a test"
        sh.moveCursorTo(0, 0);
        assertEquals(sh.getIndex(0), 0);    // "^|This is only a test"
        sh.moveCursorTo(1, 6);
        sh.moveCursorTo(0, 3);
        assertEquals(sh.getIndex(1), 6);
        assertEquals(sh.getIndex(0), 3);    // "Thi^s is| only a test"
    }

    public void testInsert ( ) {
        String a = "arg!";
        assertEquals(sh.toString(), initialStr);
        assertEquals(sh.getIndex(0), 0);    // "^\This is only a test"
        sh.insert(0, a);                    // "arg!^|This is only a test"
        assertEquals(sh.toString(), "arg!This is only a test");
        assertEquals(sh.getIndex(0), a.length());
        sh.moveCursorTo(0, 10);             // "arg!|This i^s only a test"
        assertEquals(sh.getIndex(0), 10);
        sh.insert(1, "><");                 // "arg!><|This i^s only a test"
        assertEquals(sh.toString(), "arg!><This is only a test");
        assertEquals(sh.getIndex(0), 12);
        assertEquals(sh.getIndex(1), 6);
        sh.insert(0, "%*%");                // "arg!><|This i%*%^s only a test"
        assertEquals(sh.toString(), "arg!><This i%*%s only a test");
        assertEquals(sh.getIndex(0), 15);
        assertEquals(sh.getIndex(1), 6);
    }
}
