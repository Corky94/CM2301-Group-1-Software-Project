import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientSend {
	private static boolean  debug = true;
	private static String sender;

	public static boolean send() {
		Scanner in = new Scanner(System.in);
		System.out.print("Recipient: ");
	    String receiver = in.nextLine();

	    System.out.print("Message: ");
	    String message = in.nextLine();
		
		try{  
			Message m = new Message();
            //getBytes() until encrypted.
			m.sender = sender.getBytes();
			m.receiver = receiver;
			m.message = message.getBytes();

			if(debug){
				System.out.println("Sender: " + m.sender);
				System.out.println("Receiver: " + m.receiver);
				System.out.println("Message: " + m.message);
			}	
			
			Socket s = new Socket("localhost", 12345);  
			OutputStream os = s.getOutputStream();  
			ObjectOutputStream oos = new ObjectOutputStream(os);  
			oos.writeObject(m);   
			oos.close();  
			os.close();  
			s.close();  
			return true;
		}catch(Exception e){
			System.out.println(e);
		}
		return false;
	}  
}
