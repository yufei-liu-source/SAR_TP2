package IRC;

import java.io.*;
import java.net.*;
public class ThreadClientIRC extends Thread{

	ServeurIRC serv;
	Socket ssv;
	String nom;
	BufferedReader in;
	PrintWriter out;

	public ThreadClientIRC(Socket ssv, ServeurIRC serv) {
		this.ssv = ssv;
		this.serv = serv;
		start();
	}
	
	public void run(){
		try{
			in = new BufferedReader(
					new InputStreamReader(ssv.getInputStream()));
			out = new PrintWriter(ssv.getOutputStream(),true);
			String req = in.readLine();
			setNom(req);
			
			//envoi un premier message d'accueil
			String rep = "Bienvenue "+nom;
			out.println(rep); 

			//envoi la liste des clients connectes
			serv.EnvoyerListeClients(out);

			while(true){
				//attendre un phrase de reponse 
				req = in.readLine();
				
				if (req.equals("Bye")){ 
					//supprimer le client 
					serv.supprimerClient(this);
					break;
				}
				
				//repondre au client
				rep = req;
				

				//envoyer le message à tous les clients
				serv.EnvoyerATous(rep);
				
			}
			
			System.out.println("Fin Communication");
			
		}catch (IOException e){
			System.err.println("Erreur : " +e);
		}
		finally{
			try{
				ssv.close(); 
				in.close(); 
				out.close();
			}catch (IOException e){}
		}
	}

	public void Envoyer(String s){
		out.println(s);
	}

	public void setNom(String s){
		this.nom = s;
	}
	
	public String getNom(){
		return this.nom;
	}
}
