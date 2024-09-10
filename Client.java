import java.io.BufferedReader;
import java.io.EOFException;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import java.io.IOException;

public class Client{
	private static final int PORT = 16000; //Enter the port number of server process here
	private static final String serverAddress = "localhost"; //Enter server ip address here
	private static Socket cs = null;
	private static Scanner sc = new Scanner(System.in);
	private static BufferedReader br = null;
	private static PrintWriter pw = null;
	private static String smsg = "";
	private static String cmsg = "";
	private static int choice = 0;
	public static void main(String[] args)throws IOException{
		connectToServer();
		chatWithServer();
	}
	public static void connectToServer()throws IOException{
		System.out.println("Connecting to server...");
		cs = new Socket(serverAddress, PORT);
		System.out.println("Connected to server whose ip address: "+cs.getInetAddress()+" and port no.: "+cs.getPort()+" successfully");
	}
	public static void chatWithServer()throws IOException{
		try{
			br = new BufferedReader(new InputStreamReader(cs.getInputStream()));
			pw = new PrintWriter(cs.getOutputStream(), true);

			while(true){
				System.out.print("Enter message: ");
				cmsg = sc.nextLine();
				pw.println(cmsg);
				System.out.println("Response sent");

				System.out.println("Waiting for response...");
				smsg = br.readLine();
				if(smsg != null) System.out.println("Message from client: "+smsg);
				
				System.out.print("Enter 0 to end connection (1 - continue): ");
				choice = sc.nextInt();
				sc.nextLine();
				if(choice == 0){
					pw.println("END"); //To end connection
					System.out.println("Disconnected from server successfully");
					break;
				}
			}
		}catch(SocketException | EOFException e){
			System.out.println("Server disconnected or connection lost");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			sc.close();
			if(cs != null) cs.close();
		}
	}
}