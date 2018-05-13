import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GestionOperations extends Remote{
	
	public boolean inscrireNP(NoeudParticipant np, NoeudBlock nb) throws RemoteException;
	public void ajouteroperation(Operation o, NoeudBlock nb) throws RemoteException;
	public double demandepoints(NoeudParticipant np, NoeudBlock nb) throws RemoteException;

}
