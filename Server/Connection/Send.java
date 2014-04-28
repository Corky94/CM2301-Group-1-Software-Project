/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import Message.NodeList;
import java.io.*;
import java.util.Stack;
import java.util.logging.*;
import javax.net.ssl.SSLSocket;

/**
 *
 * @author Marc
 */
public class Send {
    private NodeList n = new NodeList();

    public Send(Stack st){
        
        //n.addNode();
        try {
            

            System.out.println(n.getList());
            System.out.println(st);
            ServerSSL ss = new ServerSSL();
            String server = n.getNode();
            char[] pass = Connection.Server.getPassword();
            while(server != null){
                
                System.out.println(server);
                SSLSocket s = ss.send(server,12348,pass);
                OutputStream os = s.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(st);
                oos.close();
                os.close();
                s.close();
                server = n.getNode();
            }
            n =null;
        }
        catch (IOException ex) {
            Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
        } 
        n =null;
    }
   
    
}
