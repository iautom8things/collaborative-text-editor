package test;

import commands.*;
import handler.Document;
import handler.DocumentController;
import handler.OutOfBoundsException;
import handler.TextPosition;
import java.awt.Color;
import java.net.InetAddress;
import junit.framework.TestCase;
import user.CTEUser;
import user.CTEUserManager;

public class MoveCursorPositionCommandTester extends TestCase {

    private Document _document;
    private CTEUser _user0; //user who's starting position is 0
    private DocumentController _controller;
    private CTEUserManager _userManager = new CTEUserManager();

    public static void main(String[] args) {
        MoveCursorPositionCommandTester test = new MoveCursorPositionCommandTester();
        test.setUp();     
        test.testMove0From0();
        test.setUp();
        test.testMove1From0();
        test.setUp();
        test.testMove3From0();  
        test.setUp();
        test.testMove5From0();
        test.setUp();
        test.testMove6From0();   
        test.setUp();
        test.testBack1From0();  
        test.setUp();
        test.testBack1From4();
        test.setUp();
        test.testBack6From4();
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
    
    public void testMove0From0 () {
        try{
            MoveCursorPositionCommand command0 = new MoveCursorPositionCommand(_user0, 0);
            command0.execute(_controller);
            _user0.getPosition();
            assertEquals(_user0.getPosition().getPosition(), 0);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void testMove1From0() {
        try {
            MoveCursorPositionCommand command0 = new MoveCursorPositionCommand(_user0, 1);
            command0.execute(_controller);
            assertEquals(_user0.getPosition().getPosition(), 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testMove3From0() {
        try {
            MoveCursorPositionCommand command0 = new MoveCursorPositionCommand(_user0, 3);
            System.out.println("doc size" + _document.getLastPosition());
            System.out.println("current position: " + _user0.getPosition());
            command0.execute(_controller);
            assertEquals(_user0.getPosition().getPosition(), 3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    
    public void testMove5From0() {
        try {
            MoveCursorPositionCommand command0 = new MoveCursorPositionCommand(_user0, 5);
            command0.execute(_controller);
            assertEquals(_user0.getPosition().getPosition(), 5);            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testMove6From0() {//Doc Size: 5 Positions: 0 Move: 6
        try {
            MoveCursorPositionCommand command0 = new MoveCursorPositionCommand(_user0, 6);
            command0.execute(_controller);
            assertEquals(_user0.getPosition().getPosition(), 6);
        } catch (OutOfBoundsException oobe) {
            String message = "Out of Bounds Exception: Position is beyond end of document.";
            assertEquals(message, oobe.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testBack1From0() {
        try {
            MoveCursorPositionCommand command0 = new MoveCursorPositionCommand(_user0, -1);
            command0.execute(_controller);
            _user0.getPosition();
            assertEquals(_user0.getPosition().getPosition(), 0);
        } catch (OutOfBoundsException oobe) {
            String message = "Out of Bounds Exception: Minimum position reached. Can not decrement position.";
            assertEquals(message, oobe.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    
    public void testBack1From4() {
        try {
            _user0.setPosition(new TextPosition(4));
            MoveCursorPositionCommand command0 = new MoveCursorPositionCommand(_user0, -1);
            command0.execute(_controller);
            _user0.getPosition();
            assertEquals(_user0.getPosition().getPosition(), 3);            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void testBack6From4() {
        try {
            _user0.setPosition(new TextPosition(4));
            MoveCursorPositionCommand command0 = new MoveCursorPositionCommand(_user0, -6);
            command0.execute(_controller);
            _user0.getPosition();
        } catch (Exception e) {
            String errorMessage = "Out of Bounds Exception: Minimum position reached. Can not decrement position.";
            assertEquals(e.getMessage(), errorMessage);
            assertEquals(_user0.getPosition().getPosition(), 4);
        }
    }    
}
