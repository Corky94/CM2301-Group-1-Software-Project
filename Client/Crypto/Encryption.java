/*
*The Encryption class is designed to manipulate data,
*using encryption keys to stop unwanted recipients 
*from viewing sensitive data. It takes in data and
*returns it in either an encrypted or decrypted format.
*/

package Crypto;

import Connection.ClientReceive;
import Console.User;
import Message.Message;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;

public class Encryption{
    
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

    public static byte[] decryptString(byte[] data){
        KeyPair rsaKeys = KeyVault.getRSAKeys(User.getPassword());
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

    public static byte[] encryptRemotePassword(byte[] remotePassword){
        try{
            Key aesKey = KeyVault.getAESKey(User.getPassword());
            Cipher encrypt = Cipher.getInstance("AES");
            encrypt.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encryptedData = encrypt.doFinal(remotePassword);
            return encryptedData; 
        }catch( InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex){
            throw new RuntimeException(ex);
        }
    }

    public static byte[] decryptRemotePassword(byte[] encryptedPassword){
        Key aesKey = KeyVault.getAESKey(User.getPassword());
        try{
            Cipher decrypt = Cipher.getInstance("AES");
            decrypt.init(Cipher.DECRYPT_MODE, aesKey);
            byte[] decryptedData = decrypt.doFinal(encryptedPassword);
            return decryptedData;
        }catch(InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex){
            throw new RuntimeException(ex);
        }
    }
    
    //the following two methods are only used when asking for a authentication request, they are unsecure as they are unencrypted.
    public static byte[] serialiseTicket(Ticket t){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(t);
        } catch (IOException ex) {
            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        return baos.toByteArray();
    }
    
    public static Ticket deserialiseTicket(byte[] ticket) throws ClassNotFoundException, IOException{ 
        ByteArrayInputStream bais = new ByteArrayInputStream(ticket);
        ObjectInputStream ois = new ObjectInputStream(bais);
        return (Ticket) ois.readObject();
    }

    public static byte[] encryptAuth(Key publicRsa, Ticket a){
        try{
            Cipher encrypt = Cipher.getInstance("RSA");
            encrypt.init(Cipher.ENCRYPT_MODE, publicRsa);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try (CipherOutputStream cos = new CipherOutputStream(baos, encrypt); ObjectOutputStream oos = new ObjectOutputStream(cos)) {
                oos.writeObject(a);
            }
            return baos.toByteArray();
        }catch(IOException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException ex){
            throw new RuntimeException(ex);
        }
    }

    public static Object decryptAuth(byte[] auth){
        try {
            PrivateKey privateRsa = KeyVault.getRSAKeys(User.getPassword()).getPrivate();
            Cipher decrypt = Cipher.getInstance("RSA");
            decrypt.init(Cipher.DECRYPT_MODE, privateRsa); 
            ByteArrayInputStream bais = new ByteArrayInputStream(auth);
            CipherInputStream sis = new CipherInputStream(bais, decrypt);
            ObjectInputStream ois = new ObjectInputStream(sis);
            return ois.readObject();
        } catch (    NoSuchAlgorithmException | IOException | NoSuchPaddingException | InvalidKeyException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void encryptFile(FileOutputStream data){
        Key aesKey = KeyVault.getAESKey(User.getPassword());
        try{
            Cipher encrypt = Cipher.getInstance("AES");
            encrypt.init(Cipher.ENCRYPT_MODE, aesKey);
            CipherOutputStream cos = new CipherOutputStream(data, encrypt); 
        }catch(InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException ex){
            throw new RuntimeException(ex);
        }
    }

    public static void decryptFile(String dir){
        Key aesKey = KeyVault.getAESKey(User.getPassword());
        try{
            Cipher decrypt = Cipher.getInstance("AES");
            decrypt.init(Cipher.DECRYPT_MODE, aesKey);
            FileInputStream fis = new FileInputStream(dir);
            CipherInputStream cis = new CipherInputStream(fis, decrypt );
        }catch(FileNotFoundException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException ex) {
            throw new RuntimeException(ex);
        }
    }
     
    //This needs to be reworked   
    
}
