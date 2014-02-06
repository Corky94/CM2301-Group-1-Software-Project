
// This is a basic structure of the User class, there is still much to be done and al the methods to be implimented
public class User {


//	private int userId;
//	private String password;
	private Message[] savedMessages;
	private Message[] newMessages; 
	public boolean registered;
        public boolean loggedIn;

	User() {
	
	}
        
        public void register(){
            
            new Register();
            
            
            
            registered = true;
            
            
        }
        
        
        public void login(){
            
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
            
            System.out.println("You have " + savedMessages.length + " saved messages");
            
            
            
        }
        
        
        public Message[] recieveEmails(){
            
            
            //input method
            
            return newMessages;
        }
        
        public void createMessage(){
            
            
            
        } 
        
        public void sendMessage(Message m){
            
            
        }
        
        public void deleteMessage(int x){
            
            
            // delete message from savedMessages[x] 
            
        }
        
        
}
