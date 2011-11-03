package user;

import java.awt.Color;

/**
 * A utility to return a different Color for sizes up to 7.
 */
public class ColorList {

    /*
     * Returns a different color for input 0-7. After 7, returns DARK_GRAY.
     * @Requires
     *      int size >= 0
     */
    public static Color getColor ( int size ) {
        Color result;
        if (size == 0) { result = Color.BLACK; }
        else if (size == 1) { result = Color.RED; }
        else if (size == 2) { result = Color.BLUE; }
        else if (size == 3) { result = Color.CYAN; }
        else if (size == 4) { result = Color.GREEN; }
        else if (size == 5) { result = Color.PINK; }
        else if (size == 6) { result = Color.MAGENTA; }
        else if (size == 7) { result = Color.ORANGE; }
        else { result = Color.DARK_GRAY; }

        return result;
    }
}
