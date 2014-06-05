// This is a basic structure of the User class, there is still much to be done and al the methods to be implimented
package Console;

import Message.Message;
import Connection.*;
import Crypto.*;
import java.io.File;
import java.security.*;

public class User {

    private static SecureDetails s;
    public static boolean loggedIn;
    private static char[] pass;
    public static ClientSSL clissl;

    public User() {

    }

    public static void setPassword(char[] p) {
        pass = p;
    }

    public static char[] getPassword() {
        return pass;
    }

    public static void login(char[] password) {
        pass = password;
        clissl = new ClientSSL();
        new ClientUpdate();
    }

    public void logout() {
        loggedIn = false;
    }

    public static Message[] receiveEmails() {
        s = new SecureDetails();
        return ClientReceive.receive(s.getID());
    }

    public static void createMessage(String contents, String recipitent, String subject) {
        s = new SecureDetails();
        Message m = new Message();
        m.setReceiver(recipitent);
        PublicKey recipientPK = ClientReceive.getKey(m.getReceiver());
        String sender = s.getID();
        SessionKey sKey = KeyGen.generateSessionKey();
        m.setSubject(Encryption.encryptString(sKey, subject));
        m.setSender(Encryption.encryptString(sKey, sender));
        m.setMessage(Encryption.encryptString(sKey, contents));
        Encryption.encryptSessionKey(sKey, recipientPK);
        m.setSessionKey(sKey);
        ClientSend.send(m);
    }

    public static void deleteMessage(Message m) {
        m.setKey(null);
        m.setNeedingKey(false);
        m.setSender(null);

        ClientSend.send(m);
    }

    public static void main(String[] args) {
        User u = new User();
        File f = new File("user2.ser");
        if (f.exists()) {
            new GUI.GuiLogin();
        }
        else {
            GUI.GuiRegister.NewRegister();
        }
    }

}
