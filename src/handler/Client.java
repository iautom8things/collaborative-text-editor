package handler;

import java.util.Observable;
import java.awt.event.KeyEvent;
import user.*;

public class Client extends Observable {
    
    private DocumentController _controller;
    //private ConnectionManager _connectionManager; NOT implemented yet

    /*
     * Create a new Client with a blank Document.
     * @Ensures
     *      _controller.getDocument().toString() = "";
     */
    public Client() {
        _controller = new DocumentController();
        setDocument("");
        //_connectionManager = new ConnectionManager();
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
        Document newDoc = new Document(text);
        _controller.setDocument(newDoc);
        
        //update text panes
        notifyDocumentGotUpdated();
    }

    /*
     * Not really sure how the command should go from the GUI to the 
     * DocumentController... this is my solution
     */
    public void passCommand(Command command) throws InvalidUserIDException, UserNotFoundException, OutOfBoundsException {
        _controller.executeCommand(command);
        //since command execution was successful, update everyone's text pane
        notifyDocumentGotUpdated();  
    }
    
    public synchronized void initateCollaboration ( ) {
        //_connectionManager = new ConnectionManager();
    }
  
    public synchronized Document getDocument () {
        return _controller.getDocument();
    }

    public String toString() {
        return "Client{" + "_controller=" + _controller.toString() + '}';
    }
    
    /*
     * Notifies the observers of the Document that it has changed.
     * Partially implemented for a GUI observer.
     * TO DO: Implement for a Server Observer
     */
    private void notifyDocumentGotUpdated(){
        setChanged();
        notifyObservers(_controller.getDocument().toString());
    }
}
