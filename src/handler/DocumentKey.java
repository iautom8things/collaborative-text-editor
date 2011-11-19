package handler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 * A Key for sending the Document name and password to the server.
 */
public class DocumentKey implements Serializable{

    private String _documentName; //Name of the Document
    private String _pass; //Password to connect to the Document

    /*
     * Create a new DocumentKey.
     *  @Requires
     *      documentName != null
     *      pass != pass
     *  @Ensures
     *      _documentName = documentName
     *      _pass = pass
     */
    public DocumentKey(String documentName, String pass) {
        _documentName = documentName;
        _pass = pass;
    }

    /*
     * 
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        ObjectOutputStream.PutField fields = out.putFields();
        fields.put("_documentName", _documentName);
        fields.put("_pass", _pass);
        out.writeFields();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream.GetField fields = in.readFields();
        _documentName = (String) fields.get("_documentName", null);
        _pass = (String) fields.get("_pass", null);
    }

    public String toString() {
        return "DocumentKey{" + "_documentName=" + _documentName + ", _pass=" + _pass + '}';
    }
}