/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Crypto;

import Message.Message;
import java.awt.Point;
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
        Encryption.encryptString(publicKey, data);
    }

    /**
     * Test of decryptString method, of class Encryption.
     */
    @Test
    public void testDecryptString() {
        System.out.println("decryptString");
        KeyGen kg = new KeyGen();
        KeyVault kv = new KeyVault();
        char[] localPassword = "oassword".toCharArray();
        kv.createKeyStore(localPassword);
        kv.setRSAKeys(localPassword);
        String data = "Test String 1 √ ) &";
        PublicKey pk = KeyVault.getRSAKeys(localPassword).getPublic();
        String decrypted = Encryption.bTS(Encryption.decryptString(Encryption.encryptString(pk, data), localPassword));
        if(! decrypted.equals(data)){
            fail("Did not decrypt secussfully");
        }
        KeyVault.destroyKeyStore(localPassword);
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
        char[] localPassword = "password".toCharArray();
        kv.createKeyStore(localPassword);
        kv.setRSAKeys(localPassword);
        kv.setAESKey(localPassword);
        KeyPair kp = KeyVault.getRSAKeys(localPassword);
        byte[] remotePassword = HashUtils.hashKeyToByte(kp.getPrivate());
        byte[] result = Encryption.encryptRemotePassword(remotePassword, localPassword);
        KeyVault.destroyKeyStore(localPassword);
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
        KeyPair kp = KeyVault.getRSAKeys(localPassword);
        byte[] remotePassword = HashUtils.hashKeyToByte(kp.getPrivate());
        Encryption.decryptRemotePassword(Encryption.encryptRemotePassword(remotePassword, localPassword), localPassword) ;
        KeyVault.destroyKeyStore(localPassword);
    }
    @Test
    public void testEncryptObject(){
        System.out.println("encryptObject");
        KeyVault kv = new KeyVault();
        HashUtils hu = new HashUtils();
        char[] localPassword = "password".toCharArray();
        kv.createKeyStore(localPassword);
        kv.setRSAKeys(localPassword);
        kv.setAESKey(localPassword);
        Message exampleObject = new Message();
        byte[] encrypted = Encryption.encryptObject(localPassword, KeyVault.getRSAKeys(localPassword).getPublic(), exampleObject);
        Encryption.bTS(encrypted);
        KeyVault.destroyKeyStore(localPassword);
    }
    
    @Test
    public void testDecryptObject(){
        System.out.println("decryptObject");
        KeyVault kv = new KeyVault();
        HashUtils hu = new HashUtils();
        char[] localPassword = "password".toCharArray();
        kv.createKeyStore(localPassword);
        kv.setRSAKeys(localPassword);
        kv.setAESKey(localPassword);
        String exampleObject = new String("HASDHASHDHS");
        byte[] encryptedObject = Encryption.encryptObject(localPassword, KeyVault.getRSAKeys(localPassword).getPublic(), exampleObject);
        System.out.println("decrypted object: " + Encryption.decryptObject(localPassword, KeyVault.getRSAKeys(localPassword).getPrivate(), encryptedObject));
        KeyVault.destroyKeyStore(localPassword);
    }
}
