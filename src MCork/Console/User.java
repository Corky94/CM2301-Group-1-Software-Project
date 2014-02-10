
// This is a basic structure of the User class, there is still much to be done and al the methods to be implimented
package Console;

import Connection.*;
import Server.Sql;
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
        private Connection c;
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
            c = new Connection();
            Message m = new Message();
            String recipitent = null;
            String contents = null;
            Scanner in = new Scanner(System.in);
            Sql sq = new Sql();
            
            System.out.println("Please enter recpitent Id:");
                
            recipitent = in.nextLine();
            
            if (sq.idExist(recipitent) == true){
                m.setRecipient(recipitent);
                m.setSender();
            }
            
            else{
                System.out.println("Recipitent does not exist");
            }
            
            System.out.println("Please enter the message contents: ");
            
            contents = in.nextLine();
            
            m.setContents(contents);
            
            
            
            c.sendMessage(m.getContents(), m.getSender(), recipitent);
            
            
            

            
            
             
            
        } 
        
        public void deleteMessage(int x){
            
            
            // delete message from savedMessages[x] 
            
        }
        
        
}
