import java.util.ArrayList;


public class NoeudBlock {
	
	private int pointblockchain;
	private ArrayList<NoeudParticipant> noeudsinscrits;
	private ArrayList<Block> blockchain ;
	private ArrayList<Operation> attente;
	private ArrayList<NoeudBlock> voisins;
	
	
	public void retransmissiondemande() {
		//enovoeyr demande des np à tous les ovisins
	}
	
	public void transmissionblock() {
		//transmettre à voisin nouveau block créé
	}
	
	public Block getBlock(int profondeur) {
		return blockchain.get(profondeur);
	}
	
	public ArrayList<Block> getBlockChain() { //peut-etre à ne pas utiliser
		return blockchain;
	}
	
	public void creerblock() {
		
	}

}
