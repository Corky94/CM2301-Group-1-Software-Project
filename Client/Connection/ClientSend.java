package Connection;

import Console.*;
import Crypto.*;
import Message.Message;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;

public class ClientSend {

    public static void send(Message m) {
        SSLSocket s;
        try {
            ClientSSL c = Console.User.clissl;
Socket:
            while (true) {
                s = c.main(12346);
                if (s != null) {
                    break Socket;
                }
            }
            Packet p = Authentication.createPacket(m, c.getNodeAddress());
            OutputStream os = s.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(p);
            oos.close();
            os.close();
            s.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void registerToServer(String id, byte[] key) {
        Message m = new Message();
        m.setKey(key);
        m.setReceiver(id);
        send(m);
    }

    public static void stressTest() {
        User.setPassword("pass".toCharArray());
        KeyVault.createTrustStore();
        KeyVault.createKeyStore();
        KeyVault.setRSAKeys();
        KeyVault.setAESKey();
        ClientSSL clissl = new ClientSSL();
        User.clissl = clissl;
        Message m = null;
        Packet p = Authentication.createPacket(m, clissl.getNodeAddress());
        p.setMessage(new Message());
        while (true) {
            try (SSLSocket s = clissl.main(12346); OutputStream os = s.getOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(os)) {

                oos.writeObject(p);
                oos.flush();
                oos.reset();
                oos.close();
                os.flush();
                os.close();
                s.close();
            }
            catch (IOException ex) {
                Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
