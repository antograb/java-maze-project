package dijkstra;

import java.util.Hashtable;

public final class Pi implements PiInterface {

	private Hashtable<VertexInterface, Integer> hashPi;

	public Pi(Hashtable<VertexInterface, Integer> hashPi) {
		this.hashPi = new Hashtable<VertexInterface, Integer>(hashPi);
	}

	public int getPi(VertexInterface vertex) {
		return hashPi.get(vertex);
	}

	public void setPi(int i, VertexInterface vertex) {
		hashPi.put(vertex, i);
	}
}
