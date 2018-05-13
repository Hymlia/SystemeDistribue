import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class NoeudParticipant extends UnicastRemoteObject implements Noeud {
	public NoeudBlock inscription;
	private double pointblockchain=0.0;
	private String machine;
	private String port;
	
	public static void main(String [] args)
	{
		if (args.length != 2)
		{
			System.out.println(
					"Usage : java Client <machine du Serveur>:<port du rmiregistry>") ;
			System.exit(0) ;
		}
		try
		{
			GestionOperations stub = (GestionOperations) Naming.lookup(
					"rmi://" + args[0] + ":" + args[1] + "/GestionOperations") ;
			// stub est désormais utilisable comme un objet local.
			stub.inscrireNP(this, inscription) ;
		}
		catch (NotBoundException re) { System.out.println(re) ; }
		catch (RemoteException re) { System.out.println(re) ; }
		catch (MalformedURLException e) { System.out.println(e) ; }
	}
		
	public NoeudParticipant(String nom, String p) throws RemoteException
{
		machine = nom;
		port = p;
	}
	
	public void envoiedemande() {
		ArrayList<Noeud> n = new ArrayList<Noeud>();
		n.add(this);
		Operation o = new Operation("Demande", n , 0);
		try
	    {
	      NoeudBlockInterface nb = (NoeudBlockInterface) Naming.lookup("rmi://" + inscription.getmachine() + ":" + inscription.getport() + "/NoeudBlockInterface") ;
	      pointblockchain = nb.demandepoints(this);
	      System.out.println("Le noeud participant : "  +toString() + "possède "+pointblockchain+" poitns.");
	    }
	    catch (NotBoundException re) { System.out.println(re) ; }
	    catch (RemoteException re) { System.out.println(re) ; }
	    catch (MalformedURLException e) { System.out.println(e) ; }
	}
	
	public void sinscrire(NoeudBlock nb){
		try
	    {
	      NoeudBlockInterface nbi = (NoeudBlockInterface) Naming.lookup("rmi://" + nb.getmachine() + ":" + nb.getport() + "/NoeudBlockInterface") ;
	      boolean accepte = nbi.inscrireNP(this);
	      if(accepte) {
	    	  inscription = nb;
	    	  System.out.println("L'inscription au noeud bloc : "+nb.toString()+" a reussie.");
	      }
	      else {
	    	  System.out.println("L'inscription au noeud bloc : "+nb.toString()+" a échoué.");
	      }
	    }
	    catch (NotBoundException re) { System.out.println(re) ; }
	    catch (RemoteException re) { System.out.println(re) ; }
	    catch (MalformedURLException e) { System.out.println(e) ; }
	}
	
	public void enmarche()throws InterruptedException{
		Thread t = new Thread(demandeconstantepoints());
		t.start();
		
	}
	
	public Runnable demandeconstantepoints()throws InterruptedException {
		while(true) {
			Thread.sleep(5000);
			envoiedemande();
		}
	}
	
	public String toString() {
		return this.getmachine()+" : " + this.getport();
	}

	public String getmachine() {
		return machine;
	}

	public String getport() {
		return port;
	}
	
	
}
