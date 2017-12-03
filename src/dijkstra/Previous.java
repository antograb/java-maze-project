package dijkstra;

import java.util.ArrayList;
import java.util.Hashtable;

public class Previous implements PreviousInterface {
	
	private Hashtable<VertexInterface, VertexInterface> hashPrevious = new Hashtable<VertexInterface, VertexInterface>() ;
	private ArrayList<VertexInterface> shortestPath = new ArrayList<VertexInterface>() ;

	public VertexInterface getPrevious(VertexInterface vertex) {
		
		return hashPrevious.get(vertex) ;
		
	}

	public void setPrevious(VertexInterface father, VertexInterface son) {
		
		hashPrevious.put(son, father) ;

	}

	public ArrayList<VertexInterface> getShortestPathTo(VertexInterface vertex) {
		
		VertexInterface father = getPrevious(vertex) ;
		shortestPath.add(father) ;
		
		if (getPrevious(father) == null) return shortestPath ;
		else {
			return getShortestPathTo(father) ;
		}
		
	}

}
