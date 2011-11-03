package handler;
import user.User;
import user.CTEUserManager;


/**
 * TODO Need to update everyone behind the actual writer when he/she writes/deletes!
 *      Need to ensure CTEUserManager is thread-safe
 *
 * @author Manuel
 */
public class SharedDocument implements Document {
    private volatile StringBuffer _buffer;
    private volatile CTEUserManager _userManager;
    private volatile TextPosition _lastPosition;

    /*
     *  Create an empty SharedDocuemnt.
     *
     */
    public SharedDocument ( ) {
        _buffer = new StringBuffer();
        _userManager = new CTEUserManager();
        _lastPosition = new TextPosition();
    }

    /*
     *  Create a new SharedDocument with the given string.
     *  This will most likely be used for an offline user beginning a new
     *  shared document.
     *
     */
    public SharedDocument ( String str ) {
        _userManager = new CTEUserManager();
        _buffer = new StringBuffer(str);
    }

    /*
     *  Return the length of the Document.
     */
    public synchronized int getLength ( ) { return _buffer.length(); }

    public synchronized void insertText ( String writer, String str ) throws OutOfBoundsException {

    }

    public synchronized void deleteText ( User writer, TextPosition toPos ) {

    }

    public synchronized void moveCursorTo ( User writer, TextPosition toPos ) throws OutOfBoundsException {

    }

    public synchronized String toString ( ) {
        return _buffer.toString();
    }

}
