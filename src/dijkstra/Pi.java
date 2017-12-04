package dijkstra;

import java.util.Hashtable;

public class Pi implements PiInterface {
	
	private Hashtable<VertexInterface, Integer> hashPi = new Hashtable<VertexInterface, Integer>() ;
	
	public Pi(Hashtable<VertexInterface, Integer> hashPi) {
		
		this.hashPi = hashPi ;
		
	}
	
	public int getPi(VertexInterface vertex) {
		
		return hashPi.get(vertex) ;
		
	}

	public void setPi(int i, VertexInterface vertex) {
		
		hashPi.put(vertex, i) ;

	}

}
