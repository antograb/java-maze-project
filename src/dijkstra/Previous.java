package dijkstra;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import maze.Box;

public final class Previous implements PreviousInterface {

	private Hashtable<VertexInterface, VertexInterface> hashPrevious;
	private ArrayList<VertexInterface> shortestPath;

	public Previous(Hashtable<VertexInterface, VertexInterface> hashPrevious,
			ArrayList<VertexInterface> shortestPath) {

		this.hashPrevious = new Hashtable<VertexInterface, VertexInterface>(hashPrevious);
		this.shortestPath = new ArrayList<VertexInterface>(shortestPath);
	}

	public VertexInterface getPrevious(VertexInterface vertex) {

		return hashPrevious.get(vertex);
	}

	public void setPrevious(VertexInterface father, VertexInterface son) {

		hashPrevious.put(son, father);
	}

	public ArrayList<VertexInterface> getShortestPathTo(VertexInterface vertex) {

		shortestPath.add(vertex);
		VertexInterface father = getPrevious(vertex);

		if (getPrevious(father) == null) {
			shortestPath.add(father);
			return shortestPath;
		}
		else {
			return getShortestPathTo(father);
		}
	}
}
