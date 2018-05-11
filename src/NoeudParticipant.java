import java.util.ArrayList;


public class NoeudParticipant extends Noeud {
	public NoeudBlock inscription;
	private int pointblockchain=0;		
		
	public NoeudParticipant(String nom, String p) {
		super(nom,p);
	}
	
	public void envoiedemande() {
		ArrayList<Noeud> n = new ArrayList<Noeud>();
		n.add(this);
		Operation o = new Operation("Demande", n , 0);
	}
	
	public void sinscrire(NoeudBlock nb){
		ArrayList<Noeud> n = new ArrayList<Noeud>();
		n.add(this);
		Operation o = new Operation("Inscription", n , 0);
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
