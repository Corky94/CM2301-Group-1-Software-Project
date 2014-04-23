/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Connection;

import Crypto.Ticket;

/**
 *
 * @author maxchandler
 */
public class Packet {
        private static Ticket t;
        private static Message[] m;
        
        Packet(Ticket tick, Message[] messages){
            t = tick;
            m = new Message[messages.length];
            m = messages;
        }
        
        public static Ticket getTicket(){
            return t;
        }
        
        public static Message[] getMessages(){
            return m;
        }
        
        public static Message getMessage(){
            return m[0];
        }
}
