/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Crypto;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import org.junit.After;
import org.junit.AfterClass;
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
        instance.generateRSAKeys();
    }

    /**
     * Test of generateCertificate method, of class KeyGen.
     */
    @Test
    public void testGenerateCertificate() {
        System.out.println("generateCertificate");
        char[] localPassword = "password".toCharArray();
        KeyVault kv = new KeyVault();
        kv.createKeyStore(localPassword);
        kv.setRSAKeys(localPassword);
        KeyPair kp = kv.getRSAKeys(localPassword);
        PublicKey pubKey = kp.getPublic();
        PrivateKey priKey = kp.getPrivate();
        X509Certificate[] result = KeyGen.generateCertificate(pubKey, priKey);
        kv.destroyKeyStore(localPassword);
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
     * Test of generateRemotePassword method, of class KeyGen.
     */
    @Test
    public void testGenerateRemotePassword() {
        System.out.println("generateRemotePassword");
        char[] localPassword = "password".toCharArray();
        KeyVault kv = new KeyVault();
        kv.createKeyStore(localPassword);
        kv.setRSAKeys(localPassword);
        KeyGen instance = new KeyGen();
        instance.generateRemotePassword(localPassword);
        kv.destroyKeyStore(localPassword);
    }

    /**
     * Test of generateUserID method, of class KeyGen.
     */
    @Test
    public void testGenerateUserID() {
        System.out.println("generateUserID");
        char[] localPassword = "password".toCharArray();
        KeyVault kv = new KeyVault();
        kv.createKeyStore(localPassword);
        kv.setRSAKeys(localPassword);
        KeyGen instance = new KeyGen();
        instance.generateUserID(localPassword);
        kv.destroyKeyStore(localPassword);
    }
    
}
