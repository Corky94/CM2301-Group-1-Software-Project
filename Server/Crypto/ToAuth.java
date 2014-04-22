package Crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maxchandler
 */
public class ToAuth {
    private static class Pair{
        private final int userId;
        private final byte[] otp;
        Pair(int id, byte[] p){
            userId = id;
            otp = p;
        }
        
        public int getId(){
            return userId;
        }
        
        public byte[] getOtp(){
            return otp;
        }
    }
    
    private static Pair[] list = new Pair[1];
    private static int pointer = 0;
    
    ToAuth(){
        list = new Pair[10];
    }
    
    ToAuth(int size){
        list = new Pair[size];
    }
    
    public static void addToAuthUser(int id, String otp){
        System.out.println(pointer + "   :   " + list.length);
        if(pointer >= list.length)
            expand();
        list[pointer] = new Pair(id, hash(otp));
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
    
    public static Pair getUser(int id){
        for(Pair a : list){
            if(a != null){
                if (a.getId() == id)
                    return a;
            }
        }
        return null;
    }
    
    private static void expand(){
        Pair[] temp = list;
        list = new Pair[(2 * temp.length)];
        for (int i = 0; i < temp.length; ++i) {
            if (temp[i] != null) {
              copyToAuth(temp[i]);
            }
        }
    }
    
    private static void copyToAuth(Pair p){
        list[pointer] = p;
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
            
        }
        messageDigest.update(otp.getBytes());
        return messageDigest.digest();
    }
}
