/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Crypto;

import Connection.Packet;
import Connection.SessionKey;
import Console.User;
import java.io.FileOutputStream;
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
        Ticket t = Ticket.generateRequest("123123" , "Node Address");
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

    /**
     * Test of decryptTicket method, of class Encryption.
     */
    @Test
    public void testDecryptTicket() {
        System.out.println("decryptTicket");
        Packet p = null;
        Packet expResult = null;
        Packet result = Encryption.decryptTicket(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of encryptAuth method, of class Encryption.
     */
    @Test
    public void testEncryptAuth() {
        System.out.println("encryptAuth");
        SessionKey sKey = null;
        Ticket t = null;
        Encryption.encryptAuth(sKey, t);
    }

    /**
     * Test of decryptAuth method, of class Encryption.
     */
    @Test
    public void testDecryptAuth() {
        System.out.println("decryptAuth");
        SessionKey sessionKey = KeyGen.generateSessionKey();
        KeyPair kp = KeyVault.getRSAKeys();
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
        SessionKey sKey = null;
        SessionKey expResult = null;
        SessionKey result = Encryption.decryptSessionKey(sKey);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of encryptFile method, of class Encryption.
     */
    @Test
    public void testEncryptFile() {
        System.out.println("encryptFile");
        FileOutputStream data = null;
        Encryption.encryptFile(data);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of decryptFile method, of class Encryption.
     */
    @Test
    public void testDecryptFile() {
        System.out.println("decryptFile");
        String dir = "";
        Encryption.decryptFile(dir);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
