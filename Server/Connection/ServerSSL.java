package Connection;

import Crypto.*;
import javax.net.ssl.*;
import java.security.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerSSL {

/*Get keys from Max's Keyvault. */

     public static SSLServerSocket main(int port) {

          
        try {
           KeyVault kv = new KeyVault();
           KeyStore ks = kv.loadKeyStore("pass".toCharArray());

           KeyManagerFactory kmf = KeyManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
           kmf.init(ks, "pass".toCharArray());

           TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm()); 
           tmf.init(ks); 

           SSLContext sc = SSLContext.getInstance("TLS"); 
           TrustManager[] trustManagers = tmf.getTrustManagers(); 
           sc.init(kmf.getKeyManagers(), trustManagers, null);

              /* setting up handshake with client */
              
            try {
               SSLServerSocketFactory ssf = sc.getServerSocketFactory(); 
               SSLServerSocket s = (SSLServerSocket) ssf.createServerSocket(port);
               System.out.println("Created Server Socket");
               String[] suites = s.getSupportedCipherSuites();
               s.setEnabledCipherSuites(suites);
               
               return s;
               
        
          } catch (Exception exception) {
              exception.printStackTrace();
              return null;
          }
          } catch (NoSuchAlgorithmException ex) {
                  Logger.getLogger(ServerSSL.class.getName()).log(Level.SEVERE, null, ex);
                  return null;
              } catch (KeyManagementException ex) {
             Logger.getLogger(ServerSSL.class.getName()).log(Level.SEVERE, null, ex);
             return null;
         } catch (KeyStoreException ex) {
             Logger.getLogger(ServerSSL.class.getName()).log(Level.SEVERE, null, ex);
             return null;
         } catch (UnrecoverableKeyException ex) {
             Logger.getLogger(ServerSSL.class.getName()).log(Level.SEVERE, null, ex);
             return null;
         }
      }
}
