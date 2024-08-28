import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client{
	public static void main(String[] args)throws IOException{
		String serverAddress = "localhost";
		int port = 16000;
		PrintWriter pw = null;
		BufferedReader br = null;
		Socket cs=null;
		Scanner sc = new Scanner(System.in);
		try{
			while(true){
				System.out.print("Enter message: ");
				String msg = sc.nextLine();
				cs = new Socket(serverAddress, port);
				System.out.println("Connected to server");
				br = new BufferedReader(new InputStreamReader(cs.getInputStream()));
				pw = new PrintWriter(cs.getOutputStream(), true);
				pw.println(msg);
				String smsg = br.readLine();
				if(smsg != null) System.out.println("Message from server: "+smsg);
				System.out.print("Enter 1 to end connection(0 - continue): ");
				int i = sc.nextInt();
				if(i == 1){
					cs = null;
					System.out.println("Disconnected from server");
					return;
				}	
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}