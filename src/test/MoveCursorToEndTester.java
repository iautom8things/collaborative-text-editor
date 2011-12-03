package test;

import commands.*;
import handler.Document;
import handler.DocumentController;
import handler.TextPosition;
import java.awt.Color;
import java.net.InetAddress;
import junit.framework.TestCase;
import user.CTEUser;
import user.CTEUserManager;

public class MoveCursorToEndTester extends TestCase {

    private Document _document;
    private CTEUser _user0;
    private DocumentController _controller;
    private CTEUserManager _userManager = new CTEUserManager();

    public static void main(String[] args) {
        MoveCursorToEndTester test = new MoveCursorToEndTester();
        test.setUp();
        test.test0();
        test.setUp();
        test.test1();
        test.setUp();
        test.test2();
        test.setUp();
        test.test3();
        test.setUp();
        test.test4();
        test.setUp();
        test.test5();
        test.setUp();
        test.test6();
    }
 
    protected void setUp() {
        try{
            _document = new Document("hello");
            Color inputColor0 = Color.black;
            String userID0 = "mlbrinle";
            InetAddress _IPAddress0 = null;
            _IPAddress0 = InetAddress.getLocalHost();      
            _user0 = new CTEUser(userID0, _IPAddress0, inputColor0);
            _userManager = new CTEUserManager();
            _userManager.addUser(_user0);
            _controller = new DocumentController(_userManager, _document);
        }
        catch(Exception e){e.printStackTrace();}
    }
    
    private void moveCursorThenTest(int position){
        try{
            //set the user position
            _user0.setPosition(new TextPosition(position));
            
            //run the move cursor to end command
            MoveCursorToEnd command0 = new MoveCursorToEnd(_user0);
            command0.execute(_controller);
            assertEquals(_user0.getPosition().getPosition(), 5);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Set position 0. Go to End.
    public void test0(){
        moveCursorThenTest(0);
    }
    
    //Set position to 1. Go to End.
    public void test1(){
        moveCursorThenTest(1);
    }
    
    //Set position to 2. Go to End.
    public void test2(){
        moveCursorThenTest(2);
    }
    
    //Set position to 3. Go to End.
    public void test3(){
        moveCursorThenTest(3);
    }
    
    //Set position to 4. Go to End.
    public void test4(){
        moveCursorThenTest(4);
    }
    
    //Set position to 5. Go to End.
    public void test5(){
        moveCursorThenTest(5);
    }
    
    //Set position to 6. Go to End.
    public void test6(){
        moveCursorThenTest(6);
    }
}