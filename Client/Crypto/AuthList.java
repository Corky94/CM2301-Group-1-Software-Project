package Crypto;

import Console.SecureDetails;
import Console.User;

/**
 *
 * @author maxchandler
 */
public class AuthList{
    private static class Authenticated{
        private final Ticket au; 
        private final long authTime;

        Authenticated(Ticket a){
            au = a;
            authTime = System.currentTimeMillis();
        }

        Authenticated(Ticket a, long time) {
            au = a;
            authTime = time;
        }
        
        public Ticket getAuth(){
            return au;
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
    
    public static void addAuth(Ticket a){
        a.setChallenge(false);
        if(pointer >= list.length)
            expand();
        list[pointer] = new Authenticated(a);
        pointer++;
    }
    
    public static void addAuth(){
    
    }
    
    public static Ticket getNode(String nodeId){
        for(Authenticated a : list){
            if(a != null){
                if(a.getAuth().getId().equals(nodeId))
                    return a.getAuth();
            }
        }
        return null;
    }
    
    public static Ticket getAuth(String nodeId){
        Ticket out = null;
        SecureDetails sd = new SecureDetails(User.getPassword());
        for(Authenticated a : list){
            if(a != null){
                if (a.getAuth().getId().equals(nodeId))
                    return new Ticket(sd.getID(), a.getAuth().getOtp(), KeyVault.getRSAKeys().getPublic(), false);
            }
        }
        return null;
    }
    
    public static void deleteAuth(String userId){
        for(int i = 0; i < list.length; i++){
            if(list[i] != null){
                if(list[i].getAuth().getId().equals(userId))
                    list[i] = null;
            }
        }
    }
    
    public static boolean exists(String userId){
        for(Authenticated a : list){
            if(a != null){
                if (a.getAuth().getId().equals(userId))
                    return true;
            }
        }
        return false;
    }
    
    /*private static Authenticated getUser(String userId){
        for(Authenticated a : list){
            if(a != null){
                if (a.getAuth().getId().equals(userId))
                    return a;
            }
        }
        return null;
    }*/
    
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
    
    public static void removeExpiredAuth(){
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
    
    public static void printList(){
        int i = 0; 
        for(Authenticated a : list){
            if(a != null){
                System.out.println("Location: " + i);
                System.out.println("Time: " + a.getTime());
                System.out.println("ID: " + a.getAuth().getId());
                System.out.println("Hash: " + a.getAuth().getOtp());
                System.out.println("Request: " + a.getAuth().is_challenge());
            }
        i++;
        }
    }
    
    /*public static void main(String[] args){
        while(true){
            Ticket a = new Ticket("asjduedasd232", "password", null);
            AuthList.addAuth(a);
            printList();
        }
    }*/
}