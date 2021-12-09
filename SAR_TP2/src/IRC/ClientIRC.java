package IRC;

import java.io.*;
import java.net.*;

public class ClientIRC extends Thread{

	static int port = 4020;
	static InetAddress hote= null;
	Socket sc = null ;
	BufferedReader in; 
	PrintWriter out;
	String nom;
	int compteur=0;

	public ClientIRC(String nom){
		this.nom = nom;
	}

	public void run(){

		try{

			sc = new Socket();
					
			in = new BufferedReader(
					new InputStreamReader(sc.getInputStream()));
			out = new PrintWriter(sc.getOutputStream(),true);

			String rep; 
			String req;

			//envoyer le pseudonyme au serveur
			req = nom;
			out.println(nom);
			System.out.println("Hello server, my pseudo : "+nom);
			
			//recevoir le message d'accueil du serveur
			rep = in.readLine();
			System.out.println("Server tell me : " + rep);
			
			for (int i = 0; i < 10; i++) {

				//recevoir un message du serveur
				rep = in.readLine();
				System.out.println("Server tell me : " + rep);
				
				//incrementer le nb d'echanges
				compteur++;

				//repondre au serveur
				req = nom + " count : " + compteur;
				out.println(req);

				//faire une pause de 3sec
				try {
					sleep(3000);
				}catch (InterruptedException e) {
					System.out.println("Erreur : " +e);
				}
				
			}

			//recevoir un message du serveur
			rep = in.readLine();
			System.out.println("Server tell me : " + rep);

			//faire une pause de 2sec
			try {
				sleep(2000);
			}catch (InterruptedException e) {
				System.out.println("Erreur : " +e);
			}

			//envoyer un message « Bye » au serveur
			req = "Bye";
			out.println(req);

		}catch(IOException e){
			System.err.println("Impossible cree socket du client : " +e);
		}
		finally{
			try{
				sc.close();
				in.close();
				out.close();
			}
			catch (IOException e){}
		}
	}
	
	public static void main (String[] args){
		String name;
		try{
			if (args.length>=2){
				hote = InetAddress.getByName(args[0]);
				port = Integer.parseInt(args[1]);
				name = args[2];
			}
			else{
				hote = InetAddress.getLocalHost();
				name = "test";
			}
		}catch(UnknownHostException e){
			System.err.println("Machine inconnue :" +e);
		}
		System.out.println("the name insert is : " + name);
		ClientIRC client1 = new ClientIRC(name);
	}
	
}