package Connection;

import Crypto.AuthenticatedList;
import Crypto.Encryption;
import Crypto.ServerAuthentication;
import Message.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.*;


public class ClientHandler implements Runnable {
    private static boolean debug = true;
    private SSLSocket socket;
    private Message message;

    ClientHandler(SSLServerSocket s){
        try {
            this.socket = (SSLSocket) s.accept();
        }
        catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            s = null;
        }  
    }
        
    public void run() {  
        try{
            SSLSocket s = socket;
            InputStream is = s.getInputStream();  
            ObjectInputStream ois = new ObjectInputStream(is);
            Packet p = (Packet) ois.readObject();
            if(p.getEncryptedTicket()!= null){
                System.out.println("Received Auth Ticket");
                if(ServerAuthentication.verifyEncryptedTicket(p) == true){
                    System.out.println("User Authenticated Sucessfully");
                    if(p.getMessages() != null){
                        Message m = p.getMessages()[0];
                        if (m == null){   

                        }
                        else if (m.getKey() != null){
                            registerUser(m,null);                              
                            s.close(); 
                            is.close();
                            ois.close();
                            registerUser(m,"all");
                        }else if (m.isNeedingKey() == true) {
                            getKey(m,s);
                            is.close();  
                            s.close(); 
                            ois.close();
                        }else if(m.getMessage() != null && m.getReceiver() != null) {
                            storeMessage(m);
                            is.close();  
                            s.close(); 
                            ois.close();
                            storeMessage(m,"");
                        }else {  //send messages to client  
                            System.out.println("Wrong");
                            getMessages(s, m.getReceiver());
                            is.close();  
                            s.close(); 
                            ois.close();
                        }
                        m = null;
                    }
                }
            }else if(p.getTicket() != null){
                System.out.println("Challenge request from client: " + p.getTicket().getClientId());
                //Client is trying to initiate authentication challenge
                Packet challenge = ServerAuthentication.handleChallenge(p.getTicket());
                returnChallenge(s, challenge);
            }
            //System.out.println("Socket is closed? " + s.isClosed());
            ois.close();
            is.close(); 
            s.close(); 
            //System.out.println("Socket is closed? " + s.isClosed());
            s = null;
            is = null;
            ois = null;
            //System.out.println("Socket is null? " + s);
            Thread.sleep(10);
        } catch(IOException | ClassNotFoundException | InterruptedException e){
            throw new RuntimeException(e);             
        }  
    }
    private static boolean storeMessage(Message m) {
        Sql s = new Sql();
        System.out.println("Storing");
        s.sendMessage(m.getSender(), m.getSubject(), m.getMessage(), m.getReceiver());
        System.out.println("Stored");
        return true;
    } 

    private static boolean storeMessage(Message m, String a) {
        Sql s = new Sql();
        System.out.println("Storing");
        s.sendMessage(m.getSender(), m.getSubject(), m.getMessage(), m.getReceiver(),a);
        System.out.println("Stored");
        return true;
    } 

    private static void returnChallenge(SSLSocket s, Packet challenge){  
        try (OutputStream os = s.getOutputStream()) {
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(challenge);
            oos.flush();
            oos.close();
            os.flush();
            os.close();
        }catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    private static void getMessages(SSLSocket s, String id) {  
        Sql sq = new Sql();
        System.out.println("Getting messages for client: " + id);
        Message[] m = sq.getMessage(id);
        System.out.println("Retreived messages for client:" + id);
        Packet p = Encryption.encryptTicket(AuthenticatedList.getClientTicket(id));
        p.setMessages(m);
        try{
            OutputStream os = s.getOutputStream();  
            ObjectOutputStream oos = new ObjectOutputStream(os);  
            oos.writeObject(p);
            oos.flush();
            oos.close();
            os.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void registerUser(Message m, String all){
        Sql s = new Sql();
        s.register(m.getReceiver(), m.getKey(),all);      
    }

    public static void getKey(Message m, SSLSocket soc){
        System.out.println("Getting key");
        Message message = new Message();
        Sql s = new Sql();
        byte[] key = s.getKey(m.getReceiver());
        System.out.println(key);
        message.setKey(key);
        System.out.println("Got key \n sending key");
        sendKeyToClient(message, soc);
        System.out.println("Sent Key"); 
    }

    public static void sendKeyToClient(Message m, SSLSocket s){
        try{
            Packet p = new Packet();
            p.setMessage(m);
            OutputStream os = s.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);  
            oos.writeObject(p);   
            oos.close();  
            os.close();  
            s.close();  
            }catch(IOException e){
        }  
    }
}
    
        


