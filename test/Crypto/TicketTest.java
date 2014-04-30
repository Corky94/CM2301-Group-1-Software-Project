/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Crypto;

import Console.User;
import java.security.Key;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author maxchandler
 */
public class TicketTest {
    
    public TicketTest() {
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
     * Test of generateRequest method, of class Ticket.
     */
    @Test
    public void testGenerateRequest() {
        System.out.println("generateRequest");
        KeyVault.setRSAKeys();
        String ID = "Client ID";
        String nodeAddress = "Node Address";
        Ticket expResult = new Ticket(
            "Client ID",
            null,
            "Node Address",
            null,
            KeyVault.getRSAKeys().getPublic(),
            null,
            true
        );
        Ticket result = Ticket.generateRequest(ID, nodeAddress);
        if(!result.getClientId().equals(expResult.getClientId()) & result.getNodeAddress().equals(expResult.getNodeAddress()) & result.getClientPublicKey().equals(expResult.getClientPublicKey()))
            fail("Ticket not constructed correctly");
    }

    /**
     * Test of getClientId method, of class Ticket.
     */
    @Test
    public void testGetClientId() {
        System.out.println("getClientId");
        Ticket newTicket = new Ticket(
            "Client ID",
            "Node ID 1",
            "Node Address",
            "One Time Password",
            KeyVault.getRSAKeys().getPublic(),
            KeyVault.getRSAKeys().getPublic(),
            true
        );
        String expResult = "Client ID";
        String result = newTicket.getClientId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNodeId method, of class Ticket.
     */
    @Test
    public void testGetNodeId() {
        System.out.println("getNodeId");
        Ticket instance = new Ticket(
            "Client ID",
            "Node ID 1",
            "Node Address",
            "One Time Password",
            KeyVault.getRSAKeys().getPublic(),
            KeyVault.getRSAKeys().getPublic(),
            true
        );
        String expResult = "Node ID 1";
        String result = instance.getNodeId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNodeAddress method, of class Ticket.
     */
    @Test
    public void testGetNodeAddress() {
        System.out.println("getNodeAddress");
        Ticket instance = new Ticket(
            "Client ID",
            "Node ID 1",
            "Node Address",
            "One Time Password",
            KeyVault.getRSAKeys().getPublic(),
            KeyVault.getRSAKeys().getPublic(),
            true
        );
        String expResult = "Node Address";
        String result = instance.getNodeAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPassword method, of class Ticket.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Ticket instance = new Ticket(
            "Client ID",
            "Node ID 1",
            "Node Address",
            "One Time Password",
            KeyVault.getRSAKeys().getPublic(),
            KeyVault.getRSAKeys().getPublic(),
            true
        );
        String expResult = "One Time Password";
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of getClientPublicKey method, of class Ticket.
     */
    @Test
    public void testGetClientPublicKey() {
        System.out.println("getClientPublicKey");
        Ticket instance = new Ticket(
            "Client ID",
            "Node ID 1",
            "Node Address",
            "One Time Password",
            KeyVault.getRSAKeys().getPublic(),
            KeyVault.getRSAKeys().getPublic(),
            true
        );
        Key expResult = KeyVault.getRSAKeys().getPublic();
        Key result = instance.getClientPublicKey();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNodePublicKey method, of class Ticket.
     */
    @Test
    public void testGetNodePublicKey() {
        System.out.println("getNodePublicKey");
        Ticket instance = new Ticket(
            "Client ID",
            "Node ID 1",
            "Node Address",
            "One Time Password",
            KeyVault.getRSAKeys().getPublic(),
            KeyVault.getRSAKeys().getPublic(),
            true
        );
        Key expResult = KeyVault.getRSAKeys().getPublic();
        Key result = instance.getNodePublicKey();
        assertEquals(expResult, result);
    }

    /**
     * Test of is_challenge method, of class Ticket.
     */
    @Test
    public void testIs_challenge() {
        System.out.println("is_challenge");
        Ticket instance = new Ticket(
            "Client ID",
            "Node ID 1",
            "Node Address",
            "One Time Password",
            KeyVault.getRSAKeys().getPublic(),
            KeyVault.getRSAKeys().getPublic(),
            true
        );
        boolean expResult = true;
        boolean result = instance.is_challenge();
        assertEquals(expResult, result);
    }

    /**
     * Test of setChallenge method, of class Ticket.
     */
    @Test
    public void testSetChallenge() {
        System.out.println("setChallenge");
        boolean b = false;
        Ticket instance = new Ticket(
            "Client ID",
            "Node ID 1",
            "Node Address",
            "One Time Password",
            KeyVault.getRSAKeys().getPublic(),
            KeyVault.getRSAKeys().getPublic(),
            true
        );
        instance.setChallenge(b);
        boolean expResult = false;
        boolean result = instance.is_challenge();
        assertEquals(expResult, result);
    }

    /**
     * Test of printTicket method, of class Ticket.
     */
    @Test
    public void testPrintTicket() {
        System.out.println("printTicket");
        Ticket instance = new Ticket(
            "Client ID",
            "Node ID 1",
            "Node Address",
            "One Time Password",
            KeyVault.getRSAKeys().getPublic(),
            KeyVault.getRSAKeys().getPublic(),
            true
        );
        instance.printTicket();
    }
    
}
