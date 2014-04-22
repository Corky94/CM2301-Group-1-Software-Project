package Crypto;

import Console.SecureDetails;
import Console.User;
import java.security.Key;

/**
 *
 * @author maxchandler
 */
//Client side auth object, it differes to the node as passwords are stored as
//string and not as hashes of the strings.
public class Ticket{
    private final String clientId;
    private final String nodeId;
    private final String password;
    private final Key clientPublicKey;
    private final Key nodePublicKey;
    private Boolean is_challenge;
    
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
        clientId = t.clientId;
        nodeId = t.nodeId;
        password = t.password;
        clientPublicKey = t.clientPublicKey;
        nodePublicKey = t.nodePublicKey; 
        is_challenge = t.is_challenge;   
    }
    
    public static Ticket generateRequest(String nodeId){
        SecureDetails sd = new SecureDetails(User.getPassword());
        return new Ticket(sd.getID(), nodeId, null, KeyVault.getRSAKeys().getPublic(), null, true);
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
    public void setChallenge(boolean b){
        is_challenge = b;
    }
}