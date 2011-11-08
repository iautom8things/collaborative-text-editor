package handler;

public class InsertTextCommand implements Command {

    private String _user;
    private String _text;

    public InsertTextCommand ( String userName, String text) {
        _user = userName;
        _text = text;
    }

    public void execute ( Document doc ) { doc.insertText(_user, _text); }
}
