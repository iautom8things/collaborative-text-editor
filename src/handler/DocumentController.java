package handler;

/**
 * Controller for the Document Model.
 *
 * @author Manuel
 */
public class DocumentController {

    private Document _document;

    public DocumentController ( ) { _document = new Document(); }

    public void executeCommand ( Command command ) { command.execute(_document); }
}
