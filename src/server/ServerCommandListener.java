package server;

import commands.*;
import user.*;
import handler.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.concurrent.*;
import java.net.InetAddress;
import java.lang.Exception;
import java.lang.CloneNotSupportedException;
import debug.Debug;

public class ServerCommandListener extends UnicastRemoteObject implements ServerCommandListenerInterface {

    private static final boolean DEBUG = Debug.VERBOSE;

    private volatile ConcurrentMap<DocumentKey, DocumentController> _documentMap;
    private volatile ConcurrentMap<CTEUser, String> _clientMap;

    public ServerCommandListener ( ) throws Exception {
        if (DEBUG) { System.out.println("ServerCommandListener Constructor Called."); }

        _documentMap = new ConcurrentHashMap<DocumentKey, DocumentController>();
        _clientMap = new ConcurrentHashMap<CTEUser, String>();
    }

    /**
     * Register a client's CommandListener with this ServerCommandListener so
     * that it knows how to send commands to it.
     */
    @Override
    public synchronized void initializeClient ( CTEUser user, String pid ) throws RemoteException {
        if (DEBUG) { System.out.println("initializeClient called with User: " + user + " pid: " + pid); }
        String host = "localhost";
        String RMIAddress = "rmi://" + host + "/ClientListener" + pid;
        if (DEBUG) { System.out.println("Adding RMIAddress: " + RMIAddress + " for User: " + user); }

        _clientMap.put(user, RMIAddress);
        if (DEBUG) { System.out.println(_clientMap); }
    }
    /**
     * Register a Client with a Document.
     *
     */
    @Override
    public synchronized void registerClient ( CTEUser user, DocumentKey key, String pid ) throws RemoteException {
        if (DEBUG) { System.out.println("registerClient called with " + user + " and pid: " + pid); }
        //this.execute(new NetworkCommand(new AddUserCommand(user), key, user));
        if (DEBUG) { System.out.println("CLIENTMAP: " + _clientMap); }
    }

    /**
     * When a Client disconnects from the server, it should remove any
     * reference the Server has to the Client.
     */
    @Override
    public synchronized void decomissionClient ( CTEUser user, String pid ) throws RemoteException {
        if (DEBUG) { System.out.println("decomissionClient called with User: " + user + " pid: " + pid); }
        assert(_clientMap.containsKey(user));
        //String host = user.getIPAddress().getHostAddress();
        String host = "localhost";
        String RMIAddress = "rmi://" + host + "/ClientListener" + pid;
        assert(_clientMap.get(user).equals(RMIAddress));

        _clientMap.remove(user);

    }

    /**
     * Try to initialize a new Document on the server.  Returns boolean of whether or not this call was Successful in initializing a NEW Document.
     */
    @Override
    public synchronized boolean initializeDocument ( DocumentKey key, DocumentController controller ) throws RemoteException {
        boolean result;
        if (DEBUG) { System.out.println("initDocument with key: " + key + " for DocController: " + controller); }
        if (_documentMap.containsKey(key)) {
            if (DEBUG) { System.out.println("FAILURE: Document already initalized."); }

            result = false;
        }
        else {
            if (DEBUG) { System.out.println("Successful initalization of document: " + key); }

            try { _documentMap.put(key, (DocumentController) controller.clone()); }
            catch (CloneNotSupportedException cnse) { cnse.printStackTrace(); }
            result = true;
        }
        return result;
    }

    /**
     * Join an existing Document.
     */
    @Override
    public synchronized DocumentController joinDocument ( CTEUser newUser, DocumentKey key ) throws RemoteException {
        if (DEBUG) { System.out.println("joinDocument called by User: " + newUser + " For Document: " + key); }
        assert(_clientMap.containsKey(newUser));
        assert(_documentMap.containsKey(key));



        return _documentMap.get(key);
    }







    @Override
    public synchronized void execute ( NetworkCommand netCommand ) throws RemoteException {
        if (DEBUG) { System.out.println("execute Called with argument: " + netCommand); }

        // Execute the command on the correct DocumentController
        try {
            NetworkCommand clonedNetCommand = (NetworkCommand) netCommand.clone();
            Command docCommand = clonedNetCommand.getCommand();
            DocumentKey key = clonedNetCommand.getDocumentKey();
            DocumentController docController = _documentMap.get(key);
            docController.executeCommand(docCommand);

            //if (DEBUG) { System.out.println("UserManager: " + docController.getUserManager()); }

            // for all Users attached to this docController, forward the network
            // command to each of them
            for (CTEUser user : docController.getUsers()) {
                if (DEBUG) {
                    System.out.println("Sending NetCommand To Client @: " + user);
                    System.out.println(">>> ClientMap: " + _clientMap);
                }

                //InetAddress ipAddress = user.getIPAddress();
                //String host = ipAddress.getHostAddress();
                //String host = "localhost";
                try {
                    String RMIAddress =_clientMap.get(user);
                    if (DEBUG) { System.out.println("Connecting to host: " + user + " |**| " + RMIAddress); }
                    ClientCommandListenerInterface clientCommListener = (ClientCommandListenerInterface) Naming.lookup(RMIAddress);
                    clientCommListener.execute(netCommand);
                }
                catch (ConnectException ce) { System.out.println("Unable to Connect to " + user); ce.printStackTrace(); }
                catch (Exception e) { e.printStackTrace(); }
            }
        }
        catch (InvalidUserIDException e) { System.out.println("\n"+"IUIE"+"\n"); e.printStackTrace(); }
        catch (UserNotFoundException e) { System.out.println("\n"+"UNFE"+"\n"); e.printStackTrace(); }
        catch (OutOfBoundsException e) { System.out.println("\n"+"OOBE"+"\n"); e.printStackTrace(); }
        catch (CloneNotSupportedException e) { System.out.println("\n"+"CNSE"+"\n"); e.printStackTrace(); }
    }
}
