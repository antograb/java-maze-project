package dijkstra;
import java.util.ArrayList;

public interface GraphInterface {
	
	/** Returns the number of Vertexes **/
	public int getVertexNumber() ;
	
	/** Return the cost between two vertexes **/
	public int getCost(VertexInterface start, VertexInterface end) ;
	
	/** Returns an list of Vertexes **/
	public ArrayList<VertexInterface> getVertexes() ;
	
	/** Returns the neighbour list for a given box */
	public ArrayList<VertexInterface> generateNeighbours(VertexInterface vertex) ;

}
