/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Console;

import Connection.ClientSend;
import Crypto.*;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Scanner;
import javax.crypto.SecretKey;

/**
 *
 * @author Marc
 */
public class Register {
    
            
    
        Register() throws NoSuchAlgorithmException, SQLException, InvalidKeySpecException, IOException{
        
            Scanner input = new Scanner(System.in);
            
            String pass = "1";
            String confirm = "2";
            
            
            while (pass.equals(confirm) == false){
                
                System.out.println("Please create a password");
            
                pass = input.nextLine();
            
                System.out.println("Please confirm your password");
            
                confirm = input.nextLine();
                
                if (pass.equals(confirm) == false){
                    System.out.println("The passwords you entered did not match, please try again \n");
                }
            }
            System.out.println("Congrats");
            
            
            
            
            
            KeyGen kg = new KeyGen();
            KeyVault kv = new KeyVault();
            Encryption e = new Encryption();
//            
            KeyPair k = kg.generateRSAKeys();
            SecretKey sk =kg.generateAESKey();
            
            
            
            kv.setRSAKeys(pass.toCharArray());
            kv.setAESKey(pass.toCharArray());
            
            byte[] key = kv.getRSAKeys(pass.toCharArray()).getPublic().getEncoded();
            
            System.out.println(kv.getRSAKeys(pass.toCharArray()).getPrivate());


//            
            String UserID = kg.hashKeyToString(kv.getRSAKeys(pass.toCharArray()).getPublic());
//            


            System.out.println(key);
            System.out.println(UserID);

            
            ClientSend.registerToServer(UserID, key);
            
//            
            
            SecureDetails setup = new SecureDetails();
            
            
            
            
            
            
            
                        
            
            setup.setId(UserID);         
            setup.setRegistered();
            setup.saveDetails();
            
        
    }
    
}
