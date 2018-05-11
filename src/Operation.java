import java.util.ArrayList;
import java.util.Date;

public class Operation {
	private String nom;
	private ArrayList<Noeud> noeudsconsernes;
	private double quantite;
	private long timeStamp; 
	
	public Operation(String n, ArrayList<Noeud>nc, double q){
		this.nom = n;
		this.noeudsconsernes = nc;
		this.quantite =q;
		this.timeStamp = new Date().getTime();
	}

}
