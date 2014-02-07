import java.security.*;
import javax.crypto.*;
import java.io.*;

public class KeyVault{

	private final String keyStoreDir = "";
	private final String keyStoreName = "keyStoreName";
	private final String keyStoreType = "JCEKS";

	KeyVault(){
	}

	//Keystore methods	
	private void createKeyStore(char[] localPassword) {
		try {
		    KeyStore ks = KeyStore.getInstance(keyStoreType);  
		    KeyStore.ProtectionParameter passwordProtection = new KeyStore.PasswordProtection(localPassword);
	    	ks.load(null, localPassword);

	    	if (checkIfKsExists(keyStoreName) != true){
		    	FileOutputStream fos = null;
		        fos = new FileOutputStream(keyStoreName);
		        ks.store(fos, localPassword);

		        if (fos != null)
		        	fos.close();
			}
			else{
				throw new RuntimeException("KEYSTORE ALREADY EXISTS");
			}
		    //These have been left seperate to design different errors in the future
	    }catch(FileNotFoundException ex){
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
	    }catch(IOException ex){    	
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}catch(KeyStoreException ex){    	
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}catch(NoSuchAlgorithmException ex){    	
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}catch(java.security.cert.CertificateException ex){    	
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}

	}

	private KeyStore loadKeyStore(char[] localPassword) throws KeyStoreException{
		KeyStore ks  = KeyStore.getInstance(keyStoreType);
		try{
			ks.load(new FileInputStream(keyStoreName), localPassword);
		}catch(FileNotFoundException ex){
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
	    }catch(IOException ex){    	
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}
		catch(NoSuchAlgorithmException ex){    	
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}catch(java.security.cert.CertificateException ex){    	
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}
		finally{
			return ks;
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
			PrivateKey priKey = rsaKeys.getPrivate();
			Certificate[] cert = kg.signKey(priKey);

			KeyStore.ProtectionParameter passwordProtection = new KeyStore.PasswordProtection(localPassword);

			KeyStore.PrivateKeyEntry rsaPriEntry = new KeyStore.PrivateKeyEntry(priKey, cert);
			ks.setEntry("rsaPriKey", rsaPriEntry, passwordProtection);

			FileOutputStream fos = null;
	        fos = new FileOutputStream(keyStoreName);
	        ks.store(fos, localPassword);

	         if (fos != null) {
	            fos.close();
	    }
	    }catch(FileNotFoundException ex){
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
	    }catch(IOException ex){    	
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}catch(KeyStoreException ex){    	
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}catch(NoSuchAlgorithmException ex){    	
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}catch(java.security.cert.CertificateException ex){    	
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
	        fos = new FileOutputStream(keyStoreName);
	        ks.store(fos, localPassword);

	         if (fos != null) {
	            fos.close();
	    	}
	    }catch(FileNotFoundException ex){
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
	    }catch(IOException ex){    	
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}catch(KeyStoreException ex){    	
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}catch(NoSuchAlgorithmException ex){    	
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}catch(java.security.cert.CertificateException ex){    	
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}
	}
	/*
	public boolean setPassword(char[] userPassword, char[] localPassword){

	}
	
	//Getter methods
	public char[] getPassword(char[] localPassword){

	}

	public KeyPair getRSAKeys(char[] localPassword){

	}
	*/
	public Key getAESKey(char[] localPassword){
		try{
			KeyVault kv = new KeyVault();
			KeyStore ks = kv.loadKeyStore(localPassword);

			KeyStore.ProtectionParameter passwordProtection = new KeyStore.PasswordProtection(localPassword);
			KeyStore.SecretKeyEntry aesKeyEntry = (KeyStore.SecretKeyEntry) ks.getEntry("aesKey", passwordProtection);
			Key aesKey = aesKeyEntry.getSecretKey();
			return aesKey;
		}catch(NoSuchAlgorithmException ex){
			//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}catch(KeyStoreException ex){
			//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}catch(UnrecoverableEntryException ex){
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}
	}

	public static void main (String[] args){
		char[] password = "password".toCharArray();

		KeyVault kv = new KeyVault();

		//kv.createKeyStore(password);
		kv.setAESKey(password);
		System.out.println(kv.getAESKey(password));

	}

}


/*

private void storeKeys(SecretKey aesKey, KeyPair rsaKeys) throws KeyStoreException
	{

		//CHANGE TO BOOL

	//byte[] rsaPubKey = rsaKeys.getPublic();
	//PrivateKey rsaPriKey = rsaKeys.getPrivate();
	//Certificate[] rsaCert = 
    

    //Keystore has to be type JCEKS to store private keys
    KeyStore ks = KeyStore.getInstance("JCEKS");  
    try{
    	ks.load(null, password);
    }catch(FileNotFoundException e){
    	System.out.println(e);
    }catch(IOException e){    	
    	System.out.println(e);
	}catch(NoSuchAlgorithmException e){    	
    	System.out.println(e);
	}catch(java.security.cert.CertificateException e){    	
    	System.out.println(e);
	}

	//Use user's password to protect the keystore
    KeyStore.ProtectionParameter passwordProtection = new KeyStore.PasswordProtection(password);

    // get my private key
    //KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry)
    //    ks.getEntry("privateKeyAlias", protParam);
    //PrivateKey myPrivateKey = pkEntry.getPrivateKey();

    // save my secret key
	//KeyStore.SecretKeyEntry rsaPrivateKey = new KeyStore.PrivateKeyEntry(rsaPriKey, rsaCert);
	//ks.setEntry("rsaPrivateKey", rsaPrivateKey, passwordProtection);

    //Save AES key
	KeyStore.SecretKeyEntry aesKeyEntry = new KeyStore.SecretKeyEntry(aesKey);
	ks.setEntry("aesKey", aesKeyEntry, passwordProtection);
    
    // store away the keystore
    FileOutputStream fos = null;
    try {
        fos = new FileOutputStream("newKeyStoreName");
        ks.store(fos, password);
    }catch(FileNotFoundException e){
    	System.out.println(e);
    }catch(IOException e){    	
    	System.out.println(e);
	}catch(NoSuchAlgorithmException e){    	
    	System.out.println(e);
	}catch(java.security.cert.CertificateException e){    	
    	System.out.println(e);
	}finally {
    	try{
	        if (fos != null) {
	            fos.close();
	        }
	    }catch(IOException e){
	    	System.out.println(e);
	    }
    }
}
*/