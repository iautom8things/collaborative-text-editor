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
 * A command to execute Document's remove method which removes text given a
 * user's name and an offset.
 *
 * @author Manuel
 */
public class RemoveTextCommand implements Command, Serializable, Cloneable {

    private CTEUser _user;
    private TextPosition _toPos;

    public RemoveTextCommand ( CTEUser user, TextPosition toPos) {
        _user = user;
        _toPos = toPos;
    }

    /**
     * For serialization of this Command.
     */
    private void writeObject ( ObjectOutputStream out ) throws IOException {
        ObjectOutputStream.PutField fields = out.putFields();
        fields.put("_user", _user);
        fields.put("_toPos", _toPos);
        out.writeFields();
    }

    /**
     * For deserialization of this Command.
     */
    private void readObject ( ObjectInputStream in ) throws IOException, ClassNotFoundException {
        ObjectInputStream.GetField fields = in.readFields();
        _user = (CTEUser) fields.get("_user", null);
        _toPos = (TextPosition) fields.get("_toPos", null);
    }

    @Override
    public void execute ( DocumentController controller ) throws InvalidUserIDException, UserNotFoundException, OutOfBoundsException {
        CTEUserManager userManager = controller.getUserManager();
        Document doc = controller.getDocument();
        TextPosition fromPos = _user.getPosition();
        TextPosition toPos = _toPos; // This is to preserve the state of the command if there's a need to swap below

        if (fromPos.isBeyond(toPos)) { // If fromPos is actually beyond toPos, then swap them
            TextPosition temp = toPos;
            toPos = fromPos;
            fromPos = temp;
        }

        doc.deleteText(fromPos, toPos);
        int deletionRange = fromPos.getPosition() - toPos.getPosition();
        userManager.updateBetween(fromPos, toPos);
        userManager.updateBeyond(toPos, deletionRange);
        userManager.setCursorForUser(_user.getUserID(), fromPos);
    }

    @Override
    public Object clone ( ) throws CloneNotSupportedException {
        CTEUser clonedUser = (CTEUser) _user.clone();
        TextPosition clonedTP = (TextPosition) _toPos.clone();
        RemoveTextCommand clone = new RemoveTextCommand(clonedUser, clonedTP);

        return clone;
    }
}
