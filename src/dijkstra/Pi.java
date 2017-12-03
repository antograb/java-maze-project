package dijkstra;

import java.util.Hashtable;

public class Pi implements PiInterface {
	
	private Hashtable<VertexInterface, Integer> hashPi = new Hashtable<VertexInterface, Integer>() ;
	
	public int getPi(VertexInterface vertex) {
		
		return hashPi.get(vertex) ;
		
	}

	public void setPi(int i, VertexInterface vertex) {
		
		hashPi.put(vertex, i) ;

	}

}
