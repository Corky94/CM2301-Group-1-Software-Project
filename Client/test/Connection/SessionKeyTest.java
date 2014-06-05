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
 * @author Marc
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
    public void testConstructor(){
        System.out.println("SessionKey Constructor, with key and iv");
        byte[] key = "key".getBytes();
        byte[] iv = "iv".getBytes();
        SessionKey sk = new SessionKey(key, iv);
        
        byte[] resultKey = sk.getEncryptedKey();
        byte[] resultIv = sk.getIvSpec();
        
        assertArrayEquals(key, resultKey);
        assertArrayEquals(iv, resultIv);
        
    }
   

    /**
     * Test of getEncryptedKey method, of class SessionKey.
     */
   
    /**
     * Test of setKey method, of class SessionKey.
     */
    @Test
    public void testSetKey() {
        System.out.println("setKey");
        SecretKey key = null;
        SessionKey instance = new SessionKey();
        instance.setKey(key);
        System.out.println("getKey");
        SecretKey result = instance.getKey();
        assertEquals(key, result);
        // TODO review the generated test code and remove the default call to fail.

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
        System.out.println("getEncryptedKey");        
        byte[] result = instance.getEncryptedKey();
        assertArrayEquals(key, result);
        // TODO review the generated test code and remove the default call to fail.

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
        System.out.println("getIvSpec");
        byte[] result = instance.getIvSpec();
        assertArrayEquals(iv, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of deleteKey method, of class SessionKey.
     */
    @Test
    public void testDeleteKey() {
        System.out.println("deleteKey");
        SessionKey instance = new SessionKey();
        instance.deleteKey();
        SecretKey key = instance.getKey();
        assertNull(key);

    }

    /**
     * Test of deleteEncryptedKey method, of class SessionKey.
     */
    @Test
    public void testDeleteEncryptedKey() {
        System.out.println("deleteEncryptedKey");
        SessionKey instance = new SessionKey();
        instance.deleteEncryptedKey();
        byte[] result = instance.getEncryptedKey();
        assertNull(result);
        // TODO review the generated test code and remove the default call to fail.

    }
    
}
