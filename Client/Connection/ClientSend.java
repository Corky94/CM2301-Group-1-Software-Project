package Connection;

import Message.Message;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;

public class ClientSend {
	

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
                System.out.println("Created Socket");
                OutputStream os = s.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(m);
                oos.close();
                os.close();
                s.close();
                
                return true;
            } catch (IOException ex) {
                Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            return false;
        }
        

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
