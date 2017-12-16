package model;

import java.util.ArrayList;
import java.util.Hashtable;

import dijkstra.VertexInterface;

public class Previous extends dijkstra.Previous {

	public Previous(Hashtable<VertexInterface, VertexInterface> hashPrevious, ArrayList<VertexInterface> shortestPath) {
		super(hashPrevious, shortestPath);
	}
}
