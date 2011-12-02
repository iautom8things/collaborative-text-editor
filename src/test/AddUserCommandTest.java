package test;

import junit.framework.TestCase;
import handler.DocumentController;
import user.CTEUser;
import commands.AddUserCommand;
import java.awt.Color;
import java.net.InetAddress;

public class AddUserCommandTest extends TestCase{
  
  private DocumentController _docController;
  private CTEUser _firstUser;
  private CTEUser _secondUser;
  private CTEUser _thirdUser;
  private CTEUser _fourthUser;
  private CTEUser _fifthUser;
  private CTEUser _sixthUser;
  private AddUserCommand _firstCommand;
  private AddUserCommand _secondCommand;
  private AddUserCommand _thirdCommand;
  private AddUserCommand _fourthCommand;
  private AddUserCommand _fifthCommand;
  private AddUserCommand _sixthCommand;
  
  public static void main(String[] args) {
    
  }
  
  protected void setUp ( ) {
    _docController = new DocumentController();
    try {
      _firstUser = new CTEUser("firstUser", InetAddress.getLocalHost(), Color.BLACK);
      _firstCommand = new AddUserCommand(_firstUser);
      _secondUser = new CTEUser("secondUser", InetAddress.getLocalHost(), Color.BLACK);
      _secondCommand = new AddUserCommand(_secondUser);
      _thirdUser = new CTEUser("thirdUser", InetAddress.getLocalHost(), Color.BLACK);
      _thirdCommand = new AddUserCommand(_thirdUser);
      _fourthUser = new CTEUser("fourthUser", InetAddress.getLocalHost(), Color.BLACK);
      _fourthCommand = new AddUserCommand(_fourthUser);
      _fifthUser = new CTEUser("fifthUser", InetAddress.getLocalHost(), Color.BLACK);
      _fifthCommand = new AddUserCommand(_fifthUser);
      _sixthUser = new CTEUser("sixthUser", InetAddress.getLocalHost(), Color.BLACK);
      _sixthCommand = new AddUserCommand(_sixthUser);
    }
    catch (Exception e) { System.out.println(e.getMessage()); }
  }
  
  public void testAddUserExecute(){
    try{
      assertEquals(0, _docController.getUsers().size());
      
      _firstCommand.execute(_docController);
      assertEquals(1, _docController.getUsers().size());
      assertTrue(_docController.getUsers().contains(_firstUser));
      
      _secondCommand.execute(_docController);
      assertEquals(2, _docController.getUsers().size());
      assertTrue(_docController.getUsers().contains(_secondUser));
      
      _thirdCommand.execute(_docController);
      assertEquals(3, _docController.getUsers().size());
      assertTrue(_docController.getUsers().contains(_thirdUser));
      
      _fourthCommand.execute(_docController);
      assertEquals(4, _docController.getUsers().size());
      assertTrue(_docController.getUsers().contains(_fourthUser));
      
      _fifthCommand.execute(_docController);
      assertEquals(5, _docController.getUsers().size());
      assertTrue(_docController.getUsers().contains(_fifthUser));
      
      _sixthCommand.execute(_docController);
      assertEquals(6, _docController.getUsers().size());
      assertTrue(_docController.getUsers().contains(_sixthUser));
    }
    catch (Exception e){
      System.out.println(e.getMessage());
    }
  }
  
}//End class AddUserCommandTest