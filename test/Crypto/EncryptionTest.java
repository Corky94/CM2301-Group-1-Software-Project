/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Crypto;

import Console.User;
import java.security.Key;
import java.security.KeyPair;
import java.security.PublicKey;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
        KeyGen kg = new KeyGen();
        Key publicKey = kg.generateRSAKeys().getPublic();
        String data = "Test String 1 √ ) &" ;
        Encryption.encryptString(publicKey, data);
    }

    /**
     * Test of decryptString method, of class Encryption.
     */
    @Test
    public void testDecryptString() {
        System.out.println("decryptString");
        KeyVault.createKeyStore();
        KeyVault.setRSAKeys();
        String data = "Test String 1 √ ) &";
        PublicKey pk = KeyVault.getRSAKeys().getPublic();
        String decrypted = Encryption.bTS(Encryption.decryptString(Encryption.encryptString(pk, data)));
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
     * Test of encryptRemotePassword method, of class Encryption.
     */
    @Test
    public void testEncryptRemotePassword() {
        System.out.println("encryptRemotePassword");
        KeyVault kv = new KeyVault();
        KeyVault.createKeyStore();
        KeyVault.setRSAKeys();
        KeyVault.setAESKey();
        KeyPair kp = KeyVault.getRSAKeys();
        byte[] remotePassword = HashUtils.hashKeyToByte(kp.getPrivate());
        byte[] result = Encryption.encryptRemotePassword(remotePassword);
        KeyVault.destroyKeyStore();
    }

    /**
     * Test of decryptRemotePassword method, of class Encryption.
     */
    @Test
    public void testDecryptRemotePassword() {
        System.out.println("decryptRemotePassword");
        KeyVault kv = new KeyVault();
        HashUtils hu = new HashUtils();
        KeyVault.createKeyStore();
        KeyVault.setRSAKeys();
        KeyVault.setAESKey();
        KeyPair kp = KeyVault.getRSAKeys();
        byte[] remotePassword = HashUtils.hashKeyToByte(kp.getPrivate());
        Encryption.decryptRemotePassword(Encryption.encryptRemotePassword(remotePassword)) ;
        KeyVault.destroyKeyStore();
    }
}
