/*
*The KeyVault is needed to store cryptographic 
*keys securely on the local system.
*/

package Crypto;

import Console.User;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;
/*
*KeyVault is responcible for storing and retreiving keys from the vault, it also provides a password check for user login. 
*/

public class KeyVault{
    //incase we decide on different system setup
    private static final String KEY_STORE_DIR = "";
    private static final String KEY_STORE_NAME = "keystore";
    private static final String KEY_STORE_TYPE = "JCEKS";
    private static final String TRUST_STORE_DIR = "";
    private static final String TRUST_STORE_NAME = "truststore";
    private static final String TRUST_STORE_TYPE = "JCEKS";

    //PASSWORD IN REFERENCE IS TO OPEN THE KEYVAULT (LOCAL PASSWORD)
    public static boolean checkPassword(char[] localPassword){
        try{
            KeyStore ks  = KeyStore.getInstance(KEY_STORE_TYPE);
            ks.load(new FileInputStream(KEY_STORE_DIR + KEY_STORE_NAME), localPassword);
            return true;
        }catch(KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException ex){
            return false;
        } 
    }

    public static PublicKey bytesToKey(byte[] bytes){
        try{
            PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bytes));
            return publicKey;
        }catch( NoSuchAlgorithmException | InvalidKeySpecException ex){
            throw new RuntimeException(ex);
        }
    }

    //Keystore methods	
    public static void createKeyStore() {
        try {
            KeyStore ks = KeyStore.getInstance(KEY_STORE_TYPE);  
            ks.load(null, User.getPassword());
            if (checkIfKsExists() != true){
                try (FileOutputStream fos = new FileOutputStream(KEY_STORE_DIR + KEY_STORE_NAME)) {
                    ks.store(fos, User.getPassword());
                }
            }
            else
                throw new RuntimeException("KEYSTORE ALREADY EXISTS");
        }catch(IOException | RuntimeException | KeyStoreException | NoSuchAlgorithmException | CertificateException ex){
            throw new RuntimeException(ex);
        }
    }

    public static boolean destroyKeyStore(){
        if(checkPassword(User.getPassword())){
            File vault = new File(KEY_STORE_DIR + KEY_STORE_NAME);
            if(vault.delete()){
                return true;
            }
        }
        return false;
    }

    public static KeyStore loadKeyStore(){       
        try{
            KeyStore ks  = KeyStore.getInstance(KEY_STORE_TYPE);
            ks.load(new FileInputStream(KEY_STORE_DIR + KEY_STORE_NAME), User.getPassword());
            return ks;
        }catch(IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException ex){
            throw new RuntimeException(ex);
        }
    }

    public static boolean checkIfKsExists(){
        File f = new File(KEY_STORE_DIR + KEY_STORE_NAME);
        return f.exists() && !f.isDirectory();
    }

    //Setter methods
    public static void setRSAKeys(){
        try{
            KeyStore ks = loadKeyStore();
            KeyGen kg = new KeyGen();
            KeyPair rsaKeys = kg.generateRSAKeys();
            PublicKey pubKey = rsaKeys.getPublic();
            PrivateKey priKey = rsaKeys.getPrivate();

            KeyStore.ProtectionParameter passwordProtection = new KeyStore.PasswordProtection(User.getPassword());

            KeyStore.PrivateKeyEntry rsaEntry = new KeyStore.PrivateKeyEntry(priKey, KeyGen.generateCertificate(pubKey, priKey));
            ks.setEntry("rsaKeys", rsaEntry, passwordProtection);

            try (FileOutputStream fos = new FileOutputStream(KEY_STORE_DIR + KEY_STORE_NAME)) {
                ks.store(fos, User.getPassword());
            }
        }catch(IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException ex){
            throw new RuntimeException(ex);
        }
    }

    public static void setAESKey(){
        try{
            KeyStore ks = loadKeyStore();
            KeyGen kg = new KeyGen();
            SecretKey aesKey = kg.generateAESKey();

            KeyStore.ProtectionParameter passwordProtection = new KeyStore.PasswordProtection(User.getPassword());
            KeyStore.SecretKeyEntry aesKeyEntry = new KeyStore.SecretKeyEntry(aesKey);
            ks.setEntry("aesKey", aesKeyEntry, passwordProtection);
            try (FileOutputStream fos = new FileOutputStream(KEY_STORE_DIR + KEY_STORE_NAME)) {
                ks.store(fos, User.getPassword());
            }
            
        }catch(IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException ex){
            throw new RuntimeException(ex);
        }
    }

    //Getter methods
    public static KeyPair getRSAKeys(){
        try{
            KeyStore ks = loadKeyStore();
            KeyStore.ProtectionParameter passwordProtection = new KeyStore.PasswordProtection(User.getPassword());
            KeyStore.PrivateKeyEntry rsaKeyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry("rsaKeys", passwordProtection);
            PrivateKey priKey = rsaKeyEntry.getPrivateKey();
            java.security.cert.Certificate rsaCert = rsaKeyEntry.getCertificate();
            PublicKey pubKey = rsaCert.getPublicKey();
            KeyPair rsaKeys = new KeyPair(pubKey, priKey);
            return rsaKeys;
        }catch(KeyStoreException | NoSuchAlgorithmException | UnrecoverableEntryException ex){
            throw new RuntimeException(ex);
        }
    }

    public static Key getAESKey(){
        try{
            KeyStore ks = loadKeyStore();
            KeyStore.ProtectionParameter passwordProtection = new KeyStore.PasswordProtection(User.getPassword());
            KeyStore.SecretKeyEntry aesKeyEntry = (KeyStore.SecretKeyEntry) ks.getEntry("aesKey", passwordProtection);
            Key aesKey = aesKeyEntry.getSecretKey();
            return aesKey;
        }catch(KeyStoreException | NoSuchAlgorithmException | UnrecoverableEntryException ex){
            throw new RuntimeException(ex);
        }
    }
    
    //truststore methods - Will be implemented once we use CA's	
    public static void createTrustStore() {
        try {
            KeyStore trustStore = KeyStore.getInstance(TRUST_STORE_TYPE);  
            trustStore.load(null, User.getPassword());
            if (checkIfTsExists() != true){
                try (FileOutputStream fos = new FileOutputStream(TRUST_STORE_DIR + TRUST_STORE_NAME)) {
                    trustStore.store(fos, User.getPassword());
                }
            }
            else
                throw new RuntimeException("TRUSTSTORE ALREADY EXISTS");
        }catch(IOException | RuntimeException | KeyStoreException | NoSuchAlgorithmException | CertificateException ex){
            throw new RuntimeException(ex);
        }
    }
    
    public static boolean checkIfTsExists(){
        File f = new File(TRUST_STORE_DIR + TRUST_STORE_NAME);
        return f.exists() && !f.isDirectory();
    }
    
    public static boolean destroyTrustStore(){
        if(checkPassword(User.getPassword())){
            File vault = new File(TRUST_STORE_DIR + TRUST_STORE_NAME);
            if(vault.delete()){
                return true;
            }
        }
        return false;
    }

    public static KeyStore loadTrustStore(){       
        try{
            KeyStore ks  = KeyStore.getInstance(TRUST_STORE_TYPE);
            ks.load(new FileInputStream(TRUST_STORE_DIR + TRUST_STORE_NAME), User.getPassword());
            return ks;
        }catch(IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException ex){
            throw new RuntimeException(ex);
        }
    }
    
    public static void addTrust(java.security.cert.Certificate trustedCert){
        try {
            KeyStore ks = KeyVault.loadTrustStore();
            KeyStore.ProtectionParameter passwordProtection = new KeyStore.PasswordProtection(User.getPassword());
            KeyStore.Entry newEntry = new KeyStore.TrustedCertificateEntry(trustedCert);
            ks.setEntry("nodeCert", newEntry, null);
        } catch (KeyStoreException ex) {
            Logger.getLogger(KeyVault.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}