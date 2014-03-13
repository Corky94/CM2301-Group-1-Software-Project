/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Connection;


import Message.Message;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.Stack;
import java.util.logging.*;

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
    
    
    
   
    
    public Connection connection(IDServer id){
        
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String url = id.getUrl();
        String user = id.getUser();
        String password = id.getPassword();

        try {
            con = DriverManager.getConnection(url, user, password);
            
           
        } catch (SQLException ex) {

            return null;
        } 
        return con;
    }  
    public Connection connection(MessageServer id){
        
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String url = id.getUrl();
        String user = id.getUser();
        String password = id.getPassword();

        try {
            con = DriverManager.getConnection(url, user, password);
            
           
        } catch (SQLException ex) {

            return null;
        } 
        return con;
    }  
    
    public void register(String id, byte[] key, String all) {
        
        System.out.println("reg");
        Stack servers = this.getIDServers();
        Connection con = null;
        IDServer idServerObj;
        
        if (all != null){
           while(con ==null){
               try {
                   idServerObj = (IDServer) servers.pop();        
                   con = this.connection(idServerObj);
                   
                   
                   if (this.tableExist(con) == false){
                       this.setUpIDTable(con);
                       System.out.println(tableExist(con));
                   }
               
                   PreparedStatement pst = null;
               
                   pst = con.prepareStatement("INSERT INTO id(UserID, PublicKey) VALUES(?,?)");
                   pst.setString(1, id);
                   pst.setBytes(2, key);
                   pst.executeUpdate();
                   con.close();
               } catch (SQLException ex) {
                   Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
               }
            
        }
           
           
        }
        else{
            try {
                outerLoop:
                while(true){
                    idServerObj = (IDServer) servers.pop();        
                    con = this.connection(idServerObj);
                    if (con != null) {
                        if (this.tableExist(con) == false){
                            this.setUpIDTable(con);
                            System.out.println(tableExist(con));
                    }

                    PreparedStatement pst = null;

                    pst = con.prepareStatement("INSERT INTO id(UserID, PublicKey) VALUES(?,?)");
                    pst.setString(1, id);
                    pst.setBytes(2, key);
                    pst.executeUpdate();
                    con.close();
                    break outerLoop;
                    }
                }
                    
         }          
         catch (SQLException ex) {
                Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
                
        }
        }

        
        
        
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
    
    public boolean tableExist(Connection con){
        
        try {
            
            DatabaseMetaData meta = con.getMetaData();
            ResultSet res = meta.getTables(null, null, null, new String[] {"TABLE"});
            return res.next();
            
        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
        
    }
    
    public void setUpIDTable(Connection con) {
        try {
            PreparedStatement pst = null;    
            pst = con.prepareStatement("CREATE TABLE id " +
                    "(UserID VARCHAR(255), " + 
                    "PublicKey VARBINARY(4096),"
                    + "PRIMARY KEY ( UserID)) ");
            pst.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void setUpMessageTable(Connection con) {
        try {
            PreparedStatement pst = null;    
            pst = con.prepareStatement("CREATE TABLE Messages " +
                    "(keyId INT AUTO_INCREMENT, " + 
                    "UserID VARCHAR(255), "+ 
                    "Sender VARBINARY(4096)," +
                    " Subject VARBINARY(4096)," +
                    " Message LONGBLOB,"
                    + "PRIMARY KEY ( keyId)) ");
            pst.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
//     public void delete(){
//        try {
//            Connection con = this.connectionID();
//            PreparedStatement pst = null;
//            System.out.println(con);
//            
//            pst = con.prepareStatement("DELETE FROM id");
//            pst.executeUpdate();
////            pst = con.prepareStatement("DELETE FROM Messages");
////            pst.executeUpdate();
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        
//    }
     
     public Stack getIDServers(){
         
         return new IDServer().getDetails();
         
     }
     
     
     
     
     
     
     
     // tests 
     
     
     
     
     public void addIdServer(String url, String user, String password){
         IDServer id = new IDServer();
         
         id.setUrl(url);
         id.setUser(user);
         id.setPassword(password);
         
         id.updateDetails(id);
         
         Connection con = connection(id);
         setUpIDTable(con);
         deleteIDTest(con);
         
         
     }
     
     public void addMessageServer(String url, String user, String password){
         MessageServer mes = new MessageServer();
         
         mes.setUrl(url);
         mes.setUser(user);
         mes.setPassword(password);
         
         mes.updateDetails(mes);
         
         Connection con = connection(mes);

         setUpMessageTable(con);
         deleteMessageTest(con);
         
     }
     
     public void deleteIDTest(Connection con ){
        try {
         
            PreparedStatement pst = null;
            System.out.println(con);
            
            pst = con.prepareStatement("DROP TABLE id");
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     }
     public void deleteMessageTest(Connection con ){
        try {
         
            PreparedStatement pst = null;
            System.out.println(con);
            
            pst = con.prepareStatement("DROP TABLE Messages");
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     }
     
     
     
}