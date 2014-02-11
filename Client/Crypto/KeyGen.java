package Crypto;

import javax.crypto.*;
import java.util.*;
import java.math.*;
import java.lang.*;
import java.security.*;

import java.security.cert.X509Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateParsingException;
import java.security.cert.CertificateFactory;

import javax.security.auth.x500.X500Principal;

import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.InvalidKeySpecException;

import java.io.IOException;
import java.io.ByteArrayInputStream;

import java.text.MessageFormat;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.misc.MiscObjectIdentifiers;
import org.bouncycastle.asn1.misc.NetscapeCertType;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.asn1.x509.ExtendedKeyUsage;
import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.jce.X509Principal;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.x509.AttributeCertificateHolder;
import org.bouncycastle.x509.AttributeCertificateIssuer;
import org.bouncycastle.x509.X509Attribute;
import org.bouncycastle.x509.X509V1CertificateGenerator;
import org.bouncycastle.x509.X509V2AttributeCertificate;
import org.bouncycastle.x509.X509V2AttributeCertificateGenerator;
import org.bouncycastle.x509.X509V3CertificateGenerator;


public class KeyGen{
	final private int RSA_KEY_LENGTH = 2048;
	final private int AES_KEY_LENGTH = 256;
	final protected static char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
	static { Security.addProvider(new BouncyCastleProvider());}

	public KeyGen(){
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
        	Vector                      order = new Vector();

			attrs.put(X509Principal.C, "AU");
	        attrs.put(X509Principal.O, "The Legion of the Bouncy Castle");
	        attrs.put(X509Principal.L, "Melbourne");
	        attrs.put(X509Principal.CN, "Eric H. Echidna");
	        attrs.put(X509Principal.EmailAddress, "feedback-crypto@spongycastle.org");

	        order.addElement(X509Principal.C);
	        order.addElement(X509Principal.O);
	        order.addElement(X509Principal.L);
	        order.addElement(X509Principal.CN);
	        order.addElement(X509Principal.EmailAddress);

	        String  issuer = "C=AU, O=The Legion of the Bouncy Castle, OU=Bouncy Primary Certificate";

			v3CertGen.reset();

	        v3CertGen.setSerialNumber(BigInteger.valueOf(20));
	        v3CertGen.setIssuerDN(new X509Principal(issuer));
	        v3CertGen.setNotBefore(new Date(System.currentTimeMillis() - 1000L * 60 * 60 * 24 * 30));
	        v3CertGen.setNotAfter(new Date(System.currentTimeMillis() + (1000L * 60 * 60 * 24 * 30)));
       		v3CertGen.setSubjectDN(new X509Principal(order, attrs));
	        v3CertGen.setPublicKey(pubKey);
	        v3CertGen.setSignatureAlgorithm("SHA256WithRSA");

	        X509Certificate cert = v3CertGen.generate(priKey);

			cert.checkValidity(new Date());
		    cert.verify(pubKey);

		    X509Certificate[] chain = new X509Certificate[1];
		    chain[0] = cert;
		    return chain;
		}catch(CertificateEncodingException ex){
            throw new RuntimeException(ex);
		}catch(NoSuchAlgorithmException ex){
            throw new RuntimeException(ex);
		}catch(SignatureException ex){
            throw new RuntimeException(ex);
		}catch(InvalidKeyException ex){
            throw new RuntimeException(ex);
		}catch(CertificateParsingException ex){
			throw new RuntimeException(ex);
		}catch(NoSuchProviderException ex){
			throw new RuntimeException(ex);
		}catch(Exception ex){
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
		KeyPair rsaPair = kv.getRSAKeys(localPassword);
		PrivateKey rsaPri = rsaPair.getPrivate();
		byte[] remotePassword = kg.hashKeyToByte(rsaPri);
		return remotePassword;
	}
	
	public String generateUserID(char[] localPassword){
		KeyVault kv = new KeyVault();
		KeyGen kg = new KeyGen();
		KeyPair rsaPair = kv.getRSAKeys(localPassword);
		Key rsaPub = rsaPair.getPublic();
		String userID = kg.hashKeyToString(rsaPub);
		return userID;
	}

	public String hashKeyToString(Key inputKey){
		byte[] key = inputKey.getEncoded();
		try{
			MessageDigest hash = MessageDigest.getInstance("SHA-256");
			hash.update(key); 
			byte[] bytes = hash.digest();
			//convert to hex string
			char[] hexChars = new char[bytes.length * 2];
			    for ( int j = 0; j < bytes.length; j++ ) {
			        int v = bytes[j] & 0xFF;
			        hexChars[j * 2] = HEX_ARRAY[v >>> 4];
			        hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
			    }
			return new String(hexChars);
		}catch(NoSuchAlgorithmException ex){
			//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}
	}

	public byte[] hashKeyToByte(Key inputKey){
			byte[] key = inputKey.getEncoded();
			try{
				MessageDigest hash = MessageDigest.getInstance("SHA-256");
				hash.update(key); 
				byte[] bytes = hash.digest();
				return bytes;
			}catch(NoSuchAlgorithmException ex){
			//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}
	}
        
        public PublicKey getKey(byte[] id){
            
            return null;
        }

	public static void main (String[] args){
		//KeyGen kg = new KeyGen();
		//KeyPair kp = kg.generateRSAKeys();
		/*
		CertificateGenerator cg = new CertificateGenerator();
		try{
			X509Certificate cert = cg.createAcIssuerCert(kp.getPublic(), kp.getPrivate());
			cert.checkValidity(new Date());
		    cert.verify(kp.getPublic());
		}catch(Exception ex){
			throw new RuntimeException(ex);

		}*/
		//kg.generateCertificate(kp.getPublic(), kp.getPrivate());
		//System.out.println(kg.hashKey(kg.generateAESKey()));
		//USER ID
		//System.out.println(kg.hashKey(kg.generateRSAKeys().getPublic()));
		//USER REMOTE PASSWORD
		//System.out.println(kg.hashKey(kg.generateRSAKeys().getPrivate()));
	}

}