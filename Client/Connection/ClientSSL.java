package Connection;
 
import Console.*;
import Crypto.*;
import Message.*;
import java.io.*;
import java.net.*;
import java.security.*;
import java.security.cert.*;
import java.util.logging.*;
import javax.net.ssl.*;
 
public class ClientSSL {
 
    private SSLContext sc;
    private SSLSocketFactory sf;
    private NodeList n = new NodeList();
    private static String node;
 
    public ClientSSL() {
        try {
            //for now, we're not using CAs
            TrustManager trm = new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                
                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                
                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            };
            
            
            KeyStore ks = KeyVault.loadKeyStore();
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            kmf.init(ks, User.getPassword() );
            
            //Leave these incase we decide to introduce CA's again.
            //KeyStore trustStore = KeyVault.loadTrustStore();
            //TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm()); 
            //tmf.init(trustStore);
 
            sc = SSLContext.getInstance("TLS");
            sc.getClientSessionContext().setSessionCacheSize(1);
            sc.getClientSessionContext().setSessionTimeout(1000);
            sc.init(kmf.getKeyManagers(),  new TrustManager[] { trm }, null);
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
            //In the future, if we need to get a nodes cert. This is 90% complete.
            //KeyVault.addTrust(getSslCert(node, portNumber));
            s.startHandshake();
            System.out.println("Test");
            return s;
        }catch(ConnectException ste){
            return null;
        }
    }
    
    public static String getNodeAddress(){
        return node;
    }
    
    private void chooseHost(){
        node = n.getNode();
        SecureDetails sd = new SecureDetails();
        Message m = new Message();
        m.setReceiver(sd.getID());
        try{
            SSLSocket s = (SSLSocket) sf.createSocket(node, 12346);
        }catch(ConnectException ste){
            chooseHost();
        }
        catch (IOException ex) {
            Logger.getLogger(ClientSSL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    //for the future
    public static java.security.cert.Certificate getSslCert(String host, int port){
        try {
            // create custom trust manager to ignore trust paths
            TrustManager trm = new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                
                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                
                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            };
            
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[] { trm }, null);
            SSLSocketFactory factory =sc.getSocketFactory();
            try (SSLSocket socket = (SSLSocket)factory.createSocket(host, port)) {
                socket.startHandshake();
                SSLSession session = socket.getSession();
                return session.getPeerCertificates()[0];
            }
        } catch (KeyManagementException | NoSuchAlgorithmException | IOException ex) {
            Logger.getLogger(ClientSSL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}