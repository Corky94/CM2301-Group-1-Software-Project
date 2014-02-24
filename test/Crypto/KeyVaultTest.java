/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Crypto;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import static org.hamcrest.CoreMatchers.instanceOf;
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
public class KeyVaultTest {
    
    public KeyVaultTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        KeyVault kv = new KeyVault();
        kv.createKeyStore("password".toCharArray());
    }
    
    @After
    public void tearDown() {
        KeyVault kv = new KeyVault();
        kv.destroyKeyStore("password".toCharArray());
    }

    /**
     * Test of checkPassword method, of class KeyVault.
     */
    @Test
    public void testCheckPassword() {
        System.out.println("checkPassword");
        char[] localPassword = "password".toCharArray();
        KeyVault kv = new KeyVault();
        boolean expResult = true;
        boolean result = kv.checkPassword(localPassword);
        assertEquals(expResult, result);
    }

    /**
     * Test of bytesToKey method, of class KeyVault.
     */
    @Test
    public void testBytesToKey() {
        System.out.println("bytesToKey");
        KeyVault instance = new KeyVault();
        KeyGen kg = new KeyGen();
        KeyPair kp = kg.generateRSAKeys();
        PublicKey pubKey = kp.getPublic();
        byte[] encoded = pubKey.getEncoded();
        PublicKey expResult = pubKey;
        PublicKey result = instance.bytesToKey(encoded);
        assertEquals(expResult, result);
    }

    /**
     * Test of createKeyStore method, of class KeyVault.
     */
    @Test
    public void testCreateKeyStore() {
        System.out.println("createKeyStore");
        char[] localPassword = "password".toCharArray();
        KeyVault instance = new KeyVault();
        instance.destroyKeyStore(localPassword);
        instance.createKeyStore(localPassword);
    }

    /**
     * Test of destroyKeyStore method, of class KeyVault.
     */
    @Test
    public void testDestroyKeyStore() {
        System.out.println("destroyKeyStore");
        char[] localPassword = "password".toCharArray();
        KeyVault instance = new KeyVault();
        boolean expResult = true;
        boolean result = instance.destroyKeyStore(localPassword);
        assertEquals(expResult, result);
        instance.createKeyStore(localPassword);
    }

    /**
     * Test of loadKeyStore method, of class KeyVault.
     */
    @Test
    public void testLoadKeyStore() {
        System.out.println("loadKeyStore");
        char[] localPassword = null;
        KeyVault instance = new KeyVault();
        KeyStore expResult = instance.loadKeyStore(localPassword);
    }

    /**
     * Test of setRSAKeys method, of class KeyVault.
     */
    @Test
    public void testSetRSAKeys() {
        System.out.println("setRSAKeys");
        char[] localPassword = "password".toCharArray();
        KeyVault instance = new KeyVault();
        boolean expResult = true;
        boolean result = instance.setRSAKeys(localPassword);
        assertEquals(expResult, result);
    }

    /**
     * Test of setAESKey method, of class KeyVault.
     */
    @Test
    public void testSetAESKey() {
        System.out.println("setAESKey");
        char[] localPassword = "password".toCharArray();
        KeyVault instance = new KeyVault();
        instance.setAESKey(localPassword);
    }

    /**
     * Test of getRSAKeys method, of class KeyVault.
     */
    @Test
    public void testGetRSAKeys() {
        System.out.println("getRSAKeys");
        char[] localPassword = "password".toCharArray();
        KeyVault instance = new KeyVault();
        //NEEDS TO CREATE RSA KEYS THEN COMPARE TO THE ONES BROUGHT BACK!
        instance.setRSAKeys(localPassword);
        
        KeyPair result = instance.getRSAKeys(localPassword);
        
        KeyPair dummy = new KeyPair(null, null);

        if(!result.getClass().equals(dummy.getClass())){
          fail("Not correct return type");
        }
    }

    /**
     * Test of getAESKey method, of class KeyVault.
     */
    @Test
    public void testGetAESKey() {
        System.out.println("getAESKey");
        char[] localPassword = "password".toCharArray();
        KeyVault instance = new KeyVault();
        instance.setAESKey(localPassword);
        Key kp = instance.getAESKey(localPassword);
        Key expResult = kp;
        Key result = instance.getAESKey(localPassword);
        assertEquals(expResult, result);
    }
    
}
