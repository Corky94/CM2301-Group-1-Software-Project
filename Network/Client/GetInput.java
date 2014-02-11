import java.util.Scanner;

public class GetInput {
	private static boolean debug = true;
	public static void main(String[] args) {
		int choice, check = -1;

		Scanner in = new Scanner(System.in);
		System.out.println("1. Compose a new message."); 
		System.out.println("2. Collect your messages.");
		System.out.print("Type 1 or 2 now:");

		check = in.nextInt();
		
		while(check < 1 | check > 2) {
			System.out.print("Please type 1 or 2: ");
			check = in.nextInt();
		}
		choice = check;
		if(debug) System.out.println(check);

		if (choice == 1) {
			send();
		} else {
			receive();
		}

	}

	private static boolean send() {
		if(ClientSend.send()) {
			return true; //success
		} 
		return false; //failure 
	}

	private static boolean receive() {
		if(ClientReceive.receive()) return true; //success
		return false; //failure
	}

}