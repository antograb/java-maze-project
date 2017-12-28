package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import dijkstra.VertexInterface;
import dijkstra.Dijkstra;
import dijkstra.Previous;
import maze.Maze;

public class MazeAppModel extends Observable {

	private String			   filename;
	private Maze                       maze;
	private VertexInterface            departure;
	private VertexInterface            arrival;
	private Previous                   previous;
	private ArrayList<VertexInterface> shortest;
	private ArrayList<VertexInterface> boxList;
	private boolean                    saved	= true;
	private boolean                    pathDrawn	= false;

	public MazeAppModel() {
		maze = new Maze(Maze.emptyMaze(10));
		initModelFromMaze();
	}

	private void initModelFromMaze() {
		departure = maze.getDeparture();
		arrival = maze.getArrival();
		boxList = maze.getVertexes();
		if (departure == null || arrival == null) {
			previous = null;
			shortest = null;
			return;
		}
		previous = (Previous) Dijkstra.dijkstra(maze, departure);
		shortest = previous.getShortestPathTo(arrival);
	}

	public void loadMaze(String filename) {
		this.filename = new String(filename);
		maze = new Maze(this.filename);
		initModelFromMaze();
		saved = true;
		setChanged();
		notifyObservers(MazeAppModelMessage.MazeRenewal);
	}

	public Maze getMaze() {
		return maze;
	}

	public String getFilename() {
		return filename;
	}

	public boolean isSaved() {
		return saved;
	}

	public ArrayList<VertexInterface> getShortest() {
		return shortest;
	}

	public boolean isPathDrawn() {
		return pathDrawn;
	}

	public void setPathDrawn(boolean pathDrawn) {
		this.pathDrawn = pathDrawn;
	}

	public void saveToFile() {
		maze.saveToTextFile(filename);
		saved = true;
	}

	public void saveToFile(String filename) {
		this.filename = new String(filename);
		maze.saveToTextFile(this.filename);
		saved = true;
		setChanged();
		notifyObservers(MazeAppModelMessage.FileChange);
	}
}
