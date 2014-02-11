import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ServerSend {
	private static boolean debug = true;
	public static void main(String args[]){  
		byte[] sender = "sender".getBytes();
		
		Scanner in = new Scanner(System.in);
		System.out.print("Recipient: ");
	    String receiver = in.nextLine();

	    System.out.print("Message: ");
	    byte[] message = in.nextLine().getBytes();
		
		try{  
			Message m = new Message();
			m.sender = sender;
			m.receiver = receiver;
			m.message = message;

			System.out.println("Sender: " + m.sender);
			System.out.println("Receiver: " + m.receiver);
			System.out.println("Message: " + m.message);
			
			Socket s = new Socket("localhost", 12345);  
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
}
