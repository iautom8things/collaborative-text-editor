/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

/**
 *
 * @author mlalford
 */
public interface SharedHandler {

    /**
     * @Requires
     * int writer >= 0;
     * int writer < _cursorIndices.length;
     */
    int getIndex(int writer);

    int getLength();

    /**
     * Insert text at the position of the given writer's cursor.
     *
     * Insertion is performed IN FRONT OF the cursor.  In other words, the
     * cursor is anchored to the tight.  The cursor should end up at the
     * end of the inserted text.
     *
     * @Requires
     * int writer >= 0;
     * int writer < _cursorIndices.length;
     */
    void insert(int writer, String str);

    /**
     * Move the cursor of a writer to a new index.
     *
     * @Requires
     * int writer >= 0;
     * int writer < _cursorIndices.length;
     * int index >= 0;
     * int index < _buffer.length();
     *
     * @Ensures
     * _cursorIndices[writer] == index;
     *
     */
    void moveCursorTo(int writer, int index);

    int numWriters();

    String toString();
    
}
