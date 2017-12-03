package dijkstra;

import java.util.HashSet;

import maze.Box;

public class ASet implements ASetInterface {

	private HashSet<Box> vertexHashSet = new HashSet<Box>() ;

	
	public ASet(HashSet<Box> vertexHashSet) {
		
		this.vertexHashSet = vertexHashSet;
		
	}

	public void add(VertexInterface vertex) {
		
		vertexHashSet.add((Box)vertex) ;
		
	}

	public boolean contains(VertexInterface vertex) {
		
		for (VertexInterface v: vertexHashSet) {			
			if (v == vertex) return true ;
		}
		return false ;
		
	}

}
