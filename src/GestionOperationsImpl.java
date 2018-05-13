import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GestionOperationsImpl extends UnicastRemoteObject implements GestionOperations {

	protected GestionOperationsImpl() throws RemoteException {
		super();
	}

	public boolean inscrireNP(NoeudParticipant np, NoeudBlock nb) throws RemoteException {
		boolean res = nb.inscrireNP(np);
		return res;
	}

	public void ajouteroperation(Operation o, NoeudBlock nb) throws RemoteException {
		nb.ajouteroperation(o);
		
	}

	public double demandepoints(NoeudParticipant np, NoeudBlock nb) throws RemoteException {
		double res = nb.demandepoints(np);
		return 0;
	}
	
	

}
