package commands;
import handler.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import user.*;
import debug.Debug;

/**
 * A command to move the cursor to a position in the Document.
 */
public class MoveCursorPositionCommand implements Command, Serializable, Cloneable {

    private CTEUser _user;
    private String _offset; //spaces to move over
    private static final boolean DEBUG = Debug.VERBOSE;

    /*
     * Insert the given text to the user's Document.
     *  @Requires
     *      user != null
     *      offset != null
     */
    public MoveCursorPositionCommand ( CTEUser user, int offset ) {
        _user = user;
        _offset = new Integer(offset).toString();
    }
    
    /**
     * Given a DocumentController, move the TextPosition of the User
     * by the _offset amount
     *
     * Requires:
     *      controller != null
     *      controller.getUserManager().contains(_user)
     *
     * Ensures:
     *      new position of _user will be = current position of _user + _offset
     *
     */
    @Override
    public void execute ( DocumentController controller ) throws InvalidUserIDException, UserNotFoundException, OutOfBoundsException {
        CTEUserManager userManager = controller.getUserManager();
        
        //Get the last available position in the document
        TextPosition lastPosition = controller.getDocument().getLastPosition();
        //Moving forward
        if (Integer.parseInt(_offset) > 0){
            TextPosition currentPosition = _user.getPosition(); //where the _user is at start of method
            if (DEBUG) { System.out.println("**currentPosition: " + currentPosition); }
            if (DEBUG) { System.out.println("**offset: " + _offset); }
            if(Integer.parseInt(_offset) > lastPosition.compareTo(currentPosition)) {
                if (DEBUG) { System.out.println("trying to go beyond end of document"); }
                //going beyond end of document not allowed
                throw new OutOfBoundsException("Position is beyond end of document.");
            }
            if (DEBUG) { System.out.println("Executing command right arrow with " + _offset); }
            //set position to a new positioni incremented by the offset
            userManager.getUser(_user.getUniqueID()).getPosition().incrementBy(Integer.parseInt(_offset));
        }
        //Moving backward
        else if (Integer.parseInt(_offset) < 0){
            if (DEBUG) { System.out.println("Executing command left arrow with " + _offset); }
            //move the position to the one decremented
            userManager.getUser(_user.getUniqueID()).getPosition().decrementBy(Math.abs(Integer.parseInt(_offset)));
        }
    }

    /**
     * For serialization of this Command.
     *  @Requires
     *      out != null
     */
    private void writeObject ( ObjectOutputStream out ) throws IOException {
        ObjectOutputStream.PutField fields = out.putFields();
        fields.put("_user", _user);
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
    @Override
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
