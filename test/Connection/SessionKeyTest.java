/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Connection;

import Console.User;
import Crypto.Encryption;
import Crypto.KeyGen;
import Crypto.KeyVault;
import javax.crypto.SecretKey;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author maxchandler
 */
public class SessionKeyTest {
    
    public SessionKeyTest() {
    }
    
        public static void setUpClass() {
        User.setPassword("password".toCharArray());
        if(KeyVault.checkIfKsExists() == true)
            KeyVault.destroyKeyStore();
    }
    
    @AfterClass
    public static void tearDownClass() {
        if(KeyVault.checkIfKsExists() == true)
            KeyVault.destroyKeyStore();
    }
    
    @Before
    public void setUp() {
        User.setPassword("password".toCharArray());
        if(KeyVault.checkIfKsExists() == true)
            KeyVault.destroyKeyStore();
        KeyVault.createKeyStore();
        KeyVault.setRSAKeys();
    }
    
    @After
    public void tearDown() {
        KeyVault.destroyKeyStore();
    }

    /**
     * Test of getKey method, of class SessionKey.
     */
    @Test
    public void testGetKey() {
        System.out.println("getKey");
        SessionKey instance = KeyGen.generateSessionKey();
        SecretKey expResult = instance.getKey();
        SecretKey result = instance.getKey();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEncryptedKey method, of class SessionKey.
     */
    @Test
    public void testGetEncryptedKey() {
        System.out.println("getEncryptedKey");
        SessionKey instance = Encryption.encryptSessionKey(KeyGen.generateSessionKey(), KeyVault.getRSAKeys().getPublic());
        byte[] expResult = instance.getEncryptedKey();
        byte[] result = instance.getEncryptedKey();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getIvSpec method, of class SessionKey.
     */
    @Test
    public void testGetIvSpec() {
        System.out.println("getIvSpec");
        SessionKey instance = KeyGen.generateSessionKey();
        byte[] expResult = instance.getIvSpec();
        byte[] result = instance.getIvSpec();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of setKey method, of class SessionKey.
     */
    @Test
    public void testSetKey() {
        System.out.println("setKey");
        SessionKey sk = KeyGen.generateSessionKey();
        SessionKey instance = new SessionKey();
        SecretKey key = sk.getKey();
        instance.setKey(key);
    }

    /**
     * Test of setEncryptedKey method, of class SessionKey.
     */
    @Test
    public void testSetEncryptedKey() {
        System.out.println("setEncryptedKey");
        byte[] key = null;
        SessionKey instance = new SessionKey();
        instance.setEncryptedKey(key);
    }

    /**
     * Test of setIvSpec method, of class SessionKey.
     */
    @Test
    public void testSetIvSpec() {
        System.out.println("setIvSpec");
        byte[] iv = null;
        SessionKey instance = new SessionKey();
        instance.setIvSpec(iv);
    }

    /**
     * Test of deleteKey method, of class SessionKey.
     */
    @Test
    public void testDeleteKey() {
        System.out.println("deleteKey");
        SessionKey instance = KeyGen.generateSessionKey();
        instance.deleteKey();
    }

    /**
     * Test of deleteEncryptedKey method, of class SessionKey.
     */
    @Test
    public void testDeleteEncryptedKey() {
        System.out.println("deleteEncryptedKey");
        SessionKey instance = KeyGen.generateSessionKey();
        instance.setEncryptedKey(null);
        instance.deleteEncryptedKey();
    }
    
}
