import java.rmi.RemoteException;
import java.util.ArrayList;


public class NoeudBlock extends Noeud implements NoeudBlockInterface {
	
	private ArrayList<NoeudParticipant> noeudsinscrits;
	private ArrayList<Double> meriteinscrits;
	private ArrayList<Block> blockchain ;
	private ArrayList<Operation> attente;
	private ArrayList<NoeudBlock> voisins;
	
	public NoeudBlock(String p, String n) {
		super(n,p);
		noeudsinscrits = new ArrayList<NoeudParticipant>();
		blockchain = new ArrayList<Block>() ;
		attente = new ArrayList<Operation>();
	}
	
	public void addVoisin(NoeudBlock nb) {
		voisins.add(nb);
	}
	
	public void envoieoperation() {
		//enovoyer demande des np à tous les voisins
	}
	
	public void transmissionblock(Block b) {
		//transmettre à voisin nouveau block créé
		
	}
	
	public Block getBlock(int profondeur) {
		return blockchain.get(profondeur);
	}
	
	public ArrayList<Block> getBlockChain() { //peut-etre à ne pas utiliser
		return blockchain;
	}
	
	public boolean inscrireNP(NoeudParticipant np)throws RemoteException {
		noeudsinscrits.add(np);
		int nbnp = noeudsinscrits.size();
		meriteinscrits.clear();
		for(int i =0 ; i<nbnp; i++) {
			meriteinscrits.add(1.0/nbnp);
		}
		ArrayList<Noeud> a = new ArrayList<Noeud>();
		a.add(np);
		a.add(this);
		Operation o = new Operation("Inscription",a,0);
		attente.add(o);
		return true;
	}
	
	public void ajouteroperation(Operation o)throws RemoteException {
			attente.add(o);
	}
	
	public void creerblock() {
		//attente
		
		//creation block
		ArrayList<Operation> op = new ArrayList<Operation>();
		for(int i =0; i<4; i++) {
			op.add(attente.get(i));
		}
		for(int i =0; i<4; i++) {
			attente.remove(0);
		}
		Block b = new Block(op,blockchain.get(blockchain.size()-1).calculateHash());
		blockchain.add(b);
		transmissionblock(b);
	}
	
	public double demandepoints(NoeudParticipant np)throws RemoteException {
		double res = 0.0;
		for(int i=0; i<blockchain.size(); i++) {
			for(int j=0; j<blockchain.get(i).data.size(); j++) {
				Operation opcourante = blockchain.get(i).data.get(j);
				if(opcourante.getNom().equals("Don")) {
					ArrayList<Noeud> noeuds = opcourante.getNoeudsConsernes();
					if(noeuds.get(0).getmachine()== np.getmachine() && noeuds.get(0).getport() == np.getport()) {
						res = res - opcourante.getQuantite();
					}
					else if(noeuds.get(1).getmachine()== np.getmachine() && noeuds.get(1).getport() == np.getport()) {
						res = res + opcourante.getQuantite();
					}
				}
			}
		}
		
		return res;
	}
	

}
