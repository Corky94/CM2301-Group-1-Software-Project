/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Connection;

import java.util.Stack;
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
public class MessageServerTest {
    private MessageServer instance = new MessageServer();
    public MessageServerTest() {
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
     * Test of getUrl method, of class IDServer.
     */


    /**
     * Test of setUrl method, of class IDServer.
     */
    @Test
    public void testUrl() {
        System.out.println("setUrl");
        String url = "test";
        instance.setUrl(url);
        System.out.println("getUrl");
        String g = instance.getUrl();
        
        assertTrue("The url was not set correctly ",g.contains(url.subSequence(0, 4)));
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getUser method, of class IDServer.
     */
   

    /**
     * Test of setUser method, of class IDServer.
     */
    @Test
    public void testUser() {
        System.out.println("setUser");
        String user = "test";
        instance.setUser(user);
        System.out.println("getUser");
        String g = instance.getUser();
        
        assertTrue("The user was not set correctly ",g.equalsIgnoreCase(user));
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getPassword method, of class IDServer.
     */
   

    /**
     * Test of setPassword method, of class IDServer.
     */
    @Test
    public void testPassword() {
        System.out.println("setPassword");
        String password = "test";
        instance.setPassword(password);
        System.out.println("getPassword");
        String g = instance.getPassword();
        
        assertTrue("The user was not set correctly ",g.equalsIgnoreCase(password));
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of getDetails method, of class IDServer.
     */
    @Test
    public void testGetDetails() {
        System.out.println("updateDetails");
        MessageServer server = null;
        instance.updateDetails(server);
        System.out.println("getDetails");
        Stack result = instance.getDetails();
        
        
        
        assertNull(result.pop());
        // TODO review the generated test code and remove the default call to fail.
        
    }

}