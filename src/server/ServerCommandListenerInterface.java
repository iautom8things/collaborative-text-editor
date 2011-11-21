package server;

import commands.NetworkCommand;
import user.CTEUser;
import java.rmi.*;
import handler.*;

public interface ServerCommandListenerInterface extends Remote {

    public void initDocument ( DocumentKey key, DocumentController controller ) throws RemoteException;

    public void execute ( NetworkCommand netCommand ) throws RemoteException;
}
