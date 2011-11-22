package commands;
import user.*;
import handler.*;
import java.lang.Cloneable;
import java.lang.CloneNotSupportedException;

public class DisconnectFromCollaboration implements Command, Cloneable {



    public DisconnectFromCollaboration ( String userName, TextPosition toPos ) {

    }

    @Override
    public void execute ( DocumentController controller ) throws InvalidUserIDException, UserNotFoundException, OutOfBoundsException {

    }

    @Override
    public Object clone ( ) throws CloneNotSupportedException { return null; }
}
