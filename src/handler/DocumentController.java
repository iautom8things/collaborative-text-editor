package handler;

import commands.*;
import user.*;
import java.net.*;
import java.util.Collection;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.lang.Cloneable;
import java.lang.CloneNotSupportedException;

/**
 * Controller for the Document Model.
 *
 * @author Manuel
 */
public class DocumentController implements Serializable, Cloneable {

    private volatile CTEUserManager _userManager;
    private volatile Document _document;

    /**
     * DEFAULT CONSTRUCTOR
     * Constructs a DocumentController with default variable values.
     * @Ensures  
     *  this.getDocument().equals(new Document())
     *  && this.getUserManager().equals(new CTEUserManager())
     */
    public DocumentController ( ) {
        _document = new Document();
        _userManager = new CTEUserManager();

    }
    
    /* 
     * Constructs a DocumentController with the given
     * CTEUSerManager and Document variables. 
     * @Requires
     *  given.manager && given.doc != null
     * @Ensures
     *  this.getDocument().equals(manager)
     *  && this.getUserManager().equals(doc)
     */
    public DocumentController (CTEUserManager manager, Document doc ) {
        _document = doc;
        _userManager = manager;
    }

    /**
     * Set the Document.
     * @Requires
     *  given.document != null
     * @Ensures
     *  this.new.getDocument().equals(document)
     */
    public void setDocument (Document document ) { _document = document; }

    /**
     * Get the Document.
     * @Ensures
     *  returns this._document
     */
    public Document getDocument ( ) { return _document; }

    /**
     * Get the UserManager.
     * @Ensures
     *  returns this._userManager
     */
    public CTEUserManager getUserManager ( ) { return _userManager; }

    /**
     * Returns a String representation of the Document
     */
    public String toString ( ) { return "DocumentController{ " + _document + " " + _userManager +" }"; }

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

    /*
     * Returns a Collection of CTEUsers currently 
     * being processed by this._userManager.
     * @Ensures
     *  returns this._userManager.getUsers()
     */
    public Collection<CTEUser> getUsers ( ) { return _userManager.getUsers(); }

    private void writeObject ( ObjectOutputStream out ) throws IOException {
        ObjectOutputStream.PutField fields = out.putFields();
        fields.put("_userManager", _userManager);
        fields.put("_document", _document);
        out.writeFields();
    }

    private void readObject ( ObjectInputStream in ) throws IOException, ClassNotFoundException {
        ObjectInputStream.GetField fields = in.readFields();
        _userManager = (CTEUserManager) fields.get("_userManager", null);
        _document = (Document) fields.get("_document", null);
    }

    @Override
    public Object clone ( ) throws CloneNotSupportedException {
        Document clonedDoc = (Document) _document.clone();
        CTEUserManager clonedManager = (CTEUserManager) _userManager.clone();
        DocumentController clone = new DocumentController(clonedManager, clonedDoc);

        return clone;
    }
}
