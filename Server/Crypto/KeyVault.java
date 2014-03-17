/*
*The KeyVault is needed to store cryptographic 
*keys securely on the local system.
*Max Chandler
*/

package Crypto;

import java.security.*;
import javax.crypto.*;
import java.io.*;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
/*
*KeyVault is responcible for storing and retreiving keys from the vault, it also provides a password check for user login. 
*/

public class KeyVault{
    private static final String KEY_STORE_DIR = "";
    private static final String KEY_STORE_NAME = "keystore";
    private static final String KEY_STORE_TYPE = "JCEKS";

    public KeyVault(){
    }

    //PASSWORD IN REFERENCE IS TO OPEN THE KEYVAULT (LOCAL PASSWORD)
    public boolean checkPassword(char[] localPassword){
        try{
            loadKeyStore(localPassword);
            return true;
        }catch(Exception ex){
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
    public void createKeyStore(char[] localPassword) {
        try {
            KeyStore ks = KeyStore.getInstance(KEY_STORE_TYPE);  
            KeyStore.ProtectionParameter passwordProtection = new KeyStore.PasswordProtection(localPassword);
            ks.load(null, localPassword);

            if (checkIfKsExists(KEY_STORE_DIR + KEY_STORE_NAME) != true){
                FileOutputStream fos = null;
                fos = new FileOutputStream(KEY_STORE_DIR + KEY_STORE_NAME);
                ks.store(fos, localPassword);

                if (fos != null)
                    fos.close();
            }
            else
                throw new RuntimeException("KEYSTORE ALREADY EXISTS");
            
        }catch(IOException | RuntimeException | KeyStoreException | NoSuchAlgorithmException | CertificateException ex){
            throw new RuntimeException(ex);
        }
    }

    public static boolean destroyKeyStore(char[] localPassword){
        KeyVault kv = new KeyVault();
        if(kv.checkPassword(localPassword)){
            File vault = new File(KEY_STORE_DIR + KEY_STORE_NAME);
            if(vault.delete())
                return true;
        }
        return false;
    }

    public KeyStore loadKeyStore(char[] localPassword){
        if (this.checkIfKsExists(KEY_STORE_DIR + KEY_STORE_NAME) == false)
            createKeyStore(localPassword);         
        try{
            KeyStore ks  = KeyStore.getInstance(KEY_STORE_TYPE);
            ks.load(new FileInputStream(KEY_STORE_DIR + KEY_STORE_NAME), localPassword);
            return ks;
        }catch(IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException ex){
            throw new RuntimeException(ex);
        }
    }

    private boolean checkIfKsExists(String keyStoreName){
        File f = new File(KEY_STORE_DIR + keyStoreName);
        if(f.exists() && !f.isDirectory())
            return true;
        return false;
    }


    //Setter methods
    public boolean setRSAKeys(char[] localPassword){
        try{
            KeyVault kv = new KeyVault();
            KeyStore ks = kv.loadKeyStore(localPassword);
            //generate the rsaKeys
            KeyGen kg = new KeyGen();
            KeyPair rsaKeys = kg.generateRSAKeys();
            PublicKey pubKey = rsaKeys.getPublic();
            PrivateKey priKey = rsaKeys.getPrivate();

            KeyStore.ProtectionParameter passwordProtection = new KeyStore.PasswordProtection(localPassword);

            KeyStore.PrivateKeyEntry rsaEntry = new KeyStore.PrivateKeyEntry(priKey, KeyGen.generateCertificate(pubKey, priKey));
            ks.setEntry("rsaKeys", rsaEntry, passwordProtection);

            FileOutputStream fos = null;
            fos = new FileOutputStream(KEY_STORE_DIR + KEY_STORE_NAME);
            ks.store(fos, localPassword);

            if (fos != null) {
                fos.close();
            }

            return true;
        }catch(IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException ex){
            throw new RuntimeException(ex);
        }
    }

    public void setAESKey(char[] localPassword){
        try{
            KeyVault kv = new KeyVault();
            KeyStore ks = kv.loadKeyStore(localPassword);
            //generate the aesKey
            KeyGen kg = new KeyGen();
            SecretKey aesKey = kg.generateAESKey();

            KeyStore.ProtectionParameter passwordProtection = new KeyStore.PasswordProtection(localPassword);

            KeyStore.SecretKeyEntry aesKeyEntry = new KeyStore.SecretKeyEntry(aesKey);
            ks.setEntry("aesKey", aesKeyEntry, passwordProtection);

            FileOutputStream fos = null;
            fos = new FileOutputStream(KEY_STORE_DIR + KEY_STORE_NAME);
            ks.store(fos, localPassword);

             if (fos != null) {
                fos.close();
            }
        }catch(IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException ex){
            throw new RuntimeException(ex);
        }
    }

    //Getter methods
    public static KeyPair getRSAKeys(char[] localPassword){
        try{
            KeyVault kv = new KeyVault();
            KeyStore ks = kv.loadKeyStore(localPassword);

            KeyStore.ProtectionParameter passwordProtection = new KeyStore.PasswordProtection(localPassword);
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


    public static Key getAESKey(char[] localPassword){
        try{
            KeyVault kv = new KeyVault();
            KeyStore ks = kv.loadKeyStore(localPassword);

            KeyStore.ProtectionParameter passwordProtection = new KeyStore.PasswordProtection(localPassword);
            KeyStore.SecretKeyEntry aesKeyEntry = (KeyStore.SecretKeyEntry) ks.getEntry("aesKey", passwordProtection);
            Key aesKey = aesKeyEntry.getSecretKey();
            return aesKey;
        }catch(KeyStoreException | NoSuchAlgorithmException | UnrecoverableEntryException ex){
            throw new RuntimeException(ex);
        }
    }
    
}