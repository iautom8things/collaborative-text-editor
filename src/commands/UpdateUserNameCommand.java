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
 *
 * @author Manuel
 */
public class UpdateUserNameCommand implements Command, Serializable, Cloneable {

    private CTEUser _user;
    private String _name;

    public UpdateUserNameCommand ( CTEUser user, String name) {
        _user = user;
        _name = name;
    }

    @Override
    public void execute ( DocumentController controller ) throws InvalidUserIDException, UserNotFoundException, OutOfBoundsException {
        CTEUserManager userManager = controller.getUserManager();
        userManager.setUserName(_user, _name);
    }

    @Override
    public Object clone ( ) throws CloneNotSupportedException {
        CTEUser clonedUser = (CTEUser) _user.clone();
        UpdateUserNameCommand clone = new UpdateUserNameCommand(clonedUser, _name);

        return clone;
    }

    /**
     * For serialization of this Command. (Serializable)
     */
    private void writeObject ( ObjectOutputStream out ) throws IOException {
        ObjectOutputStream.PutField fields = out.putFields();
        fields.put("_user", _user);
        fields.put("_name", _name);
        out.writeFields();
    }

    /**
     * For deserialization of this Command. (Serializable)
     */
    private void readObject ( ObjectInputStream in ) throws IOException, ClassNotFoundException {
        ObjectInputStream.GetField fields = in.readFields();
        _user = (CTEUser) fields.get("_user", null);
        _name = (String) fields.get("_name", null);
    }
}


