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
    public static Color getColor(int size){
        if(size == 0){
            return Color.BLACK;
        }
        else if(size == 1){
            return Color.RED;
        }
        else if(size == 2){
            return Color.BLUE;
        }
        else if(size == 3){
            return Color.CYAN;
        }
        else if(size == 4){
            return Color.GREEN;
        }
        else if(size == 5){
            return Color.PINK;
        }
        else if(size == 6){
            return Color.MAGENTA;
        }
        else if(size == 7){
            return Color.ORANGE;
        }
        else {
            return Color.DARK_GRAY;
        }
    }
    
}
