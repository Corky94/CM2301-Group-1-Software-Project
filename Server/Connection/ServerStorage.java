/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marc
 */
public class ServerStorage implements java.io.Serializable  {
    
    private String dir = "";
    private final String idServerDetailsFileName = "Id Servers.ser";
    private final String messageServerDetailsFileName = "Message Servers.ser";
    private Stack idServerList;
    private Stack messageServerList;
    
    
    public ServerStorage(){
        try{
            
            FileInputStream fis = new FileInputStream(dir + idServerDetailsFileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            idServerList = (Stack) ois.readObject();
            ois.close();

        }
        catch(FileNotFoundException e){
            try{
                idServerList = new Stack();           
                FileOutputStream fos = new FileOutputStream(dir + idServerDetailsFileName);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(idServerList);
                oos.close();
            }
            catch(FileNotFoundException ex){
            } 
            catch (IOException ex) {
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerStorage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try{
            
            FileInputStream fis = new FileInputStream(dir + messageServerDetailsFileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            messageServerList = (Stack) ois.readObject();
            ois.close();

        }
        catch(FileNotFoundException e){
            try{
                messageServerList = new Stack();           
                FileOutputStream fos = new FileOutputStream(dir + messageServerDetailsFileName);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(messageServerList);
                oos.close();
            }
            catch(FileNotFoundException ex){
            } 
            catch (IOException ex) {
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerStorage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerStorage.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    public void updateIDSeverDetails(Stack details){
        try{

                FileOutputStream fos = new FileOutputStream(dir + idServerDetailsFileName);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(details);
                oos.close();
                
            }
            catch(FileNotFoundException ex){
                
            } 
            catch (IOException ex) {
            }
    }

    
    public Stack getIdServerList() {
        return idServerList;
    }
    
    public void updateMessageServerList(Stack details){
        
        try{

                    FileOutputStream fos = new FileOutputStream(dir + messageServerDetailsFileName);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(details);
                    oos.close();

                }
                catch(FileNotFoundException ex){

                } 
                catch (IOException ex) {
                }
        }
    
    public Stack getMessageServerList(){
        
        return messageServerList;
        
    }
    
}