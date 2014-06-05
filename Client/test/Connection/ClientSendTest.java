/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import Console.*;
import Message.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Marc
 */
public class ClientSendTest {

    public ClientSendTest() {
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
     * Test of send method, of class ClientSend.
     */
    @Test
    public void testSend() {
        System.out.println("send");
        Message m = null;
        ClientSend.send(m);

    }

    /**
     * Test of registerToServer method, of class ClientSend.
     */
    @Test
    public void testRegisterToServer() {
        System.out.println("registerToServer");
        String id = "";
        byte[] key = null;
        ClientSend.registerToServer(id, key);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of stressTest method, of class ClientSend.
     */
    @Ignore
    public void testStressTest() {
        System.out.println("stressTest");
        ClientSend.stressTest();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
