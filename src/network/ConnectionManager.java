/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package network;
import java.io.IOException;

/**
 *
 * @author mlalford
 */
public class ConnectionManager {

    private CollabTextEditServer _server;

    public ConnectionManager(int port) throws IOException{
        _server = new CollabTextEditServer(null, port);
    }
}
