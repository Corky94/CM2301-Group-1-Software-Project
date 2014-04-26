package Connection;
 
import Crypto.Authentication;
import Message.Message;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
 
public class ClientSend {
    
    public static void send(Message m){
        SSLSocket s;
        try{  
            ClientSSL c = Console.User.clissl;
            Socket:
            while(true){
                s = c.main(12346);
                if (s != null){
                    break Socket;
                }
            }
            Packet p = Authentication.createPacket(m, ClientSSL.getNodeAddress());
            OutputStream os = s.getOutputStream();  
            ObjectOutputStream oos = new ObjectOutputStream(os);  
            oos.writeObject(p);   
            oos.close();  
            os.close();  
            s.close();  
        }catch(IOException e){
                System.out.println(e);
        }
    }
        
    public static void registerToServer(String id, byte[] key){
        Message m = new Message();
        m.setKey(key);
        m.setReceiver(id);
        send(m);
    }
    
    public static void stressTest(){
        ClientSSL clissl = Console.User.clissl;
        Message m = null;
        Packet p = Authentication.createPacket(m, null);
        p.setMessage(new Message());
        while (true) {
            try (SSLSocket s = clissl.main(12346); OutputStream os = s.getOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(os)  ) {
                System.out.println("Created Socket");
                System.out.println(s.getHandshakeSession());
                oos.writeObject(p);
                oos.flush();
                oos.reset();
                oos.close();  
                os.flush();
                os.close();
                s.close();
                System.out.println(s.isConnected());     
            }catch (IOException ex) {
                Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }
    }
    
}