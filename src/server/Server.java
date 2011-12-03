package server;

import java.rmi.*;
import java.lang.Exception;
import security.ZeroSecurityManager;

public class Server {

    private static final String HOST = "localhost";

    public static void main (String[] args) throws Exception {
        if (System.getSecurityManager() == null) {System.setSecurityManager(new ZeroSecurityManager());}
        ServerCommandListener temp = new ServerCommandListener();
        String rmiObjectName = "rmi://" + HOST + "/CommandListener";
        Naming.rebind(rmiObjectName, temp);
        System.out.println("Binding Complete to " + rmiObjectName+ " ...\n");
    }
}
