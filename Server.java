import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import java.io.IOException;

public class Server{
	private static final int PORT = 16000; //Port number
	private static ServerSocket ss = null;
	private static Socket cs = null;
	private static Scanner sc = null;
	private static BufferedReader br = null;
	private static PrintWriter pw = null;
	private static String cmsg = "";
	private static String smsg = "";
	private static int choice = 0;
	public static void main(String[] args)throws IOException{
		initScanner();
		initServer();
		startServer();
		handleClient();
	}
	public static void initScanner(){
		sc = new Scanner(System.in);
	}
	public static void initServer()throws IOException{
		ss = new ServerSocket(PORT);
	}
	public static void startServer()throws IOException{
		System.out.println("Server is running at: "+ss.getLocalSocketAddress()+" and listening at TCP port no. : "+ss.getLocalPort()+"...");
		System.out.println("Waiting for a client...");
		cs = ss.accept();
		System.out.println("I got a client with IPv4 address: "+cs.getRemoteSocketAddress()+" and port no. : "+cs.getPort());
	}
	public static void handleClient()throws IOException{
		try{
			br = new BufferedReader(new InputStreamReader(cs.getInputStream()));
			pw = new PrintWriter(cs.getOutputStream(), true);

			while(true){
				System.out.println("Waiting for response...");
				cmsg = br.readLine();
				if(cmsg.equals("END")){
					System.out.println("Client got disconnected");	
					break;
				}
				if(cmsg != null) System.out.println("Message from client: "+cmsg);
				System.out.print("Enter message: ");
				smsg = sc.nextLine();
				pw.println(smsg);
				System.out.println("Response sent");
				System.out.print("Enter 0 to stop server (1 - continue): ");
				choice = sc.nextInt();
				sc.nextLine();
				if(choice == 0){
					System.out.println("Server is going to shutdown...");
					System.out.println("Server is down");
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			sc.close();
			if(cs != null) cs.close();
			if(ss != null) ss.close();
		}
	}
}