/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.*;
import java.util.*;

public class AdminInput implements Runnable {

    ServerStorage ss = new ServerStorage();

    public void run() {
        while (true) {
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine().toLowerCase();
            if (input.equals("add server")) {
                addServer(scan);
            } else {
                System.out.println("If you wish to add a server please enter the command: add server");
            }
        }
    }

    private void addServer(Scanner scan) {
        while (true) {
            System.out.println("What type of server do you wish to add (I)d or (M)essage");
            String input = scan.nextLine().toLowerCase();
            if (input.equals("i")) {
                System.out.println("Enter the url of the server:");
                String url = scan.nextLine();
                System.out.println("Enter the username for the server:");
                String user = scan.nextLine();
                System.out.println("Enter the password for the server:");
                Console console = System.console();

                String password = new String(console.readPassword());
                Sql s = new Sql();
                if (s.addIdServer(url, user, password) == true) {
                    System.out.println("Server Successfully added");
                    new Send(ss.getIdServerList());
                    break;
                } else {
                    System.out.println("The system could not connect to the server");
                }
            } else if (input.equals("m")) {
                System.out.println("Enter the url of the server:");
                String url = scan.nextLine();
                System.out.println("Enter the username for the server:");
                String user = scan.nextLine();
                System.out.println("Enter the password for the server:");
                Console console = System.console();

                String password = new String(console.readPassword());
                Sql s = new Sql();
                if (s.addMessageServer(url, user, password) == true) {
                    System.out.println("Server Successfully added");
                    new Send(ss.getMessageServerList());
                    break;
                } else {
                    System.out.println("The system could not connect to the server");
                }
            } else {
                System.out.println("Please enter a valid command");
            }
        }
    }
}
