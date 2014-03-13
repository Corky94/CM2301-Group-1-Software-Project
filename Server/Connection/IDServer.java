/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author Marc
 */
public class IDServer implements java.io.Serializable {
    
    private String url;
    private String user;
    private String password;
    private ServerStorage ss = new ServerStorage();
    private Stack details;

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = "jdbc:mysql://" + url + 
                "?verifyServerCertificate=false"+
		"&useSSL=true"+
		"&requireSSL=true" ;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the details
     */
    public Stack getDetails() {
        return ss.getIdServerList();
    }

    /**
     * @param details the details to set
     */
    public void updateDetails(IDServer server) {
        details = ss.getIdServerList();
        details.add(details);
        ss.updateIDSeverDetails(details);
        
        
    }

    /**
     * @return the status
     */
    
    
    
    
}
