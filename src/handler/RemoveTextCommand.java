package handler;
import user.*;

/**
 * A command to execute Document's remove method which removes text given a
 * user's name and an offset.
 *
 * @author Manuel
 */
public class RemoveTextCommand implements Command {

    private String _userName;
    private TextPosition _toPos;

    public RemoveTextCommand ( String userName, TextPosition toPos) {
        _userName = userName;
        _toPos = toPos;
    }

    @Override
    public void execute ( Document doc, UserManager userManager ) throws InvalidUserIDException, UserNotFoundException, OutOfBoundsException {
        User user = userManager.getUser(_userName);
        TextPosition fromPos = user.getPosition();
        TextPosition toPos = _toPos; // This is to preserve the state of the command if there's a need to swap below

        if (fromPos.isBeyond(toPos)) { // If fromPos is actually beyond toPos, then swap them
            TextPosition temp = toPos;
            toPos = fromPos;
            fromPos = temp;
        }

        doc.deleteText(fromPos, toPos);
        int deletionRange = fromPos.getPosition() - toPos.getPosition();
        userManager.updateBetween(fromPos, toPos);
        userManager.updateBeyond(toPos, deletionRange);
        userManager.setCursorForUser(_userName, fromPos);
    }
}
