
// This is a basic structure of the User class, there is still much to be done and al the methods to be implimented
package console;

import java.security.*;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


public class User {

//	private Message[] savedMessages;
        private SecureDetails s;
	private Message[] newMessages; 
        public boolean loggedIn;
        private Connection c;

	User() {
	
	}
            
        
        public void login(){
            
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
        
        public void createMessage() throws NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException{
            

            
            
             
            
        } 
        
        public void sendMessage(byte[] m){
            

            
        }
        
        public void deleteMessage(int x){
            
            
            // delete message from savedMessages[x] 
            
        }
        
        
}
