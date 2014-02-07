/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package console;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        
        User u = new User();
        SecureDetails s = new SecureDetails();
        
        System.out.println(s.getPassword());
        
            
        if (s.getRegistered() == true && u.loggedIn != true){
            
            u.login(); 
            
            
            
        }
        else if(s.getRegistered() == false){
            
            new Register();
            
            u.login();
            
        
    }
        // TODO code application logic here
    }
}
