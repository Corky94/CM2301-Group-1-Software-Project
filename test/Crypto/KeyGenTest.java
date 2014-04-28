/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Crypto;

import Console.User;
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
        KeyVault.createKeyStore();
        KeyVault.setRSAKeys();
        KeyPair kp = KeyVault.getRSAKeys();
        PublicKey pubKey = kp.getPublic();
        PrivateKey priKey = kp.getPrivate();
        X509Certificate[] result = KeyGen.generateCertificate(pubKey, priKey);
        KeyVault.destroyKeyStore();
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
        KeyVault.createKeyStore();
        KeyVault.setRSAKeys();
        KeyGen.generateUserID();
        KeyVault.destroyKeyStore();
    }
    
}
