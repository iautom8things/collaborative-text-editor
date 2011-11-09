package handler;
import user.*;

public class RemoveUserCommand implements Command {

    private String _userID;

    public RemoveUserCommand ( String userID ) { _userID = userID; }

    @Override
    public void execute ( Document doc, UserManager userManager ) throws InvalidUserIDException, UserNotFoundException, OutOfBoundsException {
        userManager.removeUser(_userID);
    }
}
