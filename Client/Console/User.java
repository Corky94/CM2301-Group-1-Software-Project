
// This is a basic structure of the User class, there is still much to be done and al the methods to be implimented
package Console;

import Message.Message;
import Connection.*;
import Crypto.*;
import java.security.*;
import java.util.Scanner;



public class User {

//	private Message[] savedMessages;
        private SecureDetails s;
        public boolean loggedIn;
        private String realm;
        private ClientSend c;
        private byte[] publicKey;
        public char[] pass;
        
	public User() {
	
	}
            
        
        
        public void login(char[] password){
            
            realm = null;
            s = new SecureDetails();
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
        
        
        public Message[] receiveEmails() {
            s = new SecureDetails();
            return ClientReceive.receive(s.getID());

            //return m;
        }
        
        public void createMessage(String contents, String recipitent, String subject) {
            c = new ClientSend();
            s = new SecureDetails();
            Message m = new Message();
            Encryption e = new Encryption();
        
            
            m.setReceiver(recipitent);
          
            PublicKey pk =  e.getKey(m.receiver);

            String sender = s.getID();

            m.setSubject(e.encryptString(pk, subject));
            m.setSender(e.encryptString(pk, sender));
            m.setMessage(e.encryptString(pk, contents));
            ClientSend.sendMessage(m);
            

            
        } 
        
        public void deleteMessage(int x){
            
            
            // delete message from savedMessages[x] 
            
        }
        public static void main(String[] args) {      
            User u = new User();
            SecureDetails s = new SecureDetails();

            if (s.getRegistered() == true && u.loggedIn != true){
                new GUI.GuiLogin();
                System.out.println(s.getID());
            }
            else if(s.getRegistered() == false){

                GUI.GuiRegister.NewRegister();
                
        }

    }

        
}
