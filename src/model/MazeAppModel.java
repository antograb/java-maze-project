package model;

import java.util.ArrayList;
import java.util.Observable;

import dijkstra.VertexInterface;
import dijkstra.Dijkstra;

public class MazeAppModel extends Observable {

	private Maze                       maze      = new Maze("data/labyrinthe.txt");
	private VertexInterface            departure = maze.getDeparture();
	private VertexInterface            arrival   = maze.getArrival();
	private Previous                   previous  = (Previous) Dijkstra.dijkstra(maze, departure);
	private ArrayList<VertexInterface> shortest  = previous.getShortestPathTo(arrival);
	private ArrayList<VertexInterface> boxList   = maze.getVertexes();
	private Boolean                    modified  = false;

	public void setMaze(Maze maze) {
		this.maze = maze;
	}

	public Maze getMaze() {
		return maze;
	}
}