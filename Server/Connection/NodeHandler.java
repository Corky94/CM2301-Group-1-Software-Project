/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;


import Message.*;
import java.io.*;
import java.net.*;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.*;

/**
 *
 * @author Marc
 */
public class NodeHandler implements Runnable {
    

	private static boolean debug = true;
        private SSLSocket socket;
        private NodeList n;
        private ServerStorage s;

        
        NodeHandler(SSLServerSocket s){
            try {
                this.socket = (SSLSocket)s.accept();
            } catch (IOException ex) {
                Logger.getLogger(NodeHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        

	public void run() {  
              
            
            // to be completed 
            
                try{
                    
                System.out.println("running handler");
                
                
                InputStream is = socket.getInputStream();  
                ObjectInputStream ois = new ObjectInputStream(is);
                Stack in =(Stack) ois.readObject();
                Stack t = in;
                System.out.println(in);
                Object o = t.peek();
                
                is.close();
                ois.close();
                
                if(o instanceof MessageServer){
                    s = new ServerStorage();
                    MessageServer m = (MessageServer)o;
                    
                    System.out.println(m.getUrl());
                    s.updateMessageServerDetails(in);
                }
                else if(o instanceof IDServer){
                    s = new ServerStorage();
                    s.updateIDSeverDetails(in);
                }
                    
                else{
                    System.out.println("Test"); 
                    n = new NodeList();                    
                    n.updateList(in);
                }
                    
               n = null;
               s=null;
               
                    
               
                
	
                        
                  
		} catch(EOFException e){
                    
		}  
            catch (IOException ex) {
                Logger.getLogger(NodeHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (ClassNotFoundException ex) {
                Logger.getLogger(NodeHandler.class.getName()).log(Level.SEVERE, null, ex);
            }  
            
        }
}
