package Crypto;

import Connection.ClientReceive;
import Message.Message;
import java.security.*;
import javax.crypto.*;
import java.io.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Encryption{

	public Encryption(){
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

	public byte[] decryptString(byte[] data, String password){
		KeyVault kv = new KeyVault();
		KeyPair rsaKeys = kv.getRSAKeys(password.toCharArray());
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
            try {
                //ONLY FOR DEBUG -> byteToString
                String s = new String(input, "UTF-8");
                return s;
            } catch (UnsupportedEncodingException ex) {
                throw new RuntimeException(ex);
            }
	}
	/*
	public byte[] encryptRemotePassword(byte[] passowrd){

	}

	public byte[] decryptRemotePassword(byte[] encryptedPassword){
		//Will decrypt remote password using AES
	}
	*/
        
        public PublicKey getKey(String id){
            Message m = new Message();
            m.receiver = id;
            m.needingKey = true;
            
            byte [] key = ClientReceive.getKey(m);
           
            
            
            try {
                X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(key);
                KeyFactory kf = KeyFactory.getInstance("RSA");
                PublicKey pk = kf.generatePublic(pubKeySpec);                
                return pk;
            } catch (NoSuchAlgorithmException ex) {
                throw new RuntimeException(ex);
            }catch (InvalidKeySpecException ex) {
                throw new RuntimeException(ex);
                }
            
            
            
        } 
//	public static void main (String[] args){
//		Encryption exampleEncryption = new Encryption();
//		KeyVault kv = new KeyVault();
//		KeyPair kp = kv.getRSAKeys("".toCharArray());
//		PublicKey pk = kp.getPublic();
//		byte[] encrypted = exampleEncryption.encryptString(pk, "String to encrypt");
//		System.out.println(exampleEncryption.bTS(exampleEncryption.decryptString(encrypted, "")));
//	}
}
