/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Console;

import Message.*;
import java.io.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Marc
 */
public class SecureDetailsTest {
    
    public SecureDetailsTest() {
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
        File details = new File("user2.ser");
        details.delete();
    }

    /**
     * Test of setRegistered method, of class SecureDetails.
     */
    @Test
    public void testSetRegistered() {
        System.out.println("setRegistered");
        SecureDetails instance = new SecureDetails();
        instance.setRegistered();
        // TODO review the generated test code and remove the default call to fail.
        System.out.println("getRegistered");
        assertTrue(instance.getRegistered());
    }

    /**
     * Test of setId method, of class SecureDetails.
     */
    @Test
    public void testId() {
        System.out.println("setId");
        String key = "";
        SecureDetails instance = new SecureDetails();
        instance.setId(key);
        System.out.println("getId");
        String result = instance.getID();
        assertEquals(key,result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of addMessage method, of class SecureDetails.
     */
    @Ignore
    public void testAddMessage() {
        System.out.println("addMessage");
        Message m = null;
        SecureDetails instance = new SecureDetails();
        instance.addMessage(m);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteMessage method, of class SecureDetails.
     */
    @Ignore
    public void testDeleteMessage() {
        System.out.println("deleteMessage");
        Message m = null;
        SecureDetails instance = new SecureDetails();
        instance.deleteMessage(m);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessages method, of class SecureDetails.
     */
    @Ignore
    public void testGetMessages() {
        System.out.println("getMessages");
        SecureDetails instance = new SecureDetails();
        Message[] expResult = null;
        Message[] result = instance.getMessages();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    /**
     * Test of saveDetails method, of class SecureDetails.
     */
    @Test
    public void testSaveDetails() {
        System.out.println("saveDetails");
        SecureDetails instance = new SecureDetails();
        instance.saveDetails();
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
