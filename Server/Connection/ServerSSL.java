package Connection;

import Crypto.*;
import Message.*;
import java.io.*;
import java.net.*;
import javax.net.ssl.*;
import java.security.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerSSL {

/*Get keys from Max's Keyvault. */

     public  SSLServerSocket main(int port, char[] pass) {

          
        try {
           KeyStore ks = KeyVault.loadKeyStore();

           KeyManagerFactory kmf = KeyManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
           kmf.init(ks, pass);

           TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm()); 
           tmf.init(ks); 

           SSLContext sc = SSLContext.getInstance("TLS"); 
           sc.getServerSessionContext().setSessionCacheSize(1000);
           sc.getServerSessionContext().setSessionTimeout(10000);
           TrustManager[] trustManagers = tmf.getTrustManagers(); 
           sc.init(kmf.getKeyManagers(), trustManagers, null);

              /* setting up handshake with client */
              
            
           SSLServerSocketFactory ssf = sc.getServerSocketFactory(); 
           SSLServerSocket s = (SSLServerSocket) ssf.createServerSocket(port);
           System.out.println("Created Server Socket");
           String[] suites = s.getSupportedCipherSuites();
           s.setEnabledCipherSuites(suites);
           sc =null; 

           return s;

        
          
          } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException | UnrecoverableKeyException | IOException ex) {
                  Logger.getLogger(ServerSSL.class.getName()).log(Level.SEVERE, null, ex);
                  return null;
              }
        
      }
     public SSLSocket send(String address, int portNumber, char[] pass)  {
         try {
            KeyStore ks = KeyVault.loadKeyStore();

            KeyManagerFactory kmf = KeyManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            kmf.init(ks, pass);
            tmf.init(ks);
 
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.getClientSessionContext().setSessionCacheSize(1);
            sc.getClientSessionContext().setSessionTimeout(10000);
            sc.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
            SSLSocketFactory sf = sc.getSocketFactory();
       
            SSLSocket s = (SSLSocket) sf.createSocket(address, portNumber);
            s.setUseClientMode(true);
            s.setEnabledCipherSuites(s.getSupportedCipherSuites());
            s.startHandshake();
            
            return s;
            
        } catch (KeyStoreException | NoSuchAlgorithmException | KeyManagementException | UnrecoverableKeyException | IOException ex ) {
            Logger.getLogger(ServerSSL.class.getName()).log(Level.SEVERE, null, ex);
        
         }
         
        return null;
    }
}
