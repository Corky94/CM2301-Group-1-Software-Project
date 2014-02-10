/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Console;

import Encryption.*;
import Server.*;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Scanner;

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
            Sql s = new Sql();
            Encryption e = new Encryption();
//            
            KeyPair k = kg.generateRSAKeys();
            
            byte[] key = (k.getPublic().getEncoded());
            
            for (int i = 0; i< key.length; i++){
                
                System.out.print(key[i]);
                
            }
            System.out.println();

//            
            String UserID = kg.hashKey(k.getPublic());
//            
//            String UserID = e.bTS(hash);
//            
//            System.out.println(hash);

            System.out.println(key);
            System.out.println(UserID);
//            
            
            SecureDetails setup = new SecureDetails();
            
            s.register(UserID, key);
            
            
            
            
            
                        
            
            setup.setId(UserID);
            setup.setPrivateKey(k.getPrivate());          
            setup.setPassword(pass);
            setup.saveDetails();
            
        
    }
    
}
