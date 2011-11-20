package network;

import java.io.*;
import java.net.*;
import java.util.*;
import handler.*;

/**
 * Much of this class was copied from the networking source Kyle posted in
 * Google Docs.
 */
public class ClientNetworkManager {

    private static InetAddress _host; //Using localhost for now
    private int _port; //Using 1234 for now
    private Socket _socket;
    private BufferedReader _input; //listener for messages from the client
    private ObjectOutputStream _output;

    public ClientNetworkManager ( ) {
        try {
            _host = InetAddress.getLocalHost();
            _port = 1234;
            _socket = null;
            _input = null;
            _output = null;
        }
        catch (UnknownHostException uhEx) {
            //TO DO: Better exception handling
            System.out.println("Host ID not found!");
            uhEx.printStackTrace();
        }
    }

    public void connect ( ) {
        try {
            _socket = new Socket(_host, _port);
            _input = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
            _output = new ObjectOutputStream(_socket.getOutputStream());

            //TO DO: Fix hack; the following line listens to the server because the servers greets first on connect
            //_input.readObject();
            //_input.readLine();
        }
        catch (IOException ioEx) {
            //TO DO: Better exception handling
            System.out.println("IO Exception Caught!");
            ioEx.printStackTrace();
        }
    }

    public void sendCommandToServer ( NetworkCommand networkCommand ) {
        try {
            _output.writeObject(networkCommand);


            //Accept response from server on the socket's intput stream...
            //TO DO: Implement this. Acknowledgements from the server
            //response = _input.nextLine();
            //String receivedFromTheServer = _input.readLine();
            /*
            if(receivedFromTheServer instanceof NetworkCommand){
            NetworkCommand nc = (NetworkCommand)receivedFromTheServer;
            }else{
             */
            //System.out.println(receivedFromTheServer);
            //}

            //Display server's response to user...
            //System.out.println("\nSERVER> " + response);
        }
        catch (IOException ioe) { ioe.printStackTrace(); }
        catch (NullPointerException npe) { npe.printStackTrace(); }
    }

    /*
     * Disconnect from the Server. Will be used when the user clicks exit
     * collaboration, or exits the application
     */
    public void disconnect() {
        //TO DO: Better exception handling
        try { _socket.close(); }
        catch (IOException ioEx) { System.out.println("Unable to disconnect!"); }
    }
}
