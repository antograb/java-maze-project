package dijkstra;

import java.util.HashSet;

/**
 * This is the ASet class as described in the INF101 course of Telecom Paristech.
 * The ASet contains vertexes for which the shortest path has already been found (vertexes already examined by the dijkstra algorithm).
 */
public interface ASetInterface {
	
	/**
	 * Adds a vertex to the set.
	 * @param vertex
	 * 	The element to be added to the set.
	 */
	public void add(VertexInterface vertex) ;
	
	/**
	 * Checks if a vertex belongs to the set.
	 * @param vertex
	 * 	The element we must check if it belongs to the set.
	 * @return <code>true</code> if the element is in the set, <code>false</code> if not.
	 */
	public boolean contains(VertexInterface vertex) ;
	
	/**
	 * Returns the HashSet which represents the ASet.
	 * @return A <code>HashSet</code> containing the same elements as in the set.
	 */
	public HashSet<VertexInterface> getVertexHashSet();
	
 
}
