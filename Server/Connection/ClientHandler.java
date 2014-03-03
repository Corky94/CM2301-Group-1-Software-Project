package Connection;

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
                        

                        
                        if (m == null){
                            Sql sq = new Sql();
                            
                        }
                        
                        else if (m.key != null){

                            registerUser(m,null);                              
                            s.close(); 
                            is.close();
                            ois.close();
                            registerUser(m,"all");
                             
                        }else if (m.needingKey == true) {

                                getKey(m,s);
                                is.close();  
                                s.close(); 
                                ois.close();
                            
                        }else if(m.message != null && m.receiver != null) {
				storeMessage(m);
                                is.close();  
                                s.close(); 
                                ois.close();
                        
			}else {  //send messages to client  

				getMessages(s, m.receiver);
                                is.close();  
                                s.close(); 
                                ois.close();
			}
			
                        
                  
		} catch(Exception e){

		}  

        }
	private static boolean storeMessage(Message m) {
		Sql s = new Sql();
                System.out.println("Storing");
                s.sendMessage(m.sender, m.subject,  m.message, m.receiver);
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
  
        public static void registerUser(Message m, String all){
            
            Sql s = new Sql();
 
            s.register(m.receiver, m.key,all);
            
            
        }
        
        public static void getKey(Message m,Socket soc){
            System.out.println("Getting key");
            Message message = new Message();
            Sql s = new Sql();
            
            
            try {
                message.key = s.getKey(m.receiver);
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
    
        


