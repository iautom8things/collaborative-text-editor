package test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.channels.ServerSocketChannel;
import handler.*;

/*
 * A tester class... receives serialized objects through a SocketChannel, since 
 * the CollabTextEditServer uses those. To test, run this first. Then run the
 * CTEApplication
 */
public class clientSerializationTester {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Receiver Start");
        
        //Open a socket and start listening to it. 
        //This is needed just to get the SocketChannel for read/write
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(true);
        int port = 1234;
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        
        while(true){
            try{
                SocketChannel socketChannel = serverSocketChannel.accept(); //Client has connected
                ObjectInputStream objectInputStream = new ObjectInputStream(socketChannel.socket().getInputStream());

                NetworkCommand networkCommand = (NetworkCommand)objectInputStream.readObject();
                System.out.println("object: '" + networkCommand.toString() + "'");
            }catch(Exception e){}
        }
    }
}