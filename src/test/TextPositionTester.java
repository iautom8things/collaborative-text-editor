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
        assertEquals(_textPositionA.equals(_textPositionA), true);
        assertEquals(_textPositionA.equals(_textPositionB), true);
        assertEquals(_textPositionB.equals(_textPositionA), true);
        _textPositionB.increment();
        assertEquals(_textPositionA.equals(_textPositionB), false);
        }
        catch (OutOfBoundsException oobe) {
            out.println(oobe.getMessage());
        }
    }

}
