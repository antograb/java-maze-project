package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import dijkstra.*;
import maze.*;

public class MazeAppModel extends Observable {
	/** The file into which the maze is to be saved */
	private String                     filename;
	private Maze                       maze;
	private VertexInterface            departure;
	private VertexInterface            arrival;
	private Previous                   previous;
	private ArrayList<VertexInterface> shortest;
	private boolean                    saved	= true;
	/*
	 * There are three states for pathDrawn :
	 * 	0 : path isn't drawn
	 * 	1 : path has been animated
	 * 	2 : path has already been animated, but needs to be re-drawn
	 * 		because of window resizing. Therefore there is no need to
	 * 		animate the path again
	 */
	private int                        pathDrawn = 0;

	public MazeAppModel() {
		maze = new Maze(Maze.emptyMaze(10));
		initModelFromMaze();
	}

	private void initModelFromMaze() {
		departure = maze.getDeparture();
		arrival = maze.getArrival();
		if (departure == null || arrival == null) {
			previous = null;
			shortest = null;
		}
		else {
			previous = (Previous) Dijkstra.dijkstra(maze, departure);
			if (((Box) arrival).getNeighbourList() == null) {
				shortest = null;
			}
			else {
				shortest = previous.getShortestPathTo(arrival);
			}
		}
	}

	public void loadMaze(String filename) {
		this.filename = new String(filename);
		maze = new Maze(this.filename);
		initModelFromMaze();
		saved = true;
		setChanged();
		notifyObservers(MazeAppModelMessage.MazeLoaded);
	}

	public Maze getMaze() {
		return maze;
	}

	public Box getBox(int boxLine, int boxRow) {
		return this.maze.getBox(boxLine, boxRow);
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

	public ArrayList<VertexInterface> getShortestShallowCopy() {
		ArrayList<VertexInterface> shallowCopy = new ArrayList<VertexInterface>();
		for (VertexInterface vertex : this.shortest) {
			shallowCopy.add(vertex);
		}
		return shallowCopy;
	}

	public int isPathDrawn() {
		return pathDrawn;
	}

	public void setPathDrawn(int pathDrawn) {
		this.pathDrawn = pathDrawn;
	}

	public void saveToFile() {
		maze.saveToTextFile(filename);
		saved = true;
		setChanged();
		notifyObservers(MazeAppModelMessage.MazeSaved);
	}

	public void saveToFile(String filename) {
		this.filename = filename;
		maze.saveToTextFile(this.filename);
		saved = true;
		setChanged();
		notifyObservers(MazeAppModelMessage.FileChange);
	}

	public void toggleBox(int boxLine, int boxRow) {
		if (maze.getBox(boxLine, boxRow).isWalkable()) {
			setWBox(boxLine, boxRow);
		}
		else {
			setEBox(boxLine, boxRow);
		}
	}

	public void setWBox(int boxLine, int boxRow) {
		maze.setWBox(boxLine, boxRow);
		maze.resetNeighbourLists();
		initModelFromMaze();
		saved = false;
		setChanged();
		notifyObservers(MazeAppModelMessage.MazeRenewal);
	}

	public void setEBox(int boxLine, int boxRow) {
		maze.setEBox(boxLine, boxRow);
		maze.resetNeighbourLists();
		initModelFromMaze();
		saved = false;
		setChanged();
		notifyObservers(MazeAppModelMessage.MazeRenewal);
	}

	public void setDBox(int boxLine, int boxRow) {
		if (departure != null) {
			int departureRow = ((EBox) departure).getX();
			int departureLine = ((EBox) departure).getY();
			maze.setEBox(departureLine, departureRow);
		}
		maze.setDBox(boxLine, boxRow);
		maze.resetNeighbourLists();
		initModelFromMaze();
		saved = false;
		setChanged();
		notifyObservers(MazeAppModelMessage.MazeRenewal);
	}

	public void setABox(int boxLine, int boxRow) {
		if (arrival != null) {
			int arrivalRow = ((EBox) arrival).getX();
			int arrivalLine = ((EBox) arrival).getY();
			maze.setEBox(arrivalLine, arrivalRow);
		}
		maze.setABox(boxLine, boxRow);
		maze.resetNeighbourLists();
		initModelFromMaze();
		saved = false;
		setChanged();
		notifyObservers(MazeAppModelMessage.MazeRenewal);
	}

	public void newClearMaze(int numberOfLines, int numberOfRows) {

		maze = new Maze(Maze.emptyMaze(numberOfLines, numberOfRows));
		initModelFromMaze();
		saved = false;
		setChanged();
		notifyObservers(MazeAppModelMessage.MazeRenewal);
	}

	public void addEmptyColumn() {
		addEmptyColumn(maze.getDimensionX());
	}

	public void addEmptyColumn(int posColumn) {
		maze.addEmptyColumn(posColumn);
		initModelFromMaze();
		saved = false;
		setChanged();
		notifyObservers(MazeAppModelMessage.MazeRenewal);
	}

	public void delColumn(int posColumn) {
		maze.delColumn(posColumn);
		initModelFromMaze();
		saved = false;
		setChanged();
		notifyObservers(MazeAppModelMessage.MazeRenewal);
	}

	public void delColumn() {
		delColumn(maze.getDimensionX() - 1);
	}

	public void addEmptyLine() {
		addEmptyLine(maze.getDimensionY());
	}

	public void addEmptyLine(int posLine) {
		maze.addEmptyLine(posLine);
		initModelFromMaze();
		saved = false;
		setChanged();
		notifyObservers(MazeAppModelMessage.MazeRenewal);
	}

	public void delLine(int posLine) {
		maze.delLine(posLine);
		initModelFromMaze();
		saved = false;
		setChanged();
		notifyObservers(MazeAppModelMessage.MazeRenewal);
	}

	public void delLine() {
		delLine(maze.getDimensionY() - 1);
	}

	public VertexInterface getDeparture() {
		return departure;
	}

	public void clearMaze() {
		newClearMaze(maze.getDimensionY(), maze.getDimensionX());
	}

	public int getDimensionX() {
		return maze.getDimensionX();
	}

	public int getDimensionY() {
		return maze.getDimensionY();
	}
}
