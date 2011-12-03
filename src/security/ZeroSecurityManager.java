package security;

import java.rmi.*;
import java.security.*;
public class ZeroSecurityManager extends RMISecurityManager {
    public void checkPermission(Permission permission) {
        System.out.println("checkPermission for : "
        + permission.toString());
    }
}
