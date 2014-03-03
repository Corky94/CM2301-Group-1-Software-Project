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
    private LinkedList<IDServer> idServerList;
    
    
    public ServerStorage(){
        try{
            
            FileInputStream fis = new FileInputStream(dir + idServerDetailsFileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            idServerList = (LinkedList<IDServer>) ois.readObject();
            ois.close();

        }
        catch(FileNotFoundException e){
            try{
                idServerList = new LinkedList<IDServer>();               
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
        
//        try{
//            
//            FileInputStream fis = new FileInputStream(dir + messageServerDetailsFileName);
//            ObjectInputStream ois = new ObjectInputStream(fis);
//            LinkedList<IDServer> idServerList = (LinkedList<IDServer>) ois.readObject();
//            ois.close();
//
//        }
//        catch(FileNotFoundException e){
//            try{
//                LinkedList<IDServer> idServerList = new LinkedList<IDServer>();               
//                FileOutputStream fos = new FileOutputStream(dir + idServerDetailsFileName);
//                ObjectOutputStream oos = new ObjectOutputStream(fos);
//                oos.writeObject(idServerList);
//                oos.close();
//            }
//            catch(FileNotFoundException ex){
//            } 
//            catch (IOException ex) {
//            }
//        }
//        catch(IOException e){
//        }
//        catch(ClassNotFoundException e){
//        }


    }
    public void updateIDSeverDetails(LinkedList<IDServer> details){
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

    /**
     * @return the idServerList
     */
    
    
    public LinkedList<IDServer> getIdServerList() {
        return idServerList;
    }
    

    
}
