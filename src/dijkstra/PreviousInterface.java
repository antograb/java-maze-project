package dijkstra;

import java.util.ArrayList;

/**
 * The <code>PreviousInterface</code> contains information about the father (as in the graph's theory) of some <code>VertexInterface</code>.
 * The father of a <code>VertexInterface</code> is the <code>VertexInterface</code> used to reach it.
 * Typically in Dijkstra, it's the <code>VertexInterface</code> used to reach <code>vertex</code> with the lowest cost.
 */
public interface PreviousInterface {
	
	/**
	 * Returns the father of a <code>VertexInterface</code>.
	 * @param vertex
	 * 	The <code>VertexInterface</code> from which we get the father (previous <code>VertexInterface</code>).
	 * @return The direct previous element used to reach <code>vertex</code>.
	 */
	public VertexInterface getPrevious(VertexInterface vertex) ;
	
	/**
	 * Sets the father of an element to another element.
	 * @param father
	 * 	The element that will become the father of the other element.
	 * @param son
	 * 	The element that will be assigned a new father.
	 */
	public void setPrevious(VertexInterface father, VertexInterface son) ;
	
	/**
	 * Shortest path from root to a vertex.
	 * @param vertex
	 * 	The vertex to which the shortest path will be computed.
	 * @return An <code>ArrayList</code> of the <code>VertexInterface</code> used to reach vertex with the lowest cost.
	 */
	public ArrayList<VertexInterface> getShortestPathTo(VertexInterface vertex) ;	

}
