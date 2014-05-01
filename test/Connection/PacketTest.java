/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Connection;

import Console.User;
import Crypto.Encryption;
import Crypto.KeyGen;
import Crypto.KeyVault;
import Message.Message;
import java.security.Key;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author maxchandler
 */
public class PacketTest {
    
    public PacketTest() {
    }
    
    public static void setUpClass() {
        User.setPassword("password".toCharArray());
        if(KeyVault.checkIfKsExists() == true)
            KeyVault.destroyKeyStore();
    }
    
    @AfterClass
    public static void tearDownClass() {
        if(KeyVault.checkIfKsExists() == true)
            KeyVault.destroyKeyStore();
    }
    
    @Before
    public void setUp() {
        User.setPassword("password".toCharArray());
        if(KeyVault.checkIfKsExists() == true)
            KeyVault.destroyKeyStore();
        KeyVault.createKeyStore();
        KeyVault.setRSAKeys();
    }
    
    @After
    public void tearDown() {
        KeyVault.destroyKeyStore();
    }

    /**
     * Test of setTicket method, of class Packet.
     */
    @Test
    public void testSetTicket_Ticket() {
        System.out.println("setTicket");
        Key pk = KeyVault.getRSAKeys().getPublic();
        Ticket t = new Ticket(
            "clientId",
            "nodeId",
            "nodeAddress",
            "password",
            pk,
            pk, 
            true
        );
        Packet instance = new Packet();
        instance.setTicket(ticket);
    }

    /**
     * Test of setTicket method, of class Packet.
     */
    @Test
    public void testSetTicket_Ticket_SessionKey() {
        System.out.println("setTicket");
        Key pk = KeyVault.getRSAKeys().getPublic();
        Ticket ticket = new Ticket("1","1","1","1",pk,pk,false);
        SessionKey key = KeyGen.generateSessionKey();
        Packet instance = new Packet();
        instance.setTicket(ticket, key);
    }

    /**
     * Test of setMessages method, of class Packet.
     */
    @Test
    public void testSetMessages() {
        System.out.println("setMessages");
        Ticket expResult = new Ticket(
            "Client ID",
            null,
            "Node Address",
            null,
            KeyVault.getRSAKeys().getPublic(),
            null,
            true
        );
        Message[] messages = null;
        Packet instance = new Packet();
        instance.setMessages(messages);
    }

    /**
     * Test of setMessage method, of class Packet.
     */
    @Test
    public void testSetMessage() {
        System.out.println("setMessage");
        Message message = new Message();
        Packet instance = new Packet();
        instance.setMessage(message);
    }

    /**
     * Test of setEncryptedTicket method, of class Packet.
     */
    @Test
    public void testSetEncryptedTicket() {
        System.out.println("setEncryptedTicket");
        SessionKey sk = KeyGen.generateSessionKey();
        Ticket t = new Ticket();
        Encryption.encryptTicket(null)
        byte[] ticket = null;
        Packet instance = new Packet();
        instance.setEncryptedTicket(ticket);
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
