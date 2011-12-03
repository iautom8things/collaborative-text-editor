package test;

import junit.framework.TestCase;
import handler.DocumentController;
import user.CTEUser;
import user.CTEUserManager;
import commands.AddUserCommand;
import commands.UpdateUserNameCommand;
import java.awt.Color;
import java.net.InetAddress;

public class UpdateUserNameCommandTest extends TestCase {
  
  private DocumentController _docController;
  private CTEUser _firstUser;
  private CTEUser _secondUser;
  private AddUserCommand _addFirstUserCommand;
  private AddUserCommand _addSecondUserCommand;
  private UpdateUserNameCommand _changeFirstUserCommand;
  private UpdateUserNameCommand _changeSecondUserCommand;
  
  @Override
  public void setUp ( ) {
    _docController = new DocumentController();
    try {
      _firstUser = new CTEUser("firstUser", InetAddress.getLocalHost(), Color.BLACK);
      _addFirstUserCommand = new AddUserCommand(_firstUser);
      _changeFirstUserCommand = new UpdateUserNameCommand(_firstUser, "changedFirstUser");
      
      _secondUser = new CTEUser("secondUser", InetAddress.getLocalHost(), Color.BLACK);
      _addSecondUserCommand = new AddUserCommand(_secondUser);
      _changeSecondUserCommand = new UpdateUserNameCommand(_secondUser, "changedSecondUser");
    }
    catch (Exception e) { fail(e.getMessage()); }
  }
  
  public void testUpdateUserNameExecute ( ) {
    try {
      assertEquals(0, _docController.getUsers().size());
      //The following CTEUserManager will be used to obtain the newly named CTEUser
      //to compare (referential equality?) with the private instance within this class
      CTEUserManager userManager = _docController.getUserManager();
      
      //Add first user
      _addFirstUserCommand.execute(_docController);
      assertEquals(1, _docController.getUsers().size());
      assertTrue(_docController.getUsers().contains(_firstUser));
      
      //Change first user's name and perform "appropriate" assertion(s)
      _changeFirstUserCommand.execute(_docController);
      assertEquals(1, _docController.getUsers().size());
      CTEUser changedFirstUser = userManager.getUser(_firstUser.getUniqueID());
      assertEquals(changedFirstUser, _firstUser);  //referential equality
      assertEquals("changedFirstUser", changedFirstUser.getName());  //state equality
      
      //Add second user
      _addSecondUserCommand.execute(_docController);
      assertEquals(2, _docController.getUsers().size());
      assertTrue(_docController.getUsers().contains(_secondUser));
      
      //Change second user's name and perform "appropriate" assertion(s)
      _changeSecondUserCommand.execute(_docController);
      assertEquals(2, _docController.getUsers().size());
      CTEUser changedSecondUser = userManager.getUser(_secondUser.getUniqueID());
      assertEquals(changedSecondUser, _secondUser);  //referential equality
      assertEquals("changedSecondUser", changedSecondUser.getName());  //state equality
    }
    catch (Exception e) { fail(e.getMessage()); }
  }//End testUpdateUserNameExecute()
  
}//End class UpdateUserNameCommandTest
