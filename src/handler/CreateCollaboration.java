package handler;
import user.*;

public class CreateCollaboration implements Command {


    CTEUser _user; //The user creating the collaboration
    Document _document; //The new blank
    
    /*
     * Command to connect to the server and host a new blank file.
     * @Requires:
     *      user != null
     */
    public CreateCollaboration ( CTEUser user ) {
        _user = user;
    }

    public void execute ( Document doc, UserManager userManager ) throws InvalidUserIDException, UserNotFoundException, OutOfBoundsException {
        //
    }
}
