/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Crypto;

import java.security.Key;
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
        Key publicKey = null;
        String data = "";
        Encryption instance = new Encryption();
        byte[] expResult = null;
        byte[] result = instance.encryptString(publicKey, data);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of decryptString method, of class Encryption.
     */
    @Test
    public void testDecryptString() {
        System.out.println("decryptString");
        byte[] data = null;
        char[] localPassword = null;
        Encryption instance = new Encryption();
        byte[] expResult = null;
        byte[] result = instance.decryptString(data, localPassword);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of bTS method, of class Encryption.
     */
    @Test
    public void testBTS() {
        System.out.println("bTS");
        byte[] input = null;
        Encryption instance = new Encryption();
        String expResult = "";
        String result = instance.bTS(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of encryptRemotePassword method, of class Encryption.
     */
    @Test
    public void testEncryptRemotePassword() {
        System.out.println("encryptRemotePassword");
        byte[] remotePassword = null;
        char[] localPassword = null;
        Encryption instance = new Encryption();
        byte[] expResult = null;
        byte[] result = instance.encryptRemotePassword(remotePassword, localPassword);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of decryptRemotePassword method, of class Encryption.
     */
    @Test
    public void testDecryptRemotePassword() {
        System.out.println("decryptRemotePassword");
        byte[] encryptedPassword = null;
        char[] localPassword = null;
        Encryption instance = new Encryption();
        byte[] expResult = null;
        byte[] result = instance.decryptRemotePassword(encryptedPassword, localPassword);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getKey method, of class Encryption.
     */
    @Test
    public void testGetKey() {
        System.out.println("getKey");
        String id = "";
        Encryption instance = new Encryption();
        PublicKey expResult = null;
        PublicKey result = instance.getKey(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}