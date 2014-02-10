package Encryption;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.*;


public class KeyGen{
	final private int rsaKeyLength = 2048;
	final private int aesKeyLength = 256;
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();


	public KeyGen(){
	}

	//Keygen methods
	public KeyPair generateRSAKeys(){
		try{
			KeyPairGenerator rsaPairGenerator = KeyPairGenerator.getInstance("RSA");
			rsaPairGenerator.initialize(rsaKeyLength);
			KeyPair rsaKeyPair = rsaPairGenerator.genKeyPair();
			return rsaKeyPair;
		}catch(NoSuchAlgorithmException ex){
			//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}
	} 
       public PublicKey convertToKey() throws NoSuchAlgorithmException, InvalidKeySpecException{
            KeyPair kp = generateRSAKeys();
            byte[] key = kp.getPublic().getEncoded();
            
            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(key);    
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PublicKey publicKey = 
            kf.generatePublic(pubKeySpec);
            System.out.println("Worked");
            return publicKey;
        }
        

	public SecretKey generateAESKey(){
		try{
			//different methods in this one, uses javax.crypto instead of java.security
			KeyGenerator aesGenerator = KeyGenerator.getInstance("AES");
			aesGenerator.init(aesKeyLength);
			SecretKey aesKey = aesGenerator.generateKey();
			return aesKey;
		}catch(NoSuchAlgorithmException ex){
			//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}
	}

	public String hashKey(Key inputKey){
		byte[] key = inputKey.getEncoded();
		try{
			MessageDigest hash = MessageDigest.getInstance("SHA-256");
			hash.update(key); 
			byte[] bytes = hash.digest();
			//convert to hex string
			char[] hexChars = new char[bytes.length * 2];
			    for ( int j = 0; j < bytes.length; j++ ) {
			        int v = bytes[j] & 0xFF;
			        hexChars[j * 2] = hexArray[v >>> 4];
			        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
			    }
			return new String(hexChars);
		}catch(NoSuchAlgorithmException ex){
			//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}
	}

	public static void main (String[] args){
		KeyGen kg = new KeyGen();
		System.out.println(kg.hashKey(kg.generateAESKey()));
		System.out.println(kg.hashKey(kg.generateRSAKeys().getPublic()));
		System.out.println(kg.hashKey(kg.generateRSAKeys().getPrivate()));
	}

}