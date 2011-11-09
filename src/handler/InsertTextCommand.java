package handler;
import user.*;

/**
 * A command to execute Document's insert method which inserts text given a
 * position and text to insert.
 *
 * @author Manuel
 */
public class InsertTextCommand implements Command {

    private String _userName;
    private String _text;

    public InsertTextCommand ( String userName, String text) {
        _userName = userName;
        _text = text;
    }

    @Override
    public void execute ( Document doc, UserManager userManager ) throws InvalidUserIDException, UserNotFoundException, OutOfBoundsException {
        User usr = userManager.getUser(_userName);
        TextPosition pos = usr.getPosition();
        int len = _text.length();

        doc.insertText(pos, _text);
        userManager.updateBeyond(pos, len);
        pos.incrementBy(len);
    }
}
