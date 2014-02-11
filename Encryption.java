import java.security.*;
import javax.crypto.*;
import java.io.*;

/*
*Encryption decrypts and encrypts data, calls KeyVault to retreivie relevant keys.
*/

public class Encryption{

	Encryption(){
	}

	public byte[] encryptString(Key publicKey, String data){
        try{
        	Cipher encrypt = Cipher.getInstance("RSA");
        	encrypt.init(Cipher.ENCRYPT_MODE, publicKey);
        	byte[] encryptedData = encrypt.doFinal(data.getBytes());
        	return encryptedData; 
        }catch(Exception ex){
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
	    }
	}

	public byte[] decryptString(byte[] data, char[] localPassword){
		KeyVault kv = new KeyVault();
		KeyPair rsaKeys = kv.getRSAKeys(localPassword);
		PrivateKey privateKey = rsaKeys.getPrivate();
		try{
			Cipher decrypt = Cipher.getInstance("RSA");
			decrypt.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] decryptedData = decrypt.doFinal(data);
			return decryptedData;
		}catch(Exception ex){
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
	    }
	}

	public String bTS(byte[] input){
		//ONLY FOR DEBUG -> byteToString
		String s = new String(input);
		return s;
	}
	
	public byte[] encryptRemotePassword(byte[] remotePassword, char[] localPassword){
		try{
			KeyVault kv = new KeyVault();
			Key aesKey = kv.getAESKey(localPassword);
		    Cipher encrypt = Cipher.getInstance("AES");
        	encrypt.init(Cipher.ENCRYPT_MODE, aesKey);
        	byte[] encryptedData = encrypt.doFinal(remotePassword);
        	return encryptedData; 
        }catch(Exception ex){
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
	    }
	}

	public byte[] decryptRemotePassword(byte[] encryptedPassword, char[] localPassword){
		KeyVault kv = new KeyVault();
		Key aesKey = kv.getAESKey(localPassword);
		try{
			Cipher decrypt = Cipher.getInstance("AES");
			decrypt.init(Cipher.DECRYPT_MODE, aesKey);
			byte[] decryptedData = decrypt.doFinal(encryptedPassword);
			return decryptedData;
		}catch(Exception ex){
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
	    }
	}
	
	/*
	//
	// Left in for testing purposes
	//
	public static void main (String[] args){
		Encryption exampleEncryption = new Encryption();
		KeyVault kv = new KeyVault();
		KeyPair kp = kv.getRSAKeys("password".toCharArray());
		PublicKey pk = kp.getPublic();
		byte[] encrypted = exampleEncryption.encryptString(pk, "String to encrypt ˙∆˙©¥©¥©¥©¥©¥©¥© Lorem ipsumLorem ipsumLorem ipsumLorem ipsumLorem ipsumLorem ipsumLorem ipsumLorem ipsumLorem ipsumLorem ipsumLorem ipsumLorem ipsumLorem ipsumLorem ipsumLorem ipsum");
		System.out.println(exampleEncryption.bTS(exampleEncryption.decryptString(encrypted)));
	}
	*/
}
