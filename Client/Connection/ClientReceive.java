package Connection;

import Message.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.security.*;
import java.security.spec.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;

public class ClientReceive {
    private static boolean  debug = true;
    private static byte[] sender = "sender".getBytes();

    public static Message[] receive(String Id, char[] localPassword) {
        try{
            Message[] allMessages;
            Message m = new Message();
            m.setSender(null);
            m.setReceiver(Id);
            m.setMessage(null);
            SSLSocket s;
            while (true){
                ClientSSL c = Console.User.clissl;
                s = c.main(12346);

                System.out.println(s);
                OutputStream os = s.getOutputStream();  
                ObjectOutputStream oos = new ObjectOutputStream(os);  
                oos.writeObject(m);   

                //get reply from server
                InputStream is = s.getInputStream();  
                ObjectInputStream ois = new ObjectInputStream(is);
                allMessages = (Message[]) ois.readObject();
                //if(debug) System.out.println(m);
                int i = allMessages.length;

                oos.close();  
                os.close(); 
                is.close();
                ois.close();
                s.close(); 
                return allMessages;
            }
        }catch(IOException | ClassNotFoundException e){
            Logger.getLogger(ClientReceive.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;		
    }  
        
    private static byte[] getKeyFromServer(Message m){  
        Message message = new Message();
        SSLSocket s;
        try {
            ClientSSL c = Console.User.clissl;
            s = c.main(12346);
            OutputStream os = s.getOutputStream(); 
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(m);
            InputStream is = s.getInputStream();  
            ObjectInputStream ois = new ObjectInputStream(is);
            message = (Message) ois.readObject();             
            is.close();
            ois.close();
            return message.getKey();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ClientSend.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("TEST");
        return null;
    }

    public static PublicKey getKey(String id, char[] pass) {
        Message m = new Message();
        m.setReceiver(id);
        m.setNeedingKey(true);
        byte[] key = getKeyFromServer(m);
        try {
            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(key);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PublicKey pk = kf.generatePublic(pubKeySpec);
            return pk;
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            throw new RuntimeException(ex);
        }
    }
}

