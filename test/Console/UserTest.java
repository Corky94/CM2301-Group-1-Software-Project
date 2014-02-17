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
        File kv = new File("keyStoreName");
        details.delete();
        kv.delete();
        Message m = null;
        ClientSend c = new ClientSend();
        c.sendMessage(m);
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
        User instance = new User();
        instance.login(password);
        assertEquals(true, instance.loggedIn);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of logout method, of class User.
     */
    @Test
    public void testLogout() {
        System.out.println("logout");
        User instance = new User();
        instance.logout();

    }

    /**
     * Test of readMessages method, of class User.
     */
    @Ignore
    public void testReadMessages() {
        System.out.println("readMessages");
        User instance = new User();
        instance.readMessages();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }



    /**
     * Test of createMessage method, of class User.
     */
    @Test
    public void testCreateMessage() {
        System.out.println("createMessage");
        SecureDetails s = new SecureDetails();
        String contents = "";
        String recipitent = s.getID();
        String subject = "";
        User instance = new User();
        instance.createMessage(contents, recipitent, subject);
        

    }
        /**
     * Test of receiveEmails method, of class User.
     */
    @Test
    public void testReceiveEmails() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(UserTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("receiveEmails");
        User instance = new User();
        Message m[] =instance.receiveEmails();
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of deleteMessage method, of class User.
     */
    @Ignore
    public void testDeleteMessage() {
        System.out.println("deleteMessage");
        int x = 0;
        User instance = new User();
        instance.deleteMessage(x);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class User.
     */
    @Ignore
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        User.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}