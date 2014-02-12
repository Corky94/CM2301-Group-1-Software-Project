/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Console;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Marc
 */
public class Java {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException, SQLException, InvalidKeySpecException, IOException {

//        KeyGen kg = new KeyGen();
//        
//        kg.convertToKey();
        
        

//        
//        Encryption e = new Encryption();
//        
//        Key p = e.convertToKey(k);
        
        User u = new User();
        SecureDetails s = new SecureDetails();
        
        System.out.println(s.getID());
        
            
        if (s.getRegistered() == true && u.loggedIn != true){
            u.login();
            u.recieveEmails();
    
        
            
            
            
            
        }
        else if(s.getRegistered() == false){
            
            new Register();
            u.login();
            

            
            
        
    }
        // TODO code application logic here
    }
}
