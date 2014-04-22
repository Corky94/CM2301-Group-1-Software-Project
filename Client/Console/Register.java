/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Console;

import Connection.*;
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
    
            
    
        public Register(char[] password) {
            User.setPassword(password);
            KeyGen kg = new KeyGen();
            KeyVault kv = new KeyVault();
            HashUtils hu = new HashUtils();
            KeyPair k = kg.generateRSAKeys();
            kv.createKeyStore();

            kv.setRSAKeys();
            kv.setAESKey();
            
            byte[] key = kv.getRSAKeys().getPublic().getEncoded();

            String UserID =kg.generateUserID();
            Console.User.clissl = new ClientSSL();
            ClientSend.registerToServer(UserID, key,password);

            SecureDetails setup = new SecureDetails();

            setup.setId(UserID);         
            setup.setRegistered();
            setup.saveDetails();
            
        
    }
    
}
