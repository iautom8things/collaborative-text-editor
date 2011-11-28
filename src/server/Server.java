package server;

import java.rmi.*;
import java.lang.Exception;

public class Server {

    private static final String HOST = "localhost";

    public static void main (String[] args) throws Exception {
        ServerCommandListener temp = new ServerCommandListener();
        String rmiObjectName = "rmi://" + HOST + "/CommandListener";
        Naming.rebind(rmiObjectName, temp);
        System.out.println("Binding Complete to " + rmiObjectName+ " ...\n");
    }
}
