/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Crypto;

import java.security.Key;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author maxchandler
 */
public class HashUtilsTest {
    
    public HashUtilsTest() {
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
     * Test of hashKeyToString method, of class HashUtils.
     */
    @Test
    public void testHashKeyToString() {
        System.out.println("hashKeyToString");
        KeyGen kg = new KeyGen();
        Key inputKey = (kg.generateRSAKeys()).getPublic();
        HashUtils instance = new HashUtils();
        instance.hashKeyToString(inputKey);
    }

    /**
     * Test of hashKeyToByte method, of class HashUtils.
     */
    @Test
    public void testHashKeyToByte() {
        System.out.println("hashKeyToByte");
        KeyGen kg = new KeyGen();
        Key inputKey = (kg.generateRSAKeys()).getPublic();
        HashUtils instance = new HashUtils();
        instance.hashKeyToByte(inputKey);
    }

    /**
     * Test of hashSha256 method, of class HashUtils.
     */
    @Test
    public void testHashSha256() {
        System.out.println("hashSha256");
        KeyGen kg = new KeyGen();
        byte[] input = ((kg.generateRSAKeys()).getPublic()).getEncoded();
        HashUtils instance = new HashUtils();
        instance.hashSha256(input);
    }

    /**
     * Test of doubleSha256 method, of class HashUtils.
     */
    @Test
    public void testDoubleSha256() {
        System.out.println("doubleSha256");
        KeyGen kg = new KeyGen();
        byte[] input = ((kg.generateRSAKeys()).getPublic()).getEncoded();
        HashUtils instance = new HashUtils();
        instance.doubleSha256(input);
    }
    
}
