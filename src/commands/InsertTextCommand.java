package commands;
import handler.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import user.*;

/**
 * A command to execute Document's insert method which inserts text given a
 * position and text to insert.
 *
 * @author Manuel
 */
public class InsertTextCommand implements Command, Serializable{

    private String _userName;
    private String _text;

    /*
     * Insert the given text to the user's Document.
     */
    public InsertTextCommand ( String userName, String text ) {
        _userName = userName;
        _text = text;
    }

    public void execute ( Document doc, UserManager userManager ) throws InvalidUserIDException, UserNotFoundException, OutOfBoundsException {
        User usr = userManager.getUser(_userName);
        TextPosition pos = usr.getPosition();
        int len = _text.length();

        doc.insertText(pos, _text);
        userManager.updateBeyond(pos, len);
        pos.incrementBy(len);
    }

    private void writeObject ( ObjectOutputStream out ) throws IOException {
        ObjectOutputStream.PutField fields = out.putFields();
        fields.put("_userName", _userName);
        fields.put("_text", _text);
        out.writeFields();
    }

    private void readObject ( ObjectInputStream in ) throws IOException, ClassNotFoundException {
        ObjectInputStream.GetField fields = in.readFields();
        _userName = (String)fields.get("_userName", null);
        _text = (String)fields.get("_text", null);
    }

    public String toString ( ) {
        return "InsertTextCommand{" + "_userName: " + _userName + ", _text: " + _text + " }";
    }
}
