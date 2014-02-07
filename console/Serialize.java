package console;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Serialize {
	private static boolean debug = false;

	public static void main(String [] args) {

            String sender = "Person One";

	    Scanner in = new Scanner(System.in);
	    System.out.print("Recipient: ");
	    String receiver = in.nextLine();

	    System.out.print("Message: ");
	    String message = in.nextLine();

	    Serializer(sender, receiver, message);  
	}

    private static void Serializer(String sender, String receiver, String message) {
        Message m = new Message();
	    m.sender = sender;
	    m.receiver = receiver;
	    m.message = rot13(message); //rot13 is a temporary cipher. 

      	try {
         	//Creates a file with the recievers name and our choice of extension as the name.
         	//String fileName = receiver + ".extension";
         	String fileName = "message.extension";
         	FileOutputStream fileOut = new FileOutputStream(fileName);
         	ObjectOutputStream out = new ObjectOutputStream(fileOut);
         	out.writeObject(m);
         	out.close();
         	fileOut.close();
         	if(debug) System.out.printf("Success! \n");
         	send(fileName); //Send the message to the server. 
         	//delete(fileName); //deletes the message from the directory.
      	} catch(IOException e) {
          	e.printStackTrace();
          	System.out.printf("Error, could not serialize! \n");
      	}
   	}

   /*
   Deletes files after they have been sent.
   */
    private static void delete(String fileName) {
	    try {
	        File file = new File(fileName);
	        if(file.delete()) {
	            if(debug) System.out.println(file.getName() + " is deleted! \n");
	        } else {
	            if(debug) System.out.println("Delete operation is failed. \n");
	        }
	    } catch(Exception e) {
	        e.printStackTrace();
	 
	    }
    }


   private static void send(String fileName) {
        // put send code here.
      
        //executes command in console. Allows us to use c++ or other languages
        //by passing in parameters from the program. 
        String[] cmd = new String[] {"python", "Sender.py", fileName};

        try {
            InputStream is = Runtime.getRuntime().exec(cmd).getInputStream(); 
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader buff = new BufferedReader (isr);
            String line;
            while((line = buff.readLine()) != null)
            //prints output.
            System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String rot13(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if       (c >= 'a' && c <= 'm') c += 13;
            else if  (c >= 'A' && c <= 'M') c += 13;
            else if  (c >= 'n' && c <= 'z') c -= 13;
            else if  (c >= 'N' && c <= 'Z') c -= 13;
            sb.append(c);
        }
        return sb.toString();
    }

}