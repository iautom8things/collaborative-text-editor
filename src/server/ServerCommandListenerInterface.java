package server;

import commands.NetworkCommand;
import user.CTEUser;
import java.rmi.*;

public interface ServerCommandListenerInterface extends Remote {

    public void execute ( NetworkCommand netCommand ) throws RemoteException;
}
