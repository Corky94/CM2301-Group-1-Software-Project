package Crypto;

import java.security.*;
import javax.crypto.*;
import java.io.*;

import java.security.spec.X509EncodedKeySpec;
import java.security.cert.X509Certificate;

import org.bouncycastle.x509.X509V2AttributeCertificate;

/*
*KeyVault is responcible for storing and retreiving keys from the vault, it also provides a password check for user login. 
*/

public class KeyVault{

	private final String KEY_STORE_DIR = "";
	private final String KEY_STORE_NAME = "keyStoreName";
	private final String KEY_STORE_TYPE = "JCEKS";

	public KeyVault(){
	}
		
	//PASSWORD IN REFERENCE IS TO OPEN THE KEYVAULT (LOCAL PASSWORD)
	public boolean checkPassword(char[] localPassword){
		try{
			KeyVault ks = new KeyVault();
			ks.loadKeyStore(localPassword);
			return true;
		}catch(Exception ex){
			return false;
		}
	}
	
	public PublicKey bytesToKey(byte[] bytes){
		try{
			PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bytes));
    		return publicKey;
    	}catch(Exception ex){
    		throw new RuntimeException(ex);
    	}
	}

	//Keystore methods	
	public void createKeyStore(char[] localPassword) {
		try {
		    KeyStore ks = KeyStore.getInstance(KEY_STORE_TYPE);  
		    KeyStore.ProtectionParameter passwordProtection = new KeyStore.PasswordProtection(localPassword);
	    	ks.load(null, localPassword);

	    	if (checkIfKsExists(KEY_STORE_DIR + KEY_STORE_NAME) != true){
		    	FileOutputStream fos = null;
		        fos = new FileOutputStream(KEY_STORE_DIR + KEY_STORE_NAME);
		        ks.store(fos, localPassword);

		        if (fos != null)
		        	fos.close();
			}
			else{
				throw new RuntimeException("KEYSTORE ALREADY EXISTS");
			}
	    }catch(Exception ex){
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
	    }
	}
        
        public boolean destroyKeyStore(char[] localPassword){
            KeyVault kv = new KeyVault();
            if(kv.checkPassword(localPassword)){
                File vault = new File(KEY_STORE_DIR + KEY_STORE_NAME);
                if(vault.delete()){
                    return true;
                }
            }
            return false;
        }
        
	public KeyStore loadKeyStore(char[] localPassword){
        if (this.checkIfKsExists(KEY_STORE_DIR + KEY_STORE_NAME)== false){
            createKeyStore(localPassword);
        }         
		try{
			KeyStore ks  = KeyStore.getInstance(KEY_STORE_TYPE);
			ks.load(new FileInputStream(KEY_STORE_DIR + KEY_STORE_NAME), localPassword);
			return ks;
		}catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}

	private boolean checkIfKsExists(String keyStoreName){
		File f = new File(keyStoreName);
		if(f.exists() && !f.isDirectory()){
			return true;
		}
		return false;
	}

	
	//Setter methods
	public boolean setRSAKeys(char[] localPassword){
		try{
			KeyVault kv = new KeyVault();
			KeyStore ks = kv.loadKeyStore(localPassword);
			//generate the rsaKeys
			KeyGen kg = new KeyGen();
			KeyPair rsaKeys = kg.generateRSAKeys();
			PublicKey pubKey = rsaKeys.getPublic();
			PrivateKey priKey = rsaKeys.getPrivate();

			KeyStore.ProtectionParameter passwordProtection = new KeyStore.PasswordProtection(localPassword);

			KeyStore.PrivateKeyEntry rsaEntry = new KeyStore.PrivateKeyEntry(priKey, kg.generateCertificate(pubKey, priKey));
			ks.setEntry("rsaKeys", rsaEntry, passwordProtection);

			FileOutputStream fos = null;
	        fos = new FileOutputStream(KEY_STORE_DIR + KEY_STORE_NAME);
	        ks.store(fos, localPassword);

	        if (fos != null) {
	            fos.close();
	    	}

	    	return true;
	    }catch(Exception ex){
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
	    }
	}
	
	public void setAESKey(char[] localPassword){
		try{
			KeyVault kv = new KeyVault();
			KeyStore ks = kv.loadKeyStore(localPassword);
			//generate the aesKey
			KeyGen kg = new KeyGen();
			SecretKey aesKey = kg.generateAESKey();

			KeyStore.ProtectionParameter passwordProtection = new KeyStore.PasswordProtection(localPassword);

			KeyStore.SecretKeyEntry aesKeyEntry = new KeyStore.SecretKeyEntry(aesKey);
			ks.setEntry("aesKey", aesKeyEntry, passwordProtection);

			FileOutputStream fos = null;
	        fos = new FileOutputStream(KEY_STORE_DIR + KEY_STORE_NAME);
	        ks.store(fos, localPassword);

	         if (fos != null) {
	            fos.close();
	    	}
	    }catch(Exception ex){
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
	    }
	}
	
	//Getter methods
	public KeyPair getRSAKeys(char[] localPassword){
		try{
			KeyVault kv = new KeyVault();
			KeyStore ks = kv.loadKeyStore(localPassword);

			KeyStore.ProtectionParameter passwordProtection = new KeyStore.PasswordProtection(localPassword);
			KeyStore.PrivateKeyEntry rsaKeyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry("rsaKeys", passwordProtection);
			PrivateKey priKey = rsaKeyEntry.getPrivateKey();
			java.security.cert.Certificate rsaCert = rsaKeyEntry.getCertificate();
			PublicKey pubKey = rsaCert.getPublicKey();
			KeyPair rsaKeys = new KeyPair(pubKey, priKey);
			return rsaKeys;
		}catch(Exception ex){
			//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}
	}
	
	
	public Key getAESKey(char[] localPassword){
		try{
			KeyVault kv = new KeyVault();
			KeyStore ks = kv.loadKeyStore(localPassword);

			KeyStore.ProtectionParameter passwordProtection = new KeyStore.PasswordProtection(localPassword);
			KeyStore.SecretKeyEntry aesKeyEntry = (KeyStore.SecretKeyEntry) ks.getEntry("aesKey", passwordProtection);
			Key aesKey = aesKeyEntry.getSecretKey();
			return aesKey;
		}catch(Exception ex){
			//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}
	}
        /*
	//
	// Unit testing
	//
	public static void main (String[] args){
		//First initiate the keyvault kv
		KeyVault kv = new KeyVault();
		//Password and fake password created
		char[] password = "password".toCharArray();
		char[] badPassword = "wrongpassword".toCharArray();
		//Create the kv, the kv directory and name needs to be clear else it'll throw an error.
		System.out.println("Testing KeyVault");
		kv.createKeyStore(password);
		System.out.println("KeyVault created sucessfully");
		//Test loading the keystore
		if(kv.loadKeyStore(password) instanceof KeyStore){
			System.out.println("KeyVault loaded sucessfully");
		}
		//Test if correct password is accepted
		System.out.println("Testing KeyVault security (with local password)");
		if(kv.checkPassword(password) == true){
			System.out.println("KeyVault accepts password correctly");
		}
		//Test if bad password is rejected
		if(kv.checkPassword(badPassword) == false){
			System.out.println("KeyVault rejects badPassword correctly");
		}
		System.out.println("Checking key creation and loading");

		System.out.println("Testing AES PrivateKey");
		//Attempt to set AES Keys
		kv.setAESKey(password);
		System.out.println("AES Keys created sucessfully");
		//Load AES keys
		if(kv.getAESKey(password) instanceof PrivateKey){
			System.out.println("AES Keys loaded sucessfully");
		}

		System.out.println("Testing RSA KeyPair");
		//Attempt to set RSA Keys
		kv.setRSAKeys(password);
		System.out.println("RSA Keys created sucessfully");
		//Load RSA Keys
		if(kv.getRSAKeys(password) instanceof KeyPair){
			System.out.println("RSA Keys loaded sucessfully");
		}
	}*/
}