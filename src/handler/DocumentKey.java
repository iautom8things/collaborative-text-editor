package handler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.Cloneable;
import java.lang.CloneNotSupportedException;
/*
 * A Key for sending the Document name and password to the server.
 */
public class DocumentKey implements Serializable, Cloneable {

    private String _documentName; //Name of the Document
    private String _pass; //Password to connect to the Document

    /*
     * DEFAULT CONSTRUCTOR
     * Will create a new DocumentKey with default variable values.
     * @Ensures
     *  this.getName() == "default_document"
     *  this.getPass() == "default_password"
     */
    public DocumentKey () {
      this._documentName = "default_document";
      this._pass = "default_password";
    }

    /*
     * Create a new DocumentKey.
     *  @Requires
     *      documentName != null
     *      pass != pass
     *  @Ensures
     *      _documentName = documentName
     *      _pass = pass
     */
    public DocumentKey ( String documentName, String pass ) {
        _documentName = documentName;
        _pass = pass;
    }

    /*
     * Will return the name of the document of this key.
     * @Ensures
     *  returns this._documentName
     */
    public String getName ( ) { return _documentName; }

    /*
     * Will return this key's password.
     * @Ensures
     *  returns this._pass
     */
    public String getPass ( ) { return _pass; }

    private void writeObject ( ObjectOutputStream out ) throws IOException {
        ObjectOutputStream.PutField fields = out.putFields();
        fields.put("_documentName", _documentName);
        fields.put("_pass", _pass);
        out.writeFields();
    }

    private void readObject ( ObjectInputStream in ) throws IOException, ClassNotFoundException {
        ObjectInputStream.GetField fields = in.readFields();
        _documentName = (String) fields.get("_documentName", null);
        _pass = (String) fields.get("_pass", null);
    }

    public String toString ( ) {
        return "DocumentKey{" + "name: " + _documentName + ", password: " + _pass + " }";
    }

    @Override
    public boolean equals ( Object other ) {
        if (other == null) { return false; }
        if (other == this) { return true; }
        if (other.getClass() != this.getClass()) { return false; }

        DocumentKey otherKey = (DocumentKey) other;
        return (_documentName.equals(otherKey.getName())) && (_pass.equals(otherKey.getPass()));
    }

    @Override
    public int hashCode ( ) { return this.toString().hashCode(); }

    @Override
    public Object clone ( ) throws CloneNotSupportedException { return new DocumentKey(_documentName, _pass); }
}
