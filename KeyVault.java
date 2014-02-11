import java.security.*;
import javax.crypto.*;
import java.io.*;

//import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import org.bouncycastle.x509.X509V2AttributeCertificate;

public class KeyVault{

	private final String KEY_STORE_DIR = "";
	private final String KEY_STORE_NAME = "keyStoreName";
	private final String KEY_STORE_TYPE = "JCEKS";

	KeyVault(){
	}

	//WORKS
	public boolean checkPassword(char[] localPassword){
		try{
			KeyVault ks = new KeyVault();
			ks.loadKeyStore(localPassword);
			return true;
		}catch(Exception ex){
			return false;
		}
	}

	/*
	public KeyPair byte[]tokeypair(){

	}*/

	//WORKS
	//Keystore methods	
	private void createKeyStore(char[] localPassword) {
		try {
		    KeyStore ks = KeyStore.getInstance(KEY_STORE_TYPE);  
		    KeyStore.ProtectionParameter passwordProtection = new KeyStore.PasswordProtection(localPassword);
	    	ks.load(null, localPassword);

	    	if (checkIfKsExists(KEY_STORE_NAME) != true){
		    	FileOutputStream fos = null;
		        fos = new FileOutputStream(KEY_STORE_NAME);
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

	//WORKS
	private KeyStore loadKeyStore(char[] localPassword){
		try{
			KeyStore ks  = KeyStore.getInstance(KEY_STORE_TYPE);
			ks.load(new FileInputStream(KEY_STORE_NAME), localPassword);
			return ks;
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
		}catch (KeyStoreException ex){
            throw new RuntimeException(ex);
		}
	}

	//WORKS
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
	        fos = new FileOutputStream(KEY_STORE_NAME);
	        ks.store(fos, localPassword);

	        if (fos != null) {
	            fos.close();
	    	}

	    	return true;
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
	        fos = new FileOutputStream(KEY_STORE_NAME);
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
		//char[] password = "password".toCharArray();
		//char[] badPassword = "wrongpassword".toCharArray();

		//KeyVault kv = new KeyVault();
		//kv.createKeyStore(password);
		//kv.setAESKey(password);
		//kv.setRSAKeys(password);
		//System.out.println(kv.getRSAKeys(password));
		//System.out.println(kv.getAESKey(password));
		//System.out.println(kv.checkPassword(password));
		//System.out.println(kv.checkPassword(badPassword));
		//System.out.println(kv.loadKeyStore(password));
		//System.out.println(kv.loadKeyStore(badPassword));


	}
}
