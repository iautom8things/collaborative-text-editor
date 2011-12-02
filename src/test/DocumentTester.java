package test;

import handler.TextPosition;
import handler.Document;
import handler.OutOfBoundsException;
import junit.framework.TestCase;
import static java.lang.System.out;
import java.lang.Exception;

public class DocumentTester extends TestCase {
    private Document _doc;
    private static final String _initialStr = "This is only a test";
    private static final String _foobarStr = "foobar";
    private static final String _addthisStr = "addthis";
    private static final String _emptyStr = "";
    private static final String _newLine = "\n";
    private TextPosition _tp0;
    private TextPosition _tp1;
    private TextPosition _tp5;
    private TextPosition _tp6;
    private TextPosition _tp7;
    private TextPosition _tp10;
    private TextPosition _tp15;
    private TextPosition _tp20;

    @Override
    public void setUp ( ) {
        _doc = new Document(_initialStr);
        try {
            _tp0 = new TextPosition();
            _tp1 = new TextPosition(1);
            _tp5 = new TextPosition(5);
            _tp6 = new TextPosition(6);
            _tp7 = new TextPosition(7);
            _tp20 = new TextPosition(20);
        }
        catch (OutOfBoundsException oobe) { fail(oobe.getMessage()); }
    }

    public void testDocumentConstructor ( ) {
        try {
            // Test Default Constructor
            _doc = new Document();
            assertEquals(0, _doc.getLength());
            assertEquals(_emptyStr, _doc.contents());
            assertEquals(0, _doc.getLastPosition().getPosition());

            // Test initialized Constructor with Empty String
            _doc = new Document(_emptyStr);
            assertEquals(0, _doc.getLength());
            assertEquals(_emptyStr, _doc.contents());
            assertEquals(0, _doc.getLastPosition().getPosition());

            // Test initialized Constructor
            _doc = new Document(_initialStr);
            assertEquals(_initialStr, _doc.contents());
            assertEquals(_initialStr.length(), _doc.getLength());
            assertEquals(_initialStr.length(), _doc.getLastPosition().getPosition());
        }
        catch (OutOfBoundsException oobe) { fail(oobe.getMessage()); }
    }
    public void testGetLength ( ) {
        // Test initialized Document
        assertEquals(_initialStr.length(), _doc.getLength());
        // Test Empty Document
        _doc = new Document();
        assertEquals(0, _doc.getLength());
        try {
            // Test Insert text
            _doc.insertText(_tp0, _addthisStr);
            assertEquals(_addthisStr.length(), _doc.getLength());
            // Test Insert Empty String
            _doc.insertText(_tp0, _emptyStr);
            assertEquals(_addthisStr.length(), _doc.getLength());
            // Test Delete Nothing
            _doc.deleteText(_tp0, _tp0);
            assertEquals(_addthisStr.length(), _doc.getLength());
        }
        catch (OutOfBoundsException oobe) { fail(oobe.getMessage()); }
    }

    public void testGetLastPosition ( ) {
        try {
            // Test Initialized Document
            assertEquals(_initialStr.length(), _doc.getLastPosition().getPosition());

            // Test empty Document
            _doc = new Document();
            assertEquals(0, _doc.getLastPosition().getPosition());
            _doc.insertText(_tp0, _emptyStr);
            assertEquals(0, _doc.getLastPosition().getPosition());
            _doc.deleteText(_tp0, _tp0);
            assertEquals(0, _doc.getLastPosition().getPosition());

        }
        catch (OutOfBoundsException oobe) { fail(oobe.getMessage()); }
    }

