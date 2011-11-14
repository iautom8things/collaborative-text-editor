package handler;

import network.*;
import user.*;
import gui.*;
import java.util.Observable;
import static java.lang.System.out;

public class Client extends Observable {

    public static void main(String[] args) {
        out.println("Begin Main");
        Client _client = new Client();
        _client.setDocument("Test document!");
        EditorGUI gui = new EditorGUI();
        gui.launch();
    }    
    
    private DocumentController _controller;
    //private ConnectionManager _connectionManager;
    
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
        command.execute(_controller.getDocument(), _controller.getUserManager());
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
        Document newDoc = new Document(text);
        _controller.setDocument(newDoc);
        setChanged();
        notifyObservers(this);
    }    
}
