
// This is a basic structure of the User class, there is still much to be done and al the methods to be implimented
package Console;

import Message.Message;
import Connection.*;
import Crypto.*;
import java.io.File;
import java.security.*;
import java.util.Scanner;



public class User {

//	private Message[] savedMessages;
        private SecureDetails s;
        public boolean loggedIn;
        private String realm;
        private ClientSend c;
        private byte[] publicKey;
        private char[] pass;
        
	public User() {
	
	}
            
        
        
        public void login(char[] password){
            
            realm = null;
            s = new SecureDetails(password);
            Scanner in = new Scanner(System.in);
            KeyVault kv = new KeyVault();
            
            
            while (kv.checkPassword(password) != true){
                
               
                if (kv.checkPassword(password) == false){
                    System.out.println("Password Incorrect \n Press enter to try again");                
 
                }

            }
            
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
        
        
        public Message[] receiveEmails(SecureDetails s) {
           
            return ClientReceive.receive(s.getID(), pass);

            //return m;
        }
        
        public void createMessage(String contents, String recipitent, String subject) {
            c = new ClientSend();
            s = new SecureDetails(pass);
            Message m = new Message();
            Encryption e = new Encryption();
        
            
            m.setReceiver(recipitent);
          
            PublicKey pk =  e.getKey(m.getReceiver());

            String sender = s.getID();

            m.setSubject(e.encryptString(pk, subject));
            m.setSender(e.encryptString(pk, sender));
            m.setMessage(e.encryptString(pk, contents));
            ClientSend.sendMessage(m, pass);
            

            
        } 
        
        public void deleteMessage(int x){
            
            
            // delete message from savedMessages[x] 
            
        }
        public static void main(String[] args) {      
            User u = new User();
            File f = new File("user2");

            if (f.exists()){
                new GUI.GuiLogin();
                
            }
            else {

                GUI.GuiRegister.NewRegister();
                
        }

    }

        
}
