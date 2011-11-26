package commands;
import handler.*;
import user.*;
import java.lang.Cloneable;
import java.lang.CloneNotSupportedException;

/**
 *
 * @author Manuel
 */
public class RemoveUserCommand implements Command, Cloneable {

    private CTEUser _user;

    public RemoveUserCommand ( CTEUser user ) { _user = user; }

    @Override
    public void execute ( DocumentController controller ) throws InvalidUserIDException, UserNotFoundException, OutOfBoundsException {
        CTEUserManager userManager = controller.getUserManager();
        userManager.removeUser(_user);
    }

    @Override
    public Object clone ( ) throws CloneNotSupportedException {
        CTEUser clonedUser = (CTEUser) _user.clone();
        RemoveUserCommand clone = new RemoveUserCommand(clonedUser);

        return clone;
    }
}
