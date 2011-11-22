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

    private static final boolean DEBUG = true;

    private ClientCommandServerThread _commServerThread;
    private ServerCommandListenerInterface _serverCommandListener;
    private DocumentController _controller;
    private ClientNetworkManager _manager;
    private CTEUser _localUser;
    private DocumentKey _docKey;
    private boolean _isCollaborating;

    /**
     * Create a new Client with a blank Document.
     * Ensures:
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


    /**
     * Return whether this Client is in Collaboration or not.
     */
    public boolean isCollaborating ( ) { return _isCollaborating; }

    /**
     * Return a String representation of this Client.
     */
    public String toString ( ) {
        return "Client{ " + "_controller: " + _controller.toString() + "_isCollaborating: " + _isCollaborating + " }";
    }

    /**
     * Return the CTEUser of this Client.
     */
    public CTEUser getUser ( ) { return _localUser; }


    /************
     * Commands *
     ***********/


    /**
     * Creates a new Document with the given String.
     *
     * Requires:
     *      text != null
     * Ensures:
     *      this.toString() == text
     */
    public synchronized void setDocument ( String text ) {
        Document newDoc = new Document(text);
        _controller.setDocument(newDoc);

        //update text panes
        notifyDocumentGotUpdated();
    }

    /**
     * Given a command if this Client IS NOT collaborating, execute it on this._controller,
     *                  if this Client IS collaborating, wrap it with a
     *                  NetworkCommand and Forward it to the Server.
     *
     */
    public void passCommand ( Command command ) throws RemoteException, InvalidUserIDException, UserNotFoundException, OutOfBoundsException {
        if (DEBUG) { System.out.println("passCommand called " + _localUser); }
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
    public void executeNetworkCommand ( NetworkCommand netCommand ) throws InvalidUserIDException, UserNotFoundException, OutOfBoundsException {
        // TODO Actually execute the command and notify the GUI
        if (DEBUG) {
            System.out.println("The Client has received: " + _localUser);
            System.out.println(netCommand);
        }

        Command command = netCommand.getCommand();
        _executeCommand(command);
    }

    /**
     * Initiates a Connection to the Server.
     */
    public synchronized void initateCollaboration ( ) throws NotBoundException, MalformedURLException, RemoteException {
        if (DEBUG) { System.out.println("initateCollaboration called " + _localUser); }
        _commServerThread.start();
        _serverCommandListener = (ServerCommandListenerInterface) Naming.lookup("rmi://localhost/CommandListener");

        try { _serverCommandListener.initDocument((DocumentKey)_docKey.clone(), (DocumentController) _controller.clone()); }
        catch (CloneNotSupportedException cnse) { cnse.printStackTrace(); }
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
        if (DEBUG) { System.out.println("notifyDocumentGotUpdated called " + _localUser); }
        setChanged();
        notifyObservers(_controller.getDocument().toString());
    }

    /**
     * Creates a NetworkCommand and sends it to the Server.
     */
    private void _wrapAndForward ( Command command ) throws RemoteException {
        if (DEBUG) { System.out.println("_wrapAndForward called " + _localUser); }
        NetworkCommand netCommand = new NetworkCommand(command, _docKey, _localUser);
        //_manager.sendCommandToServer(netCommand);
        _serverCommandListener.execute(netCommand);
    }

    private void _executeCommand ( Command command ) throws InvalidUserIDException, UserNotFoundException, OutOfBoundsException {
        if (DEBUG) { System.out.println("_executeCommand called " + _localUser); }
        _controller.executeCommand(command);
        notifyDocumentGotUpdated();
    }
}
