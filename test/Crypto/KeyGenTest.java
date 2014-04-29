/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Crypto;

import Connection.SessionKey;
import Console.User;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author maxchandler
 */
public class KeyGenTest {
    
    public KeyGenTest() {
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
     * Test of generateRSAKeys method, of class KeyGen.
     */
    @Test
    public void testGenerateRSAKeys() {
        System.out.println("generateRSAKeys");
        KeyGen instance = new KeyGen();
        instance.generateRSAKeys();
    }

    /**
     * Test of generateCertificate method, of class KeyGen.
     */
    @Test
    public void testGenerateCertificate() {
        System.out.println("generateCertificate");
        KeyVault.setRSAKeys();
        KeyPair kp = KeyVault.getRSAKeys();
        PublicKey pubKey = kp.getPublic();
        PrivateKey priKey = kp.getPrivate();
        X509Certificate[] result = KeyGen.generateCertificate(pubKey, priKey);
    }

    /**
     * Test of generateAESKey method, of class KeyGen.
     */
    @Test
    public void testGenerateAESKey() {
        System.out.println("generateAESKey");
        KeyGen instance = new KeyGen();
        instance.generateAESKey();
    }

    /**
     * Test of generateUserID method, of class KeyGen.
     */
    @Test
    public void testGenerateUserID() {
        System.out.println("generateUserID");
        KeyVault kv = new KeyVault();
        KeyVault.setRSAKeys();
        KeyGen.generateUserID();
    }

    /**
     * Test of generateSessionKey method, of class KeyGen.
     */
    @Test
    public void testGenerateSessionKey() {
        System.out.println("generateSessionKey");
        KeyGen.generateSessionKey();
    }

    /**
     * Test of concancateByteArrays method, of class KeyGen.
     */
    @Test
    public void testConcancateByteArrays() {
        System.out.println("concancateByteArrays");
        byte[] a = "A".getBytes();
        byte[] b = "B".getBytes();
        byte[] expResult = KeyGen.concancateByteArrays(a, b);
        byte[] result = KeyGen.concancateByteArrays(a, b);
        assertArrayEquals(expResult, result);
    }
    
}
