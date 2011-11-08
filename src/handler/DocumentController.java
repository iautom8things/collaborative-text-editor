package handler;
import user.*;

/**
 * Controller for the Document Model.
 *
 * @author Manuel
 */
public class DocumentController {

    private UserManager _userManager;
    private Document _document;

    public DocumentController ( ) {
        _document = new Document();
        _userManager = new CTEUserManager();
    }

    public void executeCommand ( Command command ) throws UserNotFoundException, OutOfBoundsException {
        command.execute(_document, _userManager);
    }
}
