import java.util.ArrayList;


public class NoeudBlock extends Noeud {
	
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
	
	public void inscrireNP(NoeudParticipant np) {
		noeudsinscrits.add(np);
		int nbnp = noeudsinscrits.size();
		meriteinscrits.clear();
		for(int i =0 ; i<nbnp; i++) {
			meriteinscrits.add(1.0/nbnp);
		}
	}
	
	public void ajouteroperation(Operation o) {
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

}
