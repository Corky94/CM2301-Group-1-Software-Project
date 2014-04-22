package Connection;
 
import javax.net.ssl.*;
import java.security.*;
import java.io.*;
import Crypto.*;
import Message.NodeList;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class ClientSSL {
 
    private SSLContext sc;
    private SSLSocketFactory sf;
 
    public ClientSSL(char[] localPassword) {
 
        try {
            KeyVault kv = new KeyVault();
            KeyStore ks = kv.loadKeyStore();
 
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            kmf.init(ks, localPassword);
            tmf.init(ks);
 
            sc = SSLContext.getInstance("TLS");
            sc.getClientSessionContext().setSessionCacheSize(1);
            sc.getClientSessionContext().setSessionTimeout(10000);
            sc.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
            sf = sc.getSocketFactory();
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException | KeyManagementException ex) {
            Logger.getLogger(ClientSSL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    public SSLSocket main(int portNumber) throws IOException {
        NodeList n = new NodeList();
        SSLSocket s = (SSLSocket) sf.createSocket(n.getNode(), portNumber);
        s.setUseClientMode(true);
        s.setEnabledCipherSuites(s.getSupportedCipherSuites());
        s.startHandshake();
        System.out.println(s.getHandshakeSession());
        return s;
    }
}