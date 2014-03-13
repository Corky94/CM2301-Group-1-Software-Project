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
            KeyPair k = kg.generateRSAKeys();
            HashUtils hu = new HashUtils();

            kv.setRSAKeys(password);
            kv.setAESKey(password);
            
            System.out.println(kv.getAESKey(password).getEncoded());
            
            byte[] key = kv.getRSAKeys(password).getPublic().getEncoded();

            String UserID = kg.generateUserID(password);

            ClientSend.registerToServer(UserID, key, password);

            SecureDetails setup = new SecureDetails(confirm);

            setup.setId(UserID);         
            setup.setRegistered();
            System.out.println(setup.getID());
            setup.saveDetails(confirm);
            
        
    }
    
}
