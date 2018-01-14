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
	public String getLabel();

	/**
	 * Updates the attribute of the element.
	 * @return The neighbourList of the element.
	 */
	public ArrayList<VertexInterface> generateNeighbours();

	/** Returns the neighbourList of the element. */
	public ArrayList<VertexInterface> getNeighbourList();

	/**
	 * Adds all vertexes not in the ASet and candidate for an eventual path.
	 * @param vertex
	 */
	public void addCandidate(VertexInterface vertex);

	/**
	 * Returns the list of the candidates for an eventual path.
	 * @return
	 */
	public ArrayList<VertexInterface> getCandidates();
}
