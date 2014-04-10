/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Crypto;

import Console.SecureDetails;
import Console.User;
/**
 *
 * @author maxchandler
 */
public class Authentication {
    
    public static byte[] auth(String id){
        //This method is called to connect to a node, if it has the right auth
        //it will return it, if not it will generate a request.
        if(checkAuth(id) == true){
            return Encryption.encryptAuth(AuthList.getNode(id).getKey(), AuthList.getAuth(id));
        }else{
            return Encryption.encryptAuth(AuthList.getNode(id).getKey(), Ticket.generateRequest());
        } 
    }
    
    public static byte[] handleChallenge(byte[] challenge){
        SecureDetails sd = new SecureDetails(User.getPassword());
        Ticket decrypted = (Ticket) Encryption.decryptAuth(challenge);        
        if(decrypted != null){    
            //Store the password here.
            AuthList.addAuth(decrypted);
            //Create the new Ticket ready to be returned to the node, has different details
            Ticket a = AuthList.getAuth(decrypted.getId());
            return Encryption.encryptAuth(decrypted.getKey(), a);
        }
        return null;
    }
    
    private static boolean checkAuth(String serverId){
        //tidy up the auth list before we use it, and remove expired auth
        AuthList.removeExpiredAuth();
        return AuthList.exists(serverId);
    }
}
