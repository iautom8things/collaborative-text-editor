package handler;
import user.*;

/*
 * A wrapper class for a Command and DocumentKey that allows Commands to be 
 * sent to the Server.
 */
public class NetworkCommand {

    private Command _command;
    private DocumentKey _documentKey;
    
    /*
     * Create a new NetworkCommand. 
     *  @Requires:
     *      command != null
     *      documentKey != null
     */
    public NetworkCommand( Command command, DocumentKey documentKey ){
        _command = command;
        _documentKey = documentKey;
    }
    
    
}
