/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Crypto;

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
public class Base58Test {
    
    public Base58Test() {
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
     * Test of encode method, of class Base58.
     */
    @Test
    public void testEncode() {
        System.out.println("encode");
        byte[] input = null;
        String expResult = "";
        String result = Base58.encode(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of decode method, of class Base58.
     */
    @Test
    public void testDecode() {
        System.out.println("decode");
        String input = "";
        byte[] expResult = null;
        byte[] result = Base58.decode(input);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of decodeChecked method, of class Base58.
     */
    @Test
    public void testDecodeChecked() {
        System.out.println("decodeChecked");
        String input = "";
        byte[] expResult = null;
        byte[] result = Base58.decodeChecked(input);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
