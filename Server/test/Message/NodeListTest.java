/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Message;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marc
 */

public class NodeListTest {
    
    public NodeListTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
        File f = new File("NodeList.ser");
        f.delete();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addNode method, of class NodeList.
     */


    /**
     * Test of getNode method, of class NodeList.
     */
    @Test
    public void testGetNode() {

        System.out.println("getNode");
        NodeList instance = new NodeList();
        instance.addNode();
        String expResult = "192.168.56.1";
        String result = instance.getNode();
        assertEquals(expResult, result);
        
    }
}