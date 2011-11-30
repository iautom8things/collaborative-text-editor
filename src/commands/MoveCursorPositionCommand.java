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
    private TextPosition _textPosition;

    /*
     * Insert the given text to the user's Document.
     */
    public MoveCursorPositionCommand ( CTEUser user, TextPosition textPosition ) {
        _user = user;
        _textPosition = textPosition;
    }

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
        int length;
        //cursor is moved backwards
        if(currentPosition.getPosition() > _textPosition.getPosition()){
            //decrement
            length = currentPosition.getPosition() - _textPosition.getPosition();
            userManager.getUser(_user.getUniqueID()).getPosition().decrementBy(length);
            
        }
        //cursor is moved forwards
        else{
            length = _textPosition.getPosition() - currentPosition.getPosition();
            userManager.getUser(_user.getUniqueID()).getPosition().incrementBy(length);
        }
    }

    /**
     * For serialization of this Command.
     */
    private void writeObject ( ObjectOutputStream out ) throws IOException {
        ObjectOutputStream.PutField fields = out.putFields();
        fields.put("_user", _user);
        fields.put("_textPosition", _textPosition);
        out.writeFields();
    }

    /**
     * For deserialization of this Command.
     */
    private void readObject ( ObjectInputStream in ) throws IOException, ClassNotFoundException {
        ObjectInputStream.GetField fields = in.readFields();
        _user = (CTEUser) fields.get("_user", null);
        _textPosition = (TextPosition) fields.get("_textPosition", null);
    }

    /**
     * Returns a String represnetation of this command.
     */
    public String toString ( ) {
        return "MoveCursorPositionCommand{" + "_user: " + _user + ", _textPosition: " + _textPosition + " }";
    }

    /**
     * Returns a deep copy of this Command.
     */
    @Override
    public Object clone ( ) throws CloneNotSupportedException {
        CTEUser clonedUser = (CTEUser) _user.clone();
        TextPosition clonedTextPosition = (TextPosition)_textPosition.clone();
        MoveCursorPositionCommand clone = new MoveCursorPositionCommand(clonedUser, clonedTextPosition);
        return clone;
    }
}
