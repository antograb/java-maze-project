package dijkstra;

import java.util.ArrayList;

/**
 * This describes a vertex as seen in graphs (see INF101 course).
 *
 */
public interface VertexInterface {
	
	/**
	 * @return The label of the element.
	 */
	public String getLabel() ;

	/**
	 * Updates the attribute of the element.
	 * @return The neighbourList of the element.
	 */
	public ArrayList<VertexInterface> generateNeighbours();
}
