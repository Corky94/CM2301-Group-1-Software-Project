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
public class ServerCrypto {
    
    public static boolean checkUserPublicKey(String userId, byte[] publicKey){
        return ServerTools.generateUserId(publicKey).equals(userId);
    }
    
    public static boolean checkNodePublicKey(String nodeId, byte[] publicKey){
        return ServerTools.generateNodeId(publicKey).equals(nodeId);
    }
    
}
