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
public class Authentication {
    
    Authentication(){
    
    }
    
    public static byte[] handleChallenge(char[] localPassword, byte[] challenge){
        return Encryption.encryptString(Encryption.decryptString(challenge, localPassword), NEEDS_USER_ID);
    }
    
}
