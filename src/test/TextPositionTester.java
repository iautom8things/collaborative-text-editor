package test;

import handler.OutOfBoundsException;
import user.*;
import junit.framework.TestCase;
import static java.lang.System.out;
import java.net.*;
import java.awt.Color;
import handler.*;

public class TextPositionTester extends TestCase {

    private TextPosition _tp;
    private TextPosition _tp0;
    private TextPosition _tp10;
    private TextPosition _tp20;
    private TextPosition _tp30;
    private TextPosition _tp50;
    private TextPosition _tp1000;
    private TextPosition _tp2000;
    private TextPosition _tp9;
    private TextPosition _tp11;
    private TextPosition _tp19;
    private TextPosition _tp21;

    public void setUp ( ) {
        _tp = new TextPosition();
        try {
            _tp0 = new TextPosition(0);
            _tp10 = new TextPosition(10);
            _tp20 = new TextPosition(20);
            _tp30 = new TextPosition(30);
            _tp50 = new TextPosition(50);
            _tp1000 = new TextPosition(1000);
            _tp2000 = new TextPosition(2000);
            _tp9 = new TextPosition(9);
            _tp11 = new TextPosition(11);
            _tp19 = new TextPosition(19);
            _tp21 = new TextPosition(21);
        }
        catch (OutOfBoundsException oobe) { fail(oobe.getMessage()); } // This test should fail if exception thrown.
    }

    public void testTextPositionConstructor ( ) {
        _tp = new TextPosition();
        try {
            _tp0 = new TextPosition(0);
            _tp10 = new TextPosition(10);
            _tp20 = new TextPosition(20);
            _tp30 = new TextPosition(30);
            _tp50 = new TextPosition(50);
            _tp1000 = new TextPosition(1000);
            _tp2000 = new TextPosition(2000);
            _tp9 = new TextPosition(9);
            _tp11 = new TextPosition(11);
            _tp19 = new TextPosition(19);
            _tp21 = new TextPosition(21);
        }
        catch (OutOfBoundsException oobe) { fail(oobe.getMessage()); } // This test should fail if exception thrown.

        // Test if a negative initial position properly throws an exception.
        boolean expectedException = false;
        try { TextPosition negativePosition = new TextPosition(-1); }
        catch (OutOfBoundsException oobe) { expectedException = true; }
        assertTrue(expectedException);

        assertEquals(_tp.getPosition(), 0);
        assertEquals(_tp0.getPosition(), 0);
        assertEquals(_tp10.getPosition(), 10);
        assertEquals(_tp20.getPosition(), 20);
        assertEquals(_tp30.getPosition(), 30);
        assertEquals(_tp50.getPosition(), 50);
        assertEquals(_tp1000.getPosition(), 1000);
        assertEquals(_tp2000.getPosition(), 2000);

    }
    public void testIncrement ( ) {
        try {
            _tp0.increment();
            _tp10.increment();
            _tp20.increment();
            _tp30.increment();
            _tp50.increment();
            _tp1000.increment();
            _tp2000.increment();
        }
        catch (OutOfBoundsException oobe) { fail(oobe.getMessage()); } // This test should fail if an exception is thrown.
        assertEquals(_tp0.getPosition(), 1);
        assertEquals(_tp10.getPosition(), 11);
        assertEquals(_tp20.getPosition(), 21);
        assertEquals(_tp30.getPosition(), 31);
        assertEquals(_tp50.getPosition(), 51);
        assertEquals(_tp1000.getPosition(), 1001);
        assertEquals(_tp2000.getPosition(), 2001);

        // Test if incrementing past the MAXPOSITION will properly throw an
        // exception.
        boolean expectedException = false;
        try {
            _tp.setPosition(TextPosition.MAXPOSITION);
            _tp.increment();
        }
        catch (OutOfBoundsException oobe) { expectedException = true; }
        assertTrue(expectedException);
    }

    public void testIncrementBy ( ) {
        try {
            _tp10.incrementBy(10);
            assertEquals(_tp10, _tp20);
            _tp10.incrementBy(0);
            assertEquals(_tp10.getPosition(), 20);
        }
        catch (OutOfBoundsException oobe) { assertFalse(true); } // This test should fail if an exception is thrown.

        // Test if incrementing past the MAXPOSITION will properly throw an
        // exception.
        boolean expectedException = false;
        try { _tp1000.incrementBy(TextPosition.MAXPOSITION); }
        catch (OutOfBoundsException oobe) { expectedException = true; }
        assertTrue(expectedException);
    }

