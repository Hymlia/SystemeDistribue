import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NoeudBlockInterface extends Remote{
	
	public boolean inscrireNP(NoeudParticipant np) throws RemoteException;
	public void ajouteroperation(Operation o) throws RemoteException;
	public double demandepoints(NoeudParticipant np) throws RemoteException;
}
