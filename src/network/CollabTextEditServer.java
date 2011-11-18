package network;

import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;
import java.util.concurrent.*;
import static java.lang.System.out;

public class CollabTextEditServer {
    // static variables used to prevent magic values

    private static final int PORT = 1234;
    private static final String TEXT_ENCODING = "US-ASCII";
    // instance variables used internally
    private InetAddress _ip;
    private int _port;
    private Selector _selector;
    private Map<SocketChannel, List<byte[]>> _dataMap;

    /*
     * Will start an instance of this server running with the given i.p address,
     * listening on the given port number. 
     * 
     * (if given.port = 0, the system will bind a temporary port to this
     * server.)
     * 
     * @Requires
     *      0 <= given.port <= 65535
     * @Ensures
     *      IllegalArgumentException isn't thrown and the server is started
     *      successfully
     * 
     */
    public CollabTextEditServer(InetAddress address, int port) throws IOException {
        _ip = address;
        _port = port;
        // using ConcurrentHashMap to prevent:
        // java.util.ComcurrentModificationException when iterating
        _dataMap = new ConcurrentHashMap<SocketChannel, List<byte[]>>();
        // attempt to start the server,
        // lots of things going on here (the reason for abstract)
        // and will enter into its main loop.
        startServer();
    }

    // /////////////////////////////////////////////////////////////////////////
    // HELPER METHODS: _EchoServer /////////////////////////////////////////

    /*
     * Will attempt to start this server with the given i.p and port
     * information.
     */
    private void startServer() throws IOException {
        // create a new selector from the system's default selector provider
        _selector = Selector.open();

        // create an open, unbound channel
        ServerSocketChannel serverChannel = ServerSocketChannel.open();

        // set channel to Non-Blocking IO Mode
        serverChannel.configureBlocking(false);

        // create a SocketAddress instance to pair the server's i.p and port
        // number
        InetSocketAddress listenAddress = new InetSocketAddress(_ip,
                _port);

        // bind the channel to this server's port number and i.p address
        serverChannel.socket().bind(listenAddress);

        // register the server channel with the selector for key retrieval
        serverChannel.register(_selector, SelectionKey.OP_ACCEPT);

        print("Server started successfully.");

        // enter main loop
        while (true) {
            // check for i/o events
            _selector.select();
            java.util.Iterator<SelectionKey> keys = _selector.selectedKeys().iterator();

            // if any events exist, process them
            while (keys.hasNext()) {
                // save the event key
                SelectionKey key = keys.next();
                // remove the event from the list
                keys.remove();

                // if the key is valid, process it
                if (key.isValid()) {
                    // if the channel is ready to accept the key
                    if (key.isAcceptable()) {
                        // start the new connection to this server
                        this.accept(key);
                    } // if the key's channel is ready to be read
                    else if (key.isReadable()) {
                        // read the event key
                        this.read(key);
                    } // if the key can be written
                    else if (key.isWritable()) {
                        // write the key
                        this.write(key);
                    }
                }
            }// end of: Event Key loop
        }// end of: Main loop
    }

    /*
     * Will accept and establish the connection between the key event and this
     * server.
     */
    private void accept(SelectionKey key) throws IOException {
        // save the key's channel
        ServerSocketChannel serverChannel = (ServerSocketChannel) (key.channel());
        // CASTING WARNING: needed as key.channel() will return a
        // SelectableChannel, is ok because ServerSocketChannel extends
        // AbstractSelectableChannel which extends SelectableChannel.

        // save a channel with the connection to the key event accepted
        SocketChannel channel = serverChannel.accept();
        // channel is in blocking mode by default,
        // need to correct this.
        channel.configureBlocking(false);

        // write welcome message
        channel.write(ByteBuffer.wrap("Welcome, this is the echo server\r\n".getBytes(TEXT_ENCODING)));

        // save an instance of a socket associated with the channel
        Socket socket = channel.socket();
        // save the end address the socket is connected to
        // (the i.p/port where the connection is coming from)
        SocketAddress remoteAddress = socket.getRemoteSocketAddress();
        print("Connected to: " + remoteAddress);

        // register the channel with the selector for future events
        _dataMap.put(channel, new ArrayList<byte[]>());
        channel.register(_selector, SelectionKey.OP_READ);
    }

