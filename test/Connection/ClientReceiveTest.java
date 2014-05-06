/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Connection;

import Console.*;
import Message.*;
import java.security.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Marc
 */
public class ClientReceiveTest {
    
    public ClientReceiveTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        new User();
        User.login("pass".toCharArray());
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
     * Test of receive method, of class ClientReceive.
     */
    @Test
    public void testReceive() {
        System.out.println("receive");
        String Id = "XEZWowbanpFFqhT7WazDqSjJz7nNs23PBuzStEZeV9be44Eza";
        Message[] result = ClientReceive.receive(Id);
        assertNotNull(result);

    }

    /**
     * Test of getKey method, of class ClientReceive.
     */
    @Test
    public void testGetKey() {
        System.out.println("getKey");
        String id = "XEZWowbanpFFqhT7WazDqSjJz7nNs23PBuzStEZeV9be44Eza";
        PublicKey result = ClientReceive.getKey(id);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.

    }
    
}
