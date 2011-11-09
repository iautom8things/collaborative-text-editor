package handler;
import user.*;
import java.util.Observable;

/**
 * Controller for the Document Model.
 *
 * @author Manuel
 */
public class DocumentController extends Observable {

    private UserManager _userManager;
    private Document _document;

    public DocumentController ( ) {
        _document = new Document();
        _userManager = new CTEUserManager();
    }

    public void executeCommand ( Command command ) throws InvalidUserIDException, UserNotFoundException, OutOfBoundsException {
        command.execute(_document, _userManager);
        setChanged();
        notifyObservers(this);
    }

    public Document getDocument ( ) { return _document; }

    public void setDocument ( String text ) {
        _document = new Document(text);
        setChanged();
        notifyObservers(this);
    }

    public String toString ( ) { return _document.toString(); }
}
