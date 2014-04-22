
// This is a basic structure of the User class, there is still much to be done and al the methods to be implimented
package Console;

import Message.Message;
import Connection.*;
import Crypto.*;
import java.security.*;
import java.util.Scanner;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



public class User {

//	private Message[] savedMessages;
        private SecureDetails s;
        public boolean loggedIn;
        private String realm;
        private ClientSend c;
        private byte[] publicKey;
        public char[] pass;
        public static ClientSSL clissl;
	public User() {
	
	}
            
        
        
        public void login(char[] password){
            
            realm = null;
            s = new SecureDetails();
            
            clissl = new ClientSSL(password);
            loggedIn = true;   
            pass = password;
         
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
        
        
        public Message[] receiveEmails() {
            s = new SecureDetails();
            return ClientReceive.receive(s.getID(),pass);

            //return m;
        }
        
        public void createMessage(String contents, String recipitent, String subject) {
            c = new ClientSend();
            s = new SecureDetails();
            Message m = new Message();
            Encryption e = new Encryption();
        
            
            m.setReceiver(recipitent);
          
            PublicKey pk =  new ClientReceive().getKey(m.getReceiver(),pass);
            System.out.println("got key");

            String sender = s.getID();

            m.setSubject(e.encryptString(pk, subject));
            m.setSender(e.encryptString(pk, sender));
            m.setMessage(e.encryptString(pk, contents));
            ClientSend.sendMessage(m,pass);
            System.out.println("Message sent");

            

            
        } 
        
        public void deleteMessage(int x){
            
            
            // delete message from savedMessages[x] 
            
        }
        public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {      
            User u = new User();
            SecureDetails s = new SecureDetails();
            try {
            // Set System L&F
            UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
            } 
    catch (UnsupportedLookAndFeelException e) {
       // handle exception
    }
            if (s.getRegistered() == true && u.loggedIn != true){
                new GUI.GuiLogin();
                System.out.println(s.getID());
            }
            else if(s.getRegistered() == false){

                GUI.GuiRegister.NewRegister();
                
        }

    }

        
}
