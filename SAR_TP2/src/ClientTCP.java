import java.io.*;
import java.net.*;

public class ClientTCP{

	public static void main (String[] args){
		int port=4020;
		InetAddress hote=null;
		Socket sc=null;
		BufferedReader in;
		PrintWriter out;
		try{
			if (args.length>=2){
				hote = InetAddress.getByName(args[0]);
				port = Integer.parseInt(args[1]);
			}else{
				hote = InetAddress.getLocalHost();
			}
		}
		catch(UnknownHostException e){		
			System.err.println("Machine inconnue :" +e);
		}
		try{
			sc = new Socket(hote, port);
			System.out.println("Client - connexion au serveur");
			in = new BufferedReader(
					new InputStreamReader(sc.getInputStream()));
			out = new PrintWriter(sc.getOutputStream(),true);
			String req;
			for(int i = 0; i < 10; i++) {
				String str = "Bonjour numero "+(i+1); 
				System.out.println("Client - send : "+str);
				out.println(str);

				String rep = in.readLine();
				System.out.println("Client - read : "+rep);
			}
			req = "Bye";
			out.println(req);
			
//			String str = "Hello, this is client!";
//			
//			System.out.println("Client send : " + str);
//			out.println(str);
//			
//			String rep = in.readLine();
//			System.out.println("Client read : " + rep);
		}
		catch(IOException e){
			System.err.println("Impossible de creer la socket du client : " +e);
		}
		finally{
			try{	
				sc.close();
			}
			catch (IOException e){}
		}
	}

}
