/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Message;

import Connection.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Stack;
import java.util.logging.*;

/**
 *
 * @author Marc
 */
public class NodeList {
    
    private Stack nodeList;
    private final String FILE_NAME ="NodeList.ser"; 
    
    public NodeList(){
        try{
            
            FileInputStream fis = new FileInputStream(FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            nodeList = (Stack) ois.readObject();
            ois.close();

        }
        catch(FileNotFoundException e){
            try{
                nodeList = new Stack();           
                FileOutputStream fos = new FileOutputStream(FILE_NAME);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(nodeList);
                oos.close();
 
            }
            catch(FileNotFoundException ex){
            } 
            catch (IOException ex) {
            }
        } catch (IOException ex) {
            
        } catch (ClassNotFoundException ex) {
           
        }
        shuffle();
        
    }
    private void shuffle(){
        Collections.shuffle(nodeList);
    }
    
    private void saveNodeList(){
        try{

                FileOutputStream fos = new FileOutputStream(FILE_NAME);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(nodeList);
                oos.close();
                
            }
            catch(FileNotFoundException ex){
                
            } 
            catch (IOException ex) {
            }
    }

    public void updateStack(Stack s){
        this.nodeList = s;
        saveNodeList();
    }
    
    public String getNode(){
        
        return (String)nodeList.pop();
        
    
    }
}   
    

