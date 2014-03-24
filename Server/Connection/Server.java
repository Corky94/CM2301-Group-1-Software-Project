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
                  SSLServerSocket updateSocket = ServerSSL.main(12347);
                  SSLServerSocket nodeSocket = ServerSSL.main(12348);
                  Thread maintain = new Thread(new Maintainence());
                  AdminInput ai = new AdminInput();
                  Thread admin = new Thread(ai); 
                  admin.start();
 //                 maintain.start();
                  
                  ClientAcceptor c = new ClientAcceptor(querySocket);
                  Thread query = new Thread(c);
                  query.start();
                  
                  UpdateAcceptor u = new UpdateAcceptor(updateSocket);
                  Thread update = new Thread(u);
                  update.start();
                  
                  NodeAcceptor n = new NodeAcceptor(nodeSocket);
                  Thread node = new Thread(n);
                  node.start();

                  

                
        }
        
}
class Maintainence implements Runnable {
    
    public void run(){
    
    while(true){
        try {
            Thread.sleep(5000);
            System.gc();
        }
        catch (InterruptedException ex) {
            Logger.getLogger(Maintainence.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
    
}
class ClientAcceptor implements Runnable {
        private static boolean debug = true;
        private SSLServerSocket socket;
        private Message message;
        
    ClientAcceptor(SSLServerSocket s){
        this.socket = s;
    }
    
    public void run(){
        SSLServerSocket ss = socket;
        while (true){
            try {
                socket = ss;
                ClientHandler c =  new ClientHandler(socket);
                Thread t = new Thread(c);
                t.start();
                socket = null;
                t = null;
                
                Thread.sleep(10);
            
            
            }
            catch (InterruptedException ex) {
                Logger.getLogger(ClientAcceptor.class.getName()).log(Level.SEVERE, null, ex);
            }
            

        }
    }
}



class UpdateAcceptor implements Runnable {
        private static boolean debug = true;
        private SSLServerSocket socket;
        private Message message;
        
    UpdateAcceptor(SSLServerSocket s){
        this.socket = s;
    }
    
    public void run(){
        
        while (true){
            try {
                UpdateHandler u =  new UpdateHandler(socket.accept());
                Thread t = new Thread(u);
                t.start();
                System.out.println(t.getId());

            }
            catch (IOException ex) {
                Logger.getLogger(ClientAcceptor.class.getName()).log(Level.SEVERE, null, ex);
            }
            

        }
    }
}





class NodeAcceptor implements Runnable {
        private static boolean debug = true;
        private SSLServerSocket socket;
        private Message message;
        
    NodeAcceptor(SSLServerSocket s){
        this.socket = s;
    }
    
    public void run(){
        
        while (true){
            try {
                NodeHandler n =  new NodeHandler(socket.accept());
                Thread t = new Thread(n);
                t.start();
                System.out.println(t.getId());

            }
            catch (IOException ex) {
                Logger.getLogger(ClientAcceptor.class.getName()).log(Level.SEVERE, null, ex);
            }
            

        }
    }
}

               
           


