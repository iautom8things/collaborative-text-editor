package handler;

import commands.*;
import java.util.Observable;
import java.awt.event.KeyEvent;
import user.*;
import network.*;
import java.net.*;

public class Client extends Observable {

    private DocumentController _controller;
    private ClientNetworkManager _manager;
    private CTEUser _localUser;
    private DocumentKey _docKey;
    private boolean _isCollaborating;

    /**
     * Create a new Client with a blank Document.
     * @Ensures
     *      _controller.getDocument().toString() = "";
     */
    public Client ( ) {
        _isCollaborating = false;
        try { _localUser = new CTEUser("DefaultUser", InetAddress.getLocalHost(), ColorList.getColor(0)); }
        catch (Exception e) { /** This shouldn't happen >_<.*/}
        _docKey = new DocumentKey("DefaultDoc", "password");
        _controller = new DocumentController();
        setDocument("");
    }


    /***********
     * Queries *
     **********/


    public boolean isCollaborating ( ) { return _isCollaborating; }

    public String toString() {
        return "Client{" + "_controller: " + _controller.toString() + "_isCollaborating: " + _isCollaborating + " }";
    }

    public synchronized Document getDocument () { return _controller.getDocument(); }


    /************
     * Commands *
     ***********/


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

    /**
     * Not really sure how the command should go from the GUI to the
     * DocumentController... this is my solution
     */
    public void passCommand ( Command command ) throws InvalidUserIDException, UserNotFoundException, OutOfBoundsException {
        if (_isCollaborating) { _wrapAndForward(command); }
        else {
            _controller.executeCommand(command);
            //since command execution was successful, update everyone's text pane
            notifyDocumentGotUpdated();
        }
    }

    /**
     * Given the Network Command, execute it.
     */
    public void executeNetworkCommand ( NetworkCommand command ) throws InvalidUserIDException, UserNotFoundException, OutOfBoundsException {
        // TODO Actually execute the command and notify the GUI
        System.out.println("The Client has received:");
        System.out.println(command);
    }

    /**
     * Initiates a Connection to the Server.
     */
    public synchronized void initateCollaboration ( ) {
        _manager = new ClientNetworkManager();
        _manager.connect();
        _isCollaborating = true;
    }

    /**
     * Sets the DocumentKey.
     */
    public void setDocumentKey ( DocumentKey key ) { _docKey = key; }


    /*******************
     * Private Methods *
     *******************/


    /**
     * Notifies the observers of the Document that it has changed.
     * Partially implemented for a GUI observer.
     * TODO: Implement for a Server Observer
     */
    private void notifyDocumentGotUpdated ( ) {
        setChanged();
        notifyObservers(_controller.getDocument().toString());
    }

    /**
     * Creates a NetworkCommand and sends it to the Server.
     */
    private void _wrapAndForward ( Command command ) {
        if (command==null) { System.out.println("command is null"); }
        if (_docKey==null) { System.out.println("_docKey is null"); }
        if (_localUser==null) { System.out.println("_localUser is null"); }
        if (_manager==null) { System.out.println("_manager is null"); }
        System.out.println("Ohhai");
        System.out.println(_localUser);
        NetworkCommand netCommand = new NetworkCommand(command, _docKey, _localUser);
        _manager.sendCommandToServer(netCommand);
    }
}
