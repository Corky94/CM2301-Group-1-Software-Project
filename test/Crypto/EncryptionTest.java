/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Crypto;

import Connection.SessionKey;
import Console.User;
import java.security.Key;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Arrays;
import javax.crypto.SecretKey;
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
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
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
        KeyVault.destroyKeyStore();
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
        KeyVault.createKeyStore();
        KeyVault.setRSAKeys();
        KeyVault.setAESKey();
        Ticket t = Ticket.generateRequest("123123");
        SessionKey sKey = KeyGen.generateSessionKey();
        System.out.println("Session Key:" + sKey);
        System.out.println("Encrypt Auth: " + Arrays.toString(Encryption.encryptAuth(sKey, t)));
        System.out.println("Decrypt Auth:" + Encryption.decryptAuth(sKey, Encryption.encryptAuth(sKey, t)));
        KeyVault.destroyKeyStore();
    }
    
    /**
     * Test of encrypt/decrytpSessionKey method, of class Encryption.
     */
    @Test
    public void testEncryptSessionKey() {
        System.out.println("encrypt & decrypt session key");
        KeyVault.createKeyStore();
        KeyVault.setRSAKeys();
        KeyVault.setAESKey();
        KeyPair kp = KeyVault.getRSAKeys();
        SessionKey sessionKey = KeyGen.generateSessionKey();
        SessionKey encrypted = Encryption.encryptSessionKey(sessionKey, kp.getPublic());
        SessionKey decrypted = Encryption.decryptSessionKey(encrypted);
        System.out.println("Do the keys match? " + decrypted.equals(sessionKey));
        KeyVault.destroyKeyStore();
    }
}
