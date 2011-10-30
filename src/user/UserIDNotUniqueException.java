package user;

public class UserIDNotUniqueException extends Exception {
    
    public UserIDNotUniqueException(String userID){
        super("User ID is not unique" + userID);
    }
    
}
