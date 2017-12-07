package dijkstra;

import java.util.HashSet;

/**
 * This is the ASet class as described in the INF101 course of Telecom Paristech.
 */
public interface ASetInterface {
	
	/** Add a vertex to the ASet */
	public void add(VertexInterface vertex) ;
	
	/** Check if a vertex belongs to ASet */
	public boolean contains(VertexInterface vertex) ;
	
	/** Returns the HashSet which represents the ASet. */
	public HashSet<VertexInterface> getVertexHashSet();
	
 
}
