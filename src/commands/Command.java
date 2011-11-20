package commands;
import handler.*;
import user.*;

/**
 * An interface for Commands that are executed by the DocumentController.
 *
 * @author Manuel
 */
public interface Command {

    public void execute ( Document document, UserManager userManager ) throws InvalidUserIDException, UserNotFoundException, OutOfBoundsException;
}
