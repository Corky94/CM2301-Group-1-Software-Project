package Connection;
 
import javax.net.ssl.*;
import java.security.*;
import java.io.*;
import Crypto.*;
import Message.NodeList;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class ClientSSL {
 
    private SSLContext sc;
    private SSLSocketFactory sf;
    private NodeList n = new NodeList();
    private String node;
 
    public ClientSSL(char[] localPassword) {
 
        try {
            KeyVault kv = new KeyVault();
            KeyStore ks = kv.loadKeyStore(localPassword);
 
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            kmf.init(ks, localPassword);
            tmf.init(ks);
 
            sc = SSLContext.getInstance("TLS");
            sc.getClientSessionContext().setSessionCacheSize(1);
            sc.getClientSessionContext().setSessionTimeout(1000);
            sc.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
            sf = sc.getSocketFactory();
            chooseHost();
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException | KeyManagementException ex) {
            Logger.getLogger(ClientSSL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    public SSLSocket main(int portNumber) throws IOException {
        System.out.println("Start Socket");
        
   
        try{
            System.out.println(node);
            SSLSocket s = (SSLSocket) sf.createSocket(node, portNumber);
            System.out.println(s);
            
            s.setUseClientMode(true);
            s.setEnabledCipherSuites(s.getSupportedCipherSuites());
            s.startHandshake();
            System.out.println("Test");
            return s;
            
        }catch(ConnectException ste){
            return null;
        }
        
        
    }
    private void chooseHost(){
        node = n.getNode();
        try{
            SSLSocket s = (SSLSocket) sf.createSocket(node, 12346);
        }catch(ConnectException ste){
            chooseHost();
        }
        catch (IOException ex) {
            Logger.getLogger(ClientSSL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}