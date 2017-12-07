package dijkstra;

/**
 * This is the Pi class as described in the INF101 course of Telecom Paristech.
 *
 */
public interface PiInterface {
	
	/** Returns the value of pi(v) */
	public int getPi(VertexInterface vertex) ;
	
	/** Sets the Pi value of an element */
	public void setPi(int i, VertexInterface vertex) ;
	
}
