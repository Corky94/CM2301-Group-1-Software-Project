/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import Message.Message;
import java.io.*;
import java.sql.*;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.logging.*;

public class Sql {

    public Sql() {

    }

    public Connection connection(IDServer id) {
        Connection con = null;
        String url = id.getUrl();
        String user = id.getUser();
        String password = id.getPassword();
       
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            //throw new RuntimeException(ex);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public Connection connection(MessageServer id) {
        Connection con = null;
        String url = id.getUrl();
        String user = id.getUser();
        String password = id.getPassword();
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            return null;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public void register(String id, byte[] key) {
        Stack servers = this.getIDServers();
        Connection con = null;
        IDServer idServerObj;
        while (con == null) {
            try {
                idServerObj = (IDServer) servers.pop();
                con = this.connection(idServerObj);
                if (this.tableExist(con) == false) {
                    this.setUpIDTable(con);
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

    public void register(String id, byte[] key, String all) {

        Stack servers = this.getIDServers();
        Connection con = null;
        IDServer idServerObj = null;
        try {
            reg:
            while (true) {
                idServerObj = (IDServer) servers.pop();
                con = this.connection(idServerObj);
                if (con != null) {
                    if (this.tableExist(con) == false) {
                        this.setUpIDTable(con);
                    }
                    PreparedStatement pst = null;
                    pst = con.prepareStatement("INSERT INTO id(UserID, PublicKey) VALUES(?,?)");
                    pst.setString(1, id);
                    pst.setBytes(2, key);
                    pst.executeUpdate();
                    con.close();
                }
            }
        } catch (SQLException ex) {
            System.out.println("User: \n" + id + "\nAlready registered to server: \n" + idServerObj.getUrl() +"\n");
        } catch (EmptyStackException ex) {
            System.out.println("User: \n"+ id + "\nRegistered to all servers\n");
        }
    }

    public byte[] getKey(String id) {
        try {
            Stack servers = this.getIDServers();
            IDServer idServer = (IDServer) servers.pop();
            Connection con = this.connection(idServer);
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = con.prepareStatement("SELECT PublicKey FROM id WHERE UserID = (?)");
            pst.setString(1, id);
            rs = pst.executeQuery();
            rs.next();
            return rs.getBytes(1);
        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean idExist(String id) {
        try {
            Stack servers = this.getIDServers();
            IDServer idServer = (IDServer) servers.pop();
            Connection con = this.connection(idServer);
            PreparedStatement pst = null;
            ResultSet rs = null;

            pst = con.prepareStatement("SELECT PublicKey FROM id WHERE UserID = (?)");
            pst.setString(1, id);
            rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void sendMessage(byte[] sender, byte[] subject, byte[] contents, String reciever, SessionKey sk) {
        
        Stack servers = this.getMessageServers();
        Connection con = null;
        MessageServer messageServerObj;
        while (con == null) {
            try {
                messageServerObj = (MessageServer) servers.pop();
                con = this.connection(messageServerObj);
                if (this.tableExist(con) == false) {
                    this.setUpMessageTable(con);
                }
                PreparedStatement pst = null;
                pst = con.prepareStatement("INSERT INTO Messages(UserID, Sender, Subject, Message, SessionKey) VALUES(?,?,?,?,?)");
                pst.setString(1, reciever);
                pst.setBytes(2, sender);
                pst.setBytes(3, subject);
                pst.setBytes(4, contents);

                ByteArrayOutputStream b = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(b);

                oos.writeObject(sk);
                oos.flush();
                oos.close();
                b.close();

                byte[] data = b.toByteArray();

                pst.setObject(5, sk);

                pst.executeUpdate();
            } catch (SQLException | IOException ex) {
                Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void sendMessage(byte[] sender, byte[] subject, byte[] contents, String reciever, SessionKey sk, String all) {
       
        Stack servers = this.getMessageServers();
        Connection con = null;
        MessageServer messageServerObj = (MessageServer) servers.pop();
        try {
            while (true) {
                messageServerObj = (MessageServer) servers.pop();
                con = this.connection(messageServerObj);
                if (this.tableExist(con) == false) {
                    this.setUpMessageTable(con);
                  
                }
               
                PreparedStatement pst = null;
                pst = con.prepareStatement("INSERT INTO Messages(UserID, Sender, Subject, Message, SessionKey) VALUES(?,?,?,?,?)");
                pst.setString(1, reciever);
                pst.setBytes(2, sender);
                pst.setBytes(3, subject);
                pst.setBytes(4, contents);

                ByteArrayOutputStream b = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(b);

                oos.writeObject(sk);
                oos.flush();
                oos.close();
                b.close();

                byte[] data = b.toByteArray();

                pst.setObject(5, data);
                pst.executeUpdate();
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EmptyStackException ex) {
            System.out.println("All Servers sent to");
        }
    }

    public Message[] getMessage(String id) {
        Message newMessage = new Message();
        int arrayLength = 0;
        Stack servers = this.getMessageServers();
        Connection con = null;
        MessageServer messageServer;
        while (con == null) {
            try {
                
                messageServer = (MessageServer) servers.pop();
                con = this.connection(messageServer);
                if (this.tableExist(con) == false) {
                    this.setUpMessageTable(con);
                    System.out.println(tableExist(con));
                }
                PreparedStatement pst = null;
                pst = con.prepareStatement("SELECT Sender, Subject, Message, SessionKey FROM Messages WHERE UserID = (?)");
                pst.setString(1, id);
                ResultSet rs = pst.executeQuery();
                
                while (rs.next() == true) {
                    arrayLength++;
                }
                
                Message[] messages = new Message[arrayLength];
                rs = pst.executeQuery();
                int arrayLocation = 0;
                while (rs.next()) {
                    newMessage = new Message();
                    newMessage.setReceiver(id);
                    newMessage.setSender(rs.getBytes(1));
                    newMessage.setSubject(rs.getBytes(2));
                    Blob blob = rs.getBlob(3);
                    int blobLength = (int) blob.length();
                    byte[] blobAsBytes = blob.getBytes(1, blobLength);
                    blob.free();
                    newMessage.setMessage(blobAsBytes);

                    ByteArrayInputStream bis = new ByteArrayInputStream(rs.getBytes(4));
                    ObjectInputStream ois = new ObjectInputStream(bis);

                    SessionKey sk = (SessionKey) ois.readObject();

                    newMessage.setSessionKey(sk);
                    ois.close();
                    bis.close();

                    messages[arrayLocation] = newMessage;

                    arrayLocation++;

                }
                con.close();
               
                return messages;
            } catch (SQLException | IOException | ClassNotFoundException ex) {
                Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public void deleteMessage(String id, byte[] subject, byte[] message ){
        Stack servers = this.getMessageServers();
        Connection con;
        MessageServer messageServerObj;
        try {
            while (true) {
                messageServerObj = (MessageServer) servers.pop();
                con = this.connection(messageServerObj);
                if (this.tableExist(con) == false) {
                    this.setUpMessageTable(con);
                  
                }
               
                PreparedStatement pst = null;
                pst = con.prepareStatement("DELETE FROM Messages Where UserID = (?) AND  Subject = (?) AND Message =(?)");
                pst.setString(1, id);
                pst.setBytes(2, subject);
                pst.setBytes(3, message);
              
                
               
                pst.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EmptyStackException ex) {
            System.out.println("Message deleted off all server");
        }
    }

    public boolean tableExist(Connection con) {
        try {
            DatabaseMetaData meta = con.getMetaData();
            ResultSet res = meta.getTables(null, null, null, new String[]{"TABLE"});
            return res.next();
        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private void setUpIDTable(Connection con) {
        try {
            PreparedStatement pst = null;
            pst = con.prepareStatement("CREATE TABLE id "
                    + "(UserID VARCHAR(255), "
                    + "PublicKey VARBINARY(4096),"
                    + "PRIMARY KEY ( UserID)) ");
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setUpMessageTable(Connection con) {
        try {
            PreparedStatement pst = null;
            pst = con.prepareStatement("CREATE TABLE Messages "
                    + "(keyId INT AUTO_INCREMENT, "
                    + "UserID VARCHAR(255), "
                    + "Sender VARBINARY(4096),"
                    + "Subject VARBINARY(4096),"
                    + "Message LONGBLOB,"
                    + "SessionKey LONGBLOB,"
                    + "PRIMARY KEY (keyId)) ");
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Stack getIDServers() {
        return new IDServer().getDetails();
    }

    private Stack getMessageServers() {
        return new MessageServer().getDetails();
    }

    // tests 
    public boolean addIdServer(String url, String user, String password) {
        IDServer id = new IDServer();
        id.setUrl(url);
        id.setUser(user);
        id.setPassword(password);
        Connection con = connection(id);
        if (con == null) {
            return false;
        }
        if (tableExist(con) == false) {
            id.updateDetails(id);
            setUpMessageTable(con);
            ServerStorage ss = new ServerStorage();
            ss.updateOtherServers();
            return true;
        }
        System.out.println("Database Already Set up");
        return false;


    }

    public boolean addMessageServer(String url, String user, String password) {
        MessageServer mes = new MessageServer();
        mes.setUrl(url);
        mes.setUser(user);
        mes.setPassword(password);
        Connection con = connection(mes);
        if (con == null) {
            return false;
        }
        if (tableExist(con) == false) {
            mes.updateDetails(mes);
            setUpMessageTable(con);
            ServerStorage ss = new ServerStorage();
            ss.updateOtherServers();
            return true;
        }
        System.out.println("Database Already Set up");
        return false;

    }

}
