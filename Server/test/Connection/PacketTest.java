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
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marc
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
    public void initializer() {
        byte[] encrpytedTicket = "tests".getBytes();
        Message[] messages = new Message[2];

        Packet p = new Packet(encrpytedTicket, messages);

        assertNotNull(p);
    }

    @Test
    public void testSetTicket_Ticket() {
        Packet instance = new Packet();
        System.out.println("setTicket");
        Ticket ticket = null;
        instance.setTicket(ticket);
        System.out.println("getTicket");
        Ticket t = instance.getTicket();

        assertEquals(ticket, t);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setTicket method, of class Packet.
     */
    @Test
    public void testSetTicket_Ticket_SessionKey() {
        Packet instance = new Packet();
        System.out.println("setTicket (With Session Key)");
        Ticket ticket = null;
        SessionKey key = null;
        instance.setTicket(ticket, key);
        System.out.println("getTicket");
        Ticket t = instance.getTicket();
        assertEquals(ticket, t);
        System.out.println("getSessionKey");
        SessionKey sk = instance.getSessionKey();

        assertEquals(key, sk);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setMessages method, of class Packet.
     */
    @Test
    public void testSetMessages() {
        Packet instance = new Packet();
        System.out.println("setMessages");
        Message[] messages = new Message[3];
        instance.setMessages(messages);

        System.out.println("getMessages");

        Message[] m = instance.getMessages();
        assertArrayEquals(messages, m);

    }

    /**
     * Test of setMessage method, of class Packet.
     */
    @Test
    public void testSetMessage() {
        Packet instance = new Packet();
        System.out.println("setMessage");
        Message message = null;
        instance.setMessage(message);
        System.out.println("getMessage");
        Message m = instance.getMessage();

        assertEquals(message, m);

    }

    /**
     * Test of setEncryptedTicket method, of class Packet.
     */
    @Test
    public void testSetEncryptedTicket() {
        Packet instance = new Packet();
        System.out.println("setEncryptedTicket");
        byte[] ticket = null;

        instance.setEncryptedTicket(ticket);

        System.out.println("getEncrpytedTicket");

        byte[] t = instance.getEncryptedTicket();

        assertArrayEquals(ticket, t);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of setSessionKey method, of class Packet.
     */
    @Test
    public void testSetSessionKey() {
        Packet instance = new Packet();
        System.out.println("setSessionKey");
        SessionKey key = null;

        instance.setSessionKey(key);

        System.out.println("getSessionKey");

        SessionKey sk = instance.getSessionKey();

        assertEquals(key, sk);

    }

    /**
     * Test of getEncryptedTicket method, of class Packet.
     */
    /**
     * Test of deleteTicket method, of class Packet.
     */
    @Test
    public void testDeleteTicket() {
        Packet instance = new Packet();

        System.out.println("deleteTicket");

        instance.deleteTicket();

        System.out.println("getTicket");
        Ticket t = instance.getTicket();
        assertNull(t);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of printPacket method, of class Packet.
     */
    @Test
    public void testPrintPacket() {
        System.out.println("printPacket");
        Packet instance = new Packet();
        instance.setEncryptedTicket(null);
        instance.setSessionKey(null);
        instance.setMessages(new Message[3]);
        instance.setTicket(null);
        instance.printPacket();

    }

    /**
     * Test of shortPrintPacket method, of class Packet.
     */
    @Test
    public void testShortPrintPacket() {
        System.out.println("shortPrintPacket");
        Packet instance = new Packet();
        instance.setEncryptedTicket(null);
        instance.setSessionKey(null);
        instance.setMessages(new Message[3]);
        instance.setTicket(null);
        instance.shortPrintPacket();
        // TODO review the generated test code and remove the default call to fail.

    }

}
