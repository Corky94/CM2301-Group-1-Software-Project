package Connection;

import Message.*;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;


public class ServerReceive {
	private static boolean debug = true;
        
	public static void main(String args[]) { 
                System.out.println(getLocalIpAddress());
		
		Message m = new Message();
		try {  
                   
                  SSLServerSocket ss = ServerSSL.main();
                    
                  while (true){
                      ClientHandler h;
                      h = new ClientHandler(ss.accept());
                      
                      Thread t = new Thread(h);
                      t.start();
                      System.out.println(t.getId());
                      


                      
                  }
                }catch (IOException ex) {
                    Logger.getLogger(ServerReceive.class.getName()).log(Level.SEVERE, null, ex);                        
                }
        }
        private static String getLocalIpAddress() {
   try {
       for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
           NetworkInterface intf = en.nextElement();
           for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
               InetAddress inetAddress = enumIpAddr.nextElement();
               if (!inetAddress.isLoopbackAddress()) {
                   if (inetAddress instanceof Inet4Address) {
                       return ((Inet4Address)inetAddress).getHostAddress().toString();
                   }
               }
           }
       }
   } catch (SocketException ex) {
   }
   return null;
}
}
               
           


