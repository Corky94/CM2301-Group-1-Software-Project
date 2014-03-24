/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Console;

import Message.Message;
import Connection.*;
import Crypto.*;
import java.io.*;
import java.security.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marc
 */
public class SecureDetails {
    
    private HashMap details;
    private final String dir = "user2";
    private Encryption en = new Encryption();
    
    public SecureDetails(char[] pass){
        
        
    

                try {
                    FileInputStream test = new FileInputStream(dir);
                    en.decryptFile(pass, dir);
                    FileInputStream fis = new FileInputStream(dir);
                    ObjectInputStream oos = new ObjectInputStream(fis);
                    
                    details = (HashMap) oos.readObject();
                    
                } catch (FileNotFoundException ex) {
                    try{
                        details = new HashMap();
                        details.put("Registered", false);
                        FileOutputStream fos = new FileOutputStream(dir);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(details);
                        oos.close();

                    }
                    catch(FileNotFoundException e){
                    } 
                    catch (IOException e) {
                    }
                
    
            
                } catch (IOException ex) {
                    Logger.getLogger(SecureDetails.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(SecureDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
   
                

            

        

    
    
    
    public void setRegistered(){
        details.put("Registered", true);
    }
    
    
    
    public void setId(String key){
        details.put("ID", key);
    }
    
//    public void addMessage(Message m){
//        
//        Message[] savedMessages = getMessages();
//        int i = savedMessages.length + 1;
//        savedMessages[i] = m;
//        details.put("Messages",savedMessages);
//        this.saveDetails();
//    }
//    
//    public void deleteMessage(Message m){
//        Message[] savedMessages = getMessages();
//        for(int i =0; i<= savedMessages.length; i++){
//            if(m == savedMessages[i]){
//                savedMessages[i] = null;
//            }
//        }
//        details.put("Messages",savedMessages);
//        this.saveDetails();
//    }
    
    
    public Message[] getMessages(){
        Message[] savedMessages = (Message[])details.get("Messages");
        return savedMessages;
        
    }
    

    
    public String getID(){
        
        String id = (String)details.get("ID");
        return id;
    }
    

    
    public boolean getRegistered(){
        
        boolean registered = (boolean)details.get("Registered");
        return registered;
        
    }
    
    public void saveDetails(char[] pass){
        
         try{

                FileOutputStream fos = new FileOutputStream(dir);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(details);
                fos.close();
                oos.close();
                FileInputStream fid = new FileInputStream(dir);
                
                en.encryptFile(pass, fos);
                
                
                
            }
            catch(FileNotFoundException ex){
                
            } 
            catch (IOException ex) {
            }
        
    }

}
