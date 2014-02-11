import java.security.*;
import javax.crypto.*;
import java.io.*;

public class Encryption{

	Encryption(){
	}

	public byte[] encryptString (Key publicKey, String data){
        try{
        	Cipher encrypt = Cipher.getInstance("RSA");
        	encrypt.init(Cipher.ENCRYPT_MODE, publicKey);
        	byte[] encryptedData = encrypt.doFinal(data.getBytes());
        	return encryptedData; 
        }catch(NoSuchAlgorithmException ex){
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
	    }catch(NoSuchPaddingException ex){    	
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}catch(BadPaddingException ex){    	
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}catch(InvalidKeyException ex){    	
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}catch(IllegalBlockSizeException ex){    	
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}
	}

	public byte[] decryptString(byte[] data){
		KeyVault kv = new KeyVault();
		KeyPair rsaKeys = kv.getRSAKeys("password".toCharArray(
			));
		PrivateKey privateKey = rsaKeys.getPrivate();
		try{
			Cipher decrypt = Cipher.getInstance("RSA");
			decrypt.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] decryptedData = decrypt.doFinal(data);
			return decryptedData;
		}catch(NoSuchAlgorithmException ex){
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
	    }catch(NoSuchPaddingException ex){    	
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}catch(BadPaddingException ex){    	
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}catch(InvalidKeyException ex){    	
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}catch(IllegalBlockSizeException ex){    	
	    	//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}
	}

	public String bTS(byte[] input){
		//ONLY FOR DEBUG -> byteToString
		String s = new String(input);
		return s;
	}
	/*
	public byte[] encryptRemotePassword(byte[] passowrd){

	}

	public byte[] decryptRemotePassword(byte[] encryptedPassword){
		//Will decrypt remote password using AES
	}
	*/
	public static void main (String[] args){
		Encryption exampleEncryption = new Encryption();
		KeyVault kv = new KeyVault();
		KeyPair kp = kv.getRSAKeys("password".toCharArray());
		PublicKey pk = kp.getPublic();
		byte[] encrypted = exampleEncryption.encryptString(pk, "String to encrypt");
		System.out.println(exampleEncryption.bTS(exampleEncryption.decryptString(encrypted)));
	}
}
