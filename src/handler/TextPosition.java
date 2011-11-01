package handler;

/**
 * A position in a document.
 */
public class TextPosition {

    int _position; //The position where this is at
    public final static int MINPOSITION = 0; //The minimum position possible
    public final static int MAXPOSITION = Integer.MAX_VALUE; //The maximum position possible

    /*
     * Create a new TextPosition
     * @Ensures:
     * _position == 0;
     */
    public TextPosition ( ) { _position = 0; }

    /*
     * Increment the position by 1
     * @Requires:
     *      _position != MAXPOSITION
     * @Ensures:
     *      _position ++;
     */
    public void increment ( ) throws OutOfBoundsException {
        if (_position == MAXPOSITION) { throw new OutOfBoundsException("Maximum position reached. Can not increment position."); }

        _position ++;
    }

    /*
     * Decrement the position by 1
     * @Requires:
     *      _position != MINPOSITION
     * @Ensures:
     *      _position --;
     */
    public void decrement( ) throws OutOfBoundsException {
        if (_position == MINPOSITION) { throw new OutOfBoundsException("Minimum position reached. Can not decrement position."); }

        _position --;
    }

    /*
     * Set the position to the specified position.
     * @Requires:
     *      position < MAXPOSITION
     *      position > MINPOSITION
     * @Ensures:
     *      _position == position;
     */
    public void setPosition( int position ) throws OutOfBoundsException {
        if (position > MAXPOSITION) { throw new OutOfBoundsException("Max Position Reached"); }
        if (position < MINPOSITION) { throw new OutOfBoundsException("Minimum Position Reached"); }

        _position = position;
    }

    /*
     * Get the position.
     * @Ensures:
     *      _position == position;
     */
    public int getPosition( ) { return _position; }

    public boolean equals( TextPosition tp ) { return _position == tp.position(); }
}
