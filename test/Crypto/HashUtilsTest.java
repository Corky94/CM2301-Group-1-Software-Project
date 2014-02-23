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
import static org.junit.Assert.*;

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
        Key inputKey = null;
        HashUtils instance = new HashUtils();
        String expResult = "";
        String result = instance.hashKeyToString(inputKey);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashKeyToByte method, of class HashUtils.
     */
    @Test
    public void testHashKeyToByte() {
        System.out.println("hashKeyToByte");
        Key inputKey = null;
        HashUtils instance = new HashUtils();
        byte[] expResult = null;
        byte[] result = instance.hashKeyToByte(inputKey);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashSha256 method, of class HashUtils.
     */
    @Test
    public void testHashSha256() {
        System.out.println("hashSha256");
        byte[] input = null;
        HashUtils instance = new HashUtils();
        byte[] expResult = null;
        byte[] result = instance.hashSha256(input);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of doubleSha256 method, of class HashUtils.
     */
    @Test
    public void testDoubleSha256() {
        System.out.println("doubleSha256");
        byte[] input = null;
        HashUtils instance = new HashUtils();
        byte[] expResult = null;
        byte[] result = instance.doubleSha256(input);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
