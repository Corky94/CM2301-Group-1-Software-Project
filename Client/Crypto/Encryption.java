/*
*The Encryption class is designed to manipulate data,
*using encryption keys to stop unwanted recipients 
*from viewing sensitive data. It takes in data and
*returns it in either an encrypted or decrypted format.
*/

package Crypto;

import Connection.ClientReceive;
import Message.Message;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.*;
import javax.crypto.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Encryption{

	public Encryption(){
	}

	public byte[] encryptString(Key publicKey, String data){
            try{
                Cipher encrypt = Cipher.getInstance("RSA");
                encrypt.init(Cipher.ENCRYPT_MODE, publicKey);
                byte[] encryptedData = encrypt.doFinal(data.getBytes());
                return encryptedData; 
            }catch(InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex){
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
            }catch(InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex){
                throw new RuntimeException(ex);
            }
	}

	public String bTS(byte[] input){
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
            }catch( InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex){
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
            }catch(InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex){
                throw new RuntimeException(ex);
	    }
	}
        
        
        public void encryptFile(char[] localPassword, FileOutputStream data){
            KeyVault kv = new KeyVault();
            Key aesKey = kv.getAESKey(localPassword);
            
            try{
                Cipher encrypt = Cipher.getInstance("AES");
                encrypt.init(Cipher.ENCRYPT_MODE, aesKey);
                
                CipherOutputStream cos = new CipherOutputStream(data, encrypt);
                
                
            }catch(InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException ex){
                throw new RuntimeException(ex);
            
            }
	}
        
        public void decryptFile(char[] localPassword, String dir) throws FileNotFoundException {
            KeyVault kv = new KeyVault();
            Key aesKey = kv.getAESKey(localPassword);
            
            try{
                Cipher decrypt = Cipher.getInstance("AES");
                decrypt.init(Cipher.DECRYPT_MODE, aesKey);
                FileInputStream fis = new FileInputStream(dir);
                CipherInputStream cis = new CipherInputStream(fis, decrypt );
                
                
                
                
                
                
                
                
           
	}   catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchPaddingException ex) {
                Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeyException ex) {
                Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
            
            }
        
        }
        
        
    //This needs to be reworked   
    public PublicKey getKey(String id){
        Message m = new Message();
        m.setReceiver(id);
        m.setNeedingKey(true); 
        byte [] key = ClientReceive.getKey(m, "pass".toCharArray());
        try {
            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(key);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PublicKey pk = kf.generatePublic(pubKeySpec);                
            return pk;
        }catch(Exception ex){
        	throw new RuntimeException(ex);
        }   
    }
}
