/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Connection;

import Crypto.Ticket;
import Message.Message;
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
public class PacketTest {
    
    public PacketTest() {
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
     * Test of setTicket method, of class Packet.
     */
    @Test
    public void testSetTicket_Ticket() {
        System.out.println("setTicket");
        Ticket ticket = null;
        Packet instance = new Packet();
        instance.setTicket(ticket);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTicket method, of class Packet.
     */
    @Test
    public void testSetTicket_Ticket_SessionKey() {
        System.out.println("setTicket");
        Ticket ticket = null;
        SessionKey key = null;
        Packet instance = new Packet();
        instance.setTicket(ticket, key);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMessages method, of class Packet.
     */
    @Test
    public void testSetMessages() {
        System.out.println("setMessages");
        Message[] messages = null;
        Packet instance = new Packet();
        instance.setMessages(messages);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMessage method, of class Packet.
     */
    @Test
    public void testSetMessage() {
        System.out.println("setMessage");
        Message message = null;
        Packet instance = new Packet();
        instance.setMessage(message);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEncryptedTicket method, of class Packet.
     */
    @Test
    public void testSetEncryptedTicket() {
        System.out.println("setEncryptedTicket");
        byte[] ticket = null;
        Packet instance = new Packet();
        instance.setEncryptedTicket(ticket);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSessionKey method, of class Packet.
     */
    @Test
    public void testSetSessionKey() {
        System.out.println("setSessionKey");
        SessionKey key = null;
        Packet instance = new Packet();
        instance.setSessionKey(key);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEncryptedTicket method, of class Packet.
     */
    @Test
    public void testGetEncryptedTicket() {
        System.out.println("getEncryptedTicket");
        Packet instance = new Packet();
        byte[] expResult = null;
        byte[] result = instance.getEncryptedTicket();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTicket method, of class Packet.
     */
    @Test
    public void testGetTicket() {
        System.out.println("getTicket");
        Packet instance = new Packet();
        Ticket expResult = null;
        Ticket result = instance.getTicket();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessages method, of class Packet.
     */
    @Test
    public void testGetMessages() {
        System.out.println("getMessages");
        Packet instance = new Packet();
        Message[] expResult = null;
        Message[] result = instance.getMessages();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessage method, of class Packet.
     */
    @Test
    public void testGetMessage() {
        System.out.println("getMessage");
        Packet instance = new Packet();
        Message expResult = null;
        Message result = instance.getMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSessionKey method, of class Packet.
     */
    @Test
    public void testGetSessionKey() {
        System.out.println("getSessionKey");
        Packet instance = new Packet();
        SessionKey expResult = null;
        SessionKey result = instance.getSessionKey();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteTicket method, of class Packet.
     */
    @Test
    public void testDeleteTicket() {
        System.out.println("deleteTicket");
        Packet instance = new Packet();
        instance.deleteTicket();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printPacket method, of class Packet.
     */
    @Test
    public void testPrintPacket() {
        System.out.println("printPacket");
        Packet instance = new Packet();
        instance.printPacket();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of shortPrintPacket method, of class Packet.
     */
    @Test
    public void testShortPrintPacket() {
        System.out.println("shortPrintPacket");
        Packet instance = new Packet();
        instance.shortPrintPacket();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
