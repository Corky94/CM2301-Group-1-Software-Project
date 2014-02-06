/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Marc
 */
public class Message {
    
    
    
    private String contents;
    private String sender;
    private String recipient;
    
    Message(){
        
    }
    
    public void setContents(String c){
        
        contents = c;
        
    }
    
    public void setSender(String s){
        
        sender = s;
       
    }
    
    public void setRecipient(String r){
        
        recipient = r;
        
    }
    
    public String getContents(){
        
        return contents;
        
    }
    
    public String getSender(){
        
        return sender;
        
    }
    
    public String getRecipient(){
        
        return recipient;
        
    }
    
}
