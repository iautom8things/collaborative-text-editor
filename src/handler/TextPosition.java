package handler;
import java.lang.Comparable;
import java.lang.ClassCastException;

/**
 * A position in a document.
 */
public class TextPosition implements Comparable {

    int _position; //The position where this is at
    public final static int MINPOSITION = 0; //The minimum position possible
    public final static int MAXPOSITION = Integer.MAX_VALUE; //The maximum position possible

    /*
     * Create a new TextPosition with position at the origin ( _position = 0 )
     * @Ensures:
     * _position == 0;
     */
    public TextPosition ( ) { _position = 0; }

    /*
     * Create a new TextPosition with initial position not at origin.
     * @Requires:
     *      initialPosition >= MINPOSITION
     *      initialPosition <= MAXPOSITION
     * @Ensures:
     *      _position == initialPosition
     */
    public TextPosition ( int initialPosition ) throws OutOfBoundsException {
        if (initialPosition < MINPOSITION) { throw new OutOfBoundsException("TextPosition cannot be negative."); }

        _position = initialPosition;
    }
    /*
     * Increment the position by 1
     * @Requires:
     *      _position != MAXPOSITION
     * @Ensures:
     *      _position ++;
     */
    public void increment ( ) throws OutOfBoundsException {
        if (_position == MAXPOSITION) { throw new OutOfBoundsException("Maximum position reached. Can not increment position."); }

        _position = _position + 1;
    }

    /*
     * Increment the position by some amount
     * @Requires:
     *      amount <= MAXPOSITION - _position
     * @Ensures:
     *      new._position = old._position + amount
     */
    public void incrementBy ( int amount ) throws OutOfBoundsException {
        int maxIncrementSize = MAXPOSITION - _position;
        if (amount > MAXPOSITION) { throw new OutOfBoundsException("Maximum position reached. Can not increment position."); }

        _position = _position + amount;
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

        _position = _position - 1;
    }

    /*
     * Decrement the position by some amount
     * @Requires:
     *      _position >= amount
     * @Ensures:
     *      new._position = old._position - amount
     */
    public void decrementBy ( int amount ) throws OutOfBoundsException {
        if (amount > _position) { throw new OutOfBoundsException("Minimum position reached. Can not decrement position."); }

        _position = _position - amount;
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

    @Override
    public boolean equals ( Object other ) {
        if (other == null) { return false; }
        if (other == this) { return true; }
        if (other.getClass() != this.getClass()) { return false; }

        TextPosition otherTP = (TextPosition) other;
        return _position == otherTP.getPosition();
    }

    @Override
    public int hashCode ( ) {
        String result = "" + _position;
        return result.hashCode();
    }

    /*
     * Compare this TextPosition with another TextPosition.
     * @Requires:
     *      other instanceof TextPosition
     * @Ensures:
     *      returns a negative number if this._position < other.getPosition()
     *      returns 0 if this._position == other.getPosition()
     *      return a positive number if this._position > other.getPosition()
     */
    @Override
    public int compareTo ( Object other ) {
        if (!(other instanceof TextPosition)) { throw new ClassCastException("Can only compare to another TextPosition."); }
        int otherPosition = ((TextPosition) other).getPosition();
        return _position - otherPosition;
    }

    /*
     * Determine if this TextPosition is beyond another TextPosition.
     */
    public boolean isBeyond (TextPosition other ) { return this.compareTo(other) > 0; }
}
