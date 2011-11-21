package handler;

import commands.*;
import server.*;
import java.util.Observable;
import java.awt.event.KeyEvent;
import java.rmi.*;
import user.*;
import network.*;
import java.net.*;

public class Client extends Observable {

    private ClientCommandServerThread _commServerThread;
    private ServerCommandListenerInterface _serverCommandListener;
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
        catch (Exception e) { /** This shouldn't happen >_<.*/ }
        _docKey = new DocumentKey("DefaultDoc", "password");
        _controller = new DocumentController();
        try { _controller.executeCommand(new AddUserCommand(_localUser)); }
        catch (Exception e) { /** This shouldn't happen */ }
        _commServerThread = new ClientCommandServerThread(this);
    }


    /***********
     * Queries *
     **********/


    public boolean isCollaborating ( ) { return _isCollaborating; }

    public String toString ( ) {
        return "Client{" + "_controller: " + _controller.toString() + "_isCollaborating: " + _isCollaborating + " }";
    }

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
    public void passCommand ( Command command ) throws RemoteException, InvalidUserIDException, UserNotFoundException, OutOfBoundsException {
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
    public synchronized void initateCollaboration ( ) throws NotBoundException, MalformedURLException, RemoteException {
        _commServerThread.start();
        _serverCommandListener = (ServerCommandListenerInterface) Naming.lookup("rmi://localhost/CommandListener");

        _serverCommandListener.initDocument(_docKey, _controller);
        //_manager = new ClientNetworkManager();
        //_manager.connect();
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
    private void _wrapAndForward ( Command command ) throws RemoteException {
        NetworkCommand netCommand = new NetworkCommand(command, _docKey, _localUser);
        //_manager.sendCommandToServer(netCommand);
        _serverCommandListener.execute(netCommand);
    }
}
