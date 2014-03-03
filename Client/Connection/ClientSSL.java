package Connection;

import java.security.spec.X509EncodedKeySpec;
import javax.net.ssl.*;
import java.security.*;
import java.io.*; 
import Console.*;
import Crypto.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientSSL {

/* Get keys from Max's Keyvault. */

    public static SSLSocket main(char[] localPassword) {

        
        try {

          KeyVault kv = new KeyVault();
          KeyStore ks = kv.loadKeyStore(localPassword);

           KeyManagerFactory kmf = KeyManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
           kmf.init(ks, localPassword);

           TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm()); 
           tmf.init(ks);

           SSLContext sc = SSLContext.getInstance("TLS"); 
           TrustManager[] trustManagers = tmf.getTrustManagers(); 
           sc.init(kmf.getKeyManagers(), trustManagers, null); 

            /* starting handshake with server */
            
            try {
                SSLSocketFactory ssf = sc.getSocketFactory(); 
                SSLSocket s = (SSLSocket) ssf.createSocket("localhost", 12346);
                String[] suites = s.getSupportedCipherSuites();
                s.setEnabledCipherSuites(suites);
                System.out.println("Created Socket");
                s.startHandshake(); 
               return s;
            } catch (Exception exception) {
                exception.printStackTrace();
                return null;
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ClientSSL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (KeyManagementException ex) {
            Logger.getLogger(ClientSSL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (KeyStoreException ex) {
            Logger.getLogger(ClientSSL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (UnrecoverableKeyException ex) {
            Logger.getLogger(ClientSSL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}

