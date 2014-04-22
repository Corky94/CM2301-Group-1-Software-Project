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
        clientId = t.clientId;
        nodeId = t.nodeId;
        password = t.password;
        clientPublicKey = t.clientPublicKey;
        nodePublicKey = t.nodePublicKey; 
        is_challenge = t.is_challenge;   
    }
    
    public static Ticket generateRequest(String nodeId){
       
        return new Ticket(Server.getId(), nodeId, null, KeyVault.getRSAKeys().getPublic(), null, true);
    }
    
    public static String getClientId(){
        return clientId;
    }
    
    public static String getNodeId(){
        return nodeId;
    }

    public static String getPassword(){
        return password;
    }
    
    public static Key getClientPublicKey(){
        return clientPublicKey;
    }
    
    public static Key getNodePublicKey(){
        return nodePublicKey;
    }
    
    public static boolean is_challenge(){
        return is_challenge;
    }
    public static void setChallenge(boolean b){
        is_challenge = b;
    }
}