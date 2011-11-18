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
     * I guess this is where we decide what the keyEvent actually is:
     * A character, a command(copy, paste, delete)
     * I didn't look at the documentation enough yet to know how, so I just said 
     * if not backspace, then insert the character
     * TO DO: Implement all keyEvents supported and ignore those that aren't
     * TO DO: Better Exception handling
     */
    public void passKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
            InsertTextCommand command = new InsertTextCommand("username", Character.toString(keyEvent.getKeyChar()));
            try {
                _controller.executeCommand(command);
            } catch (UserNotFoundException iue) {
                iue.printStackTrace();
            } catch (InvalidUserIDException iuide) {
                iuide.printStackTrace();
            } catch (OutOfBoundsException oobe) {
                oobe.printStackTrace();
            }
            //since command execution was successful, update everyone's text pane
            notifyDocumentGotUpdated();
        } else {
            System.out.println("backspace typed!");
        }
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
