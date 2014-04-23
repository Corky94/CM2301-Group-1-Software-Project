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
public class KeyVaultTest {
    
    public KeyVaultTest() {
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
    }
    
    @After
    public void tearDown() {
        KeyVault.destroyKeyStore();
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
        KeyGen kg = new KeyGen();
        KeyPair kp = kg.generateRSAKeys();
        PublicKey pubKey = kp.getPublic();
        byte[] encoded = pubKey.getEncoded();
        PublicKey expResult = pubKey;
        PublicKey result = KeyVault.bytesToKey(encoded);
        assertEquals(expResult, result);
    }

    /**
     * Test of createKeyStore method, of class KeyVault.
     */
    @Test
    public void testCreateKeyStore() {
        System.out.println("createKeyStore");
        KeyVault.destroyKeyStore();
        KeyVault.createKeyStore();
    }

    /**
     * Test of destroyKeyStore method, of class KeyVault.
     */
    @Test
    public void testDestroyKeyStore() {
        System.out.println("destroyKeyStore");
        boolean expResult = true;
        boolean result = KeyVault.destroyKeyStore();
        assertEquals(expResult, result);
        KeyVault.createKeyStore();
    }

    /**
     * Test of loadKeyStore method, of class KeyVault.
     */
    @Test
    public void testLoadKeyStore() {
        System.out.println("loadKeyStore");
        KeyVault.loadKeyStore();
    }

    /**
     * Test of setRSAKeys method, of class KeyVault.
     */
    @Test
    public void testSetRSAKeys() {
        System.out.println("setRSAKeys");
        KeyVault.setRSAKeys();
    }

    /**
     * Test of setAESKey method, of class KeyVault.
     */
    @Test
    public void testSetAESKey() {
        System.out.println("setAESKey");
        KeyVault.setAESKey();
    }

    /**
     * Test of getRSAKeys method, of class KeyVault.
     */
    @Test
    public void testGetRSAKeys() {
        System.out.println("getRSAKeys");
        //NEEDS TO CREATE RSA KEYS THEN COMPARE TO THE ONES BROUGHT BACK!
        KeyVault.setRSAKeys();
        KeyPair result = KeyVault.getRSAKeys();
        System.out.println(result.getPublic() +"\n" + result.getPrivate());
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
        KeyVault.setAESKey();
        Key k = KeyVault.getAESKey();
        System.out.println(k);
        Key result = KeyVault.getAESKey();
        assertEquals(k, result);
    }
    
}
