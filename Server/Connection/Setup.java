/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import Crypto.*;
import java.security.KeyPair;

/**
 *
 * @author Marc
 */
public class Setup {

	public Setup(char[] password){

		KeyGen kg = new KeyGen();
		KeyVault kv = new KeyVault();
		KeyPair k = kg.generateRSAKeys();

		kv.setRSAKeys(password);
		kv.setAESKey(password);

		


	}
    
}
