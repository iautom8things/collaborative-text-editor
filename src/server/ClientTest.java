package server;

import java.lang.SecurityManager;
import java.lang.Exception;
import java.rmi.*;
import user.*;
import java.net.InetAddress;
import java.rmi.ConnectException;
import java.awt.Color;

public class ClientTest {

    public static void main ( String[] args ) throws Exception{
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new ZeroSecurityManager());
            System.out.println("Success setting Security Manager.");
        }
        try {
            ServerCommandListenerInterface temp = (ServerCommandListenerInterface) Naming.lookup("rmi://localhost/CommandListener");
            CTEUser u = new CTEUser("name", InetAddress.getLocalHost(), Color.RED);
            temp.registerClient(u);
        }
        catch (ConnectException ce) { System.out.println("Unable to connect to server!"); }
        catch (Exception e) { e.printStackTrace(); }
        System.out.println("Success receiving ServerCommandListener.");
    }
}
