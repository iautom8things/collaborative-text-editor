package test;

import handler.TextPosition;
import handler.Document;
import handler.OutOfBoundsException;
import junit.framework.TestCase;
import static java.lang.System.out;
import java.lang.Exception;

public class DocumentTester extends TestCase {
    private Document _doc;
    private String _initialStr;
    private String _foobar;
    private String _addthis;
    private TextPosition _tp0;
    private TextPosition _tp1;
    private TextPosition _tp5;
    private TextPosition _tp6;
    private TextPosition _tp10;
    private TextPosition _tp15;
    private TextPosition _tp20;

    public static void main(String[] args) {
        DocumentTester test = new DocumentTester();
        test.setUp();
        test.testDocumentConstructor();
        test.testInsertText();
        test.setUp();
        test.testDeleteText();
        test.setUp();
        test.testGetLastPosition();
    }    
    
    protected void setUp ( ) {
        _initialStr = "This is only a test!";
        _foobar = "foobar";
        _addthis = "addthis";
        _doc = new Document(_initialStr);
        try {
            _tp0 = new TextPosition();
            _tp1 = new TextPosition(1);
            _tp5 = new TextPosition(5);
            _tp6 = new TextPosition(6);
            _tp10 = new TextPosition(10);
            _tp15 = new TextPosition(15);
            _tp20 = new TextPosition(20);
        }
        catch (Exception e) { out.println(e.getMessage()); }
    }

    public void testDocumentConstructor ( ) {
        assertEquals(_doc.toString(), _initialStr);
        assertEquals(_doc.getLength(), _initialStr.length());
    }

    public void testInsertText ( ) {
        try {
            _doc.insertText(_tp0, _foobar);
            assertEquals(_doc.toString(), _foobar + _initialStr);
            _doc.insertText(_tp6, _addthis);
            assertEquals(_doc.toString(), _foobar + _addthis + _initialStr);
        }
        catch (OutOfBoundsException oobe) { out.println(oobe.getMessage()); }
    }

    public void testDeleteText ( ) {
        try {
            _doc.deleteText(_tp0, _tp0);
            assertEquals(_doc.toString(), _initialStr);
            _doc.deleteText(_tp0, _tp5);
            assertEquals(_doc.toString(), _initialStr.substring(5));
        }
        catch (OutOfBoundsException oobe) { out.println(oobe.getMessage()); }
    }

    public void testGetLastPosition ( ) {
        try {
            assertEquals(_doc.getLastPosition().getPosition(), _initialStr.length());
        }
        catch (OutOfBoundsException oobe) { out.println(oobe.getMessage()); }
    }
}
