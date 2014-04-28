/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;


import Message.*;
import java.io.*;
import java.net.*;
import java.util.Stack;
import java.util.logging.*;
import javax.net.ssl.*;

/**
 *
 * @author Marc
 */
public class UpdateHandler implements Runnable {
    
        private NodeList n;
        private Stack st;
	private SSLSocket socket;
        
        UpdateHandler(SSLServerSocket s){
            try {
                this.socket = (SSLSocket) s.accept();
            }
            catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                s = null;
        }
        
        }
	public void run() {  
              
            SSLSocket s = socket;
            n = new NodeList();
            st = n.getList();
            // to be completed 
            try {
                OutputStream os = s.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(st);
                
                os.close();
                oos.close();
                s.close();
            }
            
            catch (IOException ex) {
                Logger.getLogger(UpdateHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            n = null;
            st = null;
        }
}
