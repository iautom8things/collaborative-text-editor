package handler;
import user.*;

public class InsertTextCommand implements Command {

    private String _userName;
    private String _text;

    public InsertTextCommand ( String userName, String text) {
        _userName = userName;
        _text = text;
    }

    @Override
    public void execute ( Document doc, UserManager userManager ) throws UserNotFoundException, OutOfBoundsException {
        User usr = userManager.getUser(_userName);
        TextPosition pos = usr.getPosition();
        int len = _text.length();

        doc.insertText(pos, _text);
        userManager.updateBeyond(pos, len);
        pos.incrementBy(len);
    }
}
