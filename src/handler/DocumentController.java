package handler;

import user.*;

/**
 * Controller for the Document Model.
 *
 * @author Manuel
 */
public class DocumentController {

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
     * Set the Document.
     */
    public void setDocument (Document document ) {
        _document = document;
    }

    /**
     * Get the Document.
     */
    public Document getDocument () {
        return _document;
    }
    
    /**
     * Get the UserManager.
     */
    public UserManager getUserManager () {
        return _userManager;
    }    

    /**
     * Returns a String representation of the Document
     */
    public String toString ( ) { return _document.toString(); }
}
