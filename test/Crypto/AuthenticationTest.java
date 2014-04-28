/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Crypto;

import Connection.Packet;
import Console.User;
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
public class AuthenticationTest {
    
    public AuthenticationTest() {
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
     * Test of createPacket method, of class Authentication.
     */
    @Test
    public void testCreatePacket() {
        System.out.println("createPacket");
        Message messages = null;
        String nodeAddress = "";
        Packet expResult = null;
        Packet result = Authentication.createPacket(messages, nodeAddress);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of auth method, of class Authentication.
     */
    @Test
    public void testAuth() {
        System.out.println("auth");
        String nodeAddress = "";
        Packet expResult = null;
        Packet result = Authentication.auth(nodeAddress);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of handleChallenge method, of class Authentication.
     */
    @Test
    public void testHandleChallenge() {
        System.out.println("handleChallenge");
        Packet p = null;
        Packet expResult = null;
        Packet result = Authentication.handleChallenge(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
