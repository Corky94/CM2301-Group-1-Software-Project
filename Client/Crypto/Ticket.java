package Crypto;

import Console.SecureDetails;
import Console.User;
import java.security.Key;

/**
 *
 * @author maxchandler
 */
//Client side auth object, it differes to the node as passwords are stored as
//string and not as hashes of the strings.
public class Ticket{
    private final String userId;
    private final String otp;
    private final Key publicKey;
    private Boolean is_challenge;
    
    //constructor for saving auth details
    Ticket(String id, String p, Key k, boolean r){
        userId = id;
        otp = p;
        publicKey = k;
        is_challenge = r; 
    }
    
    public static Ticket generateRequest(){
        SecureDetails sd = new SecureDetails(User.getPassword());
        return new Ticket(sd.getID(), null, KeyVault.getRSAKeys().getPublic(), true);
    }
    
    public String getId(){
        return userId;
    }

    public String getOtp(){
        return otp;
    }
    
    public Key getKey(){
        return publicKey;
    }
    
    public boolean is_challenge(){
        return is_challenge;
    }
    public void setChallenge(boolean b){
        is_challenge = b;
    }
}