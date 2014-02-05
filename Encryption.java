import java.security.*;
import javax.crypto.*;

public class Encryption{
		private final int rsaKeyLength = 2048;
		private final int aesKeyLength = 256;

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

	public byte[] encodeString (Key publicKey, String data) throws NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException{
        Cipher encrypt = Cipher.getInstance("RSA");
        encrypt.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedData = encrypt.doFinal(data.getBytes());
        return encryptedData; 
	}

	public byte[] decodeString(Key privateKey, byte[] data) throws NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException{
		Cipher decrypt = Cipher.getInstance("RSA");
		decrypt.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] decryptedData = decrypt.doFinal(data);
		return decryptedData;
	}

	private void storeKeys(Key aesKey, KeyPair rsaKeys){
		//Will implement KeyStore soon
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

			byte[] encryptedMessage = exampleEncryption.encodeString(rsaKeyPair.getPublic(), "TEST ENCRYPTED MESSAGE 891283192319 £!@£!@£!@$/©˙∆~∫©˙∆~∫©¥¨∆˙¥");
				//System.out.println(encryptedMessage);

			byte[] decryptedMessage = exampleEncryption.decodeString(rsaKeyPair.getPrivate(), encryptedMessage);
				System.out.println(exampleEncryption.bTS(decryptedMessage));

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
