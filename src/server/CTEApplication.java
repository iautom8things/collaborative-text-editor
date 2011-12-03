package server;

import user.*;
import gui.*;
import handler.*;
import java.util.Observable;
import static java.lang.System.out;

/*
 * A Collaborative Text Editor Application.
 */
public class CTEApplication {

    /*
     * Run the application. Create a Client. Create a GUI, which will observe the Client.
     */
    public static void main( String[] args ) {
        Client _client = new Client();
        EditorGUI gui = new EditorGUI( _client );
        _client.addObserver( gui );
        gui.launch();
    }

}
