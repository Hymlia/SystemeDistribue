import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NoeudBlockInterface extends Remote{
	
	public void inscrireNP(NoeudParticipant np) throws RemoteException;
	public void ajouteroperation(Operation o) throws RemoteException;
}
