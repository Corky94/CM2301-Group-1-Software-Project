package Crypto;

import Connection.Server;
import java.security.Key;

/**
 *
 * @author maxchandler
 */
//Client side auth object, it differes to the node as passwords are stored as
//string and not as hashes of the strings.
public class Ticket{
    private static  String clientId;
    private static  String nodeId;
    private static  String password;
    private static  Key clientPublicKey;
    private static  Key nodePublicKey;
    private static Boolean is_challenge;
    
    //constructor for saving auth details
    Ticket(String cId, String nId, String p, Key clientKey, Key nodeKey, boolean r){
        clientId = cId;
        nodeId = nId;
        password = p;
        clientPublicKey = clientKey;
        nodePublicKey = nodeKey; 
        is_challenge = r; 
    }
    
    Ticket(Ticket t){
        setClientId(t.getClientId());
        setNodeId(t.getNodeId());
        setPassword(t.getPassword());
        setClientPublicKey(t.getClientPublicKey());
        setNodePublicKey(t.getNodePublicKey());
        setChallenge(t.is_challenge());
    }
    
    public static Ticket generateRequest(String nodeId){
        return new Ticket(Server.getId(), nodeId, null, KeyVault.getRSAKeys().getPublic(), null, true);
    }
    
    public String getClientId(){
        return clientId;
    }
    
    public String getNodeId(){
        return nodeId;
    }

    public String getPassword(){
        return password;
    }
    
    public Key getClientPublicKey(){
        return clientPublicKey;
    }
    
    public Key getNodePublicKey(){
        return nodePublicKey;
    }
    
    public boolean is_challenge(){
        return is_challenge;
    }
    public static void setChallenge(boolean b){
        is_challenge = b;
    }
    
    private static void setClientId(String aClientId) {
        clientId = aClientId;
    }

    private static void setNodeId(String aNodeId) {
        nodeId = aNodeId;
    }

    private static void setPassword(String aPassword) {
        password = aPassword;
    }

    private static void setClientPublicKey(Key aClientPublicKey) {
        clientPublicKey = aClientPublicKey;
    }

    private static void setNodePublicKey(Key aNodePublicKey) {
        nodePublicKey = aNodePublicKey;
    }
}