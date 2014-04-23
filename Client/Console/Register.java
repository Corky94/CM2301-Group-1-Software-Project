/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Console;

import Connection.*;
import Crypto.*;
/**
 *
 * @author Marc
 */
public class Register {
        public Register(char[] password) {
            User.setPassword(password);
            KeyVault.createKeyStore();
            KeyVault.setRSAKeys();
            KeyVault.setAESKey();
            
            byte[] key = KeyVault.getRSAKeys().getPublic().getEncoded();

            String UserID = KeyGen.generateUserID();
            Console.User.clissl = new ClientSSL();
            ClientSend.registerToServer(UserID, key,password);

            SecureDetails setup = new SecureDetails();

            setup.setId(UserID);         
            setup.setRegistered();
            setup.saveDetails();                   
    }   
}
