package Connection;

import Message.Message;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientReceive {
	private static boolean  debug = true;
	private static byte[] sender = "sender".getBytes();
        

        
        
	public static Message receive(String Id) {
		try{  
			Message m = new Message();
			m.sender = null;
			m.receiver = Id;
			m.message = null;



			Socket s = new Socket("localhost", 12346);  
			OutputStream os = s.getOutputStream();  
			ObjectOutputStream oos = new ObjectOutputStream(os);  
			oos.writeObject(m);   
			

			//get reply from server
			InputStream is = s.getInputStream();  
			ObjectInputStream ois = new ObjectInputStream(is);
			m = (Message) ois.readObject();
			//if(debug) System.out.println(m);
			

			oos.close();  
			os.close(); 
			is.close();
			ois.close();
			s.close();  
			return m;
		}catch(Exception e){
			System.out.println(e);
		}

		return null;
	}  
        
        public static byte[] getKey(Message m){  
            Message message = new Message();
            System.out.println(m.receiver);
            try {
                
                Socket s = new Socket("localhost", 12346);  
                OutputStream os = s.getOutputStream(); 
                 
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(m);

                
                InputStream is = s.getInputStream();  
                ObjectInputStream ois = new ObjectInputStream(is);


                message = (Message) ois.readObject();
                
                is.close();
                ois.close();
                
                
                return message.key;
                
 
            } catch (IOException ex) {
                Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientReceive.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("TEST");
            
            

            return null;
}
}

