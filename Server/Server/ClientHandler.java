package Server;

import Message.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClientHandler implements Runnable {
	private static boolean debug = true;
        private Socket socket;
        private Message message;
        
        ClientHandler(Socket s, Message m){
            this.socket = s;
            this.message = m;
        
        }
        
	public void run() {  
                    Socket s = socket;
                    Message m = message;
                    try{
			InputStream is = s.getInputStream();  
			ObjectInputStream ois = new ObjectInputStream(is);
    
			m = (Message) ois.readObject();
  
                        
			//store message
                        
                        if (m.key != null){

                            registerUser(m);
                        }else if (m.needingKey == true) {

                                getKey(m,s);
                            
                        }else if(m.message != null && m.receiver != null) {
				storeMessage(m);
                        
			}else {  //send messages to client  

				getMessages(s, m.receiver);
			}
			is.close();  
			s.close(); 
                        ois.close();
                        
                  
		} catch(Exception e){
			System.out.println(e);
		}  

        }
	private static boolean storeMessage(Message m) {
		Sql s = new Sql();
                
                s.sendMessage(m.sender, m.message,m.receiver);
		return true;
	} 

	private static Message getMessages(Socket s, String id) {

                Message m = new Message();
                Sql sq = new Sql();
                m = sq.getMessage(id);
                m.receiver = id;

		try{
			OutputStream os = s.getOutputStream();  
			ObjectOutputStream oos = new ObjectOutputStream(os);  
			oos.writeObject(m);
			os.close();
			oos.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return m;
	}
  
        public static void registerUser(Message m){
            
            Sql s = new Sql();
            try {
                s.register(m.receiver, m.key);
                
            } catch (SQLException ex) {
                Logger.getLogger(ServerReceive.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ServerReceive.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        public static void getKey(Message m,Socket soc){

            Message message = new Message();
            Sql s = new Sql();
   
            
            try {
                message.key = s.getKey(m.receiver);
                sendKeyToClient(message, soc);
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
			System.out.println(e);
		}  
}


}
    
        


