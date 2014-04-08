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
          
            while(n.getNode() != null){
                System.out.println(n.getNode());
                SSLSocket s = ss.send(n.getNode(),12348);
                OutputStream os = s.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(st);
                oos.close();
                os.close();
                s.close();
                
            }
            n =null;
        }
        catch (IOException ex) {
            Logger.getLogger(Send.class.getName()).log(Level.SEVERE, null, ex);
        } 
        n =null;
    }
   
    
}
