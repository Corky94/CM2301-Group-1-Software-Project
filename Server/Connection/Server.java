package Connection;

import Message.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;


public class Server  {
	private static boolean debug = true;
        
	public static void main(String args[]) { 
                
                  SSLServerSocket querySocket = ServerSSL.main(12346);
                  SSLServerSocket updateSocket = ServerSSL.main(12121);
                  SSLServerSocket nodeSocket = ServerSSL.main(23232);
                  AdminInput ai = new AdminInput();
                  Thread admin = new Thread(ai); 
                  admin.start();
                  
                  ClientHandler c = new ClientHandler(querySocket);
                  Thread query = new Thread(c);
                  query.start();
                  
                  UpdateHandler u = new UpdateHandler(updateSocket);
                  Thread update = new Thread(u);
                  update.start();
                  
                  NodeHandler n = new NodeHandler(nodeSocket);
                  Thread node = new Thread(n);
                  node.start();

                  

                
        }
        
}

               
           


