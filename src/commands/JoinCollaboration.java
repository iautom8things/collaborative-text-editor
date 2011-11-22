package commands;
import handler.*;
import user.*;
import java.lang.Cloneable;
import java.lang.CloneNotSupportedException;

public class JoinCollaboration implements Command, Cloneable {

    public JoinCollaboration ( String userName, TextPosition toPos) {

    }

    @Override
    public void execute ( DocumentController controller ) throws InvalidUserIDException, UserNotFoundException, OutOfBoundsException {

    }

    @Override
    public Object clone ( ) throws CloneNotSupportedException { return null; }
}
