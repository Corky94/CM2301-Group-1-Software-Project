package Crypto;

import static Crypto.KeyGen.HEX_ARRAY;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {
    
    	public String hashKeyToString(Key inputKey){
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
			//logger.error("Cannot close connection");
            throw new RuntimeException(ex);
		}
	}

	public byte[] hashKeyToByte(Key inputKey){
            //remove method
            byte[] key = inputKey.getEncoded();
            try{
                    MessageDigest hash = MessageDigest.getInstance("SHA-256");
                    hash.update(key); 
                    byte[] bytes = hash.digest();
                    return bytes;
            }catch(NoSuchAlgorithmException ex){
                //logger.error("Cannot close connection");
                throw new RuntimeException(ex);
            }
	}
        
        public byte[] hashSha256(byte[] input){
            try{
                    MessageDigest hash = MessageDigest.getInstance("SHA-256");
                    hash.update(input); 
                    byte[] bytes = hash.digest();
                    return bytes;
            }catch(NoSuchAlgorithmException ex){
                //logger.error("Cannot close connection");
                throw new RuntimeException(ex);
            }
        }
        
        public byte[] doubleSha256(byte[] input){
            return this.hashSha256(this.hashSha256(input));
        }
    
}
