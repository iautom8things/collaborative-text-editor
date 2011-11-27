package test;

import handler.OutOfBoundsException;
import user.*;
import junit.framework.TestCase;
import static java.lang.System.out;
import java.net.*;
import java.awt.Color;
import handler.*;

public class TextPositionTester extends TestCase {

    private TextPosition _textPosition;
    private TextPosition _textPosition0;
    private TextPosition _textPosition10;
    private TextPosition _textPosition20;
    private TextPosition _textPosition30;
    private TextPosition _textPosition50;
    private TextPosition _textPosition1000;
    private TextPosition _textPosition2000;

    public void setUp ( ) {
        _textPosition = new TextPosition();
        try {
            _textPosition0 = new TextPosition(0);
            _textPosition10 = new TextPosition(10);
            _textPosition20 = new TextPosition(20);
            _textPosition30 = new TextPosition(30);
            _textPosition50 = new TextPosition(50);
            _textPosition1000 = new TextPosition(1000);
            _textPosition2000 = new TextPosition(2000);
        }
        catch (OutOfBoundsException oobe) { assertFalse(true); } // This test should fail if exception thrown.
    }

    public void testTextPositionConstructor ( ) {
        _textPosition = new TextPosition();
        try {
            _textPosition0 = new TextPosition(0);
            _textPosition10 = new TextPosition(10);
            _textPosition20 = new TextPosition(20);
            _textPosition30 = new TextPosition(30);
            _textPosition50 = new TextPosition(50);
            _textPosition1000 = new TextPosition(1000);
            _textPosition2000 = new TextPosition(2000);
        }
        catch (OutOfBoundsException oobe) { assertFalse(true); } // This test should fail if exception thrown.

        // Test if a negative initial position properly throws an exception.
        boolean expectedException = false;
        try { TextPosition negativePosition = new TextPosition(-1); }
        catch (OutOfBoundsException oobe) { expectedException = true; }
        assertTrue(expectedException);

        assertEquals(_textPosition.getPosition(), 0);
        assertEquals(_textPosition0.getPosition(), 0);
        assertEquals(_textPosition10.getPosition(), 10);
        assertEquals(_textPosition20.getPosition(), 20);
        assertEquals(_textPosition30.getPosition(), 30);
        assertEquals(_textPosition50.getPosition(), 50);
        assertEquals(_textPosition1000.getPosition(), 1000);
        assertEquals(_textPosition2000.getPosition(), 2000);

    }
    public void testIncrement ( ) {
        try {
            _textPosition0.increment();
            _textPosition10.increment();
            _textPosition20.increment();
            _textPosition30.increment();
            _textPosition50.increment();
            _textPosition1000.increment();
            _textPosition2000.increment();
        }
        catch (OutOfBoundsException oobe) { assertFalse(true); } // This test should fail if an exception is thrown.
        assertEquals(_textPosition0.getPosition(), 1);
        assertEquals(_textPosition10.getPosition(), 11);
        assertEquals(_textPosition20.getPosition(), 21);
        assertEquals(_textPosition30.getPosition(), 31);
        assertEquals(_textPosition50.getPosition(), 51);
        assertEquals(_textPosition1000.getPosition(), 1001);
        assertEquals(_textPosition2000.getPosition(), 2001);

        // Test if incrementing past the MAXPOSITION will properly throw an
        // exception.
        boolean expectedException = false;
        try {
            _textPosition.setPosition(TextPosition.MAXPOSITION);
            _textPosition.increment();
        }
        catch (OutOfBoundsException oobe) { expectedException = true; }
        assertTrue(expectedException);
    }

