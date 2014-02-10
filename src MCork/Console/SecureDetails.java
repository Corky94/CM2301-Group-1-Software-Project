/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Console;

import Connection.*;
import java.io.*;
import java.security.*;
import java.util.HashMap;

/**
 *
 * @author Marc
 */
public class SecureDetails {
    
    private HashMap details;
    private final String dir = "/home/coding/Java/src/console/user1.ser";
    
    public SecureDetails(){
        
        
    
        try{

            FileInputStream fis = new FileInputStream(dir);
            ObjectInputStream ois = new ObjectInputStream(fis);
            details = (HashMap) ois.readObject();
            ois.close();
        }
        catch(FileNotFoundException e){
            try{
                details = new HashMap();
                details.put("Registered", false);
                
                FileOutputStream fos = new FileOutputStream(dir);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(details);
                oos.close();
                
            }
            catch(FileNotFoundException ex){
                
            } 
            catch (IOException ex) {
            }
    
        }
        catch(IOException e){
            
        }
        catch(ClassNotFoundException e){
            
        }


    }
    
    
    public void setPassword(String pass){
        
        details.put("Registered", true);
        
        details.put("Password", pass);
        
    }
    
    public void setPrivateKey(PrivateKey Private){
        
        details.put("Key", Private);
        
    }
    
    
    public void setId(String key){
        
        details.put("ID", key);
                
        
    }
    
    public void addMessage(Message m){
        
        Message[] savedMessages = getMessages();
        
        int i = savedMessages.length + 1;
        
        savedMessages[i] = m;
        
        details.put("Messages",savedMessages);
        this.saveDetails();
    }
    
    public void deleteMessage(Message m){
        
        Message[] savedMessages = getMessages();
        
        for(int i =0; i<= savedMessages.length; i++){
            
            if(m == savedMessages[i]){
                savedMessages[i] = null;
            }
            
        }
        details.put("Messages",savedMessages);
        this.saveDetails();
    }
    
    
    public Message[] getMessages(){
        
        Message[] savedMessages = (Message[])details.get("Messages");
        
        return savedMessages;
        
    }
    
    public PrivateKey getPrivateKey(){
        
        PrivateKey  privateKey = (PrivateKey)details.get("Key");
        
        return privateKey;
    }
    
    public String getID(){
        
        String id = (String)details.get("ID");
        
        return id;
        
    }
    
    public String getPassword(){
        
        String password = (String)details.get("Password");
        
        return password;
        
        
    }
    
    public boolean getRegistered(){
        
        boolean registered = (boolean)details.get("Registered");

        return registered;
        
    }
    
    public void saveDetails(){
        
         try{

                
                FileOutputStream fos = new FileOutputStream(dir);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(details);
                oos.close();
                
            }
            catch(FileNotFoundException ex){
                
            } 
            catch (IOException ex) {
            }
        
    }
    
    
    
    
    
    
    
}
