/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Crypto;
/**
 *
 * @author maxchandler
 */
public class ServerAuthentication {
    
    public static byte[] handleChallenge(byte[] input){
        Ticket t = (Ticket) Encryption.decryptAuth(input);
        /*if(t.is_challenge() == false){
            verifyClientChallenge(t);
            return null;
        }else{*/
           t = generateChallenge(t);
           return Encryption.encryptAuth(t.getClientPublicKey(), t);
        //}
    }
    
    private static Ticket generateChallenge(Ticket t){
        //takes incomming request ticket from client, adds node details and onetime password to be returned to the client
        Ticket newTicket =  new Ticket(
                t.getClientId(),
                Node.getId(), //TO COMPLETE
                ServerTools.generateOneTimePassword(),
                t.getClientPublicKey(),
                KeyVault.getRSAKeys().getPublic(),
                true
        );
        AuthenticatedList.addAuth(newTicket);
        return newTicket;
        
        //String cId, String nId, String password, Key clientKey, Key nodeKey, boolean r
    }
    
    private static boolean verifyClientChallenge(Ticket t){    
        //get the current ticket
        Ticket current = AuthenticatedList.getClientTicket(t.getClientId());
        if(t.is_challenge() == false){
            if(current.equals(t)){
                return true;
            }
        }else{
            if(ServerTools.verifyClientPublicKey(t.getClientId(), t.getClientPublicKey()) == true){
                if(current.getPassword().equals(t.getPassword()) & current.getClientId().equals(t.getClientId())){
                    //passwords & ids match node records
                    if(current.is_challenge() == true){
                        //challenge has returned, user is free to roam. better make sure the node knows
                        AuthenticatedList.deleteAuth(current.getClientId());
                        current.setChallenge(false);
                        AuthenticatedList.addAuth(current);
                        return true;
                    }
                }
            }
        }
        System.out.println("Couldn't verify client identity, verifcation failed");
        return false;
    }
}
