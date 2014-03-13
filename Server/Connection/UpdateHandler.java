/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import static Connection.ClientHandler.getKey;
import static Connection.ClientHandler.registerUser;
import Message.*;
import java.io.*;
import java.net.*;
import javax.net.ssl.*;

/**
 *
 * @author Marc
 */
public class UpdateHandler implements Runnable {
    

	private static boolean debug = true;
        private Socket socket;
        private Message message;
        
        UpdateHandler(Socket s){
            this.socket = s;
        
        }
        

	public void run() {  
              
            Socket s = socket;
            // to be completed 
            while (true){
                try{
                    
                
                InputStream is = s.getInputStream();  
                ObjectInputStream ois = new ObjectInputStream(is);
	
                        
                  
		} catch(Exception e){

		}  
            }
        }
}
