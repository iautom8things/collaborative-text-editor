package handler;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import network.*;
/*
 * A wrapper class for a Command and DocumentKey that allows Commands to be 
 * sent to the Server.
 */

public class NetworkCommand implements Serializable {

    private Command _command;
    private DocumentKey _documentKey;
    private ClientNetworkManager _client;
    
    /*
     * Create a new NetworkCommand. 
     *  @Requires:
     *      command != null
     *      documentKey != null
     */
    public NetworkCommand( Command command, DocumentKey documentKey ){
        _command = command;
        _documentKey = documentKey;
        _client = new ClientNetworkManager();
    }

    public ClientNetworkManager getClient() {
        return _client;
    }

    public Command getCommand() {
        return _command;
    }

    public DocumentKey getDocumentKey() {
        return _documentKey;
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException{
        ObjectOutputStream.PutField fields = out.putFields();
        fields.put("_command", _command);
        fields.put("_documentKey", _documentKey);
        out.writeFields();
    }
    
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
        ObjectInputStream.GetField fields = in.readFields();
        _command = (Command)fields.get("_command", null);
        _documentKey = (DocumentKey)fields.get("_documentKey", null);
    }

    public String toString() {
        return "NetworkCommand{" + "_command=" + _command + ", _documentKey=" + _documentKey + ", _client=" + _client + '}';
    }
}