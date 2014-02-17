/*
 * To change this template, choose Tools | Templates
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
 * @author Marc
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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of checkPassword method, of class KeyVault.
     */
    @Test
    public void testCheckPassword() {
        System.out.println("checkPassword");
        char[] localPassword = null;
        KeyVault instance = new KeyVault();
        boolean expResult = false;
        boolean result = instance.checkPassword(localPassword);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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