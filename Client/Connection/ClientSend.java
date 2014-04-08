package Connection;
 
import Message.Message;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
 
public class ClientSend {
<<<<<<< HEAD
 
    public static void sendMessage(Message m, char[] localPassword) {
        ClientSSL clissl = new ClientSSL(localPassword);
        try (SSLSocket s = clissl.main(12346); OutputStream os = s.getOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(m);
            oos.close();
        } catch (IOException e) {
            System.out.println(e);
        }
 
    }
 
    public static boolean registerToServer(String id, byte[] key, char[] localPassword) {
        Message m = new Message();
        m.setKey(key);
        m.setReceiver(id);
        try {
            ClientSSL clissl = new ClientSSL(localPassword);
            try (SSLSocket s = clissl.main(12346); OutputStream os = s.getOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(os)) {
=======
	

	public static void sendMessage(Message m , char[] localPassword) {

//		
		try{  
                    
			SSLSocket s = ClientSSL.main(localPassword);
			OutputStream os = s.getOutputStream();  
                        
			ObjectOutputStream oos = new ObjectOutputStream(os);  
                        
			oos.writeObject(m);   
			oos.close();  
			os.close();  
			s.close();  
                        
		}catch(IOException e){
			System.out.println(e);
		}
		
	}
        
        public static boolean registerToServer(String id, byte[] key, char[] localPassword ){
            Message m = new Message();
            m.setKey(key);
            m.setReceiver(id);
            try {

               SSLSocket s = ClientSSL.main(localPassword);
>>>>>>> 64c80b29aa6e0dd2129a4ea399a7c98e48306225
                System.out.println("Created Socket");
                oos.writeObject(m);
                oos.close();
                os.close();
            }
 
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        return false;
    }
 
    public static void main(String[] args) {
        char[] pass = "pass".toCharArray();
        ClientSSL clissl = new ClientSSL(pass);
        Message m = null;
        while (true) {
            try (SSLSocket s = clissl.main(12346); OutputStream os = s.getOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(os)  ) {
                System.out.println("Created Socket");
                 System.out.println(s.getHandshakeSession());
                oos.writeObject(m);
                oos.flush();
                oos.reset();
                oos.close();
               
                os.flush();
                os.close();
               
               
                
                s.close();
                System.out.println(s.isConnected());
               
                
            } catch (IOException ex) {
                Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
<<<<<<< HEAD
    }
 
}
=======
        

        public static void main(String[] args){
            while (true){
                OutputStream os = null;
                Message m = null;
                try {
                    SSLSocket s = ClientSSL.main("pass".toCharArray());
                    System.out.println("Created Socket");
                    os = s.getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(os);
                    oos.writeObject(m);
                    oos.close();
                    os.close();
                    s.close();
                    oos = null;
                    os = null;
                 
                    
                    
                    s = null;
//                    Thread.sleep(1000);
                    
                } catch (IOException ex) {
                    Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
//                } 
                }
            }
        }
  
          
}
>>>>>>> 64c80b29aa6e0dd2129a4ea399a7c98e48306225
