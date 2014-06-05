/*
*The Encryption class is designed to manipulate data,
*using encryption keys to stop unwanted recipients 
*from viewing sensitive data. It takes in data and
*returns it in either an encrypted or decrypted format.
*/

package Crypto;

import Connection.Packet;
import Connection.SessionKey;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;

public class Encryption{
    
    public static byte[] encryptString(Key publicKey, String data){
        try{
            Cipher encrypt = Cipher.getInstance("RSA");
            encrypt.init(Cipher.ENCRYPT_MODE, publicKey);
            return encrypt.doFinal(data.getBytes());
        }catch(InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex){
            throw new RuntimeException(ex);
        }
    }

    public static byte[] decryptString(byte[] data){
        KeyPair rsaKeys = KeyVault.getRSAKeys();
        PrivateKey privateKey = rsaKeys.getPrivate();
        try{
            Cipher decrypt = Cipher.getInstance("RSA");
            decrypt.init(Cipher.DECRYPT_MODE, privateKey);
            return decrypt.doFinal(data);
        }catch(InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex){
            throw new RuntimeException(ex);
        }
    }

    public static String bTS(byte[] input){
        String s = new String(input);
        return s;
    }
    
    public static Packet encryptTicket(Ticket t){
        Packet p = new Packet();
        SessionKey sKey = KeyGen.generateSessionKey();
        //encrypt ticket with AES session key
        p.setEncryptedTicket(Encryption.encryptAuth(sKey, t));
        //encrypt AES key with public RSA key
        p.setSessionKey(Encryption.encryptSessionKey(sKey, t.getClientPublicKey()));
        return p;
    }
    
    public static Packet decryptTicket(Packet p){
        p.setTicket(Encryption.decryptAuth(Encryption.decryptSessionKey(p.getSessionKey()), p.getEncryptedTicket()));
        return p;
    }
    
    public static byte[] encryptAuth(SessionKey sKey, Ticket t){
        SecretKey sessionKey = sKey.getKey();
        byte[] iv = sKey.getIvSpec();
        IvParameterSpec ivspec = new IvParameterSpec(iv);
        try{
            Cipher encrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
            encrypt.init(Cipher.ENCRYPT_MODE, sessionKey, ivspec);            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            CipherOutputStream cos = new CipherOutputStream( baos, encrypt );
            ObjectOutputStream oos = new ObjectOutputStream( cos );
            oos.writeObject( t );
            oos.close();
            byte[] bytes = baos.toByteArray();        
            cos.close();
            baos.close();
            return bytes;
        }catch(IOException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException ex){
            throw new RuntimeException(ex);
        }
    }

    public static Ticket decryptAuth(SessionKey sKey, byte[] auth){
        SecretKey sessionKey = sKey.getKey();
        byte[] iv = sKey.getIvSpec();
        IvParameterSpec ivspec = new IvParameterSpec(iv);
        try {
            Cipher decrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
            decrypt.init(Cipher.DECRYPT_MODE, sessionKey, ivspec); 
            ByteArrayInputStream bais = new ByteArrayInputStream(auth);
            CipherInputStream sis = new CipherInputStream(bais, decrypt);
            ObjectInputStream ois = new ObjectInputStream(sis);
            Ticket t = (Ticket) ois.readObject();
            ois.close();
            sis.close();
            bais.close();
            return t;
        } catch (NoSuchAlgorithmException | IOException | InvalidAlgorithmParameterException | NoSuchPaddingException | InvalidKeyException | ClassNotFoundException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
    
    public static SessionKey encryptSessionKey(SessionKey sessionKey, Key publicKey){
        SecretKey secretKey = sessionKey.getKey();
        try{
            Cipher encrypt = Cipher.getInstance("RSA");
            encrypt.init(Cipher.ENCRYPT_MODE, publicKey);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            CipherOutputStream cos = new CipherOutputStream(baos, encrypt); 
            ObjectOutputStream oos = new ObjectOutputStream(cos);
            oos.writeObject(secretKey);
            oos.close();
            byte[] encryptedKey = baos.toByteArray();
            sessionKey.deleteKey();
            sessionKey.setEncryptedKey(encryptedKey);
            return sessionKey;
        }catch(IOException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException ex){
            throw new RuntimeException(ex);
        }
    }
    
    public static SessionKey decryptSessionKey(SessionKey sKey){
        byte[] encryptedKey = sKey.getEncryptedKey();
        try {
            PrivateKey privateRsa = KeyVault.getRSAKeys().getPrivate();
            Cipher decrypt = Cipher.getInstance("RSA");
            decrypt.init(Cipher.DECRYPT_MODE, privateRsa); 
            ByteArrayInputStream bais = new ByteArrayInputStream(encryptedKey);
            CipherInputStream sis = new CipherInputStream(bais, decrypt);
            ObjectInputStream ois = new ObjectInputStream(sis);
            sKey.setKey((SecretKey) ois.readObject());
            sKey.deleteEncryptedKey();
            ois.close();
            sis.close();
            bais.close();
            return sKey;
        } catch (    NoSuchAlgorithmException | IOException | NoSuchPaddingException | InvalidKeyException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void encryptFile(FileOutputStream data){
        Key aesKey = KeyVault.getAESKey();
        try{
            Cipher encrypt = Cipher.getInstance("AES");
            encrypt.init(Cipher.ENCRYPT_MODE, aesKey);
            CipherOutputStream cos = new CipherOutputStream(data, encrypt); 
        }catch(InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException ex){
            throw new RuntimeException(ex);
        }
    }

    public static void decryptFile(String dir){
        Key aesKey = KeyVault.getAESKey();
        try{
            Cipher decrypt = Cipher.getInstance("AES");
            decrypt.init(Cipher.DECRYPT_MODE, aesKey);
            FileInputStream fis = new FileInputStream(dir);
            CipherInputStream cis = new CipherInputStream(fis, decrypt );
        }catch(FileNotFoundException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