    public void testInsertText ( ) {
            TextPosition endTP;
        try {
            String expectedContents;

            // Test initialized Document
            expectedContents = _initialStr;

            assertEquals(expectedContents, _doc.contents());
            assertEquals(expectedContents.length(), _doc.getLength());
            assertEquals(expectedContents.length(), _doc.getLastPosition().getPosition());

            // Test inserting empty text at the beginning
            _doc.insertText(_tp0, _emptyStr);

            assertEquals(expectedContents, _doc.contents());
            assertEquals(expectedContents.length(), _doc.getLength());
            assertEquals(expectedContents.length(), _doc.getLastPosition().getPosition());

            // Test inserting empty text in the middle of the Document
            _doc.insertText(_tp5, _emptyStr);

            assertEquals(expectedContents, _doc.contents());
            assertEquals(expectedContents.length(), _doc.getLength());
            assertEquals(expectedContents.length(), _doc.getLastPosition().getPosition());

            // Test inserting empty text at the end of the Document
            endTP = _doc.getLastPosition();
            _doc.insertText(endTP, _emptyStr);

            assertEquals(expectedContents, _doc.contents());
            assertEquals(expectedContents.length(), _doc.getLength());
            assertEquals(expectedContents.length(), _doc.getLastPosition().getPosition());

            // Test inserting text at beginning
            expectedContents = _foobarStr + expectedContents;
            _doc.insertText(_tp0, _foobarStr);

            assertEquals(expectedContents, _doc.contents());
            assertEquals(expectedContents.length(), _doc.getLength());
            assertEquals(expectedContents.length(), _doc.getLastPosition().getPosition());

            // Test inserting new line character "\n" at beginning
            expectedContents = _newLine + expectedContents;
            _doc.insertText(_tp0, _newLine);

            assertEquals(expectedContents, _doc.contents());
            assertEquals(expectedContents.length(), _doc.getLength());
            assertEquals(expectedContents.length(), _doc.getLastPosition().getPosition());

            // Test inserting in the middle of the document
            expectedContents = _newLine + _foobarStr + _addthisStr + _initialStr;
            _doc.insertText(_tp7, _addthisStr);

            assertEquals(expectedContents, _doc.contents());
            assertEquals(expectedContents.length(), _doc.getLength());
            assertEquals(expectedContents.length(), _doc.getLastPosition().getPosition());

            // Test inserting new line character "\n" in the middle of the document
            expectedContents = _newLine + _foobarStr + _newLine + _addthisStr + _initialStr;
            _doc.insertText(_tp7, _newLine);

            assertEquals(expectedContents, _doc.contents());
            assertEquals(expectedContents.length(), _doc.getLength());
            assertEquals(expectedContents.length(), _doc.getLastPosition().getPosition());

            // Test inserting at the end of the document
            endTP = _doc.getLastPosition();
            _doc.insertText(endTP, _foobarStr);
            expectedContents = expectedContents + _foobarStr;

            assertEquals(expectedContents, _doc.contents());
            assertEquals(expectedContents.length(), _doc.getLength());
            assertEquals(expectedContents.length(), _doc.getLastPosition().getPosition());

            // Test inserting new line character, "\n" at end of the document
            endTP = _doc.getLastPosition();
            _doc.insertText(endTP, _newLine);
            expectedContents = expectedContents + _newLine;

            assertEquals(expectedContents, _doc.contents());
            assertEquals(expectedContents.length(), _doc.getLength());
            assertEquals(expectedContents.length(), _doc.getLastPosition().getPosition());
        }
        catch (OutOfBoundsException oobe) { fail(oobe.getMessage()); }

        // Test that exception is properly thrown for out of bounds insertion
        boolean expectedException = false;
        try {
            endTP = _doc.getLastPosition();
            // Increment Beyond End of document
            endTP.increment();
            _doc.insertText(endTP, _foobarStr);
        }
        catch (OutOfBoundsException oobe) { expectedException = true; }
        assertTrue(expectedException);
    }

    public void testDeleteText ( ) {
        String expectedContents;
        try {
            assertEquals(_doc.contents(), _initialStr);

            // Test deleting nothing
            _doc.deleteText(_tp0, _tp0);
            assertEquals(_initialStr, _doc.contents());
            assertEquals(_initialStr.length(), _doc.getLength());
            assertEquals(_initialStr.length(), _doc.getLastPosition().getPosition());

            // Test deleting beginning of Document
            _doc.deleteText(_tp0, _tp5);
            expectedContents = _initialStr.substring(5);
            assertEquals(expectedContents, _doc.contents());
            assertEquals(expectedContents.length(), _doc.getLength());
            assertEquals(expectedContents.length(), _doc.getLastPosition().getPosition());

            // Test deleting all of Document
            TextPosition endTP = _doc.getLastPosition();
            _doc.deleteText(_tp0, endTP);
            assertEquals(_emptyStr, _doc.contents());
            assertEquals(0, _doc.getLength());
            assertEquals(_tp0, _doc.getLastPosition());
        }
        catch (OutOfBoundsException oobe) { fail(oobe.getMessage()); }

        // Test that exception is properly thrown when TextPositions are
        // given out of order.
        boolean expectedException = false;
        try { _doc.deleteText(_tp1, _tp0); }
        catch (OutOfBoundsException oobe) { expectedException = true; }
        assertTrue(expectedException);

        // Test that deleting beyond the end of the Document properly
        // throws an exception.
        expectedException = false;
        try { _doc.deleteText(_tp0, _tp20); }
        catch (OutOfBoundsException oobe) { expectedException = true; }
        assertTrue(expectedException);
    }


    public void testClone ( ) {
    }
}
