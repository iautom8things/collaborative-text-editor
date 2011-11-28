package server;

import commands.NetworkCommand;
import user.CTEUser;
import java.rmi.*;
import handler.*;

public interface ServerCommandListenerInterface extends Remote {

    public boolean initializeDocument ( DocumentKey key, DocumentController controller ) throws RemoteException;

    public DocumentController joinDocument ( CTEUser newUser, DocumentKey key ) throws RemoteException;

    public void execute ( NetworkCommand netCommand ) throws RemoteException;

    public void registerClient ( CTEUser user, DocumentKey key, String pid ) throws RemoteException;

    public void initializeClient ( CTEUser user, String pid) throws RemoteException;

    public void decomissionClient ( CTEUser user, String pid) throws RemoteException;


}
