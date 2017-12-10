package dijkstra;

/**
 * This is the Pi class as described in the INF101 course of Telecom Paristech.
 * It contains the path's cost to reach vertexes (typically, from the root of the graph).
 */
public interface PiInterface {
	
	/**
	 * Gets the cost to reach the given <code>VertexInterface</code>
	 * @param vertex
	 * 	The <code>VertexInterface</code> from which we get the Pi value (cost) to reach it.
	 * @return An <code>integer</code> giving the Pi value (cost) the reach the <code>VertexInterface</code>.
	 *
	 */
	public int getPi(VertexInterface vertex) ;
	
	/**
	 * Sets the Pi value of an element
	 * @param i
	 * 	The Pi value (cost) to set.
	 * @param vertex
	 * 	The <code>VertexInterface</code> on which the Pi value (cost) is to be set.
	 */
	public void setPi(int i, VertexInterface vertex) ;
	
}
