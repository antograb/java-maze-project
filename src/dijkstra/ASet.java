package dijkstra;

import java.util.HashSet;

import maze.Box;

public class ASet implements ASetInterface {

	private HashSet<VertexInterface> vertexHashSet = new HashSet<VertexInterface>() ;

	
	public ASet(HashSet<VertexInterface> vertexHashSet) {
		
		this.vertexHashSet = vertexHashSet;
		
	}

	public void add(VertexInterface vertex) {
		
		vertexHashSet.add((Box)vertex) ;
		
	}

	public boolean contains(VertexInterface vertex) {
		
		if (vertexHashSet.contains(vertex)) return true ;
		return false ;
		
	}

	public HashSet<VertexInterface> getVertexHashSet() {
		return vertexHashSet;
	}

}
