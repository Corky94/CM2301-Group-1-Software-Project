package Server;

import Message.Message;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ServerSend {
	private static boolean debug = true;
	
        public static void sendMessageToClient(Message m){  
            

		try{  

			System.out.println("Sender: " + m.sender);
			System.out.println("Receiver: " + m.receiver);
			System.out.println("Message: " + m.message);
			
			Socket s = new Socket("localhost", 12346);  
			OutputStream os = s.getOutputStream();  
			ObjectOutputStream oos = new ObjectOutputStream(os);  
			//Message to = new Message();  
			oos.writeObject(m);  
			//oos.writeObject(new String("another object from the client"));  
			oos.close();  
			os.close();  
			s.close();  
		}catch(Exception e){
			System.out.println(e);
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
