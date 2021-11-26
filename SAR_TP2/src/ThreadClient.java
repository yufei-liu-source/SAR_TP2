

import java.io.*;
import java.net.*;

public class ThreadClient extends Thread{

	private Socket soc;
	private String nom;
	private BufferedReader in;
	private PrintWriter out;
	
	public ThreadClient(Socket soc, String nom) {
		this.soc = soc;
		this.nom = nom;
		start();
	}
	
	public void run() {
		try {
			System.out.println("The thread is ready!");
			
			in = new BufferedReader(
					new InputStreamReader(soc.getInputStream()));
			out = new PrintWriter(soc.getOutputStream(), true);
			String rep = "Welcome" + nom;
			out.println(rep);
			
			while (true) {
				String req = in.readLine();
				if (req.equals("Bye"))break;
				System.out.println("Client said :" + req);
				
				try{
					sleep(1000);
				}catch (InterruptedException e) {
					System.err.println("Erreur" +e);
				}		
				rep = req;
				out.println(rep);	
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				soc.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
