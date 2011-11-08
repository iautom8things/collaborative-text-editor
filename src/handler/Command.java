package handler;

/**
 * An interface for Commands that are executed by the DocumentController.
 *
 * @author Manuel
 */
public interface Command {

    public void execute ( Document document );
}
