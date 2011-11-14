package test;

import network.*;
import java.io.*;
import user.*;
import junit.framework.TestCase;
import static java.lang.System.out;
import java.net.*;
import java.awt.Color;

public class CollabTextEditServerTest extends TestCase {

    CollabTextEditServer _server;
    
    public static void main(String[] args) {
        out.println("Begin Main");
        CollabTextEditServerTest test = new CollabTextEditServerTest();
        test.setUp();
    }

    protected void setUp() {
        try{
            _server = new CollabTextEditServer(InetAddress.getLocalHost(), 1234);
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
