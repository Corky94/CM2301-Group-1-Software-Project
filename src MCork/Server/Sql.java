/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Server;

import Connection.*;
import Encryption.*;
import com.sun.corba.se.impl.util.Version;
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

        String url = "jdbc:mysql://localhost:3306/Id";
        String user = "root";
        String password = "pass";

        try {
            con = DriverManager.getConnection(url, user, password);
            
           
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Version.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } 
        return con;
    }  
     
    public Connection connectionMessages(){
        
        Connection con = null;
        Statement st = null;

        String url = "jdbc:mysql://localhost:3306/Messages";
        String user = "root";
        String password = "pass";

        try {
            con = DriverManager.getConnection(url, user, password);
            
           
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Version.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } 
        return con;
    }
    public void register(String id, byte[] key) throws SQLException, IOException{
        
        Connection con = this.connectionID();
        PreparedStatement pst = null;
        
        pst = con.prepareStatement("INSERT INTO id(User, public) VALUES(?,?)");
        pst.setString(1, id);
        pst.setBytes(2, key);
        pst.executeUpdate();
        
        
    }
    
    public PublicKey getKey(String id) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException{
        
        Connection con = this.connectionID();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        Encryption e = new Encryption();
        
        pst = con.prepareStatement("SELECT public FROM id WHERE User = (?)");
        pst.setString(1, id);
        rs = pst.executeQuery();
        rs.next();
        rs.getBytes(1);
        return e.convertToKey(rs.getBytes(1)); 
        

        
    }
    
    public boolean idExist(String id) throws SQLException{
        
        Connection con = this.connectionID();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        pst = con.prepareStatement("SELECT public FROM id WHERE User = (?)");
        pst.setString(1, id);
        rs = pst.executeQuery();
        
        return rs.next();
    }
    
    public void sendMessage(byte[] sender, byte[] contents, String reciever){
        
        Connection con = this.connectionMessages();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement("INSERT INTO Storage(UserID, Sender, Contents) VALUES(?,?,?)");
            pst.setBytes(2, sender);
            pst.setString(1, reciever);
            pst.setBytes(3, contents);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("YAY");

        
        
        
    }
        
    public Message getMessage(String id){
        
        Message newMessage = new Message();
        
        Connection con = this.connectionMessages();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        
        try {
            pst = con.prepareStatement("SELECT Sender FROM Storage WHERE UserID = (?)");
            pst.setString(1, id);
            rs = pst.executeQuery();
            rs.next();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("YAY");
        
        
        
        return newMessage;
        
    }

    public static void main(String[] args) {



    }
}