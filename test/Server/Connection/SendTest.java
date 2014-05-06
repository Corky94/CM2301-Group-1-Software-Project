/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import static Connection.Server.getPassword;
import Message.NodeList;
import java.util.Arrays;
import java.util.Stack;
import javax.net.ssl.SSLServerSocket;
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
public class SendTest {
    
    public SendTest() {
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

    @Test
    public void testConstructor() {
        NodeList n = new NodeList();
        ServerStorage ss = new ServerStorage();
        Stack s = ss.getIdServerList();

        
        new Send(ss.getIdServerList());
        
        new Send(ss.getMessageServerList());
        // TODO review the generated test code and remove the default call to fail.
       
    }
}