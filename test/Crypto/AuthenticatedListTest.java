/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Crypto;

import Console.User;
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
public class AuthenticatedListTest {
    
    public AuthenticatedListTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        User.setPassword("password".toCharArray());
    }
    
    @AfterClass
    public static void tearDownClass() {
        if(KeyVault.checkIfKsExists() == true)
            KeyVault.destroyKeyStore();
    }
    
    @Before
    public void setUp() {
        KeyVault.createKeyStore();
        KeyVault.setRSAKeys();
        KeyVault.setAESKey();
    }
    
    @After
    public void tearDown() {
        KeyVault.destroyKeyStore();
    }

    /**
     * Test of addAuth method, of class AuthenticatedList.
     */
    @Test
    public void testAddAuth() {
        System.out.println("addAuth");
        Ticket newTicket =  new Ticket(
            "Client ID",
            "Node ID 1",
            "Node Address",
            "One Time Password",
            KeyVault.getRSAKeys().getPublic(),
            KeyVault.getRSAKeys().getPublic(),
            true
        );
        AuthenticatedList.addAuth(newTicket);
        System.out.println("passed");
    }

    /**
     * Test of getNodeAuthentication method, of class AuthenticatedList.
     */
    @Test
    public void testGetNodeAuthentication() {
        System.out.println("getNodeAuthentication");
        Ticket newTicket =  new Ticket(
            "Client ID",
            "Node ID 1",
            "Node Address",
            "One Time Password",
            KeyVault.getRSAKeys().getPublic(),
            KeyVault.getRSAKeys().getPublic(),
            true
        );
        AuthenticatedList.addAuth(newTicket);
        if(AuthenticatedList.getNodeAuthentication("Node ID 1").getNodeId().equals(newTicket.getNodeId()))
            System.out.println("passed");
        else
            fail();
    }

    /**
     * Test of getNodeAuthenticationByAddress method, of class AuthenticatedList.
     */
    @Test
    public void testGetNodeAuthenticationByAddress() {
        System.out.println("getNodeAuthenticationByAddress");
        Ticket newTicket =  new Ticket(
            "Client ID",
            "Node ID 1",
            "Node Address 1",
            "One Time Password",
            KeyVault.getRSAKeys().getPublic(),
            KeyVault.getRSAKeys().getPublic(),
            true
        );
        AuthenticatedList.addAuth(newTicket);
        if(AuthenticatedList.getNodeAuthenticationByAddress("Node Address 1").equals(newTicket))
            System.out.println("passed");
        else
            fail();
    }

    /**
     * Test of getClientAuthentication method, of class AuthenticatedList.
     */
    @Test
    public void testGetClientAuthentication() {
        System.out.println("getClientAuthentication");
        Ticket newTicket =  new Ticket(
            "Client ID",
            "Node ID 1",
            "Node Address 1",
            "One Time Password",
            KeyVault.getRSAKeys().getPublic(),
            KeyVault.getRSAKeys().getPublic(),
            true
        );
        AuthenticatedList.addAuth(newTicket);
        if(AuthenticatedList.getClientAuthentication("Client ID").getClientId().equals(newTicket.getClientId()))
            System.out.println("passed");
        else
            fail();
    }

    /**
     * Test of deleteAuth method, of class AuthenticatedList.
     */
    @Test
    public void testDeleteAuth() {
        System.out.println("deleteAuth");
        Ticket newTicket =  new Ticket(
            "Client ID",
            "Node ID 1",
            "Node Address",
            "One Time Password",
            KeyVault.getRSAKeys().getPublic(),
            KeyVault.getRSAKeys().getPublic(),
            true
        );
        AuthenticatedList.addAuth(newTicket);
        AuthenticatedList.deleteAuth("Client ID");
        System.out.println("passed");
    }

    /**
     * Test of exists method, of class AuthenticatedList.
     */
    @Test
    public void testExists() {
        System.out.println("exists");
        Ticket newTicket =  new Ticket(
            "Client ID",
            "Node ID 1",
            "Node Address",
            "One Time Password",
            KeyVault.getRSAKeys().getPublic(),
            KeyVault.getRSAKeys().getPublic(),
            true
        );
        boolean expResult = true;
        AuthenticatedList.addAuth(newTicket);
        boolean result = AuthenticatedList.exists("Node Address");
        assertEquals(expResult, result);
        System.out.println("passed");
    }

    /**
     * Test of removeExpiredAuth method, of class AuthenticatedList.
     */
    @Test
    public void testRemoveExpiredAuth() {
        System.out.println("removeExpiredAuth");
        Ticket newTicket =  new Ticket(
            "Client ID",
            "Node ID 1",
            "Node Address 1",
            "One Time Password",
            KeyVault.getRSAKeys().getPublic(),
            KeyVault.getRSAKeys().getPublic(),
            true
        );
        AuthenticatedList.addAuth(newTicket);
        AuthenticatedList.removeExpiredAuth();
        System.out.println("passed");
    }

    /**
     * Test of printList method, of class AuthenticatedList.
     */
    @Test
    public void testPrintList() {
        System.out.println("printList");
                Ticket newTicket =  new Ticket(
            "Client ID",
            "Node ID 1",
            "Node Address 1",
            "One Time Password",
            KeyVault.getRSAKeys().getPublic(),
            KeyVault.getRSAKeys().getPublic(),
            true
        );
        AuthenticatedList.addAuth(newTicket);
        AuthenticatedList.printList();
        System.out.println("passed");
    }
    
}
