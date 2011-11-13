package user;

/*
 * An Exception for when a User ID is invalid
 */
public class InvalidUserIDException extends Exception {

    public InvalidUserIDException () {
        super("User ID can not be null");
    }
    
    public InvalidUserIDException ( String userID ) {
        super("User ID is not unique: " + userID);
    }
    
}
