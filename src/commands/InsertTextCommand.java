package commands;
import handler.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.Cloneable;
import java.lang.CloneNotSupportedException;
import user.*;

/**
 * A command to execute Document's insert method which inserts text given a
 * position and text to insert.
 *
 * @author Manuel
 */
public class InsertTextCommand implements Command, Serializable, Cloneable {

    private CTEUser _user;
    private String _text;

    /*
     * Insert the given text to the user's Document.
     */
    public InsertTextCommand ( CTEUser user, String text ) {
        _user = user;
        _text = text;
    }

    /**
     * Given a DocumentController, insert _text on behalf of _user on that
     * DocumentController.
     *
     * Requires:
     *      controller != null
     *      controller.getUserManager().contains(_user)
     *
     * Ensures:
     *      
     *
     */
    public void execute ( DocumentController controller ) throws InvalidUserIDException, UserNotFoundException, OutOfBoundsException {
        CTEUserManager userManager = controller.getUserManager();
        Document doc = controller.getDocument();
        TextPosition pos = _user.getPosition();
        int len = _text.length();

        doc.insertText(pos, _text);
        userManager.updateBeyond(pos, len);
        userManager.getUser(_user.getUserID()).getPosition().incrementBy(len);
    }

    /**
     * For serialization of this Command.
     */
    private void writeObject ( ObjectOutputStream out ) throws IOException {
        ObjectOutputStream.PutField fields = out.putFields();
        fields.put("_user", _user);
        fields.put("_text", _text);
        out.writeFields();
    }

    /**
     * For deserialization of this Command.
     */
    private void readObject ( ObjectInputStream in ) throws IOException, ClassNotFoundException {
        ObjectInputStream.GetField fields = in.readFields();
        _user = (CTEUser) fields.get("_user", null);
        _text = (String) fields.get("_text", null);
    }

    /**
     * Returns a String represnetation of this command.
     */
    public String toString ( ) {
        return "InsertTextCommand{" + "_user: " + _user + ", _text: " + _text + " }";
    }

    /**
     * Returns a deep copy of this Command.
     */
    @Override
    public Object clone ( ) throws CloneNotSupportedException {
        CTEUser clonedUser = (CTEUser) _user.clone();
        InsertTextCommand clone = new InsertTextCommand(clonedUser, _text);
        return clone;
    }
}
