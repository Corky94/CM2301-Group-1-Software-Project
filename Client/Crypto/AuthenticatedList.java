package Crypto;

/**
 *
 * @author maxchandler
 */
public class AuthenticatedList{
    private static class Authenticated{
        private final Ticket t; 
        private final long authTime;

        Authenticated(Ticket a){
            t = a;
            authTime = System.currentTimeMillis();
        }

        Authenticated(Ticket a, long time) {
            t = a;
            authTime = time;
        }
        
        public Ticket getTicket(){
            return t;
        }
        
        public long getTime(){
            return authTime;
        }
    }
    
    private static Authenticated[] list = new Authenticated[1];
    private static int pointer = 0;
    
    AuthenticatedList(){
        list = new Authenticated[10];
    }
    
    AuthenticatedList(int size){
        list = new Authenticated[size];
    }
    
    public static void addAuth(Ticket a){
        a.setChallenge(false);
        if(pointer >= list.length)
            expand();
        list[pointer] = new Authenticated(a);
        pointer++;
    }
    
    /*public static void addAuth(){
    
    }*/
    
    public static Ticket getNodeAuthentication(String nodeId){
        for(Authenticated a : list){
            if(a != null){
                if(a.getTicket().getNodeId().equals(nodeId))
                    return a.getTicket();
            }
        }
        return null;
    }
    
    public static Ticket getNodeAuthenticationByAddress(String nodeAddress){
        for(Authenticated a : list){
            if(a != null){
                if(a.getTicket().getNodeAddress().equals(nodeAddress))
                    return a.getTicket();
            }
        }
        return null;
    }
    
    public static Ticket getClientAuthentication(String clientId){
        for(Authenticated a : list){
            if(a != null){
                if(a.getTicket().getClientId().equals(clientId))
                    return a.getTicket();
            }
        }
        return null;
    }
    
    /*public static Ticket getTicket(String nodeId){
        Ticket out = null;
        SecureDetails sd = new SecureDetails(User.getPassword());
        for(Authenticated a : list){
            if(a != null){
                if (a.getAuth().getNodeId().equals(nodeId))
                    return new Ticket(sd.getID(), a.getAuth().getOtp(), KeyVault.getRSAKeys().getPublic(), false);
            }
        }
        return null;
    }*/
    
    public static void deleteAuth(String clientId){
        for(int i = 0; i < list.length; i++){
            if(list[i] != null){
                if(list[i].getTicket().getClientId().equals(clientId))
                    list[i] = null;
            }
        }
    }
    
    public static boolean exists(String nodeAddress){
        for(Authenticated a : list){
            if(a != null){
                if (a.getTicket().getNodeAddress().equals(nodeAddress))
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
              list[pointer] = temp[i];
            }
        }
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
        //if the list is full this will infinite loop
        while(list[pointer] == null)
            pointer = (pointer + 1) % list.length;
    }
    
    public static void printList(){
        int i = 0; 
        System.out.println("---------------------------");
        System.out.println("Authenticated List Contents");
        System.out.println("---------------------------");
        for(Authenticated a : list){
            if(a != null){
                System.out.println("Location in list: " + i);
                System.out.println("Time of Authentication: " + a.getTime());
                System.out.println("----- Information containted in associated ticket -----");
                System.out.println("Client ID: " + a.getTicket().getClientId());
                System.out.println("Client PublicKey: " + a.getTicket().getClientPublicKey());
                System.out.println("Node ID: " + a.getTicket().getNodeId());
                System.out.println("Node Address: " + a.getTicket().getNodeAddress());
                System.out.println("Node PublicKey: " + a.getTicket().getNodePublicKey());
                System.out.println("Hash: " + a.getTicket().getPassword());
                System.out.println("Request: " + a.getTicket().is_challenge());
            }
        i++;
        }
        System.out.println("---------------------------");
        System.out.println("END");
        System.out.println("---------------------------");
    }
}