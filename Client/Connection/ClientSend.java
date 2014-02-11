package Connection;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientSend {
	private static boolean  debug = true;

	public static void sendMessage(Message m) {
//		Scanner in = new Scanner(System.in);
//		System.out.print("Recipient: ");
//	    String receiver = in.nextLine();
//
//	    System.out.print("Message: ");
//	    String message = in.nextLine();
//		
		try{  
//			Message m = new Message();
//            //getBytes() until encrypted.
//			m.sender = sender.getBytes();
//			m.receiver = receiver;
//			m.message = message.getBytes();
//
//			if(debug){
//				System.out.println("Sender: " + m.sender);
//				System.out.println("Receiver: " + m.receiver);
//				System.out.println("Message: " + m.message);
//			}	
//			
			Socket s = new Socket("localhost", 12345);  
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

                Socket s = new Socket("10.72.0.13", 12345);
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
