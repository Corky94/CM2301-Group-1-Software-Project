/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Console;

import Connection.ClientSend;
import Crypto.*;
import java.security.*;
import java.util.Arrays;
import java.util.Scanner;
import javax.crypto.SecretKey;

/**
 *
 * @author Marc
 */
public class Register {
    
            
    
        public Register(char[] password, char[] confirm) {
            System.out.println(password);
            System.out.println(confirm);
      
            while (Arrays.equals(password, confirm) == false){
                
                System.out.println("Error");
                
                if (Arrays.equals(password, confirm) == false){
                    
                }
            }

            
            
            
            
            
            KeyGen kg = new KeyGen();
            KeyVault kv = new KeyVault();
            HashUtils hu = new HashUtils();
            KeyPair k = kg.generateRSAKeys();

            kv.setRSAKeys(password);
            kv.setAESKey(password);
            
            byte[] key = kv.getRSAKeys(password).getPublic().getEncoded();

            String UserID = hu.hashKeyToString(kv.getRSAKeys(password).getPublic());

            ClientSend.registerToServer(UserID, key);

            SecureDetails setup = new SecureDetails();

            setup.setId(UserID);         
            setup.setRegistered();
            setup.saveDetails();
            
        
    }
    
}
