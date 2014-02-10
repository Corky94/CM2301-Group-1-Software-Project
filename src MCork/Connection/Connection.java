/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import Encryption.*;
import Server.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Marc
 */
public class Connection {
    
    public Connection(){
        
    }
    
    public void authenticate(){
        
        // kerberos authenticate
        
    }
    
    public PublicKey getKey(String Id) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException{
        
        Sql sq = new Sql();
        
            return sq.getKey(Id);
            
        
        
        /* 
         * method to connect to id server to return public key of the user, 
         * if user does not exist it responds with an error before the user
         * can type their message         
        */ 
        

        

          
            
       
        
            
            
            
            
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
    
    public void sendMessage(String contents, String sender, String reciever) throws NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException, SQLException, InvalidKeySpecException{
        
        PublicKey key = getKey(reciever);
        
        Encryption e = new Encryption();
        
        byte[] c = e.encryptString(key, contents);
        byte[] s = e.encryptString(key, sender);
        
        Sql sq = new Sql();
        
        sq.sendMessage(s, c, reciever);
        
        
        /* 
         * sends the encrypted message to the current server for the recpitent
         */
        
        
        
        
        
    }
    
    
}
