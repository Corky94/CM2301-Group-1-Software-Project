/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;


import Message.*;
import java.io.*;
import java.net.*;
import javax.net.ssl.*;

/**
 *
 * @author Marc
 */
public class NodeHandler implements Runnable {
    

	private static boolean debug = true;
        private SSLServerSocket socket;
        private Message message;
        
        NodeHandler(SSLServerSocket s){
            this.socket = s;
        
        }
        

	public void run() {  
              
            
            // to be completed 
            while (true){
                try{
                    
                Socket s = socket.accept();
                Thread t = new Thread();
                
                InputStream is = s.getInputStream();  
                ObjectInputStream ois = new ObjectInputStream(is);
	
                        
                  
		} catch(Exception e){

		}  
            }
        }
}
