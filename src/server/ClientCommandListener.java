package server;

import commands.*;
import user.*;
import handler.*;
import java.rmi.*;
import java.rmi.server.*;
import java.lang.Exception;

public class ClientCommandListener extends UnicastRemoteObject implements ClientCommandListenerInterface {

    private boolean DEBUG = true;

    private Client _client;

    public ClientCommandListener ( Client client) throws Exception {
        if (DEBUG) { System.out.println("ClientCommandListener Constructor Called."); }
        _client = client;
    }

    @Override
    public void execute ( NetworkCommand netCommand ) throws RemoteException {
        if (DEBUG) { System.out.println("execute Called with argument: " + netCommand); }
        try { _client.executeNetworkCommand(netCommand); }
        catch (InvalidUserIDException e ) { e.printStackTrace(); }
        catch (UserNotFoundException e ) { e.printStackTrace(); }
        catch (OutOfBoundsException e ) { e.printStackTrace(); }
    }
}
