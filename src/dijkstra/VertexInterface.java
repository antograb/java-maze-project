package dijkstra;

/**
 * This describes a vertex as seen in graphs (see INF101 course).
 *
 */
public interface VertexInterface {
	
	/**
	 * Compares a <code>VertexInterface</code> to another.
	 * @param vertex
	 * 	The vertex to which the equality will be tested.
	 * @return <code>true</code> is the two <code>VertexInterface</code> are the same, <code>false</code> if not.
	 */
	public boolean compareTo(VertexInterface vertex) ;
	
	/**
	 * @return The label of the element.
	 */
	public String getLabel() ;
}
