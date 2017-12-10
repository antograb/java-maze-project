package maze;
import java.util.ArrayList;

import dijkstra.VertexInterface;

public abstract class Box
		implements VertexInterface {

	private int x ;
	private int y ;
	private String label = null ;
	private Maze maze ;
	private ArrayList<Box> neighbourList = new ArrayList<Box>() ;
	
	public Box (String label, int x, int y, Maze maze) {
		
		this.label = label ;
		this.x = x ;
		this.y = y ;
		this.maze = maze ;
		
	}
	
	public List<VertexInterface> generateNeighbors() {
		return maze.generateNeighbours(this) ;
		
	}
	
	public boolean compareTo(VertexInterface vertex) {
		if (this.getLabel() == vertex.getLabel()) {
			return true ;
		}
		return false ;
	}
	
	public boolean isWalkable() {
		return true ;
	}
	
	public boolean isArrival() {
		return false ;
	}
	
	public boolean isDeparture() {
		return false ;
	}
	
	public List<Box> getNeighbourList() {
		return neighbourList;
	}

	public void setNeighbourList(List<Box> neighbourList) {
		this.neighbourList = neighbourList;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Maze getMaze() {
		return maze;
	}

	public void setMaze(Maze maze) {
		this.maze = maze;
	}
	
	public String toString() {
		return label;
	}
	
}
