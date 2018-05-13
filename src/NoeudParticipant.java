import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;


public class NoeudParticipant extends Noeud {
	public NoeudBlock inscription;
	private double pointblockchain=0.0;		
		
	public NoeudParticipant(String nom, String p) {
		super(nom,p);
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
	
	
}
