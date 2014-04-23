package Console;

import Connection.*;
import Crypto.*;

public class Register {
        public Register(char[] password) {
            User.setPassword(password);
            KeyVault.createTrustStore();
            KeyVault.createKeyStore();
            KeyVault.setRSAKeys();
            KeyVault.setAESKey();
            
            byte[] key = KeyVault.getRSAKeys().getPublic().getEncoded();

            String UserID = KeyGen.generateUserID();
            Console.User.clissl = new ClientSSL();
            ClientSend.registerToServer(UserID, key);

            SecureDetails setup = new SecureDetails();

            setup.setId(UserID);         
            setup.setRegistered();
            setup.saveDetails();                   
    }   
}
