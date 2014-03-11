/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Server;


import Message.Message;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sql {
    
    public Sql(){
        
    }
    public Connection connectionID(){
        
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://ephesus.cs.cf.ac.uk:3306/c1217468";
        String user = "c1217468";
        String password = "qwerty";

        try {
            con = DriverManager.getConnection(url, user, password);
            
           
        } catch (SQLException ex) {


        } 
        return con;
    }  
     
    public Connection connectionMessages(){
        
        Connection con = null;
        Statement st = null;

        String url = "jdbc:mysql://ephesus.cs.cf.ac.uk:3306/c1217468";
        String user = "c1217468";
        String password = "qwerty";

        try {
            con = DriverManager.getConnection(url, user, password);
            
           
        } catch (SQLException ex) {

        } 
        return con;
    }
    public void register(String id, byte[] key) throws SQLException, IOException{
        System.out.println(3);
        Connection con = this.connectionID();
        PreparedStatement pst = null;
        
        pst = con.prepareStatement("INSERT INTO id(UserID, PublicKey) VALUES(?,?)");
        pst.setString(1, id);
        pst.setBytes(2, key);
        pst.executeUpdate();
        con.close();
        
        
    }
    
    public byte[] getKey(String id) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException{
        
        Connection con = this.connectionID();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        
        pst = con.prepareStatement("SELECT PublicKey FROM id WHERE UserID = (?)");
        pst.setString(1, id);
        rs = pst.executeQuery();
        rs.next();
        rs.getBytes(1);
        return rs.getBytes(1); 
        

        
    }
    
    public boolean idExist(String id) throws SQLException{
        
        Connection con = this.connectionID();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        pst = con.prepareStatement("SELECT PublicKey FROM id WHERE UserID = (?)");
        pst.setString(1, id);
        rs = pst.executeQuery();
        return rs.next();
    }
    
    public void sendMessage(byte[] sender, byte[] subject,  byte[] contents, String reciever){
        
        Connection con = this.connectionID();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement("INSERT INTO Messages(UserId, Sender, Subject, Message) VALUES(?,?,?,?)");
            pst.setString(1, reciever);
            pst.setBytes(2, sender); 
            pst.setBytes(3, subject);
            pst.setBytes(4, contents);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }


        
        
        
    }
        
    public Message[] getMessage(String id){
        
        Message newMessage;
        Connection con = this.connectionMessages();
        PreparedStatement pst = null;
        ResultSet rs = null;
        int arrayLength = 0;
        
        try {
            pst = con.prepareStatement("SELECT Sender, Subject, Message FROM Messages WHERE UserID = (?)");
            pst.setString(1, id);
            rs = pst.executeQuery();
            while (rs.next() == true){
                arrayLength++;
            }
            System.out.println(arrayLength);
            Message[] messages = new Message[arrayLength];
            rs = pst.executeQuery();
            int arrayLocation = 0;
            while (rs.next()){
                newMessage = new Message();
                newMessage.setReceiver(id);
                newMessage.setSender(rs.getBytes(1));
                newMessage.setSubject(rs.getBytes(2));
                Blob blob = rs.getBlob(3);
                int blobLength = (int) blob.length();  
                byte[] blobAsBytes = blob.getBytes(1, blobLength);
                blob.free();
                newMessage.setMessage(blobAsBytes);
                messages[arrayLocation] = newMessage;
                
                System.out.println(messages[arrayLocation]);
                arrayLocation++;
            
            }
            System.out.println(messages.length);
            return messages;
        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        
        return null;
        


    }
     public void delete(){
        try {
            Connection con = this.connectionID();
            PreparedStatement pst = null;
            System.out.println(con);
            
            pst = con.prepareStatement("DELETE FROM id");
            pst.executeUpdate();
            pst = con.prepareStatement("DELETE FROM Messages");
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}