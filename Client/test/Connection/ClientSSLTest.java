/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Connection;

import Console.*;
import java.security.cert.*;
import javax.net.ssl.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Marc
 */
public class ClientSSLTest {
    static ClientSSL instance;
    public ClientSSLTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        new User();
        User.login("pass".toCharArray());
        instance = new ClientSSL();
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
     * Test of main method, of class ClientSSL.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        int portNumber = 12346;
        
        SSLSocket result = instance.main(portNumber);
        assertNotNull( result);
        // TODO review the generated test code and remove the default call to fail.

    }
    @Test
    public void testMainWrongPort() {
        System.out.println("main wrong port");
        int portNumber = 12345;
        
        SSLSocket result = instance.main(portNumber);
        assertNull( result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getNodeAddress method, of class ClientSSL.
     */
    @Test
    public void testGetNodeAddress() {
        System.out.println("getNodeAddress");
        String result = instance.getNodeAddress();
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getSslCert method, of class ClientSSL.
     */
    @Ignore
    public void testGetSslCert() {
        System.out.println("getSslCert");
        String host = "";
        int port = 0;
        Certificate expResult = null;
        Certificate result = ClientSSL.getSslCert(host, port);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
