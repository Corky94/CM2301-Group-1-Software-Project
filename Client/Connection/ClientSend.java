package Connection;
 
import Message.Message;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
 
public class ClientSend {
 
    public static void sendMessage(Message m, char[] localPassword) {
        ClientSSL clissl = new ClientSSL(localPassword);
        try (SSLSocket s = clissl.main(12346); OutputStream os = s.getOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(m);
            oos.close();
        } catch (IOException e) {
            System.out.println(e);
        }
 
    }
 
    public static boolean registerToServer(String id, byte[] key, char[] localPassword) {
        Message m = new Message();
        m.setKey(key);
        m.setReceiver(id);
        try {
            ClientSSL clissl = new ClientSSL(localPassword);
            try (SSLSocket s = clissl.main(12346); OutputStream os = s.getOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(os)) {
                System.out.println("Created Socket");
                oos.writeObject(m);
                oos.close();
                os.close();
            }
 
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        return false;
    }
 
    public static void main(String[] args) {
        char[] pass = "pass".toCharArray();
        ClientSSL clissl = new ClientSSL(pass);
        Message m = null;
        while (true) {
            try (SSLSocket s = clissl.main(12346); OutputStream os = s.getOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(os)  ) {
                System.out.println("Created Socket");
                 System.out.println(s.getHandshakeSession());
                oos.writeObject(m);
                oos.flush();
                oos.reset();
                oos.close();
               
                os.flush();
                os.close();
               
               
                
                s.close();
                System.out.println(s.isConnected());
               
                
            } catch (IOException ex) {
                Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
 
}