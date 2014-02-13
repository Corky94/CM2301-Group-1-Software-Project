package Server;

import Message.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServerReceive {
	private static boolean debug = true;
        
	public static void main(String args[]) {  
		int port = 12346;  
		Message m = new Message();
		try {  
                    ServerSocket ss = new ServerSocket(port);
                  while (true){
                      ClientHandler h;
                      h = new ClientHandler(ss.accept(), m);
                      Thread t = new Thread(h);
                      t.start();

                  }
                }catch (IOException ex) {
                    Logger.getLogger(ServerReceive.class.getName()).log(Level.SEVERE, null, ex);                        
                } 
        }
}
               
           


