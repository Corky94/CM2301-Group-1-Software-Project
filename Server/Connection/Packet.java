/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Connection;

import Crypto.Ticket;
import Message.Message;
import java.io.Serializable;

/**
 *
 * @author maxchandler
 */
public class Packet implements Serializable {
    private  byte[] encryptedTicket;
    private  Ticket t; 
    private  Message[] m;
    private  SessionKey sessionKey;

    public Packet(){
    
    }
        
    public Packet(byte[] t, Message[] messages){
        encryptedTicket = t;
        m = new Message[messages.length];
        m = messages;
    }
    
    public void setTicket(Ticket ticket){
        t = ticket;
    }

    public void setTicket(Ticket ticket, SessionKey key){
        t = ticket;
        sessionKey = key;
    }
    
    public void setMessages(Message[] messages){
        m = new Message[messages.length];
        m = messages;
    }
    
    public void setMessage(Message message){
        m = new Message[1];
        m[0] = message;
    }
    
    
    public void setEncryptedTicket(byte[] ticket){
        encryptedTicket = ticket;
    }
    
    public void setSessionKey(SessionKey key){
        sessionKey = key;
    }
    
    public byte[] getEncryptedTicket(){
        return encryptedTicket;
    }
    
    public Ticket getTicket(){
        return t;
    }

    public Message[] getMessages(){
        return m;
    }
    
    public Message getMessage(){
        return m[0];
    }
    
    public SessionKey getSessionKey(){
        return sessionKey;
    }
        
    public void deleteTicket(){
        t = null;
    }
    
    public void printPacket(Packet p){
        System.out.println("--------------------------");
        System.out.println("Packet Contents");
        System.out.println("--------------------------");
        System.out.println("Encrypted Ticket: " + p.getEncryptedTicket());
        System.out.println("Messages: " + p.getMessages());
        System.out.println("Session Key: " + p.getSessionKey());
        System.out.println("Ticket:" + p.getTicket());
    }

}
