package Crypto;

import java.security.spec.X509EncodedKeySpec;
import javax.net.ssl.*;
import java.security.*;
import java.io.*; 

public class Client-SSL {

/* Get keys from Max's Keyvault. */

    public static void main(char[] keystorePassword) {

      KeyVault kv = new KeyVault();
      KeyStore ks = kv.loadKeyStore("keystorePassword".toCharArray());

       KeyManagerFactory kmf = KeyManagerFactory.getInstance("X509");
       kmf.init(ks, "keystorePassword".toCharArray());

       TrustManagerFactory tmf = TrustManagerFactory.getInstance("X509"); 
       tmf.init(ks);

       SSLContext sc = SSLContext.getInstance("TLS"); 
       TrustManager[] trustManagers = tmf.getTrustManagers(); 
       sc.init(kmf.getKeyManagers(), trustManagers, null);

        /* starting handshake with server */
        
        try {
            SSLSocketFactory ssf = sc.getSocketFactory(); 
            SSLSocket s = (SSLSocket) ssf.createSocket("localhost", 12346);
            System.out.println("Created Socket");
            s.startHandshake(); 

            InputStream inputstream = System.in;
            InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
            BufferedReader bufferedreader = new BufferedReader(inputstreamreader);

            OutputStream outputstream = s.getOutputStream();
            OutputStreamWriter outputstreamwriter = new OutputStreamWriter(outputstream);
            BufferedWriter bufferedwriter = new BufferedWriter(outputstreamwriter);

            String string = null;
            while ((string = bufferedreader.readLine()) != null) {
                bufferedwriter.write(string + '\n');
                bufferedwriter.flush();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
