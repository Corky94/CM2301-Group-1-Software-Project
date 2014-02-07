import java.security.*;
import javax.crypto.*;
import java.util.*;
import java.math.*;
//import org.bouncycastle.*;
//import org.bouncycastle.asn1.ASN1Encodable;


public class KeyGen{
	final private int rsaKeyLength = 2048;
	final private int aesKeyLength = 256;
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();


	KeyGen(){
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
	/*
	public X509Certificate[] generateCertificate(PrivateKey priKey){

	    Date startDate = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
	    Date endDate = new Date(System.currentTimeMillis() + 365 * 24 * 60 * 60 * 1000);

	    //ContentSigner sigGen = new JcaContentSignerBuilder("SHA1withRSA").setProvider("BC").build(privKey);

	    org.bouncycastle.asn1.SubjectPublicKeyInfo pubKey = new SubjectPublicKeyInfo("RSA", priKey);

		X509v1CertificateBuilder certBuilder = new X509v1CertificateBuilder(
												new X500Name("CN=Test"), 
												BigInteger.ONE, 
												startDate, endDate, 
												new X500Name("CN=Test"), 
												subPubKeyInfo);

		X509CertificateHolder certHolder = v1CertGen.build(sigGen);

	}
	*/

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
		//System.out.println(kg.hashKey(kg.generateAESKey()));
		//USER ID
		System.out.println(kg.hashKey(kg.generateRSAKeys().getPublic()));
		//USER REMOTE PASSWORD
		System.out.println(kg.hashKey(kg.generateRSAKeys().getPrivate()));
	}

}