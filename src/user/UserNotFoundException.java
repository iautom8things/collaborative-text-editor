package user;

/*
 * An Exception for when a User is not found
 */
public class UserNotFoundException extends Exception {

    public UserNotFoundException ( String userID ) {
        super("User not found: " + userID);
    }

}
