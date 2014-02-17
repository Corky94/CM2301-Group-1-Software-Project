package Connection;

import Message.Message;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientSend {
	private static boolean  debug = true;
        private static final String host = "192.168.0.2";

	public static void sendMessage(Message m) {

//		
		try{  
                    
			Socket s = new Socket(host, 12346);
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
        
        public static boolean registerToServer(String id, byte[] key ){
            Message m = new Message();
            m.key = key;
            m.receiver = id;
            try {

                Socket s = new Socket(host, 12346);
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

        
  
          
}
