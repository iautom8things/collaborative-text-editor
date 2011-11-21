package handler;

import commands.*;
import user.*;
import java.net.*;
import java.util.Collection;

/**
 * Controller for the Document Model.
 *
 * @author Manuel
 */
public class DocumentController {

    private volatile CTEUserManager _userManager;
    private volatile Document _document;

    /**
     * Constructs a DocumentController.
     */
    public DocumentController ( ) {
        _document = new Document();
        _userManager = new CTEUserManager();

    }

    /**
     * Set the Document.
     */
    public void setDocument (Document document ) { _document = document; }

    /**
     * Get the Document.
     */
    public Document getDocument ( ) { return _document; }

    /**
     * Get the UserManager.
     */
    public CTEUserManager getUserManager ( ) { return _userManager; }

    /**
     * Returns a String representation of the Document
     */
    public String toString ( ) { return _document.toString(); }

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
        command.execute(this);
    }

    public Collection<CTEUser> getUsers ( ) { return _userManager.getUsers(); }
}
