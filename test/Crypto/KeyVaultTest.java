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
import javax.crypto.SecretKey;
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
        KeyVault kv = new KeyVault();
        char[] password = "password".toCharArray();
        kv.destroyKeyStore(password);
    }
    
    @Before
    public void setUp() {    
        /*
        //Reasources needed to perform the test
        KeyVault kv = new KeyVault();
        KeyGen kg = new KeyGen();
        HashUtils hu = new HashUtils();
        
        KeyPair kp = kg.generateRSAKeys();
        SecretKey aes = kg.generateAESKey();
        PublicKey publicKey = kp.getPublic();
        PrivateKey secretKey = kp.getPrivate();
        byte[] encodedKey = publicKey.getEncoded();

        char[] password = "password".toCharArray();
        char[] badPassword = "wrongpassword".toCharArray();
        
        */
        
        KeyVault kv = new KeyVault();
        char[] password = "password".toCharArray();
        kv.createKeyStore(password);
    }
    
    @After
    public void tearDown() {
        System.out.println("Teardown");
        KeyVault kv = new KeyVault();
        char[] password = "password".toCharArray();
        kv.destroyKeyStore(password);
    }

    /**
     * Test of checkPassword method, of class KeyVault.
     */
    @Test
    public void testCheckPassword() {
        System.out.println("checkPassword");
        
        KeyVault kv = new KeyVault();
        char[] password = "password".toCharArray();
        
        boolean expResult = true;
        boolean result = kv.checkPassword(password);
        assertEquals(expResult, result);
        
        kv.destroyKeyStore(password);
        return;
    }

    /**
     * Test of bytesToKey method, of class KeyVault.
     */
    @Test
    public void testBytesToKey() {
        System.out.println("bytesToKey");
        byte[] bytes = null;
        KeyVault instance = new KeyVault();
        PublicKey expResult = null;
        PublicKey result = instance.bytesToKey(bytes);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createKeyStore method, of class KeyVault.
     */
    @Test
    public void testCreateKeyStore() {
        System.out.println("createKeyStore");
        char[] localPassword = null;
        //KeyVault instance = new KeyVault();
        //instance.createKeyStore(localPassword);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of destroyKeyStore method, of class KeyVault.
     */
    @Test
    public void testDestroyKeyStore() {
        System.out.println("destroyKeyStore");
        char[] localPassword = null;
        KeyVault instance = new KeyVault();
        boolean expResult = true;
        boolean result = instance.destroyKeyStore(localPassword);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadKeyStore method, of class KeyVault.
     */
    @Test
    public void testLoadKeyStore() {
        System.out.println("loadKeyStore");
        char[] localPassword = null;
        KeyVault instance = new KeyVault();
        KeyStore expResult = null;
        KeyStore result = instance.loadKeyStore(localPassword);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRSAKeys method, of class KeyVault.
     */
    @Test
    public void testSetRSAKeys() {
        System.out.println("setRSAKeys");
        char[] localPassword = null;
        KeyVault instance = new KeyVault();
        boolean expResult = false;
        boolean result = instance.setRSAKeys(localPassword);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAESKey method, of class KeyVault.
     */
    @Test
    public void testSetAESKey() {
        System.out.println("setAESKey");
        char[] localPassword = null;
        KeyVault instance = new KeyVault();
        instance.setAESKey(localPassword);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRSAKeys method, of class KeyVault.
     */
    @Test
    public void testGetRSAKeys() {
        System.out.println("getRSAKeys");
        char[] localPassword = null;
        KeyVault instance = new KeyVault();
        KeyPair expResult = null;
        KeyPair result = instance.getRSAKeys(localPassword);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAESKey method, of class KeyVault.
     */
    @Test
    public void testGetAESKey() {
        System.out.println("getAESKey");
        char[] localPassword = null;
        KeyVault instance = new KeyVault();
        Key expResult = null;
        Key result = instance.getAESKey(localPassword);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
