package commands;
import handler.*;
import user.*;

/**
 * @author Manuel
 */
public class AddUserCommand implements Command {

    private CTEUser _user;

    public AddUserCommand ( CTEUser user ) { _user = user; }

    @Override
    public void execute ( DocumentController controller ) throws InvalidUserIDException, UserNotFoundException, OutOfBoundsException {
        CTEUserManager userManager = controller.getUserManager();
        userManager.addUser(_user);
    }
}
