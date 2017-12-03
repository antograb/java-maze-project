package dijkstra;

import java.util.ArrayList;

public interface PreviousInterface {
	
	/** Returns the father of an element */
	public VertexInterface getPrevious(VertexInterface vertex) ;
	
	/** Sets the father of an element to another element */
	public void setPrevious(VertexInterface father, VertexInterface son) ;
	
	/**Shortest path from root to a vertex */
	public ArrayList<VertexInterface> getShortestPathTo(VertexInterface vertex) ;
	

}
