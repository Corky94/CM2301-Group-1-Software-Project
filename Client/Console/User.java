
// This is a basic structure of the User class, there is still much to be done and al the methods to be implimented
package Console;

import Connection.*;
import Crypto.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


public class User {

//	private Message[] savedMessages;
        private SecureDetails s;
	private Message[] newMessages; 
        public boolean loggedIn;
        private String realm;
        private ClientSend c;
        private byte[] publicKey;

	User() {
	
	}
            
        
        
        public void login(){
            
            realm = null;
            
            s = new SecureDetails();
            
            String password = "12qw12";
            
            Scanner in = new Scanner(System.in);
            
            String pass = s.getPassword();
            
            while (password.equals(pass) != true){
                
                System.out.println("Please enter your password: ");
                
                password = in.nextLine();
            
                if (password.equals(pass) == false){
                    System.out.println("Password Incorrect \n Press enter to try again");
                    
                    
                    
                    
                }

            }
            
            System.out.println("Please Choose your realm 1 or 2: ");
                
            realm = in.nextLine();
            
            loggedIn = true;
        
         
        }

        public void logout(){
            
            loggedIn = false;
            
        }
        
        public void readMessages(){
            
            if (newMessages.length<=0){
                
                System.out.println("You have no new messages");
                
            }
            else if(newMessages.length == 1){
                
                System.out.println("You have one new message");
                
            }
            
            else{
                
                System.out.println("You have " + newMessages.length + " new messages");
            }
            
            System.out.println("You have " + s.getMessages().length + " saved messages");
            
            
            
        }
        
        
        public String recieveEmails() throws NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException{
            

            
            //input method
            
            return null;
        }
        
        public void createMessage() throws NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException, SQLException, InvalidKeySpecException{
            c = new ClientSend();
            Message m = new Message();
            String recipitent = null;
            String contents = null;
            Scanner in = new Scanner(System.in);
            Encryption e = new Encryption();
            
            
            System.out.println("Please enter recpitent Id:");
                
            recipitent = in.nextLine();
            m.receiver = recipitent;
            PublicKey pk =  e.getKey(m.receiver);
           
            m.sender = s.getID().getBytes();
            
            
            
            System.out.println("Please enter the message contents: ");
            
            contents = in.nextLine();
            
            m.message = contents.getBytes();
            
            m.sender = e.encryptString(pk, m.sender);
            m.message = e.encryptString(pk, m.message);
            
            ClientSend.sendMessage(m);
            
            
            

            
            
             
            
        } 
        
        public void deleteMessage(int x){
            
            
            // delete message from savedMessages[x] 
            
        }
        
        
}
