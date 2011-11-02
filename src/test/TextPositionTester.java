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

    public static void main ( String[] args ) {
        out.println("Begin Main");
        TextPositionTester test = new TextPositionTester();
        test.setUp();
        test.testIncrement();
        test.testDecrement();
        test.testEquals();
    }

    public void setUp ( ) {
        _textPosition = new TextPosition();
    }

    public void testIncrement ( ) {
        try {
            _textPosition.setPosition(_textPosition.MAXPOSITION);
            _textPosition.increment();
        }
        catch (OutOfBoundsException oobe) {
            out.println(oobe.getMessage());
        }
    }

    public void testDecrement ( ) {
        try {
            _textPosition.setPosition(_textPosition.MINPOSITION);
            _textPosition.decrement();
        }
        catch (OutOfBoundsException oobe) {
            out.println(oobe.getMessage());
        }
    }

    public void testEquals ( ) {
        try {
            TextPosition _textPositionA = new TextPosition();
            TextPosition _textPositionB = new TextPosition();
            TextPosition _textPositionC = new TextPosition();
            assertEquals(_textPositionA.equals(_textPositionA), true);
            assertEquals(_textPositionA.equals(_textPositionB), true);
            assertEquals(_textPositionB.equals(_textPositionA), true);
            assertEquals(_textPositionB.equals(_textPositionC), true);
            assertEquals(_textPositionA.equals(_textPositionC), true);
            _textPositionB.increment();
            assertEquals(_textPositionA.equals(_textPositionB), false);
        }
        catch (OutOfBoundsException oobe) {
            out.println(oobe.getMessage());
        }
    }

    public void testCompareTo ( ) {
        try {
            TextPosition _textPosition0 = new TextPosition();
            TextPosition _textPosition10 = new TextPosition(10);
            TextPosition _textPosition30 = new TextPosition(30);
            TextPosition _textPosition50 = new TextPosition(50);
            TextPosition _textPosition2000 = new TextPosition(2000);
            assertEquals(_textPosition0.compareTo(_textPosition10), -10);
            assertEquals(_textPosition10.compareTo(_textPosition0), 10);
            assertEquals(_textPosition2000.compareTo(_textPosition2000), 0);
            assertEquals(_textPosition2000.compareTo(_textPosition50), 1950);
            assertEquals(_textPosition50.compareTo(_textPosition30), 20);
            assertEquals(_textPosition2000.compareTo(_textPosition30), 1970);
        }
        catch (OutOfBoundsException oobe) {
            out.println(oobe.getMessage());
        }
    }

    public void testIsBeyond ( ) {
        try {
            TextPosition _textPosition30 = new TextPosition(30);
            TextPosition _textPosition50 = new TextPosition(50);
            TextPosition _textPosition0 = new TextPosition();
            TextPosition _textPosition2000 = new TextPosition(2000);
            assertTrue(_textPosition2000.isBeyond(_textPosition50));
            assertTrue(_textPosition50.isBeyond(_textPosition30));
            assertTrue(_textPosition2000.isBeyond(_textPosition30));
            assertFalse(_textPosition50.isBeyond(_textPosition2000));
            assertFalse(_textPosition30.isBeyond(_textPosition50));
        }
        catch (OutOfBoundsException oobe) {
            out.println(oobe.getMessage());
        }
    }

}
