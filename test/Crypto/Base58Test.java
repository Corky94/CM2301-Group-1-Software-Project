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
        byte[] input = "Test String 1?@*√".getBytes();
        Base58.encode(input);
    }

    /**
     * Test of decode method, of class Base58.
     */
    @Test
    public void testDecode() {
        System.out.println("decode");
        String input = "Test String 1?@*√";
        byte[] expResult = Base58.decode(input);
        byte[] result = Base58.decode(input);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of decodeChecked method, of class Base58.
     */
    @Test
    public void testDecodeChecked() {
        System.out.println("decodeChecked");
        String input = "Test String 1?@*√";
        byte[] result = Base58.decodeChecked(input);
    }
    
}
