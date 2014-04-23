package Connection;

import Crypto.*;
import Message.*;
import java.io.*;
import java.security.*;
import java.util.Scanner;
import java.util.logging.*;
import javax.net.ssl.SSLServerSocket;
import javax.swing.*;


public class Server {

    private static boolean debug = true;
    private static char[] password;
    private static String id;
    public static NodeList list = new NodeList();

    public static void main(String args[]) {
        
        if(KeyVault.checkIfKsExists() == false){
            initialSetup();
        }

        login();

        ServerSSL s = new ServerSSL();


        SSLServerSocket querySocket = s.main(12346, getPassword());
        SSLServerSocket updateSocket = s.main(12347, getPassword());
        SSLServerSocket nodeSocket = s.main(12348, getPassword());
        AdminInput ai = new AdminInput();
        Thread admin = new Thread(ai);
        admin.start();


        ClientAcceptor c = new ClientAcceptor(querySocket);
        Thread query = new Thread(c);
        query.start();


        UpdateAcceptor u = new UpdateAcceptor(updateSocket);
        Thread update = new Thread(u);
        update.start();
//                  
        NodeAcceptor n = new NodeAcceptor(nodeSocket);
        Thread node = new Thread(n);
        node.start();
        new Send(list.getList());



    }

    private static void login() {
        Console console = System.console();


        System.out.println("Please enter the login password:");
        char[] pass = console.readPassword();

        while (true) {
            if (KeyVault.checkPassword(pass) == false) {
                System.out.println("Incorrect Password\n"
                        + "Please try again");
                pass = console.readPassword();

            } else {

                try {
                    password = pass;
                    FileReader fr = new FileReader("id");
                    BufferedReader br = new BufferedReader(fr);
                    id = br.readLine();
                    fr.close();
                    br.close();
                    break;
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }

            }


        }


    }

    private static void initialSetup() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("The server has yet to be set up."
                + "\nPress enter to continue.");
        sc.nextLine();
        System.out.println("You random password is now being generated."
                + "\nRemember to note down the password as it will only be shown on initial setup"
                + "\nPress enter to continue.");
        sc.nextLine();
        char[] pass = RandomPasswordGen.generatePswd(12, 12, 2, 2, 0);
        String show = new String(pass);
        System.out.println("Your random password:"
                + "\n" + show);
        System.out.println("\n The rest of the setup and updating will now commence."
                + "\nPress enter to continue.");
        sc.nextLine();
        
        password = pass;
        keySetup();
        list.addNode();
        
        KeyVault.createKeyStore();

        System.out.println("The current list of operating servers is: \n "
                + list.getList());
        System.out.println("\n Once enter is pressed the console will then"
                + "be cleared to ensure security. \n"
                + "Please ensure you have remembered your password.");
        sc.nextLine();
        clearConsole();
    }

    private static void keySetup() {
        PrintWriter out = null;
        try {            
            KeyVault.createKeyStore();
            KeyVault.setRSAKeys();
            KeyVault.setAESKey();
            id = KeyGen.generateUserID();
            out = new PrintWriter("id");
            out.println(Server.getId());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //out.close();
        }

    }

    private final static void clearConsole() {
        for (int i = 0; i <= 10; i++) {

            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        }

    }

    /**
     * @return the password
     */
    public static char[] getPassword() {
        return password;
    }

    /**
     * @return the id
     */
    public static String getId() {
        return id;
    }
}
class ClientAcceptor implements Runnable {

    private static boolean debug = true;
    private SSLServerSocket socket;
    private Message message;

    ClientAcceptor(SSLServerSocket s) {
        this.socket = s;
    }

    public void run() {
        SSLServerSocket ss = socket;
        while (true) {
            socket = ss;
            ClientHandler c = new ClientHandler(socket);

            Thread t = new Thread(c);
            System.out.println(t.getId());
            t.start();
            socket = null;
            t = null;







        }
    }
}

class UpdateAcceptor implements Runnable {

    private static boolean debug = true;
    private SSLServerSocket socket;
    private Message message;

    UpdateAcceptor(SSLServerSocket s) {
        this.socket = s;
    }

    public void run() {
        SSLServerSocket ss = socket;
        while (true) {

            socket = ss;
            UpdateHandler u = new UpdateHandler(socket);
            Thread t = new Thread(u);
            t.start();
            System.out.println(t.getId());




        }
    }
}

class NodeAcceptor implements Runnable {

    private static boolean debug = true;
    private SSLServerSocket socket;
    private Message message;

    NodeAcceptor(SSLServerSocket s) {
        this.socket = s;
    }

    public void run() {
        SSLServerSocket ss = socket;
        while (true) {

            socket = ss;
            NodeHandler n = new NodeHandler(socket);
            Thread t = new Thread(n);
            t.start();
            System.out.println(t.getId());



        }
    }
}