    public void testIncrementBy ( ) {
        try {
            _textPosition10.incrementBy(10);
            assertEquals(_textPosition10, _textPosition20);
            _textPosition10.incrementBy(0);
            assertEquals(_textPosition10.getPosition(), 20);
        }
        catch (OutOfBoundsException oobe) { assertFalse(true); } // This test should fail if an exception is thrown.

        // Test if incrementing past the MAXPOSITION will properly throw an
        // exception.
        boolean expectedException = false;
        try { _textPosition1000.incrementBy(TextPosition.MAXPOSITION); }
        catch (OutOfBoundsException oobe) { expectedException = true; }
        assertTrue(expectedException);
    }

    public void testDecrement ( ) {
        try {
            _textPosition10.decrement();
            _textPosition20.decrement();
            _textPosition30.decrement();
            _textPosition50.decrement();
            _textPosition1000.decrement();
            _textPosition2000.decrement();
        }
        catch (OutOfBoundsException oobe) { assertFalse(true); } // This test should fail if an exception is thrown.

        assertEquals(_textPosition10.getPosition(), 9);
        assertEquals(_textPosition20.getPosition(), 19);
        assertEquals(_textPosition30.getPosition(), 29);
        assertEquals(_textPosition50.getPosition(), 49);
        assertEquals(_textPosition1000.getPosition(), 999);
        assertEquals(_textPosition2000.getPosition(), 1999);

        // Test if decrementing past the MINPOSITION will properly throw an
        // exception.
        boolean expectedException = false;
        try {
            _textPosition.setPosition(TextPosition.MINPOSITION);
            _textPosition.decrement();
        }
        catch (OutOfBoundsException oobe) { expectedException = true; }
        assertTrue(expectedException);
    }

    public void testDecrementBy ( ) {
        try {
            _textPosition10.decrementBy(10);
            assertEquals(_textPosition10.getPosition(), 0);
            _textPosition1000.decrementBy(990);
            assertEquals(_textPosition1000.getPosition(), 10);
            _textPosition50.decrementBy(0);
            assertEquals(_textPosition50.getPosition(), 50);
        }
        catch (OutOfBoundsException oobe) { assertFalse(true); } // This test should fail if an exception is thrown.

        // Test if decrementing past the MINPOSITION will properly throw an
        // exception.
        boolean expectedException = false;
        try { _textPosition50.decrementBy(51); }
        catch (OutOfBoundsException oobe) { expectedException = true; }
        assertTrue(expectedException);
    }

    public void testEquals ( ) {
        try {
            TextPosition _textPositionA = new TextPosition();
            TextPosition _textPositionB = new TextPosition();
            TextPosition _textPositionC = new TextPosition();

            assertTrue(_textPositionA.equals(_textPositionA));
            assertTrue(_textPositionA.equals(_textPositionB));
            assertTrue(_textPositionB.equals(_textPositionA));
            assertTrue(_textPositionB.equals(_textPositionC));
            assertTrue(_textPositionA.equals(_textPositionC));
            _textPositionB.increment();
            assertFalse(_textPositionA.equals(_textPositionB));
        }
        catch (OutOfBoundsException oobe) { assertFalse(true); } // This test should fail if an exception is thrown.
    }

    public void testCompareTo ( ) {
        assertEquals(_textPosition0.compareTo(_textPosition10), -10);
        assertEquals(_textPosition10.compareTo(_textPosition0), 10);
        assertEquals(_textPosition2000.compareTo(_textPosition2000), 0);
        assertEquals(_textPosition2000.compareTo(_textPosition50), 1950);
        assertEquals(_textPosition50.compareTo(_textPosition30), 20);
        assertEquals(_textPosition2000.compareTo(_textPosition30), 1970);
    }

    public void testIsBeyond ( ) {
        assertTrue(_textPosition2000.isBeyond(_textPosition50));
        assertTrue(_textPosition50.isBeyond(_textPosition30));
        assertTrue(_textPosition2000.isBeyond(_textPosition30));
        assertFalse(_textPosition50.isBeyond(_textPosition2000));
        assertFalse(_textPosition30.isBeyond(_textPosition50));
    }

}
