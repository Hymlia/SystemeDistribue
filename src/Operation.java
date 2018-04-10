import java.util.ArrayList;


public class Operation {
	private String nom;
	private ArrayList<Noeud> noeudsconsernes;
	private double quantite;
	
	public Operation(String n, ArrayList<Noeud>nc, double q){
		this.nom = n;
		this.noeudsconsernes = nc;
		this.quantite =q;
	}
}
