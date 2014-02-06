import java.security.*;
import javax.crypto.*;
import java.io.*;

public class Encryption{
		private final int rsaKeyLength = 2048;
		private final int aesKeyLength = 256;
		private final char[] password = {'t','e','s','t'}
		;

	Encryption(){
	}

	public KeyPair generateRSAKeys() throws NoSuchAlgorithmException{
		KeyPairGenerator rsaPairGenerator = KeyPairGenerator.getInstance("RSA");
		rsaPairGenerator.initialize(rsaKeyLength);
		KeyPair rsaKeyPair = rsaPairGenerator.genKeyPair();
		return rsaKeyPair;
	} 

	public SecretKey generateAESKey() throws NoSuchAlgorithmException{
		//different methods in this one, uses javax.crypto instead of java.security
		KeyGenerator aesGenerator = KeyGenerator.getInstance("AES");
		aesGenerator.init(aesKeyLength);
		SecretKey aesKey = aesGenerator.generateKey();
		return aesKey;
	}

	public byte[] hashKey(byte[] key) throws NoSuchAlgorithmException {
		MessageDigest hash = MessageDigest.getInstance("SHA-256");
		hash.update(key); 
		byte[] digest = hash.digest();
		return digest; 
	}

	public byte[] encryptString (Key publicKey, String data) throws NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException{
        Cipher encrypt = Cipher.getInstance("RSA");
        encrypt.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedData = encrypt.doFinal(data.getBytes());
        return encryptedData; 
	}

	public byte[] decryptString(Key privateKey, byte[] data) throws NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException{
		Cipher decrypt = Cipher.getInstance("RSA");
		decrypt.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] decryptedData = decrypt.doFinal(data);
		return decryptedData;
	}

	
	private boolean storeAES(SecretKey key){
		//To be completed
		return false; 
	}

	private boolean storeRSA(KeyPair keys){
		//To be completed
		return false; 
	}

	private boolean storeHash(byte[] id, byte[] password){
		//To be complted
		return false;
	}

	private byte[] getUserID(){

	}

	private byte[] getUserPassword(){

	}

	//private Key getRSAPrivate(){//To complete}

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



	public String bTS(byte[] input){
		//ONLY FOR DEBUG -> byteToString
		String s = new String(input);
		return s;
	}

	public static void main (String[] args){
		Encryption exampleEncryption = new Encryption();
		try{
			//debug fun
			KeyPair rsaKeyPair = exampleEncryption.generateRSAKeys();
				//System.out.println(rsaKeyPair.getPublic());
				//System.out.println(rsaKeyPair.getPrivate());

			SecretKey aesKey = exampleEncryption.generateAESKey();
				//System.out.println(aesKey);

			byte[] userID = exampleEncryption.hashKey(rsaKeyPair.getPublic().getEncoded());
			byte[] userPassword = exampleEncryption.hashKey(rsaKeyPair.getPrivate().getEncoded());
				//System.out.println(userID);
				//System.out.println(userPassword);

			byte[] encryptedMessage = exampleEncryption.encryptString(rsaKeyPair.getPublic(), "TEST ENCRYPTED MESSAGE 891283192319 £!@£!@£!@$/©˙∆~∫©˙∆~∫©¥¨∆˙¥");
				//System.out.println(encryptedMessage);

			byte[] decryptedMessage = exampleEncryption.decryptString(rsaKeyPair.getPrivate(), encryptedMessage);
				System.out.println(exampleEncryption.bTS(decryptedMessage));

			try{
				exampleEncryption.storeKeys(aesKey, rsaKeyPair);
			}catch(KeyStoreException e){
    			System.out.println(e);
			}

		}catch (NoSuchAlgorithmException e){
			System.out.println(e);
		}catch (NoSuchPaddingException e){
			System.out.println(e);
		}catch (BadPaddingException e){
			System.out.println(e);
		}catch (InvalidKeyException e){
			System.out.println(e);
		}catch (IllegalBlockSizeException e){
			System.out.println(e);
		}

	}

}
