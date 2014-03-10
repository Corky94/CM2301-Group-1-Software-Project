/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.*;
import java.util.*;

/**
 *
 * @author Marc
 */
public class AdminInput implements Runnable{
    
    public void run(){
        Scanner scan = new Scanner(System.in);
        System.out.println("This is a test of multithreading");
        String input = scan.nextLine();
    }
    
}
