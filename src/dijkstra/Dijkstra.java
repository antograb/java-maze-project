package dijkstra;
import java.util.ArrayList;

public class Dijkstra {
	
	private PreviousInterface dijkstra(GraphInterface g, VertexInterface r, ASetInterface a, PiInterface pi, PreviousInterface previous) {
		
		a.add(r) ; ;
		VertexInterface pivot = r ;
		pi.setPi(0, r) ;
		
		ArrayList<VertexInterface> vertexList = g.getVertexes() ;
		
		for (VertexInterface vertex: vertexList) {
			if (vertex != r) {
				pi.setPi(Integer.MAX_VALUE, vertex);
			}
		}
		
		int n = g.getVertexNumber();
		for (int j = 0; j < n; j++) {
			for (VertexInterface vertex: vertexList) {
				if (! a.contains(vertex) && previous.getPrevious(vertex).compareTo(pivot)) {
					if (pi.getPi(pivot) + g.getCost(pivot, vertex) < pi.getPi(vertex)) {
						pi.setPi(pi.getPi(pivot) + g.getCost(pivot, vertex), vertex) ;
						previous.setPrevious(pivot, vertex);
					}
				}
			}
			
			int piMax = Integer.MAX_VALUE ;
			for (VertexInterface vertex: vertexList) {
				if (! a.contains(vertex) && pi.getPi(vertex) < piMax) {
					 pivot = vertex ;
					 piMax = pi.getPi(vertex) ;
				}
			}
			a.add(pivot);
		}
		
		return previous ;
		
	}
	
}
