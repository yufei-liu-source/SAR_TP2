
import java.io.*;
import java.net.*;

public class ServeurMultiThread{

	public static void main(String[] args){

		int port = 4020;
		ServerSocket se;
		
		int client = 0;

		try{
			se = new ServerSocket(port);
			while(true) {
				Socket soc = se.accept();
				//cree un nouveau thread pour le nouveau client
				client++;
				ThreadClient tc = new ThreadClient(soc,"Client No."+client);
				//lance l'execution du thread
				System.out.println("One client is connected");
			}
		}
		catch (IOException e){
			System.err.println("Erreur : " +e);
		}
	}
}

