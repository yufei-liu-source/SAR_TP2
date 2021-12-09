package IRC;
import java.io.*;
import java.net.*;
import java.util.Vector;
public class ServeurIRC{

	int port = 4020;
	ServerSocket se;
	Socket ssv= null;
	int cl=0;
	Vector<ThreadClientIRC> V;

	public ServeurIRC(){

		try{
			V = new Vector<ThreadClientIRC>();
			se = new ServerSocket(port);
			System.out.println("Le serveur a l'ecoute");

			while(true){
				ssv = se.accept();
				cl++;
				ThreadClientIRC th= new ThreadClientIRC(ssv, this);
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

	public void ajouterClient(ThreadClientIRC c){
		V.addElement(c);
		System.out.println("Nouveau Client : " + c.nom);
	}

	synchronized public void supprimerClient(ThreadClientIRC c){
		V.removeElement(c);
		cl--;
		System.out.println("Au revoir : " + c.nom);
	}

	synchronized public void EnvoyerATous(String s){
		for (int i = 0; i<V.size();i++) {
			ThreadClientIRC c  = (ThreadClientIRC) V.elementAt(i);
			c.Envoyer(s);
		}
	}

	synchronized public void EnvoyerListeClients(PrintWriter out){
		String list = "Voici la liste de clients connectee :";
		for (int i= 0; i<V.size();i++) {
			ThreadClientIRC c  = (ThreadClientIRC) V.elementAt(i);
			list = list + c.getNom()+"\n";
		}
		out.println(list);
	}

	public static void main(String[] args){
		new ServeurIRC();
	}
}
