package server;

import java.lang.SecurityManager;
import java.lang.Exception;
import java.rmi.*;
import user.*;
import commands.*;
import handler.*;
import java.net.InetAddress;
import java.rmi.ConnectException;
import java.awt.Color;
import java.lang.Thread;

public class ClientTest {

    private static final String HOST = "localhost";

    private static Client _client;

    public static void main ( String[] args ) throws Exception {
        _client = new Client();
        ClientCommandServerThread ccsThread = new ClientCommandServerThread(_client);
        ccsThread.start();
    }
}
