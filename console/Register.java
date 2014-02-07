/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package console;

import java.security.*;
import java.util.Scanner;

/**
 *
 * @author Marc
 */
public class Register {
    
            
    
        Register() throws NoSuchAlgorithmException{
        
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
            
//            Encryption e = new Encryption();
//            
            KeyPair k = kg.generateRSAKeys();
//            
            String UserID = kg.hashKey(k.getPublic());
//            
//            String UserID = e.bTS(hash);
//            
//            System.out.println(hash);
            System.out.println(UserID);
//            
            
            SecureDetails setup = new SecureDetails();
            
            
            
            
            
                        
            
            setup.setId(UserID);
            setup.setPrivateKey(k.getPrivate());          
            setup.setPassword(pass);
            setup.saveDetails();
            
        
    }
    
}
