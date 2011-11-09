package handler;
import user.*;
import java.util.Observer;

/**
 *
 *
 * @author Manuel
 */
public class AddObserverCommand implements Command {

    private Observer _observer;

    public AddObserverCommand ( Observer observer ) { _observer = observer; }

    @Override
    public void execute ( Document doc, UserManager userManager ) throws InvalidUserIDException, OutOfBoundsException, UserNotFoundException {
        doc.addObserver(_observer);
    }
}
