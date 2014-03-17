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
public class ServerCrypto {
    
    public static boolean checkUserPublicKey(String userId, byte[] publicKey){
        return ServerTools.generateUserId(publicKey).equals(userId);
    }
    
    public static boolean checkNodePublicKey(String nodeId, byte[] publicKey){
        return ServerTools.generateNodeId(publicKey).equals(nodeId);
    }
    
    public static byte[] generateChallenge(char[] localPassword, byte[] publicKey){
        Key serverPubKey = KeyVault.getRSAKeys(localPassword).getPublic();
        return Encryption.encryptString(KeyVault.bytesToKey(publicKey), HashUtils.hashKeyToString(serverPubKey));
        /*
        encrypt server public key in byte array using user public key taken from ID server
        ?add onetime password?
        */
    }
    
    public static boolean verifyChallenge(char[] localPassword, byte[] input){
        if(Encryption.decryptString(input, localPassword) != null);
            NEEDS TO TEST AGAINST REMOTE SERVER, IF ID IS VALID THEN USER IS GOOD TO GO.
        /*
        decrypt byte array using server private key
        ?check if otp is the same as one sent?
        read user id
        else
        */
        return false;
    }
    
}
