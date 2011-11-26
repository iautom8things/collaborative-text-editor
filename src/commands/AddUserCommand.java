package commands;
import handler.*;
import user.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.Cloneable;
import java.lang.CloneNotSupportedException;

/**
 * @author Manuel
 */
public class AddUserCommand implements Command, Serializable, Cloneable {

    private CTEUser _user;

    public AddUserCommand ( CTEUser user ) { _user = user; }

    @Override
    public void execute ( DocumentController controller ) throws InvalidUserIDException, UserNotFoundException, OutOfBoundsException {
        CTEUserManager userManager = controller.getUserManager();
        if (!userManager.contains(_user)) { userManager.addUser(_user); }
    }

    @Override
    public Object clone ( ) throws CloneNotSupportedException {
        CTEUser clonedUser = (CTEUser) _user.clone();
        AddUserCommand clone = new AddUserCommand(clonedUser);

        return clone;
    }

    /**
     * For serialization of this Command. (Serializable)
     */
    private void writeObject ( ObjectOutputStream out ) throws IOException {
        ObjectOutputStream.PutField fields = out.putFields();
        fields.put("_user", _user);
        out.writeFields();
    }

    /**
     * For deserialization of this Command. (Serializable)
     */
    private void readObject ( ObjectInputStream in ) throws IOException, ClassNotFoundException {
        ObjectInputStream.GetField fields = in.readFields();
        _user = (CTEUser) fields.get("_user", null);
    }
}
