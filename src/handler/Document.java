package handler;
import user.*;


/**
 * The model of a Document.
 *
 * @author Manuel
 */
public class Document {
    private volatile StringBuffer _buffer;
    private volatile TextPosition _end;

    /*
     *  Create an empty SharedDocuemnt.
     *
     */
    public Document ( ) {
        _buffer = new StringBuffer();
        _end = new TextPosition();
    }

    /*
     *  Create a new SharedDocument with the given string.
     *  This will most likely be used for an offline user beginning a new
     *  shared document.
     *
     */
    public Document ( String str ) {
        _buffer = new StringBuffer(str);
        try { _end = new TextPosition(str.length()); }
        catch (OutOfBoundsException oobe) { System.err.println(oobe.getMessage()); }
    }

    /*
     *  Return the length of the Document.
     */
    public int getLength ( ) { return _buffer.length(); }

    private synchronized void updateEndPosition ( ) {
        int size = this.getLength();
        try { _end.setPosition(size); }
        catch (OutOfBoundsException oobe) { System.err.println(oobe.getMessage()); }
    }
    /*
     *  Given a writer and a string, insert the string at the writer's
     *  position then update the writer's position to be at the end of the
     *  inserted text, while also updating any other writer that was initially
     *  beyond the position of the writer.
     */
    public synchronized void insertText ( TextPosition pos, String text ) {
        int offset = pos.getPosition();

        _buffer.insert(offset, text);
        this.updateEndPosition();
    }

    /*
     *  Given a writer and a new position, delete all character between the
     *  two positions and update all TextPositions between these two positions
     *  so they all end up at the front anchor position while decrementing all
     *  postions beyond the back position by the size of the chunk of text
     *  removed.
     */
    public synchronized void deleteText ( TextPosition from, TextPosition to) {
        int fromIndex = from.getPosition();
        int toIndex = to.getPosition();

        _buffer.delete(fromIndex, toIndex);
        this.updateEndPosition();
    }

    public synchronized String toString ( ) {
        return _buffer.toString();
    }

}

