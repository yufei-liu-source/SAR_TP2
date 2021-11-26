
import java.io.*;
import java.net.*;

public class ServeurMultiThread{

	public static void main(String[] args){

		int port = 4020;
		ServerSocket se;

		try{
			se = new ServerSocket(port);
			while(true) {
				Socket soc = se.accept();
				//cree un nouveau thread pour le nouveau client
				ThreadClient tc = new ThreadClient(soc,"toto");
				//lance l'execution du thread
				tc.start();
				System.out.println("One client is connected");
			}
		}
		catch (IOException e){
			System.err.println("Erreur : " +e);
		}
	}
}

