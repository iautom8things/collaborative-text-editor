package handler;
import user.*;
import java.io.Serializable;
import java.lang.Cloneable;
import java.lang.CloneNotSupportedException;

/**
 * The model of a Text Document.
 *
 * @author Manuel
 */
public class Document implements Serializable, Cloneable {

    private volatile StringBuffer _buffer;
    private static final boolean DEBUG = true;

    /**
     * Create an empty SharedDocuemnt.
     */
    public Document ( ) { _buffer = new StringBuffer(); }

    /**
     * Create a new SharedDocument with the given string.
     */
    public Document ( String str ) { _buffer = new StringBuffer(str); }

    /**
     * Return the length of the Document.
     */
    public synchronized int getLength ( ) { return _buffer.length(); }

    /**
     * Get a TextPosition representing the end of the Document.
     */
    public synchronized TextPosition getLastPosition ( ) throws OutOfBoundsException { return new TextPosition(_buffer.length()); }    

    /**
     * Given a writer and a string, insert the string at the writer's
     *  position then update the writer's position to be at the end of the
     *  inserted text, while also updating any other writer that was initially
     *  beyond the position of the writer.
     *
     * @Requires
     *      this.getLastPosition.isBeyond(pos)
     *      pos != null
     *      text != null
     * @Ensures
     *      new._buffer == old._buffer.subString(0,pos.getPosition()) + text + old._buffer.subString(pos.getPosition())
     */
    public synchronized void insertText ( TextPosition pos, String text ) throws OutOfBoundsException {
        TextPosition end = this.getLastPosition();
        if (pos.isBeyond(end)) { throw new OutOfBoundsException("Position cannot go beyond the end of the Document."); }

        int offset = pos.getPosition();
        _buffer.insert(offset, text);
    }

    /**
     * Given a writer and a new position, delete all character between the
     *  two positions and update all TextPositions between these two positions
     *  so they all end up at the front anchor position while decrementing all
     *  postions beyond the back position by the size of the chunk of text
     *  removed.
     *
     * @Requires
     *      from != null
     *      to != null
     *      this.getLastPosition.isBeyond(from)
     *      this.getLastPosition.isBeyond(to)
     * @Ensures
     *      new._buffer == old._buffer.subString(0, from.getPosition()) + old._buffer.subString(to.getPosition())
     */
    public synchronized void deleteText ( TextPosition from, TextPosition to) throws OutOfBoundsException {
        TextPosition end = this.getLastPosition();
        if (from.isBeyond(end) || to.isBeyond(to)) { throw new OutOfBoundsException("Neither position can go beyond the end of the Document."); }

        int fromIndex = from.getPosition();
        int toIndex = to.getPosition();
        _buffer.delete(fromIndex, toIndex);
    }

    /**
     * Return the String representation of the Document.
     */
    public synchronized String toString ( ) { return "Document{ \"" + _buffer.toString() + "\" }"; }

    /**
     * Return the contents of the Document's StringBuffer.
     */
    public synchronized String contents ( ) { return _buffer.toString(); }

    @Override
    public Object clone ( ) throws CloneNotSupportedException {
        Document clone = (Document) new Document(_buffer.toString());
        return clone;
    }
}
