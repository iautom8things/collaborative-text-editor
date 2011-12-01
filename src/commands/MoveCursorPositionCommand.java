package commands;
import handler.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import user.*;

/**
 * A command to move the cursor to a position in the Document.
 */
public class MoveCursorPositionCommand implements Command, Serializable, Cloneable {

    private CTEUser _user;
    private TextPosition _currentPosition;
    private String _offset;
    private static final boolean DEBUG = true;

    /*
     * Insert the given text to the user's Document.
     */
    public MoveCursorPositionCommand ( CTEUser user, int offset ) {
        _user = user;
        _offset = new Integer(offset).toString();
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
     *
     *
     */
    public void execute ( DocumentController controller ) throws InvalidUserIDException, UserNotFoundException, OutOfBoundsException {
        CTEUserManager userManager = controller.getUserManager();
        Document doc = controller.getDocument();
        TextPosition currentPosition = _user.getPosition();
        if (Integer.parseInt(_offset) > 0){
            print("Executing command right arrow with " + _offset);
            userManager.getUser(_user.getUniqueID()).getPosition().incrementBy(Integer.parseInt(_offset));
        }
        else if (Integer.parseInt(_offset) < 0){
            print("Executing command left arrow with " + _offset);
            userManager.getUser(_user.getUniqueID()).getPosition().decrementBy(Math.abs(Integer.parseInt(_offset)));
            print("Executing command right arrow with " + _offset);
        }
    }

    /**
     * For serialization of this Command.
     */
    private void writeObject ( ObjectOutputStream out ) throws IOException {
        ObjectOutputStream.PutField fields = out.putFields();
        fields.put("_user", _user);
        //fields.put("_offset", new Integer(_offset).toString());
        fields.put("_offset", _offset);
        out.writeFields();
    }

    /**
     * For deserialization of this Command.
     */
    private void readObject ( ObjectInputStream in ) throws IOException, ClassNotFoundException {
        ObjectInputStream.GetField fields = in.readFields();
        _user = (CTEUser) fields.get("_user", null);
        _offset = (String) fields.get("_offset", null);
    }

    /**
     * Returns a String represnetation of this command.
     */
    public String toString ( ) {
        return "MoveCursorPositionCommand{" + "_user: " + _user + ", _offset: " + _offset + " }";
    }

    /**
     * Returns a deep copy of this Command.
     */
    @Override
    public Object clone ( ) throws CloneNotSupportedException {
        CTEUser clonedUser = (CTEUser) _user.clone();
        MoveCursorPositionCommand clone = new MoveCursorPositionCommand(clonedUser, Integer.parseInt(_offset));
        return clone;
    }
}
