/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Console;

import Connection.ClientSend;
import Message.Message;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Marc
 */
public class UserTest {
    
    public UserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        RegisterTest reg = new RegisterTest();
        reg.testSomeMethod();
    }
    
    @AfterClass
    public static void tearDownClass() {
        File details = new File("user2.ser");
        File kv = new File("keystore");
        details.delete();
        kv.delete();

    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of login method, of class User.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        char[] password = "pass".toCharArray();
        User.login(password);
        assertNotNull(User.clissl);
        // TODO review the generated test code and remove the default call to fail.
    }






    /**
     * Test of createMessage method, of class User.
     */
    @Ignore
    public void testCreateMessage() {
        System.out.println("createMessage");
        SecureDetails s = new SecureDetails();
        String contents = "";
        String recipitent = s.getID();
        String subject = "";
        User.createMessage(contents, recipitent, subject);
        

    }
        /**
     * Test of receiveEmails method, of class User.
     */
    @Test
    public void testReceiveEmails() {
        
        System.out.println("receiveEmails");
        
        Message m[] =User.receiveEmails();
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of deleteMessage method, of class User.
     */
    @Ignore
    public void testDeleteMessage() {
        System.out.println("deleteMessage");
        Message m = null;
        User.deleteMessage(m);
        // TODO review the generated test code and remove the default call to fail.
    }

   

    /**
     * Test of setPassword method, of class User.
     */
    @Test
    public void testPassword() {
        System.out.println("setPassword");
        char[] p = null;
        User.setPassword(p);
        System.out.println("getPassword");
        // TODO review the generated test code and remove the default call to fail.
         char[] result = User.getPassword();
        assertArrayEquals(p, result);
    }

    /**
     * Test of getPassword method, of class User.
     */
    
    
}