package handler;

public class OutOfBoundsException extends Exception {

    /*
     * An Exception for TextPositions that go out of bounds.
     */
    public OutOfBoundsException ( String message ) {
        super("Out of Bounds Exception: " + message);
    }

}
