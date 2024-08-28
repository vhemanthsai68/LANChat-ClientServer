import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server{
	public static void main(String[] args)throws IOException{
		ServerSocket ss=null;
		Socket cs=null;
		Scanner sc = new Scanner(System.in);
		try{
			ss = new ServerSocket(16000);
			while(true){
				System.out.println("Server is running and listening at TCP port no. 16000...");
				System.out.println("Waiting for a client...");
				cs = ss.accept();
				System.out.println("I got a client");
				BufferedReader br = new BufferedReader(new InputStreamReader(cs.getInputStream()));
				PrintWriter pw = new PrintWriter(cs.getOutputStream(), true);
				String msg = br.readLine();
				if(msg != null) System.out.println("Message from client: "+msg);
				System.out.print("Enter message: ");
				String smsg = sc.nextLine();
				pw.println(smsg);
				System.out.println("Response sent");
				int i=0;
				if(cs == null){
					System.out.println("Client got disconnected");	
					System.out.println("Server is going to shutdown...");	
					ss.close();
				}
				System.out.print("Enter 1 to stop server(0 - continue): ");
				i = sc.nextInt();
				if(i == 1) ss.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}