package server;

import commands.*;
import user.*;
import handler.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.concurrent.*;
import java.net.InetAddress;
import java.lang.Exception;

public class ServerCommandListener extends UnicastRemoteObject implements ServerCommandListenerInterface {

    private boolean DEBUG = true;

    private ConcurrentMap<DocumentKey, DocumentController> _documentMap;
    private ConcurrentMap<User, InetAddress> _userAddressMap; // Really just need a concurrent set, but in order to use ConcurrentSkipListSet User needs to Implenet Comparable

    public ServerCommandListener ( ) throws Exception {
        if (DEBUG) { System.out.println("ServerCommandListener Constructor Called."); }
        _documentMap = new ConcurrentHashMap<DocumentKey, DocumentController>();
        _userAddressMap = new ConcurrentHashMap<User, InetAddress>();
    }

    /**
     * Should possibly be implemented with a Command
     */
    public void registerClient (CTEUser user) throws RemoteException {
        if (DEBUG) { System.out.println("register Called with argument: " + user); }
        InetAddress ipAddress = user.getIPAddress();
        _userAddressMap.put(user, ipAddress);
    }

    /**
     * Should possibly be implemented with a Command
     */
    public void unregisterClient (CTEUser user) throws RemoteException {
        if (DEBUG) { System.out.println("unregister Called with argument: " + user); }
        _userAddressMap.remove(user);
    }

    public void execute ( NetworkCommand netCommand ) throws RemoteException {
        if (DEBUG) { System.out.println("execute Called with argument: " + netCommand); }
        Command docCommand = netCommand.getCommand();
        DocumentKey key = netCommand.getDocumentKey();
        DocumentController docController = _documentMap.get(key);

        try { docController.executeCommand(docCommand); }
        catch (InvalidUserIDException e ) { e.printStackTrace(); }
        catch (UserNotFoundException e ) { e.printStackTrace(); }
        catch (OutOfBoundsException e ) { e.printStackTrace(); }

        // for all Users attached to this docontroller, forward the network
        // command to each of them
        for (InetAddress ipAddress: _userAddressMap.values()) {
            String host = ipAddress.getHostAddress();
            try { ServerCommandListenerInterface commListener = (ServerCommandListenerInterface) Naming.lookup("rmi://" + host + "/CommandListener"); }
            catch (ConnectException ce) { System.out.println("Unable to Connect to " + host); }
            catch (Exception e) { e.printStackTrace(); }
        }
    }
}
