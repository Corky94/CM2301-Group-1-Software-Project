/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Connection;

import javax.crypto.SecretKey;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author maxchandler
 */
public class SessionKeyTest {
    
    public SessionKeyTest() {
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
     * Test of getKey method, of class SessionKey.
     */
    @Test
    public void testGetKey() {
        System.out.println("getKey");
        SessionKey instance = new SessionKey();
        SecretKey expResult = null;
        SecretKey result = instance.getKey();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEncryptedKey method, of class SessionKey.
     */
    @Test
    public void testGetEncryptedKey() {
        System.out.println("getEncryptedKey");
        SessionKey instance = new SessionKey();
        byte[] expResult = null;
        byte[] result = instance.getEncryptedKey();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIvSpec method, of class SessionKey.
     */
    @Test
    public void testGetIvSpec() {
        System.out.println("getIvSpec");
        SessionKey instance = new SessionKey();
        byte[] expResult = null;
        byte[] result = instance.getIvSpec();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setKey method, of class SessionKey.
     */
    @Test
    public void testSetKey() {
        System.out.println("setKey");
        SecretKey key = null;
        SessionKey instance = new SessionKey();
        instance.setKey(key);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEncryptedKey method, of class SessionKey.
     */
    @Test
    public void testSetEncryptedKey() {
        System.out.println("setEncryptedKey");
        byte[] key = null;
        SessionKey instance = new SessionKey();
        instance.setEncryptedKey(key);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIvSpec method, of class SessionKey.
     */
    @Test
    public void testSetIvSpec() {
        System.out.println("setIvSpec");
        byte[] iv = null;
        SessionKey instance = new SessionKey();
        instance.setIvSpec(iv);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteKey method, of class SessionKey.
     */
    @Test
    public void testDeleteKey() {
        System.out.println("deleteKey");
        SessionKey instance = new SessionKey();
        instance.deleteKey();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteEncryptedKey method, of class SessionKey.
     */
    @Test
    public void testDeleteEncryptedKey() {
        System.out.println("deleteEncryptedKey");
        SessionKey instance = new SessionKey();
        instance.deleteEncryptedKey();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
