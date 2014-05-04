/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Crypto;

import Connection.SessionKey;
import Console.User;
import java.security.KeyPair;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author maxchandler
 */
public class EncryptionTest {
    
    public EncryptionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        User.setPassword("password".toCharArray());
    }
    
    @AfterClass
    public static void tearDownClass() {
        if(KeyVault.checkIfKsExists() == true)
            KeyVault.destroyKeyStore();
    }
    
    @Before
    public void setUp() {
        KeyVault.createKeyStore();
        KeyVault.setRSAKeys();
        KeyVault.setAESKey();
    }
    
    @After
    public void tearDown() {
        KeyVault.destroyKeyStore();
    }


    /**
     * Test of encryptString method, of class Encryption.
     */
    @Test
    public void testEncryptString() {
        System.out.println("encryptString");
        SessionKey sKey = KeyGen.generateSessionKey();
        String data = "Test String 1 √ ) &" ;
        Encryption.encryptString(sKey, data);
    }

    /**
     * Test of decryptString method, of class Encryption.
     */
    @Test
    public void testDecryptString() {
        System.out.println("decryptString");
        SessionKey sKey = KeyGen.generateSessionKey();
        String data = "Test String 1 √ ) &";
        System.out.println("String:" + data);
        byte[] encrypted = Encryption.encryptString(sKey, data);
        System.out.println("Encrypted data:" + Arrays.toString(encrypted));
        String decrypted = Encryption.bTS(Encryption.decryptString(sKey, encrypted));
        System.out.println("Decrypted String:" + decrypted);
        if(! decrypted.equals(data)){
            fail("Did not decrypt secussfully");
        }
    }

    /**
     * Test of bTS method, of class Encryption.
     */
    @Test
    public void testBTS() {
        System.out.println("bTS");
        String input = "Test String";
        byte[] bytes = input.getBytes();
        String expResult = "Test String";
        String result = Encryption.bTS(bytes);
        assertEquals(expResult, result);
    }

    /**
     * Test of encryptAuth method, of class Encryption.
     */
    @Test
    public void testEncryptTicket() {
        System.out.println("encryptTicket");
        Ticket t = Ticket.generateRequest("123123" , "Node Address");
        SessionKey sKey = KeyGen.generateSessionKey();
        System.out.println("Session Key:" + sKey);
        System.out.println("Encrypt Auth: " + Arrays.toString(Encryption.encryptAuth(sKey, t)));
        System.out.println("Decrypt Auth:" + Encryption.decryptAuth(sKey, Encryption.encryptAuth(sKey, t)));
    }
    
    /**
     * Test of encrypt/decrytpSessionKey method, of class Encryption.
     */
    @Test
    public void testEncryptSessionKey() {
        System.out.println("encrypt & decrypt session key");
        KeyVault.setRSAKeys();
        KeyVault.setAESKey();
        KeyPair kp = KeyVault.getRSAKeys();
        SessionKey sessionKey = KeyGen.generateSessionKey();
        SessionKey encrypted = Encryption.encryptSessionKey(sessionKey, kp.getPublic());
        SessionKey decrypted = Encryption.decryptSessionKey(encrypted);
        System.out.println("Do the keys match? " + decrypted.equals(sessionKey));
    }

    /**
     * Test of decryptTicket method, of class Encryption.
     */
    @Test
    public void testDecryptTicket() {
        System.out.println("decryptTicket");
        Ticket t = new Ticket(
            "Client ID",
            "Node ID 1",
            "Node Address",
            "One Time Password",
            KeyVault.getRSAKeys().getPublic(),
            KeyVault.getRSAKeys().getPublic(),
            true
        );
        Encryption.decryptTicket(Encryption.encryptTicket(t));
    }

    /**
     * Test of encryptAuth method, of class Encryption.
     */
    @Test
    public void testEncryptAuth() {
        System.out.println("encryptAuth");
        SessionKey sKey = KeyGen.generateSessionKey();;
        Ticket t = new Ticket(
            "Client ID",
            "Node ID 1",
            "Node Address",
            "One Time Password",
            KeyVault.getRSAKeys().getPublic(),
            KeyVault.getRSAKeys().getPublic(),
            true
        );
        Encryption.encryptAuth(sKey, t);
    }

    /**
     * Test of decryptAuth method, of class Encryption.
     */
    @Test
    public void testDecryptAuth() {
        System.out.println("decryptAuth");
        SessionKey sessionKey = KeyGen.generateSessionKey();
        Ticket newTicket =  new Ticket(
            "Client ID",
            "Node ID 1",
            "Node Address",
            "One Time Password",
            KeyVault.getRSAKeys().getPublic(),
            KeyVault.getRSAKeys().getPublic(),
            true
        );
        byte[] auth = Encryption.encryptAuth(sessionKey, newTicket);
        Encryption.decryptAuth(sessionKey, auth);
    }

    /**
     * Test of decryptSessionKey method, of class Encryption.
     */
    @Test
    public void testDecryptSessionKey() {
        System.out.println("decryptSessionKey");
        Encryption.decryptSessionKey(Encryption.encryptSessionKey(KeyGen.generateSessionKey(), KeyVault.getRSAKeys().getPublic()));
    }
}
