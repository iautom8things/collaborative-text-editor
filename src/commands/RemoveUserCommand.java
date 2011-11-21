package commands;
import handler.*;
import user.*;

/**
 *
 * @author Manuel
 */
public class RemoveUserCommand implements Command {

    private String _userID;

    public RemoveUserCommand ( String userID ) { _userID = userID; }

    @Override
    public void execute ( DocumentController controller ) throws InvalidUserIDException, UserNotFoundException, OutOfBoundsException {
        CTEUserManager userManager = controller.getUserManager();
        userManager.removeUser(_userID);
    }
}
