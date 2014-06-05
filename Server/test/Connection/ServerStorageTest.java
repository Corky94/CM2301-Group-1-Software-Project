/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Connection;

import java.util.Stack;
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
public class ServerStorageTest {
    
    public ServerStorageTest() {
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
     * Test of updateIDSeverDetails method, of class ServerStorage.
     */
    @Test
    public void testUpdateIDSeverDetails() {
        System.out.println("updateIDSeverDetails");
        Stack details = null;
        ServerStorage instance = new ServerStorage();
        
        instance.updateIDSeverDetails(details);
        Stack expResult = null;
        System.out.println("getIdServerList");
        instance = new ServerStorage();
        Stack result = instance.getIdServerList();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getIdServerList method, of class ServerStorage.
     */
   

    /**
     * Test of updateMessageServerDetails method, of class ServerStorage.
     */
    @Test
    public void testUpdateMessageServerDetails() {
        System.out.println("updateMessageServerDetails");
        Stack details = null;
        ServerStorage instance = new ServerStorage();
        instance.updateMessageServerDetails(details);
        System.out.println("getMessageServerList");
        instance = new ServerStorage();
        Stack expResult = null;
        Stack result = instance.getMessageServerList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of updateOtherServers method, of class ServerStorage.
     */
    @Test
    public void testUpdateOtherServers() {
        System.out.println("updateOtherServers");
        ServerStorage instance = new ServerStorage();
        instance.updateOtherServers();
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getMessageServerList method, of class ServerStorage.
     */
    
    
}
