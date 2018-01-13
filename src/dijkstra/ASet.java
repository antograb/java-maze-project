package dijkstra;

import java.util.HashSet;

public final class ASet implements ASetInterface {

	private HashSet<VertexInterface> vertexHashSet;

	public ASet(HashSet<VertexInterface> vertexHashSet) {
		this.vertexHashSet = new HashSet<VertexInterface>(vertexHashSet);
	}

	public void add(VertexInterface vertex) {
		vertexHashSet.add(vertex);
	}

	public boolean contains(VertexInterface vertex) {
		return vertexHashSet.contains(vertex);
	}

	public HashSet<VertexInterface> getVertexHashSet() {
		return vertexHashSet;
	}
}
