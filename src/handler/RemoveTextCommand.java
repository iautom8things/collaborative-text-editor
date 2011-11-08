package handler;

/**
 * A command to execute Document's remove method which removes text given a
 * user's name and an offset.
 *
 * @author Manuel
 */
public class RemoveTextCommand implements Command {

    private String _user;
    private TextPosition _offset;
    public RemoveTextCommand ( String userName, int offset ) {
        _user = userName;
        _offset = new TextPosition(offset);
    }

    public void execute ( Document doc ) { doc.deleteText(_user, _offset); }
}
