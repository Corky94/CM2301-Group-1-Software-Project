/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.*;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marc
 */
public class ServerStorage implements java.io.Serializable  {
    
    private transient String dir = "";
    private transient final String idServerDetailsFileName = "Id Servers.ser";
    private transient final String messageServerDetailsFileName = "Message Servers.ser";
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
                fos.close();
                //new Send(details);
                
            }
            catch(FileNotFoundException ex){
                
            } 
            catch (IOException ex) {
            }
    }

    
    public Stack getIdServerList() {
        return idServerList;
    }

    
    public void updateMessageServerDetails(Stack details){
        
        try{

                    FileOutputStream fos = new FileOutputStream(dir + messageServerDetailsFileName);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(details);
                    oos.close();
                    fos.close();
                   // new Send(details);

                }
                catch(FileNotFoundException ex){

                } 
                catch (IOException ex) {
                }
        }
    
    public void updateOtherServers(){
        new Send(idServerList);
        new Send(messageServerList);
    }
    public Stack getMessageServerList(){
        
        return messageServerList;
        
    }
    
}
