package test;

import handler.TextPosition;
import handler.Document;
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
        _doc.insertText(_tp0, _foobar);
        assertEquals(_doc.toString(), _foobar + _initialStr);
        _doc.insertText(_tp6, _addthis);
        assertEquals(_doc.toString(), _foobar + _addthis + _initialStr);
    }

    public void testDeleteText ( ) {
        _doc.deleteText(_tp0, _tp0);
        assertEquals(_doc.toString(), _initialStr);
    }
}
