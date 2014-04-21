/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Crypto;

import java.math.BigInteger;
import java.security.Key;
import java.security.Security;
import java.util.Arrays;
import java.util.Random;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 *
 * @author maxchandler
 */
public class ServerTools {
	final static private int RSA_KEY_LENGTH = 2048;
	final static private int AES_KEY_LENGTH = 256;
	final static private char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
	static { Security.addProvider(new BouncyCastleProvider());}
        
    public static boolean verifyClientPublicKey(String clientId, Key publicKey){
        return generateUserId(publicKey).equals(clientId);
    }
    
    public static boolean verufyNodePublicKey(String nodeId, Key publicKey){
        return generateNodeId(publicKey).equals(nodeId);
    }
    
    public static String generateOneTimePassword(){
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@£$%^&*()?<>[]{}".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 25; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }
    
    private static String generateUserId(Key k){
        //https://en.bitcoin.it/wiki/Technical_background_of_version_1_Bitcoin_addresses
        byte[] publicKey = k.getEncoded();
        RIPEMD160Digest d = new RIPEMD160Digest();
        byte [] USER_VERSION_NUMBER = bigIntToByteArray(000);
        byte[] firstRound = publicKey;
        d.update (firstRound, 0, firstRound.length);
        byte[] secondRound = new byte[d.getDigestSize()];
        d.doFinal (secondRound, 0);
        byte[] thirdRound = KeyGen.concancateByteArrays(USER_VERSION_NUMBER, secondRound);
        byte[] fourthRound = HashUtils.hashSha256(thirdRound);
        byte[] fifthRound = Arrays.copyOfRange(HashUtils.hashSha256(fourthRound), 0, 4);
        byte[] sixthRound = KeyGen.concancateByteArrays(fourthRound, fifthRound);
        return Base58.encode(sixthRound);
    }
    
    private static String generateNodeId(Key k){
        //https://en.bitcoin.it/wiki/Technical_background_of_version_1_Bitcoin_addresses
        byte[] publicKey = k.getEncoded();
        RIPEMD160Digest d = new RIPEMD160Digest();
        byte [] NODE_VERSION_NUMBER = bigIntToByteArray(010);
        byte[] firstRound = publicKey;
        d.update (firstRound, 0, firstRound.length);
        byte[] secondRound = new byte[d.getDigestSize()];
        d.doFinal (secondRound, 0);
        byte[] thirdRound = KeyGen.concancateByteArrays(NODE_VERSION_NUMBER, secondRound);
        byte[] fourthRound = HashUtils.hashSha256(thirdRound);
        byte[] fifthRound = Arrays.copyOfRange(HashUtils.hashSha256(fourthRound), 0, 4);
        byte[] sixthRound = KeyGen.concancateByteArrays(fourthRound, fifthRound);
        return Base58.encode(sixthRound);
    }
    
    private static byte[] bigIntToByteArray(int i) {
        BigInteger bigInt = BigInteger.valueOf(i);      
        return bigInt.toByteArray();
    }
    
}
