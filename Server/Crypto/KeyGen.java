package Crypto;

import javax.crypto.*;
import java.util.*;
import java.math.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.asn1.x509.X509Name;
import org.bouncycastle.x509.X509V3CertificateGenerator;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;


/*
*KeyGen is responcible for RSA and AES keygen, it also generates a self signed certificate from RSA keys & userID, remotePassword. 
*/

public class KeyGen{
	final private int RSA_KEY_LENGTH = 2048;
	final private int AES_KEY_LENGTH = 256;
	final private byte[] VERSION_NUMBER;
	final protected static char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
	static { Security.addProvider(new BouncyCastleProvider());}
    private String userID;

	public KeyGen(){
            //000 for users, 010 for nodes
            this.VERSION_NUMBER = this.bigIntToByteArray(010);
	}

	//Keygen methods
	public KeyPair generateRSAKeys(){
		try{
			KeyPairGenerator rsaPairGenerator = KeyPairGenerator.getInstance("RSA");
			rsaPairGenerator.initialize(RSA_KEY_LENGTH);
			KeyPair rsaKeyPair = rsaPairGenerator.genKeyPair();
			return rsaKeyPair;
		}catch(NoSuchAlgorithmException ex){
			//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}
	} 

	public static X509Certificate[] generateCertificate(PublicKey pubKey, PrivateKey priKey){
		try{
			X509V3CertificateGenerator  v3CertGen = new X509V3CertificateGenerator();

		    Hashtable                   attrs = new Hashtable();
			attrs.put(X509Name.C, "AU");
	        attrs.put(X509Name.O, "Anon");
	        attrs.put(X509Name.L, "Anon");
	        attrs.put(X509Name.CN, "Anon");
	        attrs.put(X509Name.EmailAddress, "anon@gmail.com");

        	Vector                      order = new Vector();
	        order.addElement(X509Name.C);
	        order.addElement(X509Name.O);
	        order.addElement(X509Name.L);
	        order.addElement(X509Name.CN);
	        order.addElement(X509Name.EmailAddress);

	        String  issuer = "C=AU, O=The Legion of the Bouncy Castle, OU=Bouncy Primary Certificate";

			v3CertGen.reset();

	        v3CertGen.setSerialNumber(BigInteger.valueOf(20));
	        v3CertGen.setIssuerDN(new X509Name(issuer));
	        v3CertGen.setNotBefore(new Date(System.currentTimeMillis() - 1000L * 60 * 60 * 24 * 30));
	        	//sign certs for two years
	        v3CertGen.setNotAfter(new Date(System.currentTimeMillis() + (1000L * 60 * 60 * 24 * 365)));
       		v3CertGen.setSubjectDN(new X509Name(order, attrs));
	        v3CertGen.setPublicKey(pubKey);
	        v3CertGen.setSignatureAlgorithm("SHA256WithRSA");

	        X509Certificate cert = v3CertGen.generate(priKey);

			cert.checkValidity(new Date());
		    cert.verify(pubKey);

		    X509Certificate[] chain = new X509Certificate[1];
		    chain[0] = cert;
		    return chain;
		}catch(IllegalArgumentException | IllegalStateException | InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException | SignatureException | CertificateException ex){
            throw new RuntimeException(ex);
		}
	}

	public SecretKey generateAESKey(){
		try{
			//different methods in this one, uses javax.crypto instead of java.security
			KeyGenerator aesGenerator = KeyGenerator.getInstance("AES");
			aesGenerator.init(AES_KEY_LENGTH);
			SecretKey aesKey = aesGenerator.generateKey();
			return aesKey;
		}catch(NoSuchAlgorithmException ex){
			//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}
	}
	
	public byte[] generateRemotePassword(char[] localPassword){
		KeyVault kv = new KeyVault();
		KeyGen kg = new KeyGen();
                HashUtils hu = new HashUtils();
		KeyPair rsaPair = kv.getRSAKeys(localPassword);
		PrivateKey rsaPri = rsaPair.getPrivate();
		byte[] remotePassword = hu.hashKeyToByte(rsaPri);
		return remotePassword;
	}
	
	public String generateUserID(char[] localPassword){
            //https://en.bitcoin.it/wiki/Technical_background_of_version_1_Bitcoin_addresses
            KeyVault kv = new KeyVault();
            KeyGen kg = new KeyGen();
            HashUtils hu = new HashUtils();
            Encryption e = new Encryption();
            KeyPair rsaPair = kv.getRSAKeys(localPassword);
            Key rsaPub = rsaPair.getPublic();
            RIPEMD160Digest d = new RIPEMD160Digest();

            byte[] firstRound = hu.hashKeyToByte(rsaPub);
            d.update (firstRound, 0, firstRound.length);
            byte[] secondRound = new byte[d.getDigestSize()];
            d.doFinal (secondRound, 0);
            byte[] thirdRound = kg.concancateByteArrays(VERSION_NUMBER, secondRound);
            byte[] fourthRound = hu.hashSha256(thirdRound);
            byte[] fifthRound = Arrays.copyOfRange(hu.hashSha256(fourthRound), 0, 4);
            byte[] sixthRound = kg.concancateByteArrays(fourthRound, fifthRound);
            return Base58.encode(sixthRound);
	}

	private byte[] concancateByteArrays(byte[] a, byte[] b){
		byte[] bytes = new byte[a.length + b.length];
		System.arraycopy(a, 0, bytes, 0, a.length);
		System.arraycopy(b, 0, bytes, a.length, b.length);
		return bytes;
	}

	private byte[] bigIntToByteArray( int i ) {
	    BigInteger bigInt = BigInteger.valueOf(i);      
	    return bigInt.toByteArray();
	}

}