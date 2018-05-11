import java.util.ArrayList;


public class Noeud {
	
	private String machine;
	private String port;
	
	public Noeud(String m , String p) {
		machine = m;
		port =p;
	}
	
	public String getmachine() {
		return machine;
	}
	
	public String getport() {
		return port;
	}
}
