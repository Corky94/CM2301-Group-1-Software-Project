/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Crypto;

import java.security.Key;

/**
 *
 * @author maxchandler
 */
public class Authentication {
    
    public static byte[] auth(String nodeId){
        //This method is called to connect to a node, if it has the right auth
        //it will return it, if not it will generate a request.
        if(doesTicketExist(nodeId) == true){
            //get the ticket and encrypt it ready to be send to the node as authentication
            Ticket t = AuthenticatedList.getNodeAuthentication(nodeId);
            return Encryption.encryptAuth(t.getNodePublicKey(), t);
        }else{
            //generates a new ticket with a request in, that is then encrypted and 
            //ready to be sent to the node
            //            
            // --- Ticket Content ---
            //clientId = clientID;
            //nodeId = nodeID;
            //otp = null;
            //clientPublicKey = clientPublicKey;
            //nodePublicKey = null; 
            //is_challenge = true; 
            Key nodePublicKey = getNodePublicKey(nodeId); //HOW DO I CONNECT TO AN ID SERVER AND GET A PUBLIC KEY?
            return Encryption.encryptAuth(nodePublicKey, Ticket.generateRequest(nodeId));
        } 
    }
    
    //method to be called as ticket is returned from node
    public static byte[] handleChallenge(byte[] challenge){
        Ticket t = (Ticket) Encryption.decryptAuth(challenge);        
        if(t == null){    
            throw new RuntimeException("Ticket returned null");
        }
        //Ticket has come back, and is no longer a challenge, however the node does not know this
        //so we'll return one copy that will be treated as a challenge returned
        Ticket newTicket = new Ticket(t);
        newTicket.setChallenge(false);
        AuthenticatedList.addAuth(newTicket);
        //And return the decrypted ticket ready to send back to the server.
        return Encryption.encryptAuth(t.getNodePublicKey(), t);
    }
    
    private static boolean doesTicketExist(String serverId){
        //tidy up the auth list before we use it, and remove expired auth
        AuthenticatedList.removeExpiredAuth();
        return AuthenticatedList.exists(serverId);
    }
}