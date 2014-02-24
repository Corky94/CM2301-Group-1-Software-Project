/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Crypto;

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
        Encryption instance = new Encryption();
        instance.encryptString(publicKey, data);
    }

    /**
     * Test of decryptString method, of class Encryption.
     */
    @Test
    public void testDecryptString() {
        System.out.println("decryptString");
        KeyGen kg = new KeyGen();
        KeyVault kv = new KeyVault();
        Encryption e = new Encryption();
        char[] localPassword = "oassword".toCharArray();
        kv.createKeyStore(localPassword);
        kv.setRSAKeys(localPassword);
        String data = "Test String 1 √ ) &";
        PublicKey pk = kv.getRSAKeys(localPassword).getPublic();
        String decrypted = e.bTS(e.decryptString(e.encryptString(pk, data), localPassword));
        if(! decrypted.equals(data)){
            fail("Did not decrypt secussfully");
        }
        kv.destroyKeyStore(localPassword);
    }

    /**
     * Test of bTS method, of class Encryption.
     */
    @Test
    public void testBTS() {
        System.out.println("bTS");
        String input = "Test String";
        byte[] bytes = input.getBytes();
        Encryption instance = new Encryption();
        String expResult = "Test String";
        String result = instance.bTS(bytes);
        assertEquals(expResult, result);
    }

    /**
     * Test of encryptRemotePassword method, of class Encryption.
     */
    @Test
    public void testEncryptRemotePassword() {
        System.out.println("encryptRemotePassword");
        KeyVault kv = new KeyVault();
        HashUtils hu = new HashUtils();
        char[] localPassword = "password".toCharArray();
        kv.createKeyStore(localPassword);
        kv.setRSAKeys(localPassword);
        kv.setAESKey(localPassword);
        KeyPair kp = kv.getRSAKeys(localPassword);
        byte[] remotePassword = hu.hashKeyToByte(kp.getPrivate());
        Encryption instance = new Encryption();
        byte[] result = instance.encryptRemotePassword(remotePassword, localPassword);
        kv.destroyKeyStore(localPassword);
    }

    /**
     * Test of decryptRemotePassword method, of class Encryption.
     */
    @Test
    public void testDecryptRemotePassword() {
        System.out.println("decryptRemotePassword");
        KeyVault kv = new KeyVault();
        HashUtils hu = new HashUtils();
        char[] localPassword = "password".toCharArray();
        kv.createKeyStore(localPassword);
        kv.setRSAKeys(localPassword);
        kv.setAESKey(localPassword);
        KeyPair kp = kv.getRSAKeys(localPassword);
        byte[] remotePassword = hu.hashKeyToByte(kp.getPrivate());
        Encryption instance = new Encryption();
        instance.decryptRemotePassword(instance.encryptRemotePassword(remotePassword, localPassword), localPassword) ;
        kv.destroyKeyStore(localPassword);
    }
}
