/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

/**
 *
 * @author Marc
 */
public class MessageServer implements java.io.Serializable {
    
    private String url;
    private String user;
    private String password;

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
    
}
