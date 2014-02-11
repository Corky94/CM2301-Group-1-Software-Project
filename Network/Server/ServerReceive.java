import java.io.InputStream;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerReceive {
	private static boolean debug = true;
	public static void main(String args[]) {  
		int port = 12345;  
		Message m = null;
		try {  
			ServerSocket ss = new ServerSocket(port);  
			Socket s = ss.accept();  
			InputStream is = s.getInputStream();  
			ObjectInputStream ois = new ObjectInputStream(is);  
			m = (Message) ois.readObject();
			
			//store message
			if(m.message != null && m.receiver != null) {
				storeMessage(m);
			} else {  //send messages to client  
				getMessages(s, ss);
			}
			is.close();  
			s.close();  
			ss.close();  
		} catch(Exception e){
			System.out.println(e);
		}  

		if(debug) {
			System.out.println("Sender: " + m.sender);
	        System.out.println("Receiver: " + m.receiver);
	        System.out.println("Message: " + m.message);
    	}
	} 

	private static boolean storeMessage(Message m) {
		//store(m.sender, m.receiver, m.message);
		return false;
	} 

	private static Message getMessages(Socket s, ServerSocket ss) {
		Message m = new Message();
		m.sender = "sender".getBytes();
		m.receiver = "receiver";
		m.message = "message".getBytes();

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
}

