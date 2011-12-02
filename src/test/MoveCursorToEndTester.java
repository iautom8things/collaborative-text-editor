package test;

import handler.Document;
import java.awt.Color;
import java.net.InetAddress;
import junit.framework.TestCase;
import user.CTEUser;

public class MoveCursorPositionCommandTester extends TestCase {

    private Document _document;
    private CTEUser _testUser0;

    public static void main(String[] args) {
        MoveCursorPositionCommandTester test = new MoveCursorPositionCommandTester();
        test.setUp();
    }

 
    protected void setUp() {
        try{
            _document = new Document("hello");
            Color inputColor0 = Color.black;
            String userID0 = "mlbrinle";
            InetAddress _IPAddress0 = null;
            _IPAddress0 = InetAddress.getLocalHost();      
            CTEUser _testUser0 = new CTEUser(userID0, _IPAddress0, inputColor0);
        }
        catch(Exception e){e.printStackTrace();}
    }
}
