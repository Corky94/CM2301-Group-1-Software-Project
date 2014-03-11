package Connection;

import Message.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientReceive {
	private static boolean  debug = true;
	private static byte[] sender = "sender".getBytes();
        private static final String host = "192.168.0.2";

        
        
	public static Message[] receive(String Id) {
		try{
			Message[] allMessages;
			Message m = new Message();
			m.setSender(null);
			m.setReceiver(Id);
			m.setMessage(null);


			while (true){
					
					Socket s = new Socket(host, 12346);



					OutputStream os = s.getOutputStream();  
					ObjectOutputStream oos = new ObjectOutputStream(os);  
					oos.writeObject(m);   


					//get reply from server
					InputStream is = s.getInputStream();  
					ObjectInputStream ois = new ObjectInputStream(is);
					allMessages = (Message[]) ois.readObject();
					//if(debug) System.out.println(m);
					int i = allMessages.length;
					

					oos.close();  
					os.close(); 
					is.close();
					ois.close();
					s.close(); 
					return allMessages;
			}
		}
		catch(Exception e){
			Logger.getLogger(ClientReceive.class.getName()).log(Level.SEVERE, null, e);
		}
			

		return null;
		
			
	}  
        
        public static byte[] getKey(Message m){  
            Message message = new Message();
            System.out.println(m.getReceiver());
            try {
                
                Socket s = new Socket(host, 12346);  
                OutputStream os = s.getOutputStream(); 
                 
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(m);

                
                InputStream is = s.getInputStream();  
                ObjectInputStream ois = new ObjectInputStream(is);


                message = (Message) ois.readObject();
                
                is.close();
                ois.close();
                
                
                return message.getKey();
                
 
            } catch (IOException ex) {
                Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientReceive.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("TEST");
            
            

            return null;
}
}