    public void testDecrement ( ) {
        try {
            _tp10.decrement();
            _tp20.decrement();
            _tp30.decrement();
            _tp50.decrement();
            _tp1000.decrement();
            _tp2000.decrement();
        }
        catch (OutOfBoundsException oobe) { fail(oobe.getMessage()); } // This test should fail if an exception is thrown.

        assertEquals(_tp10.getPosition(), 9);
        assertEquals(_tp20.getPosition(), 19);
        assertEquals(_tp30.getPosition(), 29);
        assertEquals(_tp50.getPosition(), 49);
        assertEquals(_tp1000.getPosition(), 999);
        assertEquals(_tp2000.getPosition(), 1999);

        // Test if decrementing past the MINPOSITION will properly throw an
        // exception.
        boolean expectedException = false;
        try {
            _tp.setPosition(TextPosition.MINPOSITION);
            _tp.decrement();
        }
        catch (OutOfBoundsException oobe) { expectedException = true; }
        assertTrue(expectedException);
    }

    public void testDecrementBy ( ) {
        try {
            _tp10.decrementBy(10);
            assertEquals(_tp10.getPosition(), 0);
            _tp1000.decrementBy(990);
            assertEquals(_tp1000.getPosition(), 10);
            _tp50.decrementBy(0);
            assertEquals(_tp50.getPosition(), 50);
        }
        catch (OutOfBoundsException oobe) { fail(oobe.getMessage()); } // This test should fail if an exception is thrown.

        // Test if decrementing past the MINPOSITION will properly throw an
        // exception.
        boolean expectedException = false;
        try { _tp50.decrementBy(51); }
        catch (OutOfBoundsException oobe) { expectedException = true; }
        assertTrue(expectedException);
    }

    public void testEquals ( ) {
        try {
            TextPosition tpA = new TextPosition();
            TextPosition tpB = new TextPosition();
            TextPosition tpC = new TextPosition();

            assertTrue(tpA.equals(tpA));
            assertTrue(tpA.equals(tpB));
            assertTrue(tpB.equals(tpA));
            assertTrue(tpB.equals(tpC));
            assertTrue(tpA.equals(tpC));
            tpB.increment();
            assertFalse(tpA.equals(tpB));
        }
        catch (OutOfBoundsException oobe) { fail(oobe.getMessage()); } // This test should fail if an exception is thrown.
    }

    public void testCompareTo ( ) {
        assertEquals(_tp0.compareTo(_tp10), -10);
        assertEquals(_tp10.compareTo(_tp0), 10);
        assertEquals(_tp2000.compareTo(_tp2000), 0);
        assertEquals(_tp2000.compareTo(_tp50), 1950);
        assertEquals(_tp50.compareTo(_tp30), 20);
        assertEquals(_tp2000.compareTo(_tp30), 1970);
    }

    public void testIsBeyond ( ) {
        assertTrue(_tp2000.isBeyond(_tp50));
        assertTrue(_tp50.isBeyond(_tp30));
        assertTrue(_tp2000.isBeyond(_tp30));
        assertFalse(_tp50.isBeyond(_tp2000));
        assertFalse(_tp30.isBeyond(_tp50));

        assertTrue(isBetween(_tp11, _tp10, _tp20));
        assertTrue(isBetween(_tp19, _tp10, _tp20));
        assertFalse(isBetween(_tp10, _tp10, _tp20));
        assertFalse(isBetween(_tp9, _tp10, _tp20));
        assertFalse(isBetween(_tp20, _tp10, _tp20));
        assertFalse(isBetween(_tp21, _tp10, _tp20));
    }

    /**
     * This is an extension of testing CTEUserManager's updateBetween(
     * TextPosition front, TextPosition, back).
     */
    private boolean isBetween( TextPosition tpToCheck, TextPosition front, TextPosition back ) { return (tpToCheck.isBeyond(front) && back.isBeyond(tpToCheck)); }

}
