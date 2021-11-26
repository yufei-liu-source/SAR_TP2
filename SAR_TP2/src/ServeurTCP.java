import java.io.*;
import java.net.*;

public class ServeurTCP{

	public static void main(String[] args){

		int port = 4020;
		ServerSocket se;
		Socket ssv = null;
		PrintWriter out;
		BufferedReader in;
		try{
			se = new ServerSocket(port);
			ssv = se.accept();
			in = new BufferedReader(
					new InputStreamReader(ssv.getInputStream()));
			out = new PrintWriter(ssv.getOutputStream(),true);
			
			for(int i = 0; i < 10; i++) {
				String str = in.readLine();
				System.out.println("Server read : "+str);
	
				str = "Bienvenue numero "+(i+1);
				System.out.println("Server send : "+str);
				out.println(str); 
			}
			
//			String str = in.readLine();
//			System.out.println("Server read : " + str);
//			
//			String s2 = "Bienvenue!";
//			System.out.println("Server send : " + str);
//			out.println(s2);
		}
		catch (IOException e){
			System.err.println("Erreur : " +e);
		}
		finally{
			try{
				ssv.close();
				System.out.println("Server - close the socket");
			}
			catch (IOException e){}
		}
	}
}

