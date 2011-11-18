package network;

import java.io.*;
import java.net.*;
import java.util.*;

public class CollabTextEditClient {

    private static InetAddress host;
    private static final int PORT = 1234;

    public static void main(String[] args) {
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException uhEx) {
            System.out.println("Host ID not found!");
            System.exit(1);
        }
        accessServer();
    }

    private static void accessServer() {
        Socket link = null;

        try {
            link = new Socket(host, PORT);
            Scanner input = new Scanner(link.getInputStream());
            PrintWriter output = new PrintWriter(link.getOutputStream(), true);

            //Set up stream for keyboard entry...
            Scanner userEntry = new Scanner(System.in);
            String message, response;
            do {

                response = input.nextLine();
                System.out.println("\nSERVER> " + response);
                
                System.out.print("Enter message: ");
                message = userEntry.nextLine();
                output.println(message);
            } while (!message.equals("***CLOSE***"));
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        } finally {
            try {
                System.out.println("\n* Closing connection... *");
                link.close();
            } catch (IOException ioEx) {
                System.out.println( "Unable to disconnect!");
                System.exit(1);
            }
        }
    }
}