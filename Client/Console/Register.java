/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Console;

import Connection.ClientSend;
import Crypto.*;
import java.security.*;
import java.util.Arrays;

/**
 *
 * @author Marc
 */
public class Register {
    
            
    
        public Register(char[] password, char[] confirm) {
            System.out.println(Arrays.toString(password));
            System.out.println(Arrays.toString(confirm));
      
            while (Arrays.equals(password, confirm) == false){
                
                System.out.println("Error");
                
                if (Arrays.equals(password, confirm) == false){
                    
                }
            }

            KeyGen kg = new KeyGen();
            KeyVault kv = new KeyVault();
            KeyPair k = kg.generateRSAKeys();
            HashUtils hu = new HashUtils();

            KeyVault.setRSAKeys();
            KeyVault.setAESKey();
            
            System.out.println(Arrays.toString(KeyVault.getAESKey().getEncoded()));
            
            byte[] key = KeyVault.getRSAKeys().getPublic().getEncoded();

            String UserID = KeyGen.generateUserID();

            ClientSend.registerToServer(UserID, key, password);

            SecureDetails setup = new SecureDetails(confirm);

            setup.setId(UserID);         
            setup.setRegistered();
            System.out.println(setup.getID());
            setup.saveDetails(confirm);
            
        
    }
    
}
