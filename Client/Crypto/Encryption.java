package Crypto;

import Connection.ClientReceive;
import Message.Message;
import java.security.*;
import javax.crypto.*;
import java.security.spec.X509EncodedKeySpec;

public class Encryption{

	public Encryption(){
	}

	public byte[] encryptString(Key publicKey, String data){
        try{
        	Cipher encrypt = Cipher.getInstance("RSA");
        	encrypt.init(Cipher.ENCRYPT_MODE, publicKey);
        	byte[] encryptedData = encrypt.doFinal(data.getBytes());
        	return encryptedData; 
        }catch(Exception ex){
            throw new RuntimeException(ex);
	    }
	}

	public byte[] decryptString(byte[] data, char[] localPassword){
		KeyVault kv = new KeyVault();
		KeyPair rsaKeys = kv.getRSAKeys(localPassword);
		PrivateKey privateKey = rsaKeys.getPrivate();
		try{
			Cipher decrypt = Cipher.getInstance("RSA");
			decrypt.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] decryptedData = decrypt.doFinal(data);
			return decryptedData;
		}catch(Exception ex){
            throw new RuntimeException(ex);
	    }
	}

	public String bTS(byte[] input){
		String s = new String(input);
		return s;
	}

	public byte[] encryptRemotePassword(byte[] remotePassword, char[] localPassword){
		try{
			KeyVault kv = new KeyVault();
			Key aesKey = kv.getAESKey(localPassword);
		    Cipher encrypt = Cipher.getInstance("AES");
        	encrypt.init(Cipher.ENCRYPT_MODE, aesKey);
        	byte[] encryptedData = encrypt.doFinal(remotePassword);
        	return encryptedData; 
        }catch(Exception ex){
            throw new RuntimeException(ex);
	    }
	}

	public byte[] decryptRemotePassword(byte[] encryptedPassword, char[] localPassword){
		KeyVault kv = new KeyVault();
		Key aesKey = kv.getAESKey(localPassword);
		try{
			Cipher decrypt = Cipher.getInstance("AES");
			decrypt.init(Cipher.DECRYPT_MODE, aesKey);
			byte[] decryptedData = decrypt.doFinal(encryptedPassword);
			return decryptedData;
		}catch(Exception ex){
            throw new RuntimeException(ex);
	    }
	}
        
    public PublicKey getKey(String id){
        Message m = new Message();
        m.receiver = id;
        m.needingKey = true; 
        byte [] key = ClientReceive.getKey(m);
        try {
            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(key);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PublicKey pk = kf.generatePublic(pubKeySpec);                
            return pk;
        }catch(Exception ex){
        	throw new RuntimeException(ex);
        }        
    } /*
	public static void main (String[] args){
		Encryption e = new Encryption();
		KeyVault kv = new KeyVault();
		char[] password = "password".toCharArray();

		KeyPair kp = kv.getRSAKeys(password);
		Key sk = kv.getAESKey(password);

		PublicKey pk = kp.getPublic();

		String toEncrypt = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Typi non habent claritatem insitam; est usus legentis in iis qui facit eorum claritatem. Investigationes demonstraverunt lectores legere me lius quod ii legunt saepius. Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. Mirum est notare quam littera gothica, quam nunc putamus parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima et quinta decima. Eodem modo typi, qui nunc nobis videntur parum clari, fiant sollemnes in futurum.";

		byte[] encrypted = e.encryptString(pk, toEncrypt);
		System.out.println(e.bTS(e.decryptString(encrypted, password)));
	}*/
}
