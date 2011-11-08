package handler;
import user.*;


/**
 * TODO Need to update everyone behind the actual writer when he/she writes/deletes!
 *      Need to ensure CTEUserManager is thread-safe
 *
 * @author Manuel
 */
public class Document {
    private volatile StringBuffer _buffer;
    private volatile UserManager _userManager;
    private volatile TextPosition _lastPosition;

    /*
     *  Create an empty SharedDocuemnt.
     *
     */
    public Document ( ) {
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
    public Document ( String str ) {
        _userManager = new CTEUserManager();
        _buffer = new StringBuffer(str);
    }

    /*
     *  Return the length of the Document.
     */
    public synchronized int getLength ( ) { return _buffer.length(); }

    /*
     *  Given a writer and a string, insert the string at the writer's
     *  position then update the writer's position to be at the end of the
     *  inserted text, while also updating any other writer that was initially
     *  beyond the position of the writer.
     */
    public synchronized void insertText ( String writer, String str ) throws OutOfBoundsException , UserNotFoundException {
        User usr = _userManager.getUser(writer);
        TextPosition pos = usr.getPosition();
        int index = pos.getPosition();
        int len = str.length();

        _buffer.insert(index, str);
        _userManager.updateBeyond(pos, len);
        pos.incrementBy(len);
    }

    /*
     *  Given a writer and a new position, delete all character between the
     *  two positions and update all TextPositions between these two positions
     *  so they all end up at the front anchor position while decrementing all
     *  postions beyond the back position by the size of the chunk of text
     *  removed.
     */
    public synchronized void deleteText ( String writer, TextPosition toPos ) throws OutOfBoundsException, UserNotFoundException {
        User usr = _userManager.getUser(writer);
        TextPosition fromPos = usr.getPosition();

        if (fromPos.isBeyond(toPos)) {
            // If fromPos is actually beyond toPos, then swap them
            TextPosition temp = toPos;
            toPos = fromPos;
            fromPos = temp;
        }

        int fromIndex = fromPos.getPosition();
        int toIndex = toPos.getPosition();
        int range = fromIndex - toIndex;

        _buffer.delete(fromIndex, toIndex);;
        _userManager.updateBetween(fromPos, toPos);
        _userManager.updateBeyond(toPos, range);
        _userManager.setCursorForUser(writer, fromPos);

    }

    public synchronized String toString ( ) {
        return _buffer.toString();
    }

}

