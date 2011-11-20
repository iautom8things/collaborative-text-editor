package test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.channels.ServerSocketChannel;
import handler.*;
import commands.*;
import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;
import java.util.concurrent.*;
import static java.lang.System.out;
/*
 * A tester class... receives serialized objects through a SocketChannel, since
 * the CollabTextEditServer uses those. To test, run this first. Then run the
 * CTEApplication
 */
public class clientSerializationTester {
    private static final int PORT = 1234;
    private static final String TEXT_ENCODING = "US-ASCII";
    // instance variables used internally
    private InetAddress _ip;
    private int _port;
    private Selector _selector;

    public clientSerializationTester ( InetAddress address, int port ) throws ClassNotFoundException, IOException {
        _ip = address;
        _port = port;

        startServer();
    }

    private void startServer ( ) throws ClassNotFoundException, IOException {
        _selector = Selector.open();
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        InetSocketAddress listenAddress = new InetSocketAddress(_ip, _port);
        serverChannel.socket().bind(listenAddress);
        serverChannel.register(_selector, SelectionKey.OP_ACCEPT);
        System.out.println("Server Started.");
        while (true) {
            _selector.select();
            java.util.Iterator<SelectionKey> keys = _selector.selectedKeys().iterator();
            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                keys.remove();
                if (key.isValid()) {
                    if (key.isAcceptable()) { this.accept(key); }
                    else if (key.isReadable()) { this.read(key); }
                    else if (key.isWritable()) { /**this.write(key);*/ }
                }
            }
        }
    }
    private void accept ( SelectionKey key ) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        SocketChannel channel = serverChannel.accept();
        channel.configureBlocking(false);
        Socket socket = channel.socket();
        SocketAddress remoteAddress = socket.getRemoteSocketAddress();
        System.out.println("Connected to: " + remoteAddress);
        //_dataMap.put(channel, null); /**TODO CHANGE THIS*/
        channel.register(_selector, SelectionKey.OP_READ);
    }

    private void read ( SelectionKey key) throws ClassNotFoundException, IOException {
        SocketChannel channel = (SocketChannel) key.channel();

        //try {
            ObjectInputStream objectInputStream = new ObjectInputStream(channel.socket().getInputStream());
            NetworkCommand networkCommand = (NetworkCommand) objectInputStream.readObject();
            System.out.println("object: '" + networkCommand.toString() + "'");
        //}
        //catch (Exception e) { e.printStackTrace(); }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new clientSerializationTester(null, PORT);
        //System.out.println("Receiver Start");

        ////Open a socket and start listening to it.
        ////This is needed just to get the SocketChannel for read/write
        //ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //serverSocketChannel.configureBlocking(true);
        //int port = 1234;
        //serverSocketChannel.socket().bind(new InetSocketAddress(port));

        //while(true){
            //try{
                //SocketChannel socketChannel = serverSocketChannel.accept(); //Client has connected
                //ObjectInputStream objectInputStream = new ObjectInputStream(socketChannel.socket().getInputStream());

                //NetworkCommand networkCommand = (NetworkCommand)objectInputStream.readObject();
                //System.out.println("object: '" + networkCommand.toString() + "'");
            //}catch(Exception e){ e.printStackTrace(); }
        //}
    }
}
