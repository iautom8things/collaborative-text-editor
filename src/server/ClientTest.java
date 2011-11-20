package server;

import java.lang.SecurityManager;
import java.lang.Exception;
import java.rmi.*;

public class ClientTest {

    public static void main ( String[] args ) throws Exception{
      if (System.getSecurityManager() == null) {
          System.setSecurityManager(new ZeroSecurityManager());
          System.out.println(System.getSecurityManager());
      }
        ServerCommandListener temp = (ServerCommandListener) Naming.lookup("rmi://localhost/CommandListener");
    }
}
