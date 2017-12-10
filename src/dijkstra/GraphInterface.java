package dijkstra;
import java.util.ArrayList;

/**
 * This is the Graph class as described in the INF101 course of Telecom Paristech.
 * Graph contains the representation of all the vertexes, with the information on which vertex(es) a vertex is linked to and at what cost.
 */
public interface GraphInterface {
	
	/**
	 * Gets the number of <code>VertexInterface</code> in the graph.
	 * @return An integer giving the number of <code>VertexInterface</code> in the graph.
	 */
	public int getVertexNumber() ;
	
	/**
	 * Gets the cost of the link (if it exists) between two vertexes.
	 * @param start
	 * 	The departure vertex.
	 * @param end
	 * 	The vertex from which we must see the cost of the link to <code>start</code>.
	 * @return An integer giving the cost of the link between the given <code>VertexInterface</code>.
	 */
	public int getCost(VertexInterface start, VertexInterface end) ;
	
	/**
	 * Returns all <code>VertexInterface</code> contained in the graph.
	 * @return An <code>ArrayList</code> of <code>VertexInterface</code>.
	 */
	public ArrayList<VertexInterface> getVertexes() ;
	
	/**
	 * Returns the neighbour list for a given <code>VertexInterface</code>.
	 * @param vertex
	 * 	The <code>VertexInterface</code> from which we will seek for neighbours.
	 * @return An <code>ArrayList</code> of <code>VertexInterface</code>.
	 */
	public ArrayList<VertexInterface> generateNeighbours(VertexInterface vertex) ;

}
