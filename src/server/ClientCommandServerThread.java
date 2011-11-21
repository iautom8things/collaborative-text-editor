package server;

import java.lang.SecurityManager;
import java.lang.Exception;
import java.rmi.*;
import user.*;
import commands.*;
import handler.*;
import java.net.InetAddress;
import java.rmi.ConnectException;
import java.awt.Color;
import java.lang.Thread;

public class ClientCommandServerThread extends Thread {

    private static final String HOST = "localhost";

    private Client _client;

    public ClientCommandServerThread ( Client client ) { _client = client; }

    public void run ( ) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new ZeroSecurityManager());
            System.out.println("Success setting Security Manager.");
        }
        try {
            // Set up the local Command Listener for the client
            ClientCommandListener cCommListener = new ClientCommandListener(_client);
            String rmiObjectName = "rmi://" + HOST + "/ClientListener";
            Naming.rebind(rmiObjectName, cCommListener);
        }
        catch (ConnectException ce) { System.out.println("Unable to connect to server!"); }
        catch (Exception e) { e.printStackTrace(); }
        System.out.println("Success receiving ServerCommandListener.");
    }
}
