package server;

import java.lang.SecurityManager;
import java.lang.Exception;
import java.rmi.*;
import java.rmi.registry.*;
import user.*;
import commands.*;
import handler.*;
import java.net.InetAddress;
import java.rmi.ConnectException;
import java.awt.Color;
import java.lang.Thread;
import debug.Debug;

public class ClientCommandServerThread extends Thread {

    private static final String HOST = "localhost";

    private static final boolean DEBUG = Debug.VERBOSE;

    private Client _client;
    private String _id;

    public ClientCommandServerThread ( Client client , String id ) {
        if (DEBUG) { System.out.println("clientCommThread Constructor called with id: " + id); }
        _client = client;
        _id = id;
    }

    public void run ( ) {
        if (DEBUG) { System.out.println("thread.run called."); }
        if (System.getSecurityManager() == null) {System.setSecurityManager(new SecurityManager());}
        try {
            // Set up the local Command Listener for the client
            ClientCommandListener cCommListener = new ClientCommandListener(_client);
            String rmiObjectName = "rmi://" + HOST + "/ClientListener" + _id;
            if (DEBUG) { System.out.println("Binding to address: " + rmiObjectName); }
            Naming.rebind(rmiObjectName, cCommListener);
        }
        catch (ConnectException ce) { System.out.println("Unable to connect to server!"); }
        catch (Exception e) { e.printStackTrace(); }
        System.out.println("Success receiving ServerCommandListener.");
    }
}
