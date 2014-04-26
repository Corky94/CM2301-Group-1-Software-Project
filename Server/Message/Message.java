package Message;

import Connection.SessionKey;


public class Message implements java.io.Serializable {
    private byte[] sender;
    private String receiver;
    private byte[] subject;
    private byte[] message;
    private byte[] timeAndDate;
    private byte[] key;
    private boolean needingKey;
    private SessionKey sessionKey;
    
    public void setSessionKey(SessionKey sKey){
        sessionKey = sKey;
    }
    
    public SessionKey getSessionKey(){
        return sessionKey;
    }
    /**
     * @return the sender
     */
    public byte[] getSender() {
        return sender;
    }

    /**
     * @param sender the sender to set
     */
    public void setSender(byte[] sender) {
        this.sender = sender;
    }

    /**
     * @return the receiver
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * @param receiver the receiver to set
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    /**
     * @return the subject
     */
    public byte[] getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(byte[] subject) {
        this.subject = subject;
    }

    /**
     * @return the message
     */
    public byte[] getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(byte[] message) {
        this.message = message;
    }

    /**
     * @return the timeAndDate
     */
    public byte[] getTimeAndDate() {
        return timeAndDate;
    }

    /**
     * @param timeAndDate the timeAndDate to set
     */
    public void setTimeAndDate(byte[] timeAndDate) {
        this.timeAndDate = timeAndDate;
    }

    /**
     * @return the key
     */
    public byte[] getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(byte[] key) {
        this.key = key;
    }

    /**
     * @return the needingKey
     */
    public boolean isNeedingKey() {
        return needingKey;
    }

    /**
     * @param needingKey the needingKey to set
     */
    public void setNeedingKey(boolean needingKey) {
        this.needingKey = needingKey;
    }
    
} 
   