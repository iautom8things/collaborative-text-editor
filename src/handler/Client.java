package handler;

import java.util.Observable;
import java.awt.event.KeyEvent;
import user.*;

public class Client extends Observable {
    
    private DocumentController _controller;
    //private ConnectionManager _connectionManager;

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

    public void passKeyEvent(KeyEvent keyEvent) {
        //System.out.println("Key typed: " + e.getKeyChar() + "(" + e.toString() + ")");
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
    
    private void notifyDocumentGotUpdated(){
        setChanged();
        notifyObservers(_controller.getDocument().toString());
    }
}
