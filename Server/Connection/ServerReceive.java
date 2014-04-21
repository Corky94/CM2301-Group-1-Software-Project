package Connection;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;


public class ServerReceive {
	private static boolean debug = true;
        
	public static void main(String args[]) { 
                System.out.println(getLocalIpAddress());
		
		try {  
                   
                  SSLServerSocket querySocket = ServerSSL.main(12346);
                  SSLServerSocket updateSocket = ServerSSL.main(12121);
                  SSLServerSocket nodeSocket = ServerSSL.main(23232);
                    
                  while (true){
                      ClientHandler h;
                      AdminInput ai = new AdminInput();
                      Thread admin = new Thread(ai); 
                      admin.start();
                      h = new ClientHandler(querySocket.accept());
                      
                      
                      Thread a = new Thread(h);
                      
                      a.start();
                      admin.start();
                      System.out.println(a.getId());
                      
                      System.out.println(admin.getId());
                      a.interrupt();
                      


                      
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
               
           


