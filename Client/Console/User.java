
// This is a basic structure of the User class, there is still much to be done and al the methods to be implimented
package Console;

import Message.Message;
import Connection.*;
import Crypto.*;
import java.io.File;
import java.security.*;



public class User {
//	private Message[] savedMessages;
    private static SecureDetails s;
    public static boolean loggedIn;
    private String realm;
    private ClientSend c;
    private byte[] publicKey;
    private static char[] pass;
    public  static ClientSSL clissl;

    public User() {

    }

    public static void setPassword(char[] p){
        pass = p;
    }

    public static char[] getPassword(){
        return pass;
    }


    public static void login(char[] password){
 
        
        while (KeyVault.checkPassword(password) != true){
            
            if (KeyVault.checkPassword(password) == false){
                System.out.println("Password Incorrect \n Press enter to try again");                
            }   
        } 
        pass = password;
        clissl = new ClientSSL();
    }

    public void logout(){            
        loggedIn = false;            
    }

    public void readMessages(){

//            if (newMessages.length<=0){                
//                System.out.println("You have no new messages");                
//            }
//            else if(newMessages.length == 1){                
//                System.out.println("You have one new message");
//            }            
//            else{
//              System.out.println("You have " + newMessages.length + " new messages");
//            }            
//            System.out.println("You have " + s.getMessages().length + " saved messages");
//            
//            

    }

    public static Message[] receiveEmails() {
        s = new SecureDetails();
        return ClientReceive.receive(s.getID());
    }

    public static void createMessage(String contents, String recipitent, String subject) {
        s = new SecureDetails();
        Message m = new Message();
        m.setReceiver(recipitent);
        PublicKey recipientPK =  ClientReceive.getKey(m.getReceiver());
        String sender = s.getID();
        SessionKey sKey = KeyGen.generateSessionKey();
        m.setSubject(Encryption.encryptString(sKey, subject));
        m.setSender(Encryption.encryptString(sKey, sender));
        m.setMessage(Encryption.encryptString(sKey, contents));
        Encryption.encryptSessionKey(sKey, recipientPK);
        m.setSessionKey(sKey);
        System.out.println("Message Session Key: " + m.getSessionKey());
        ClientSend.send(m);   
    } 

    public void deleteMessage(int x){  
        // delete message from savedMessages[x]         
    }

    public static void main(String[] args) {      
        User u = new User();
        File f = new File("user2.ser");
        if (f.exists())
            new GUI.GuiLogin();
        else
            GUI.GuiRegister.NewRegister();
    }
      
}
