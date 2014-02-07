/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package console;

import java.security.*;

/**
 *
 * @author Marc
 */
public class Connection {
    
    Connection(){
        
    }
    
    public void authenticate(){
        
        // kerberos authenticate
        
    }
    
    public PublicKey getKey(String Id){
        
        /* 
         * method to connect to id server to return public key of the user, 
         * if user does not exist it responds with an error before the user
         * can type their message         
        */ 
        

        

            
            return null;
            
            
       
        
            
            
            
            
        // end of testing code 
        
        
    }
    
    public String getServer(String Id){
        
        /*
         * Once the id is confirmed to exist the method will need to get the 
         * server details for the recipitent, this will allow the progam to 
         * send the message to the correct server.  
         * 
         */
        
        
        return null; 
    
    
    }
    
    public void sendMessage(byte[] message, String Id){
        
        /* 
         * sends the encrypted message to the current server for the recpitent
         */
        
//        String server = getServer(Id);
        
        
        
        
    }
    
    
}
