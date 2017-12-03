import java.util.ArrayList;

public interface GraphInterface {
	
	/** Returns the number of Vertexes */
	public int getVertexNumber() ;
	
	/** Return the cost between two vertexes */
	public int getCost(VertexInterface start, VertexInterface end) ;
	
	/** Returns an list of Vertexes */
	public ArrayList<VertexInterface> getVertexes() ;

}
