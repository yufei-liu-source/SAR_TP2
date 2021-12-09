package IRC;
import java.io.*;
import java.net.*;
public class ServeurIRC{

	int port=…

			ServerSocket se=…

			Socket ssv=…

			int cl=…

			Vector V;

	public ServeurIRC(){

		try{

			V = …

					se =…

					while(true){

						ssv = …

								cl++;

						ThreadClientIRC th=…

						ajouterClient(th);

					}

		}catch (IOException e){

			System.err.println("Erreur : " +e);

		}

		finally{

			try{

				se.close();

			}

			catch (IOException e){}

		}

	}

	public void ajouterClient(ThreadClientIRC c){…}

	synchronized public void supprimerClient(ThreadClientIRC c){…}

	synchronized public void EnvoyerATous(String s){…}

	synchronized public void EnvoyerListeClients(PrintWriter out){…}

	public static void main(String[] args){

		new ServeurIRC();

	}
}
