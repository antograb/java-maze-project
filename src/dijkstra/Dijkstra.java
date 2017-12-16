package dijkstra;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

public class Dijkstra {

	private static PreviousInterface dijkstra(GraphInterface g,
		VertexInterface r,
		ASetInterface a,
		PiInterface pi,
		PreviousInterface previous) {

		VertexInterface pivot = r;
		ArrayList<VertexInterface> vertexList = g.getVertexes();

		a.add(r);

		pi.setPi(0, r);
		for (VertexInterface vertex: vertexList) {
			if (vertex != r) {
				pi.setPi(Integer.MAX_VALUE, vertex);
			}
		}

		for (int j = 0; j < g.getVertexNumber(); j++) {
			for (VertexInterface vertex: g.generateNeighbours(pivot)) {
				if (! a.contains(vertex)) {
					if (pi.getPi(pivot) + g.getCost(pivot, vertex) < pi.getPi(vertex)) {
						pi.setPi(pi.getPi(pivot) + g.getCost(pivot, vertex), vertex);
						previous.setPrevious(pivot, vertex);
					}
				}
			}

			int piMax = Integer.MAX_VALUE;
			for (VertexInterface vertex: vertexList) {
				if (! a.contains(vertex) && pi.getPi(vertex) < piMax) {
					 pivot = vertex;
					 piMax = pi.getPi(vertex);
				}
			}
			a.add(pivot);
		}

		return previous;
	}

	public static PreviousInterface dijkstra(GraphInterface g,
						 VertexInterface r) {

		HashSet<VertexInterface> vertexHashSet = new HashSet<VertexInterface>();
		ASet aSet = new ASet(vertexHashSet);

		Hashtable<VertexInterface, Integer> hashPi =
			new Hashtable<VertexInterface, Integer>();
		Pi pi = new Pi(hashPi);

		Hashtable<VertexInterface, VertexInterface> hashPrevious =
			new Hashtable<VertexInterface, VertexInterface>();
		ArrayList<VertexInterface> shortestPath = new ArrayList<VertexInterface>();
		Previous previous = new Previous(hashPrevious, shortestPath);

		return dijkstra(g, r, aSet, pi, previous);
	}
}
