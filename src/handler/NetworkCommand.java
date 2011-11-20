package handler;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import network.*;
import user.*;

/**
 * A wrapper class for a Command and DocumentKey that allows Commands to be
 * sent to the Server.
 */

public class NetworkCommand implements Serializable {

    private Command _command;
    private DocumentKey _documentKey;
    private User _user;

    /**
     * Create a new NetworkCommand.
     *  @Requires:
     *      command != null
     *      documentKey != null
     */
    public NetworkCommand ( Command command, DocumentKey documentKey, User user ) {
        _command = command;
        _documentKey = documentKey;
        _user = user;
    }

    /**
     *  Returns the User associated with this Command.
     */
    public User getUser ( ) { return _user; }

    /**
     *  Returns the Command.
     */
    public Command getCommand ( ) { return _command; }

    /**
     *  Returns the DocumentKey this belongs to.
     */
    public DocumentKey getDocumentKey ( ) { return _documentKey; }

    /**
     *  For Serialization of this object.
     */
    private void writeObject ( ObjectOutputStream out ) throws IOException {
        ObjectOutputStream.PutField fields = out.putFields();
        fields.put("_command", _command);
        fields.put("_documentKey", _documentKey);
        fields.put("_user", _user);
        out.writeFields();
    }

    /**
     *  For Serialization of this object.
     */
    private void readObject ( ObjectInputStream in ) throws IOException, ClassNotFoundException {
        ObjectInputStream.GetField fields = in.readFields();
        _command = (Command)fields.get("_command", null);
        _documentKey = (DocumentKey)fields.get("_documentKey", null);
        _user = (CTEUser)fields.get("_user", null);
    }

    /**
     *  Return a string representation of the object.
     */
    public String toString ( ) {
        return "NetworkCommand{" + "_command: " + _command + ", _documentKey: " + _documentKey + ", _user: " + _user + " }";
    }
}
