package Connection;

import Message.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;


public class Server  {
  private static boolean debug = true;
        
  public static void main(String args[]) {
                
                
  
                ServerSSL s = new ServerSSL();
                
                
                  SSLServerSocket querySocket = s.main(12346);
                  SSLServerSocket updateSocket = s.main(12347);
                  SSLServerSocket nodeSocket = s.main(12348);
                  AdminInput ai = new AdminInput();
                  Thread admin = new Thread(ai); 
                  admin.start();

                  
                  ClientAcceptor c = new ClientAcceptor(querySocket);
                  Thread query = new Thread(c);
                  query.start();

                  
                  UpdateAcceptor u = new UpdateAcceptor(updateSocket);
                  Thread update = new Thread(u);
                  update.start();
//                  
                  NodeAcceptor n = new NodeAcceptor(nodeSocket);
                  Thread node = new Thread(n);
                  node.start();

                  NodeList list = new NodeList();
                  list.addNode();
                  //System.out.println(list.getList());
                
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
                socket = ss;
                ClientHandler c =  new ClientHandler(socket);
                
                Thread t = new Thread(c);
                System.out.println(t.getId());
                t.start();
                socket = null;
                t = null;
                
            
            
        
            
            

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
        SSLServerSocket ss = socket;
        while (true){
           
                socket = ss;
                UpdateHandler u =  new UpdateHandler(socket);
                Thread t = new Thread(u);
                t.start();
                System.out.println(t.getId());

            
            

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
        SSLServerSocket ss = socket;
        while (true){
    
                socket = ss;
                NodeHandler n =  new NodeHandler(socket);
                Thread t = new Thread(n);
                t.start();
                System.out.println(t.getId());

            

        }
    }
}

               
           


