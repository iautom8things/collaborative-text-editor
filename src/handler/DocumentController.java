package handler;
import user.*;
import java.util.Observable;

/**
 * Controller for the Document Model.
 *
 * @author Manuel
 */
public class DocumentController extends Observable {

    private volatile UserManager _userManager;
    private volatile Document _document;

    /**
     * Constructs a DocumentController.
     */
    public DocumentController ( ) {
        _document = new Document();
        _userManager = new CTEUserManager();
    }

    /**
     * Executes the given command by passing a reference to the current state
     * of the Document and UserManager to command.execute().
     *
     * @Requires
     *      command != null
     * @Ensures
     *      No two commands can execute at a given time.
     */
    public synchronized void executeCommand ( Command command ) throws InvalidUserIDException, UserNotFoundException, OutOfBoundsException {
        command.execute(_document, _userManager);
        setChanged();
        notifyObservers(this);
    }

    /**
     * Creates a new Document with the given String.
     *
     * @Requires
     *      text != null
     * @Ensures
     *      this.toString() == text
     */
    public synchronized void setDocument ( String text ) {
        _document = new Document(text);
        setChanged();
        notifyObservers(this);
    }

    /**
     * Returns a String representation of the Document
     */
    public String toString ( ) { return _document.toString(); }
}
