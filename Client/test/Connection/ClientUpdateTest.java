/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import Console.*;
import java.io.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Marc
 */
public class ClientUpdateTest {
    
    public ClientUpdateTest() {
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

    @Test
    public void testConstructor() {
        ClientUpdate instance = new ClientUpdate();
        
      
    }
}