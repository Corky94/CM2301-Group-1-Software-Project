/*
*The Encryption class is designed to manipulate data,
*using encryption keys to stop unwanted recipients 
*from viewing sensitive data. It takes in data and
*returns it in either an encrypted or decrypted format.
*Max Chandler
*/

package Crypto;

import Connection.ClientReceive;
import Message.Message;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.*;
import java.security.spec.X509EncodedKeySpec;

public class Encryption{

	public Encryption(){
	}

	public static byte[] encryptString(Key publicKey, String data){
            try{
                Cipher encrypt = Cipher.getInstance("RSA");
                encrypt.init(Cipher.ENCRYPT_MODE, publicKey);
                byte[] encryptedData = encrypt.doFinal(data.getBytes());
                return encryptedData; 
            }catch(InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex){
                throw new RuntimeException(ex);
            }
	}

	public static byte[] decryptString(byte[] data, char[] localPassword){
            KeyPair rsaKeys = KeyVault.getRSAKeys(localPassword);
            PrivateKey privateKey = rsaKeys.getPrivate();
            try{
                Cipher decrypt = Cipher.getInstance("RSA");
                decrypt.init(Cipher.DECRYPT_MODE, privateKey);
                byte[] decryptedData = decrypt.doFinal(data);
                return decryptedData;
            }catch(InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex){
                throw new RuntimeException(ex);
            }
	}

	public static String bTS(byte[] input){
            String s = new String(input);
            return s;
	}

	public static byte[] encryptRemotePassword(byte[] remotePassword, char[] localPassword){
            try{
                Key aesKey = KeyVault.getAESKey(localPassword);
                Cipher encrypt = Cipher.getInstance("AES");
        	encrypt.init(Cipher.ENCRYPT_MODE, aesKey);
        	byte[] encryptedData = encrypt.doFinal(remotePassword);
        	return encryptedData; 
            }catch( InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex){
                throw new RuntimeException(ex);
	    }
	}

	public static byte[] decryptRemotePassword(byte[] encryptedPassword, char[] localPassword){
            Key aesKey = KeyVault.getAESKey(localPassword);
            try{
                Cipher decrypt = Cipher.getInstance("AES");
                decrypt.init(Cipher.DECRYPT_MODE, aesKey);
                byte[] decryptedData = decrypt.doFinal(encryptedPassword);
                return decryptedData;
            }catch(InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex){
                throw new RuntimeException(ex);
	    }
	}
        
        public static void encryptFile(char[] localPassword, FileOutputStream data){
            Key aesKey = KeyVault.getAESKey(localPassword);
            try{
                Cipher encrypt = Cipher.getInstance("AES");
                encrypt.init(Cipher.ENCRYPT_MODE, aesKey);
                CipherOutputStream cos = new CipherOutputStream(data, encrypt); 
            }catch(InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException ex){
                throw new RuntimeException(ex);
            }
	}
        
        public static void decryptFile(char[] localPassword, String dir){
            Key aesKey = KeyVault.getAESKey(localPassword);
            try{
                Cipher decrypt = Cipher.getInstance("AES");
                decrypt.init(Cipher.DECRYPT_MODE, aesKey);
                FileInputStream fis = new FileInputStream(dir);
                CipherInputStream cis = new CipherInputStream(fis, decrypt );
            }catch(FileNotFoundException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException ex) {
                throw new RuntimeException(ex);
            }
        }
        
    public static PublicKey getKey(String id){
        Message m = new Message();
        m.setReceiver(id);
        m.setNeedingKey(true); 
        byte [] key = ClientReceive.getKey(m, "pass".toCharArray());
        try {
            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(key);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PublicKey pk = kf.generatePublic(pubKeySpec);                
            return pk;
        }catch(NoSuchAlgorithmException | InvalidKeySpecException ex){
        	throw new RuntimeException(ex);
        }   
    }
}
