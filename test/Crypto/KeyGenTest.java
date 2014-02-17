/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Crypto;

import java.security.Key;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import javax.crypto.SecretKey;
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
public class KeyGenTest {
    
    public KeyGenTest() {
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
     * Test of generateRSAKeys method, of class KeyGen.
     */
    @Test
    public void testGenerateRSAKeys() {
        System.out.println("generateRSAKeys");
        KeyGen instance = new KeyGen();
        KeyPair expResult = null;
        KeyPair result = instance.generateRSAKeys();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateCertificate method, of class KeyGen.
     */
    @Test
    public void testGenerateCertificate() {
        System.out.println("generateCertificate");
        PublicKey pubKey = null;
        PrivateKey priKey = null;
        X509Certificate[] expResult = null;
        X509Certificate[] result = KeyGen.generateCertificate(pubKey, priKey);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateAESKey method, of class KeyGen.
     */
    @Test
    public void testGenerateAESKey() {
        System.out.println("generateAESKey");
        KeyGen instance = new KeyGen();
        SecretKey expResult = null;
        SecretKey result = instance.generateAESKey();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateRemotePassword method, of class KeyGen.
     */
    @Test
    public void testGenerateRemotePassword() {
        System.out.println("generateRemotePassword");
        char[] localPassword = null;
        KeyGen instance = new KeyGen();
        byte[] expResult = null;
        byte[] result = instance.generateRemotePassword(localPassword);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateUserID method, of class KeyGen.
     */
    @Test
    public void testGenerateUserID() {
        System.out.println("generateUserID");
        char[] localPassword = null;
        KeyGen instance = new KeyGen();
        String expResult = "";
        String result = instance.generateUserID(localPassword);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashKeyToString method, of class KeyGen.
     */
    @Test
    public void testHashKeyToString() {
        System.out.println("hashKeyToString");
        Key inputKey = null;
        KeyGen instance = new KeyGen();
        String expResult = "";
        String result = instance.hashKeyToString(inputKey);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashKeyToByte method, of class KeyGen.
     */
    @Test
    public void testHashKeyToByte() {
        System.out.println("hashKeyToByte");
        Key inputKey = null;
        KeyGen instance = new KeyGen();
        byte[] expResult = null;
        byte[] result = instance.hashKeyToByte(inputKey);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}