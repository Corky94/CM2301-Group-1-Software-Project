package Message;


public class Message implements java.io.Serializable {
    public byte[] sender;
    public String receiver;
    public byte[] subject;
    public byte[] message;
    public byte[] timeAndDate;
    public byte[] key;
    public boolean needingKey;
    
    
    public void setSender(byte[] S){
        sender = S;
    } 
    
    public void setReceiver(String Send){
        
        receiver = Send;
    }
    
    public void setSubject(byte[] Subj){
        
        subject = Subj;
        
    }
    
    public void setMessage( byte[] Contents){
        
        message = Contents;
        
    }
    public void setNeedingKey(boolean answer){

        needingKey = answer;
        N
    }
    public byte[] getSender(){
        
        return sender;
    }
    public String getReceiver(){
        
        return receiver;
        
    }
    public byte[] getSubject(){
        
        return subject;
        
    }
    
    public byte[] getMessage(){
        
        return message;
    }
    
    
}

