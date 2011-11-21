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

    private volatile ConcurrentMap<DocumentKey, DocumentController> _documentMap;

    public ServerCommandListener ( ) throws Exception {
        if (DEBUG) { System.out.println("ServerCommandListener Constructor Called."); }

        _documentMap = new ConcurrentHashMap<DocumentKey, DocumentController>();
    }

    @Override
    public synchronized void execute ( NetworkCommand netCommand ) throws RemoteException {
        if (DEBUG) { System.out.println("execute Called with argument: " + netCommand); }

        Command docCommand = netCommand.getCommand();
        DocumentKey key = netCommand.getDocumentKey();
        DocumentController docController = _documentMap.get(key);
        CTEUserManager userManager = docController.getUserManager();

        // Execute the command on the correct DocumentController
        try { docController.executeCommand(docCommand); }
        catch (InvalidUserIDException e ) { System.out.println("\n"+"IUIE"+"\n"); e.printStackTrace(); }
        catch (UserNotFoundException e ) { System.out.println("\n"+"UNFE"+"\n"); e.printStackTrace(); }
        catch (OutOfBoundsException e ) { System.out.println("\n"+"OOBE"+"\n"); e.printStackTrace(); }

        // for all Users attached to this docController, forward the network
        // command to each of them
        for (CTEUser user : docController.getUsers()) {
            InetAddress ipAddress = user.getIPAddress();
            String host = ipAddress.getHostAddress();
            try { ServerCommandListenerInterface commListener = (ServerCommandListenerInterface) Naming.lookup("rmi://" + host + "/CommandListener"); }
            catch (ConnectException ce) { System.out.println("Unable to Connect to " + host); }
            catch (Exception e) { e.printStackTrace(); }
        }
    }
}
