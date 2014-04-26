package Crypto;

import java.io.Serializable;
import java.security.Key;

/**
 *
 * @author maxchandler
 */
//Client side auth object, it differes to the node as passwords are stored as
//string and not as hashes of the strings.
public class Ticket implements Serializable{
    private final String clientId;
    private final String nodeId;
    private final String password;
    private final String nodeAddress;
    private final Key clientPublicKey;
    private final Key nodePublicKey;
    private Boolean is_challenge;
    
    //constructor for saving auth details
    Ticket(String cId, String nId, String nAddress, String p, Key clientKey, Key nodeKey, boolean r){
        clientId = cId;
        nodeId = nId;
        nodeAddress = nAddress;
        password = p;
        clientPublicKey = clientKey;
        nodePublicKey = nodeKey; 
        is_challenge = r; 
    }
    
    Ticket(Ticket t){
        clientId = t.clientId;
        nodeId = t.nodeId;
        nodeAddress = t.nodeAddress;
        password = t.password;
        clientPublicKey = t.clientPublicKey;
        nodePublicKey = t.nodePublicKey; 
        is_challenge = t.is_challenge;   
    }
    
    public static Ticket generateRequest(String ID, String nodeAddress){
        return new Ticket(ID, null, nodeAddress, null, KeyVault.getRSAKeys().getPublic(), null, true);
    }
    
    public String getClientId(){
        return clientId;
    }
    
    public String getNodeId(){
        return nodeId;
    }
    
    public String getNodeAddress(){
        return nodeAddress;
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
    
    public void setChallenge(boolean b){
        is_challenge = b;
    }
}