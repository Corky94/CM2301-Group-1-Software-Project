/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Crypto;

import Connection.ClientSSL;
import Connection.Packet;
import Console.SecureDetails;
import Message.Message;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import javax.net.ssl.SSLSocket;

/**
 *
 * @author maxchandler
 */
public class Authentication {
    
    public static Packet createPacket(Message messages, String nodeAddress){
        Packet p = auth(nodeAddress);
        p.setMessage(messages);
        return p;
    }
    
    public static Packet auth(String nodeAddress){
        //This method is called to connect to a node, if it has the right auth
        //it will return it, if not it will generate a request.
        Packet p = new Packet();
        if(AuthenticatedList.getNodeAuthenticationByAddress(nodeAddress) != null){
            //get the ticket and encrypt it ready to be send to the node as authentication
            Ticket t = AuthenticatedList.getNodeAuthenticationByAddress(nodeAddress);
            return Encryption.encryptTicket(t);
        }else{
            //generates a new ticket with a request in, that is then encrypted and 
            //ready to be sent to the node
            //            
            // --- Ticket Content ---
            //clientId = clientID;
            //nodeId = null;
            //otp = null;
            //clientPublicKey = clientPublicKey;
            //nodePublicKey = null; 
            //is_challenge = true; 
            SecureDetails sd = new SecureDetails();
            p.setTicket(Ticket.generateRequest(sd.getID(), nodeAddress));
            try {
                ClientSSL c = Console.User.clissl;
                //send challenge
                try (SSLSocket s = c.main(12346)) {
                    //send challenge
                    OutputStream os = s.getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(os);
                    oos.writeObject(p);
                    oos.flush();
                    //receive challenge
                    InputStream is = s.getInputStream();
                    ObjectInputStream ois = new ObjectInputStream(is);
                    p = (Packet) ois.readObject();
                }
            } catch ( ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex){
                System.out.println(ex);
            }
            p = Authentication.handleChallenge(p);
            p.setTicket(null);
            return p;
        } 
    }
    
    //method to be called as ticket is returned from node
    public static Packet handleChallenge(Packet p){
        Ticket t = Encryption.decryptTicket(p).getTicket();
        if(t == null){    
            throw new RuntimeException("Ticket returned null");
        }
        //Ticket has come back, and is no longer a challenge, however the node does not know this
        //so we'll return one copy that will be treated as a challenge returned
        Ticket newTicket = new Ticket(t);
        newTicket.setChallenge(false);
        AuthenticatedList.deleteAuth(newTicket.getClientId());
        AuthenticatedList.addAuth(newTicket);
        //And return the decrypted ticket ready to send back to the server.
        return Encryption.encryptTicket(t);
    }
}