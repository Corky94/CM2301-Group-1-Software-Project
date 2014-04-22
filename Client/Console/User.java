
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
        
        
        public void login(char[] password){
            
            realm = null;
            s = new SecureDetails();
            Scanner in = new Scanner(System.in);
            KeyVault kv = new KeyVault();
            
            
            while (kv.checkPassword() != true){
                 pass = password;
               
                if (kv.checkPassword() == false){
                    System.out.println("Password Incorrect \n Press enter to try again");                
 
                }

            }
            
            loggedIn = true;   
           
         
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
            s = new SecureDetails();
            Message m = new Message();
            Encryption e = new Encryption();
        
            
            m.setReceiver(recipitent);
          
            PublicKey pk =  ClientReceive.getKey(m.getReceiver(),pass);

            String sender = s.getID();

            m.setSubject(Encryption.encryptString(pk, subject));
            m.setSender(Encryption.encryptString(pk, sender));
            m.setMessage(Encryption.encryptString(pk, contents));
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
