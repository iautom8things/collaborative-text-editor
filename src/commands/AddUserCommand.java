package commands;
import handler.*;
import user.*;
import java.lang.Cloneable;
import java.lang.CloneNotSupportedException;

/**
 * @author Manuel
 */
public class AddUserCommand implements Command, Cloneable {

    private CTEUser _user;

    public AddUserCommand ( CTEUser user ) { _user = user; }

    @Override
    public void execute ( DocumentController controller ) throws InvalidUserIDException, UserNotFoundException, OutOfBoundsException {
        CTEUserManager userManager = controller.getUserManager();
        userManager.addUser(_user);
    }

    @Override
    public Object clone ( ) throws CloneNotSupportedException {
        CTEUser clonedUser = (CTEUser) _user.clone();
        AddUserCommand clone = new AddUserCommand(clonedUser);

        return clone;
    }
}
