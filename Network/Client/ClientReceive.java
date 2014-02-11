import java.io.InputStream;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientReceive {
	private static boolean  debug = true;
	private static byte[] sender = "sender".getBytes();

	public static boolean receive() {
		try{  
			Message m = new Message();
			m.sender = sender;
			m.receiver = null;
			m.message = null;

			if(debug){
				System.out.println("Sending...\nSender: " + m.sender);
				System.out.println("Receiver: " + m.receiver);
				System.out.println("Message: " + m.message);
			}	
			

			Socket s = new Socket("localhost", 12345);  
			OutputStream os = s.getOutputStream();  
			ObjectOutputStream oos = new ObjectOutputStream(os);  
			oos.writeObject(m);   
			

			//get reply from server
			InputStream is = s.getInputStream();  
			ObjectInputStream ois = new ObjectInputStream(is);
			m = (Message) ois.readObject();
			//if(debug) System.out.println(m);
			if(debug){
				System.out.println("\nReceiving...\nSender: " + 
										m.sender.toString());
				System.out.println("Receiver: " + m.receiver);
				System.out.println("Message: " + m.message.toString());
			}

			oos.close();  
			if(debug) System.out.println("\noss closed");
			os.close(); 
			if(debug) System.out.println("os closed");

			is.close();
			if(debug) System.out.println("is closed");
			ois.close();
			if(debug) System.out.println("ois closed");

			 
			s.close();  
			if(debug) System.out.println("s closed");
			return true;
		}catch(Exception e){
			System.out.println(e);
		}

		return false;
	}  
}
