package user;

public class UserNotFoundException extends Exception {
    
    public UserNotFoundException(String userID){
        super("User not found: " + userID);
    }
    
}
