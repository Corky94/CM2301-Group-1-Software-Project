package Crypto;

/**
 *
 * @author maxchandler
 */
public class AuthenticatedList{
    class Authenticated{
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
    
    private  Authenticated[] list = new Authenticated[1];
    private  int pointer = 0;
    
    AuthenticatedList(){
        list = new Authenticated[10];
    }
    
    AuthenticatedList(int size){
        list = new Authenticated[size];
    }
    
    public  void addAuth(Ticket a){
        a.setChallenge(false);
        if(pointer >= list.length)
            expand();
        list[pointer] = new Authenticated(a);
        pointer++;
    }
    
    /*public static void addAuth(){
    
    }*/
    
    public  Ticket getClientTicket(String clientId){
        for(Authenticated a : list){
            if(a != null){
                if(a.getTicket().getClientId().equals(clientId))
                    return a.getTicket();
            }
        }
        return null;
    }
    
    public  Ticket getNodeTicket(String nodeId){
        for(Authenticated a : list){
            if(a != null){
                if(a.getTicket().getNodeId().equals(nodeId))
                    return a.getTicket();
            }
        }
        return null;
    }
    
    public  void deleteAuth(String clientId){
        for(int i = 0; i < list.length; i++){
            if(list[i] != null){
                if(list[i].getTicket().getClientId().equals(clientId))
                    list[i] = null;
            }
        }
    }
    
    public  boolean exists(String clientId){
        for(Authenticated a : list){
            if(a != null){
                if (a.getTicket().getClientId().equals(clientId))
                    return true;
            }
        }
        return false;
    }
    
    private  void expand(){
        Authenticated[] temp = list;
        list = new Authenticated[(2 * temp.length)];
        for (int i = 0; i < temp.length; ++i) {
            if (temp[i] != null) {
              list[pointer] = temp[i];
            }
        }
    }
    
    public  void removeExpiredAuth(){
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
    
    private  void tidy(){
        for(int i = 0; i < list.length; i++){
            if(list[i] == null){
                for(int x = i; x + 1 < list.length; x++){
                    list[x] = list[x + 1];
                }
            }
        }
        findSlot();
    }
    
    private void findSlot(){
        //if the list is full this will infinite loop
        while(list[pointer] == null)
            pointer = (pointer + 1) % list.length;
    }
    
    public void printList(){
        int i = 0; 
        for(Authenticated a : list){
            if(a != null){
                System.out.println("Location in list: " + i);
                System.out.println("Time of Authentication: " + a.getTime());
                System.out.println("----- Information containted in associated ticket -----");
                System.out.println("Client ID: " + a.getTicket().getClientId());
                System.out.println("Client PublicKey: " + a.getTicket().getClientPublicKey());
                System.out.println("Node ID: " + a.getTicket().getNodeId());
                System.out.println("Node PublicKey: " + a.getTicket().getNodePublicKey());
                System.out.println("Hash: " + a.getTicket().getPassword());
                System.out.println("Request: " + a.getTicket().is_challenge());
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