/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Connection;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Marc
 */
public class ServerSSLTest {
    
    public ServerSSLTest() {
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
     * Test of main method, of class ServerSSL.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        // Port not related to the server ports
        int port = 23456;
        char[] pass = Connection.Server.getPassword();
        ServerSSL instance = new ServerSSL();
        SSLServerSocket result = instance.main(port, pass);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of send method, of class ServerSSL.
     */
    @Ignore
    public void testSend() {
        //Tested withing SendTest.java so no need to retest; 
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
