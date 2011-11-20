package server;

import commands.*;
import user.*;
import handler.*;
import java.rmi.*;
import java.rmi.server.*;
import java.lang.Exception;

public class ClientCommandListener extends UnicastRemoteObject implements ClientCommandListenerInterface {

    private DocumentController _docController;

    public ClientCommandListener ( DocumentController controller ) throws Exception { _docController = controller; }

    public void execute ( NetworkCommand netCommand ) throws RemoteException {
        Command docCommand = netCommand.getCommand();
        CTEUser user = netCommand.getUser();

        try { _docController.executeCommand(docCommand); }
        catch (InvalidUserIDException e ) { e.printStackTrace(); }
        catch (UserNotFoundException e ) { e.printStackTrace(); }
        catch (OutOfBoundsException e ) { e.printStackTrace(); }
    }
}
