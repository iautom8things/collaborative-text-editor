package handler;

public class StringHandler implements SharedHandler {

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

    /**
     *  @Requires
     *      int writer >= 0;
     *      int writer < _cursorIndices.length;
     */

    @Override
    public synchronized int getIndex ( int writer ) {
        assert(writer < _cursorIndices.length);
        assert(writer >= 0);

        return _cursorIndices[writer];
    }

    @Override
    public synchronized int getLength ( ) {
        return _buffer.length();
    }

    @Override
    public synchronized String toString ( ) {
        return _buffer.toString();
    }

    @Override
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

    @Override
    public synchronized void moveCursorTo ( int writer, int index ) {
        assert(index < _buffer.length());
        assert(index >= 0);
        assert(writer < _cursorIndices.length);
        assert(writer >= 0);

        _cursorIndices[writer] = index;
    }

    /**
     *  Insert text at the position of the given writer's cursor.
     *
     *  Insertion is performed IN FRONT OF the cursor.  In other words, the
     *  cursor is anchored to the tight.  The cursor should end up at the
     *  end of the inserted text.
     *
     *  @Requires
     *      int writer >= 0;
     *      int writer < _cursorIndices.length;
     */

    @Override
    public synchronized void insert ( int writer, String str ) {
        assert(writer < _cursorIndices.length);
        assert(writer >= 0);

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
