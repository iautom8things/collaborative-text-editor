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
    public synchronized void initDocument ( DocumentKey key, DocumentController controller ) throws RemoteException {
        if (DEBUG) { System.out.println("initDocument with key: " + key + " for DocController: " + controller); }
        if (_documentMap.containsKey(key)) { System.out.println("DOCUMENT ALREADY INITIONALIZED!"); /** Should Raise Exception*/ }
        else { _documentMap.put(key, controller); }
    }

    @Override
    public synchronized void execute ( NetworkCommand netCommand ) throws RemoteException {
        if (DEBUG) { System.out.println("execute Called with argument: " + netCommand); }

        Command docCommand = netCommand.getCommand(); if (docCommand == null) { System.out.println("docCommand was null"); }
        DocumentKey key = netCommand.getDocumentKey(); if (key == null) { System.out.println("key was null"); }
        DocumentController docController = _documentMap.get(key); if (docController == null) { System.out.println(_documentMap); }
        CTEUserManager userManager = docController.getUserManager(); if (userManager == null) { System.out.println("userManager was null"); }

        // Execute the command on the correct DocumentController
        try { docController.executeCommand(docCommand); }
        catch (InvalidUserIDException e ) { System.out.println("\n"+"IUIE"+"\n"); e.printStackTrace(); }
        catch (UserNotFoundException e ) { System.out.println("\n"+"UNFE"+"\n"); e.printStackTrace(); }
        catch (OutOfBoundsException e ) { System.out.println("\n"+"OOBE"+"\n"); e.printStackTrace(); }

        // for all Users attached to this docController, forward the network
        // command to each of them
        for (CTEUser user : docController.getUsers()) {
            if (DEBUG) { System.out.println("Sending NetCommand:\n\t" + netCommand + "\nTo User: " + user);}

            InetAddress ipAddress = user.getIPAddress();
            //String host = ipAddress.getHostAddress();
            String host = "localhost";
            try {
                if (DEBUG) { System.out.println("Connecting to host: " + host); }
                ClientCommandListenerInterface clientCommListener = (ClientCommandListenerInterface) Naming.lookup("rmi://" + host + "/ClientListener");
                clientCommListener.execute(netCommand);
            }
            catch (ConnectException ce) { System.out.println("Unable to Connect to " + host); }
            catch (Exception e) { e.printStackTrace(); }
        }
    }
}
