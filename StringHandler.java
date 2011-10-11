public class StringHandler {

    /**
     *  Invariants:
     *  All values contained by _cursorIndices will never be negative or
     *  larger than _buffer.length().
     */

    private volatile StringBuffer _buffer;
    private volatile int[] _cursorIndices;

    /**
     *  Constructor
     */

    public StringHandler ( String str , int numWriters) {
        _buffer = new StringBuffer (str);
        _cursorIndices = new int[numWriters];
    }

    /*****************
     *    Queries    *
     *****************/

    public synchronized int getIndex ( int writer ) {
        assert(writer < _cursorIndices.length);

        return _cursorIndices[writer];
    }

    public synchronized int getLength ( ) {
        return _buffer.length();
    }

    public synchronized String toString ( ) {
        return _buffer.toString();
    }

    public int numWriters ( ) {
        return _cursorIndices.length;
    }

    /******************
     *    Commands    *
     ******************/

    /**
     *  Move the cursor of a writer to a new index.
     *
     *  @Requires
     *      int writer >= 0;
     *      int writer < _cursorIndices.length;
     *      int index >= 0;
     *      int index < _buffer.length();
     *
     *  @Ensures
     *      _cursorIndices[writer] == index;
     *
     */

    public synchronized void moveCursorTo ( int writer, int index ) {
        assert(index < _buffer.length());
        assert(index >= 0);
        assert(writer < _cursorIndices.length);

        _cursorIndices[writer] = index;
    }

    /**
     *  The cursor should end up at the end of the inserted text.  So,
     *  _cursorIndices += str.length()
     */

    public synchronized void insert ( int writer, String str ) {
        assert(writer < _cursorIndices.length);

        int index = _cursorIndices[writer],
            len = _cursorIndices.length;

        _buffer.insert(index, str);

        for (int i = 0; i < len; i++) {
            if (_cursorIndices[i] >= index) {
                _cursorIndices[i] += str.length();
            }
        }
    }

}
