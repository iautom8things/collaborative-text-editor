package server;

import java.rmi.*;
import java.security.*;

public class ZeroSecurityManager extends RMISecurityManager {

    @Override
    public void checkPermission ( Permission permission ) {
        System.out.println("checkPermission for : " + permission.toString());
    }
}
