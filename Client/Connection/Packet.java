/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Connection;

import Message.Message;


/**
 *
 * @author maxchandler
 */
public class Packet {
    private static byte[] encryptedTicket;
    private static Message[] m;

    public Packet(byte[] t, Message[] messages){
        encryptedTicket = t;
        m = new Message[messages.length];
        m = messages;
    }
    
    public Packet(byte[] ticket, Message message) {
        encryptedTicket = ticket;
        m = new Message[1];
        m[0] = message;
    }

    public static byte[] getTicket(){
        return encryptedTicket;
    }

    public static Message[] getMessages(){
        return m;
    }

    public static Message getMessage(){
        return m[0];
    }


}
