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
    private final String FILE_NAME = "NodeList.ser";

    public NodeList() {

        try {

            FileInputStream fis = new FileInputStream(FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            nodeList = (Stack) ois.readObject();
            fis.close();
            ois.close();

        } catch (FileNotFoundException e) {
            try {
                nodeList = new Stack();
                FileOutputStream fos = new FileOutputStream(FILE_NAME);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(nodeList);
                oos.close();

            } catch (FileNotFoundException ex) {
            } catch (IOException ex) {
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ServerStorage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void saveNodeList() {
        try {

            FileOutputStream fos = new FileOutputStream(FILE_NAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(nodeList);
            fos.close();
            oos.close();

        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {
        }

    }

    public void addNode() {
        try {
            InetAddress IP = InetAddress.getLocalHost();
            String address = IP.getHostAddress();

            if (!(nodeList.contains(address))) {
                nodeList.add(address);
                System.out.println("address added to stack");
                saveNodeList();
            } else {
                System.out.println("address already in stack, no need to add");

            }

        } catch (UnknownHostException ex) {
            Logger.getLogger(NodeList.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private void addNode(String address) {
      
            if (!(nodeList.contains(address))) {
                nodeList.add(address);
                System.out.println("address added to stack");
                saveNodeList();
            } else {
                System.out.println("address already in stack, no need to add");

            }

       

    }

    public String getNode() {
        try {

            return (String) nodeList.pop();
        } catch (EmptyStackException ex) {
            return null;
        }

    }

    public void updateList(Stack s) {
        try{
        String adr = (String) s.pop();
        while(adr != null){
            
            this.addNode(adr);
            adr = (String) s.pop();
        }
        }catch(EmptyStackException ex){
            
        }
    }

    public Stack getList() {
        return nodeList;
    }

}
