/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Console;



/**
 *
 * @author Marc
 */
public class Java {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

//        KeyGen kg = new KeyGen();
//        
//        kg.convertToKey();
        
        

//        
//        Encryption e = new Encryption();
//        
//        Key p = e.convertToKey(k);
        
        User u = new User();
        SecureDetails s = new SecureDetails();
        
        System.out.println(s.getID());
        
            
        if (s.getRegistered() == true && u.loggedIn != true){
            u.login();

            u.recieveEmails();

            
            
            
            
        }
        else if(s.getRegistered() == false){
            
                Register r = new Register();
                u.login();
                u.createMessage();
            

            
            
        
    }
        // TODO code application logic here
    }
}
