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
		//CALL PRI KEY as Key privateKey
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

	public static void main (String[] args){
		Encryption exampleEncryption = new Encryption();
	}

}
