package dijkstra;

/**
 * This describes a vertex as seen in graphs (see INF101 course).
 *
 */
public interface VertexInterface {
	
	/** Compare a Vertex to another. */
	public boolean compareTo(VertexInterface vertex) ;
	
	/** Returns the Label of the vertex. */
	public String getLabel() ;
}
