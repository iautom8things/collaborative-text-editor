import junit.framework.TestCase;
import java.lang.Thread;
import java.lang.Runnable;
import java.lang.InterruptedException;
import static java.lang.System.out;

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
    private StringHandler _sh;
    private String _initialStr = "This is only a test";
    private int _initialSize = 2;

    protected void setUp ( ) {
        _sh = new StringHandler(_initialStr, _initialSize);
    }

    public void testStringHandlerConstructor ( ) {
        String testStr = "42";
        int testNumWriters = 42;

        _sh = new StringHandler(testStr, testNumWriters);
        assertEquals(_sh.toString(), testStr);
        assertEquals(_sh.numWriters(), testNumWriters);
        assertEquals(_sh.getLength(), testStr.length());
        for (int i = 0; i < testNumWriters; i++) {
            assertEquals(_sh.getIndex(i), 0);
        }
    }

    public void testMoveCursorTo ( ) {
        assertEquals(_sh.getIndex(0), 0);    // "^|This is only a test"
        _sh.moveCursorTo(0,10);
        assertEquals(_sh.getIndex(0), 10);   // "|This is on^ly a test"
        _sh.moveCursorTo(0, 0);
        assertEquals(_sh.getIndex(0), 0);    // "^|This is only a test"
        _sh.moveCursorTo(1, 6);
        _sh.moveCursorTo(0, 3);
        assertEquals(_sh.getIndex(1), 6);
        assertEquals(_sh.getIndex(0), 3);    // "Thi^s is| only a test"
    }

    public void testInsert ( ) {
        String a = "arg!";
        assertEquals(_sh.toString(), _initialStr);
        assertEquals(_sh.getIndex(0), 0);    // "^\This is only a t)st"
        _sh.insert(0, a);                    // "arg!^|This is only a test"
        assertEquals(_sh.toString(), "arg!This is only a test");
        assertEquals(_sh.getIndex(0), a.length());
        _sh.moveCursorTo(0, 10);             // "arg!|This i^s only a test"
        assertEquals(_sh.getIndex(0), 10);
        _sh.insert(1, "><");                 // "arg!><|This i^s only a test"
        assertEquals(_sh.toString(), "arg!><This is only a test");
        assertEquals(_sh.getIndex(0), 12);
        assertEquals(_sh.getIndex(1), 6);
        _sh.insert(0, "%*%");                // "arg!><|This i%*%^s only a test"
        assertEquals(_sh.toString(), "arg!><This i%*%s only a test");
        assertEquals(_sh.getIndex(0), 15);
        assertEquals(_sh.getIndex(1), 6);
    }

    public void testInsertConcurrency ( ) {
        String longStr = "This is going to be a really really really really really really really really really really really really really really long string to try to test whether or not the second, much shorter string is able to RACE, and beat, this thread to insertion.  If it is able to beat it (where it inserts first) then, heyyyoooo!  We have a problem!!  But it shouldn't happen...even though it executes only a thousandth of a second later and only has 1 character when this has like a bajillion.  WTF?  Why are you still reading this?  Why am I still typing this?  I should have just used a much of lorim ipsum (sp?) stuff instead of this.  Good thing I'm watching Dexter.  Okay, that should be good.  I like turtles.";
        // Come to think of it, the length of the string probably doesn't help
        // lengthen the operation of the first instantiated thread, since
        // we're dealing with references.  However, concatenating the string
        // several times should definitely cause for a little delay.
        // It would be an interesting test to see if removing the
        // synchronization keywords from the StringHandler class and see if it
        // does in fact mess up.
        Thread[] threads = new Thread[2];
        threads[0] = new Thread(new ConcurrencyInsertTester(1000, longStr+longStr+longStr+longStr, 0));
        threads[1] = new Thread(new ConcurrencyInsertTester(1001, "?", 1));
        for (Thread t: threads) { t.start(); }
        for (Thread t: threads) {
            try { t.join(); }
            catch ( InterruptedException e ) { out.println(e); }
        }
        out.println(_sh.toString());
        assertEquals(_sh.toString(), longStr+longStr+longStr+longStr+"?"+_initialStr);
    }


    private class ConcurrencyInsertTester implements Runnable {
        private long _delay;
        private String _text;
        private int _id;

        public ConcurrencyInsertTester ( long delay, String text, int writerID ) {
            _delay = delay;
            _text = text;
            _id = writerID;
        }
        public void run ( ) {
            try { Thread.sleep(_delay); }
            catch ( InterruptedException e ) { out.println(e.toString()); }
            _sh.insert(_id, _text);
        }
    }
}
