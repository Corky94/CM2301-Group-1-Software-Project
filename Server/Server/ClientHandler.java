package Server;

import Message.*;
import java.io.*;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClientHandler implements Runnable {
	private static boolean debug = true;
        private Socket socket;
        private Message message;
        
        ClientHandler(Socket s){
            this.socket = s;
        
        }
        
	public void run() {  
                    
                    Socket s = socket;
                    try{
			InputStream is = s.getInputStream();  
			ObjectInputStream ois = new ObjectInputStream(is);
                        
                        
    
			Message m = (Message) ois.readObject();
                        
  
//                        String receiver = m.getReceiver();
//                        byte[] sender = m.getSender();
//                        byte[] subject = m.getSubject();
//                        byte[] message = m.getMessage();
			//store message
                        
                        if (m == null){
                            Sql sq = new Sql();
                            sq.delete();
                        }
                        
                        else if (m.getKey() != null){

                            registerUser(m);
                        }else if (m.isNeedingKey() == true) {

                                getKey(m,s);
                            
                        }else if(m.getMessage() != null && m.getReceiver() != null) {
				storeMessage(m);
                        
			}else {  //send messages to client  

				getMessages(s, m.getReceiver());
			}
			is.close();  
			s.close(); 
                        ois.close();
                        
                  
		} catch(Exception e){

		}  

        }
	private static boolean storeMessage(Message m) {
		Sql s = new Sql();
                System.out.println("Storing");
                s.sendMessage(m.getSender(), m.getSubject(), m.getMessage(), m.getReceiver());
                System.out.println("Stored");
		return true;
	} 

	private static void getMessages(Socket s, String id) {

                
                Sql sq = new Sql();
                System.out.println("Getting");
                Message[] m = sq.getMessage(id);
                System.out.println("Got");
		try{
			OutputStream os = s.getOutputStream();  
			ObjectOutputStream oos = new ObjectOutputStream(os);  
			oos.writeObject(m);
			os.close();
			oos.close();
		} catch (Exception e) {
			
		}

	}
  
        public static void registerUser(Message m){
            
            Sql s = new Sql();
            try {
                s.register(m.getReceiver(), m.getKey());
                
            } catch (SQLException ex) {
                Logger.getLogger(ServerReceive.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ServerReceive.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        public static void getKey(Message m,Socket soc){
            System.out.println("Getting key");
            Message message = new Message();
            Sql s = new Sql();
            
            
            try {
                message.setKey(s.getKey(m.getReceiver()));
                System.out.println("Got key \n sending key");
                sendKeyToClient(message, soc);
                System.out.println("Sent Key");
            } catch (NoSuchAlgorithmException ex) {
                throw new RuntimeException(ex);
            } catch (InvalidKeySpecException ex) {
                throw new RuntimeException(ex);   
            } catch (SQLException ex) {
                throw new RuntimeException(ex);

            
            
            } 
        }
            public static void sendKeyToClient(Message m, Socket s){
    
            try{  

                OutputStream os = s.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);  
                oos.writeObject(m);   
                oos.close();  
                os.close();  
                s.close();  
            
            }catch(Exception e){
			
		}  
}


}
    
        


