/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.ActionEvent;
import javax.swing.JPanel;
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
public class SteganographyTest {
    
    public SteganographyTest() {
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
     * Test of Steganography method, of class Steganography.
     */
    @Test
    public void testSteganography() {
        System.out.println("Steganography");
        Steganography instance = new Steganography();
        JPanel expResult = null;
        JPanel result = instance.Steganography();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actionPerformed method, of class Steganography.
     */
    @Test
    public void testActionPerformed() {
        System.out.println("actionPerformed");
        ActionEvent e = null;
        Steganography instance = new Steganography();
        instance.actionPerformed(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of encode method, of class Steganography.
     */
    @Test
    public void testEncode() {
        System.out.println("encode");
        String path = "";
        String original = "";
        String stegan = "";
        String message = "";
        Steganography instance = new Steganography();
        boolean expResult = false;
        boolean result = instance.encode(path, original, stegan, message);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of decode method, of class Steganography.
     */
    @Test
    public void testDecode() {
        System.out.println("decode");
        String path = "";
        String name = "";
        Steganography instance = new Steganography();
        String expResult = "";
        String result = instance.decode(path, name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class Steganography.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Steganography.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
