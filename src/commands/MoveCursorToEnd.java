package commands;
import handler.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import user.*;

/**
 * A command to move the cursor to the final position in the Document.
 */
public class MoveCursorToEnd implements Command, Serializable, Cloneable {

    private CTEUser _user;
    private static final boolean DEBUG = true;

    /*
     * Move the Cursor to the Document End
     */
    public MoveCursorToEnd ( CTEUser user ) {
        _user = user;
    }

    private void print ( String message ) { if (DEBUG) { System.out.println(message); } }    
    
    /**
     * Given a DocumentController, move the TextPosition of the User
     *
     * Requires:
     *      controller != null
     *      controller.getUserManager().contains(_user)
     *
     * Ensures:
     */
    public void execute ( DocumentController controller ) throws InvalidUserIDException, UserNotFoundException, OutOfBoundsException {
        print("MoveCursorToEnd Command Called");
        Document doc = controller.getDocument();
        TextPosition endPosition = doc.getLastPosition();
        _user.setPosition(endPosition);
    }

    /**
     * For serialization of this Command.
     */
    private void writeObject ( ObjectOutputStream out ) throws IOException {
        ObjectOutputStream.PutField fields = out.putFields();
        fields.put("_user", _user);
        out.writeFields();
    }

    /**
     * For deserialization of this Command.
     */
    private void readObject ( ObjectInputStream in ) throws IOException, ClassNotFoundException {
        ObjectInputStream.GetField fields = in.readFields();
        _user = (CTEUser) fields.get("_user", null);
    }

    /**
     * Returns a String represnetation of this command.
     */
    public String toString ( ) {
        return "MoveCursorToDocumentEnd{" + "_user: " + _user + " }";
    }

    /**
     * Returns a deep copy of this Command.
     */
    @Override
    public Object clone ( ) throws CloneNotSupportedException {
        CTEUser clonedUser = (CTEUser) _user.clone();
        MoveCursorToEnd clone = new MoveCursorToEnd(clonedUser);
        return clone;
    }
}
