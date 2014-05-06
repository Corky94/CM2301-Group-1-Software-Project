/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import Message.Message;
import java.io.File;
import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.*;
/**
 *
 * @author Marc
 */
public class SqlTest {
    
    public SqlTest() {
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
     * Test of connectionID method, of class Sql.
     */
    
    @Test
    public void testAddIdServer() {
        System.out.println("addIdServer");
        String url = "ephesus.cs.cf.ac.uk:3306/g1y2013b";
        String user = "g1y2013";
        String password = "dratCi";
        Sql instance = new Sql();
        instance.addIdServer(url, user, password);
        url = "ephesus.cs.cf.ac.uk:3306/g1y2013c";
        user = "g1y2013";
        password = "dratCi";
        instance.addIdServer(url, user, password);
        
        url = "ephesus.cs.cf.ac.uk:3306/g1y2013a";
        user = "g1y2013";
        password = "dratCi";
        instance.addIdServer(url, user, password);
        
        ServerStorage ss = new ServerStorage();
        
        System.out.println(ss.getIdServerList());
        // TODO review the generated test code and remove the default call to fail.

    }

    @Test
    public void testAddMessageServer() {
        System.out.println("addMessageServer");
        String url = "ephesus.cs.cf.ac.uk:3306/g1y2013d";
        String user = "g1y2013";
        String password = "dratCi";
        Sql instance = new Sql();
        instance.addMessageServer(url, user, password);
        url = "ephesus.cs.cf.ac.uk:3306/g1y2013e";
        user = "g1y2013";
        password = "dratCi";
        instance.addMessageServer(url, user, password);
        
        url = "ephesus.cs.cf.ac.uk:3306/g1y2013f";
        user = "g1y2013";
        password = "dratCi";
        instance.addMessageServer(url, user, password);
        // TODO review the generated test code and remove the default call to fail.
        
        ServerStorage ss = new ServerStorage();
        System.out.println(ss.getMessageServerList());
        
        
    }


    /**
     * Test of connection method, of class Sql.
     */
    @Test
    public void testConnection_IDServer() {
        System.out.println("connection");
        String url = "ephesus.cs.cf.ac.uk:3306/g1y2013a";
        String user = "g1y2013";
        String password = "dratCi";
        
        IDServer id = new IDServer();
        id.setPassword(password);
        id.setUrl(url);
        id.setUser(user);
        Sql instance = new Sql();
        Connection result = instance.connection(id);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of connection method, of class Sql.
     */
    @Test
    public void testConnection_MessageServer() {
        System.out.println("connection");
        String url = "ephesus.cs.cf.ac.uk:3306/g1y2013a";
        String user = "g1y2013";
        String password = "dratCi";
        MessageServer  id = new MessageServer();
        id.setPassword(password);
        id.setUrl(url);
        id.setUser(user);
        Sql instance = new Sql();
        Connection result = instance.connection(id);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of register method, of class Sql.
     */
    

    /**
     * Test of tableExist method, of class Sql.
     */
    @Test
    public void testTableExist() {
        System.out.println("tableExist");
        String url = "ephesus.cs.cf.ac.uk:3306/g1y2013a";
        String user = "g1y2013";
        String password = "dratCi";
        MessageServer  mes = new MessageServer();
        mes.setPassword(password);
        mes.setUrl(url);
        mes.setUser(user);
        Sql instance = new Sql();
        Connection con = instance.connection(mes);
        
        boolean expResult = true;
        boolean result = instance.tableExist(con);
        assertEquals(expResult, result);
        
        IDServer id = new IDServer();
        id.setPassword(password);
        id.setUrl(url);
        id.setUser(user);
        instance = new Sql();
        con = instance.connection(id);
        result = instance.tableExist(con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }


    
}