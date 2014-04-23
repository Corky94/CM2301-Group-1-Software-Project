/*
*This class has been designed to help with
*the creation of User ID’s and remote passwords.
*/

package Crypto;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {
    final protected static char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();   
    public static String hashKeyToString(Key inputKey){
        byte[] key = inputKey.getEncoded();
        try{
            MessageDigest hash = MessageDigest.getInstance("SHA-256");
            hash.update(key); 
            byte[] bytes = hash.digest();
            //convert to hex string
            char[] hexChars = new char[bytes.length * 2];
                for ( int j = 0; j < bytes.length; j++ ) {
                    int v = bytes[j] & 0xFF;
                    hexChars[j * 2] = HEX_ARRAY[v >>> 4];
                    hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
                }
            return new String(hexChars);
        }catch(NoSuchAlgorithmException ex){
            throw new RuntimeException(ex);
        }
    }

    public static byte[] hashKeyToByte(Key inputKey){
        byte[] key = inputKey.getEncoded();
        try{
            MessageDigest hash = MessageDigest.getInstance("SHA-256");
            hash.update(key); 
            byte[] bytes = hash.digest();
            return bytes;
        }catch(NoSuchAlgorithmException ex){
            throw new RuntimeException(ex);
        }
    }

    public static byte[] hashSha256(byte[] input){
        try{
            MessageDigest hash = MessageDigest.getInstance("SHA-256");
            hash.update(input); 
            byte[] bytes = hash.digest();
            return bytes;
        }catch(NoSuchAlgorithmException ex){
            throw new RuntimeException(ex);
        }
    }

    public static byte[] doubleSha256(byte[] input){
        return hashSha256(hashSha256(input));
    }
}
