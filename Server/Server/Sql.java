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
    
    public void sendMessage(byte[] sender, byte[] contents, String reciever){
        
        Connection con = this.connectionID();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement("INSERT INTO Messages(UserId, Sender, Message) VALUES(?,?,?)");
            pst.setBytes(2, sender);
            pst.setString(1, reciever);
            pst.setBytes(3, contents);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }


        
        
        
    }
        
    public Message getMessage(String id){
        
        Message newMessage = new Message();
        
        Connection con = this.connectionMessages();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        
        try {
            pst = con.prepareStatement("SELECT Sender, Message FROM Messages WHERE UserID = (?)");
            pst.setString(1, id);
            rs = pst.executeQuery();
            rs.next();
            newMessage.sender = rs.getBytes(1);
            newMessage.message = rs.getBytes(2);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        
        return newMessage;
        
    }

    public static void main(String[] args) {



    }
}