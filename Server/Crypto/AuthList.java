package Crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maxchandler
 */
public class AuthList{
    private static class Authenticated{
        private final int clientID;
        private final byte[] otpHash;
        private final long authTime;

        Authenticated(int id, byte[] otpH){
            clientID = id;
            otpHash = otpH;
            authTime = System.currentTimeMillis();
        }

        Authenticated(int id, byte[] otpH, long time) {
            clientID = id;
            otpHash = otpH;
            authTime = time;
        }
        
        public int getId(){
            return clientID;
        }
        
        public byte[] getOtpHash(){
            return otpHash;
        }
        
        public long getTime(){
            return authTime;
        }
    }
    
    private static Authenticated[] list = new Authenticated[1];
    private static int pointer = 0;
    
    AuthList(){
        list = new Authenticated[10];
    }
    
    AuthList(int size){
        list = new Authenticated[size];
    }
    
    public static void addAuthUser(int id, String otp){
        System.out.println(pointer + "   :   " + list.length);
        if(pointer >= list.length)
            expand();
        list[pointer] = new Authenticated(id, hash(otp));
        pointer++;
    }
    
    public static void deleteAuth(int id){
        for(int i = 0; i < list.length; i++){
            if(list[i] != null){
                if(list[i].getId() == id)
                    list[i] = null;
            }
        }
    }
    
    private static Authenticated getUser(int id){
        for(Authenticated a : list){
            if(a != null){
                if (a.getId() == id)
                    return a;
            }
        }
        return null;
    }
    
    private static void expand(){
        Authenticated[] temp = list;
        list = new Authenticated[(2 * temp.length)];
        for (int i = 0; i < temp.length; ++i) {
            if (temp[i] != null) {
              copyAuthUser(temp[i]);
            }
        }
    }
    
    private static void copyAuthUser(Authenticated a){
        list[pointer] = a;
    }
    
    public static void removeOldAuth(){
        for(int i = 0; i < list.length; i++){
            if(list[i] != null){
                //4 hour expiration time at the moment
                long authTime = 1000L * 60 * 60 * 4;
                if(list[i].getTime() > (System.currentTimeMillis() + authTime)){
                    list[i] = null;
                }
            }
        }
        tidy();
    }
    
    private static void tidy(){
        for(int i = 0; i < list.length; i++){
            if(list[i] == null){
                for(int x = i; x + 1 < list.length; x++){
                    list[x] = list[x + 1];
                }
            }
        }
        findSlot();
    }
    
    private static void findSlot(){
        while(list[pointer] == null)
            pointer = (pointer + 1) % list.length;
    }
        
    private static byte[] hash(String otp){
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AuthList.class.getName()).log(Level.SEVERE, null, ex);
        }
        messageDigest.update(otp.getBytes());
        return messageDigest.digest();
    }
    
    public static boolean checkAuth(int id, String otp){
        return Arrays.equals(getUser(id).getOtpHash(), hash(otp));
    }
    
    public static void printList(){
        int i = 0; 
        for(Authenticated a : list){
            if(a != null){
                System.out.println("Location: " + i);
                System.out.println("ID: " + a.getId());
                System.out.println("Hash: " + Arrays.toString(a.getOtpHash()));
                System.out.println("Time: " + a.getTime());
            }
        i++;
        }
    }
}