/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Crypto;

import java.math.BigInteger;
import java.security.Security;
import java.util.Arrays;
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
    
    public static String generateUserId(byte[] publicKey){
        //https://en.bitcoin.it/wiki/Technical_background_of_version_1_Bitcoin_addresses
        KeyGen kg = new KeyGen();
        HashUtils hu = new HashUtils();
        RIPEMD160Digest d = new RIPEMD160Digest();
        byte [] USER_VERSION_NUMBER = bigIntToByteArray(000);
        byte[] firstRound = publicKey;
        d.update (firstRound, 0, firstRound.length);
        byte[] secondRound = new byte[d.getDigestSize()];
        d.doFinal (secondRound, 0);
        byte[] thirdRound = kg.concancateByteArrays(USER_VERSION_NUMBER, secondRound);
        byte[] fourthRound = hu.hashSha256(thirdRound);
        byte[] fifthRound = Arrays.copyOfRange(hu.hashSha256(fourthRound), 0, 4);
        byte[] sixthRound = kg.concancateByteArrays(fourthRound, fifthRound);
        return Base58.encode(sixthRound);
    }
    
    public static String generateNodeId(byte[] publicKey){
        //https://en.bitcoin.it/wiki/Technical_background_of_version_1_Bitcoin_addresses
        KeyGen kg = new KeyGen();
        HashUtils hu = new HashUtils();
        RIPEMD160Digest d = new RIPEMD160Digest();
        byte [] NODE_VERSION_NUMBER = bigIntToByteArray(000);
        byte[] firstRound = publicKey;
        d.update (firstRound, 0, firstRound.length);
        byte[] secondRound = new byte[d.getDigestSize()];
        d.doFinal (secondRound, 0);
        byte[] thirdRound = kg.concancateByteArrays(NODE_VERSION_NUMBER, secondRound);
        byte[] fourthRound = hu.hashSha256(thirdRound);
        byte[] fifthRound = Arrays.copyOfRange(hu.hashSha256(fourthRound), 0, 4);
        byte[] sixthRound = kg.concancateByteArrays(fourthRound, fifthRound);
        return Base58.encode(sixthRound);
    }
    
    private static byte[] bigIntToByteArray( int i ) {
        BigInteger bigInt = BigInteger.valueOf(i);      
        return bigInt.toByteArray();
    }
    
}
