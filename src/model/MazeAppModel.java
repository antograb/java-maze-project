package model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import dijkstra.VertexInterface;
import dijkstra.Dijkstra;
import dijkstra.Previous;

public class MazeAppModel extends Observable {

	private Maze                       maze          = new Maze("data/labyrinthe.txt");
	private VertexInterface            departure     = maze.getDeparture();
	private VertexInterface            arrival       = maze.getArrival();
	private Previous                   previous      = (Previous) Dijkstra.dijkstra(maze, departure);
	private ArrayList<VertexInterface> shortest      = previous.getShortestPathTo(arrival);
	private ArrayList<VertexInterface> boxList       = maze.getVertexes();
	private Boolean                    modified      = false;
	private ArrayList<Observer>        listObserver  = new ArrayList<Observer>();

	public void setMaze(Maze maze) {
		this.maze = maze;
		modified = true;
		setChanged();
		notifyObservers(maze);
	}

	public Maze getMaze() {
		return maze;
	}

	public boolean isModified() {
		return modified;
	}

	public void saveToFile() {
		maze.saveToTextFile("data/labyOutput.txt");
	}
}
