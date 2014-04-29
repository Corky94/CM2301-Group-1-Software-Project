/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Message;

import Connection.SessionKey;
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
public class MessageTest {
    
    public MessageTest() {
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
     * Test of setSessionKey method, of class Message.
     */
    @Test
    public void testSetSessionKey() {
        System.out.println("setSessionKey");
        SessionKey sKey = null;
        Message instance = new Message();
        instance.setSessionKey(sKey);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSessionKey method, of class Message.
     */
    @Test
    public void testGetSessionKey() {
        System.out.println("getSessionKey");
        Message instance = new Message();
        SessionKey expResult = null;
        SessionKey result = instance.getSessionKey();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSender method, of class Message.
     */
    @Test
    public void testGetSender() {
        System.out.println("getSender");
        Message instance = new Message();
        byte[] expResult = null;
        byte[] result = instance.getSender();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSender method, of class Message.
     */
    @Test
    public void testSetSender() {
        System.out.println("setSender");
        byte[] sender = null;
        Message instance = new Message();
        instance.setSender(sender);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReceiver method, of class Message.
     */
    @Test
    public void testGetReceiver() {
        System.out.println("getReceiver");
        Message instance = new Message();
        String expResult = "";
        String result = instance.getReceiver();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setReceiver method, of class Message.
     */
    @Test
    public void testSetReceiver() {
        System.out.println("setReceiver");
        String receiver = "";
        Message instance = new Message();
        instance.setReceiver(receiver);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSubject method, of class Message.
     */
    @Test
    public void testGetSubject() {
        System.out.println("getSubject");
        Message instance = new Message();
        byte[] expResult = null;
        byte[] result = instance.getSubject();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSubject method, of class Message.
     */
    @Test
    public void testSetSubject() {
        System.out.println("setSubject");
        byte[] subject = null;
        Message instance = new Message();
        instance.setSubject(subject);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessage method, of class Message.
     */
    @Test
    public void testGetMessage() {
        System.out.println("getMessage");
        Message instance = new Message();
        byte[] expResult = null;
        byte[] result = instance.getMessage();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMessage method, of class Message.
     */
    @Test
    public void testSetMessage() {
        System.out.println("setMessage");
        byte[] message = null;
        Message instance = new Message();
        instance.setMessage(message);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTimeAndDate method, of class Message.
     */
    @Test
    public void testGetTimeAndDate() {
        System.out.println("getTimeAndDate");
        Message instance = new Message();
        byte[] expResult = null;
        byte[] result = instance.getTimeAndDate();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTimeAndDate method, of class Message.
     */
    @Test
    public void testSetTimeAndDate() {
        System.out.println("setTimeAndDate");
        byte[] timeAndDate = null;
        Message instance = new Message();
        instance.setTimeAndDate(timeAndDate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getKey method, of class Message.
     */
    @Test
    public void testGetKey() {
        System.out.println("getKey");
        Message instance = new Message();
        byte[] expResult = null;
        byte[] result = instance.getKey();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setKey method, of class Message.
     */
    @Test
    public void testSetKey() {
        System.out.println("setKey");
        byte[] key = null;
        Message instance = new Message();
        instance.setKey(key);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isNeedingKey method, of class Message.
     */
    @Test
    public void testIsNeedingKey() {
        System.out.println("isNeedingKey");
        Message instance = new Message();
        boolean expResult = false;
        boolean result = instance.isNeedingKey();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNeedingKey method, of class Message.
     */
    @Test
    public void testSetNeedingKey() {
        System.out.println("setNeedingKey");
        boolean needingKey = false;
        Message instance = new Message();
        instance.setNeedingKey(needingKey);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