    /*
     * Will read event information from the connection. If there is no data
     * read, the connection is assumed to have been severed. If data remains, it
     * is handled accordingly.
     */
    private void read(SelectionKey key) throws IOException {

        SocketChannel channel = (SocketChannel) key.channel();

        // clear up some memory space for the buffer
        // no idea why 8192 in size, seems to be a standard
        // i.e: everyone else is doing it
        ByteBuffer buffer = ByteBuffer.allocate(8192);

        // used to determine how many bytes are read from the channel
        int numRead = -1;

        // try to read from the channel, into the buffer
        try {
            numRead = channel.read(buffer);
        } // if an IOException is caught
        catch (IOException e) {
            // print the stack trace
            e.printStackTrace();
        }

        // if the end of the buffer stream was reached
        if (numRead == -1) {
            // the connection has been severed,
            // remove connection from the map
            _dataMap.remove(channel);

            // This is only done for some fancy close message formatting.//
            // save the socket to retrieve the remote i.p address for print out
            Socket socket = channel.socket();
            SocketAddress remoteAddr = socket.getRemoteSocketAddress();
            print("Connection closed by client: " + remoteAddr);

            // close connection
            channel.close();
            // add the key to the canceled-key set
            key.cancel();
            // NOTE: if already closed or canceled, produces no errors.
            // This is for safety measures only.
        } // otherwise,
        // print the received string
        else {
            // set up a byte array to hold the info
            byte[] data = new byte[numRead];
            // copy the information in the first array to the second
            System.arraycopy(buffer.array(), 0, data, 0, numRead);

            // print the array's data
            print("Got: " + new String(data, TEXT_ENCODING));

            // echo the data back to the client
            doEcho(key, data);
        }
    }

    /*
     * Will write all queued up data for the given key's channel to the channel
     * and sets the key to read mode.
     */
    private void write(SelectionKey key) throws IOException {
        // save the connection
        SocketChannel channel = (SocketChannel) key.channel();
        // retrieves the bytes associated with the channel awaiting
        // processing and sets up a traversal method.
        List<byte[]> pendingData = _dataMap.get(channel);
        java.util.Iterator<byte[]> items = pendingData.iterator();

        // if there is data left in the stream
        while (items.hasNext()) {
            // save the data
            byte[] item = items.next();
            // remove it from the list so as to not be processed multiple times
            items.remove();

            // write the data to the channel
            channel.write(ByteBuffer.wrap(item));
        }
        // set the key to read mode
        key.interestOps(SelectionKey.OP_READ);
    }

    /*
     * Will send the given data to the given key's channel,
     * echoing it back for the client to read.
     */
    // NOTE: this is strictly for the echo implementation and not necessarily
    // needed for our purposes. ALTHOUGH, the algorithm for sending data back to
    // the client is important. We will need to of course understand the basics
    // but with the added layering process included.
    private void doEcho(SelectionKey key, byte[] data) {
        // save the given key's channel
        SocketChannel channel = (SocketChannel) key.channel();

        // retrieves the bytes associated with the channel awaiting processing
        List<byte[]> pendingData = _dataMap.get(channel);

        // add to it the given data
        pendingData.add(data);

        // set the key to write mode
        key.interestOps(SelectionKey.OP_WRITE);
    }

    /*
     * Will print the given string to the command line. A quick is dirty way to
     * use out.println(...)
     */
    private static void print(String s) {
        out.println(s);
    }

    /*
     * Creates a new instance of this server.
     */
    public static void main(String[] args) throws Exception {
        // will create a new server with a wildcard i.p and on a given port
        // (static port initially set to 1234)
        new CollabTextEditServer(null, PORT);
    }
}