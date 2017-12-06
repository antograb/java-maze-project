package dijkstra;

import java.util.HashSet;

public interface ASetInterface {
	
	/** Add a vertex to the ASet */
	public void add(VertexInterface vertex) ;
	
	/** Check if a vertex belongs to ASet */
	public boolean contains(VertexInterface vertex) ;

	public HashSet<VertexInterface> getVertexHashSet();
	
 
}
