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

	private String                     filename;
	private Maze                       maze;
	private VertexInterface            departure;
	private VertexInterface            arrival;
	private Previous                   previous;
	private ArrayList<VertexInterface> shortest;
	private ArrayList<VertexInterface> boxList;
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
		boxList = maze.getVertexes();
		if (departure == null || arrival == null) {
			previous = null;
			shortest = null;
		}
		else {
			previous = (Previous) Dijkstra.dijkstra(maze, departure);
			shortest = previous.getShortestPathTo(arrival);
		}
		setChanged();
		notifyObservers(MazeAppModelMessage.MazeRenewal);
	}

	public void loadMaze(String filename) {
		this.filename = new String(filename);
		maze = new Maze(this.filename);
		initModelFromMaze();
		saved = true;
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

	public ArrayList<VertexInterface> getShortestDeepCopy() {
		ArrayList<VertexInterface> deepCopy = new ArrayList<VertexInterface>();
		for (VertexInterface vertex : this.shortest) {
			deepCopy.add(vertex);
		}
		return deepCopy;
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
	}

	public void saveToFile(String filename) {
		this.filename = new String(filename);
		maze.saveToTextFile(this.filename);
		saved = true;
		setChanged();
		notifyObservers(MazeAppModelMessage.FileChange);
	}

	public void toggleBox(int boxLine, int boxRow) {
		if (maze.getMaze()[boxLine][boxRow].isWalkable()) {
			setWBox(boxLine, boxRow);
		}
		else {
			setEBox(boxLine, boxRow);
		}
	}

	public void setWBox(int boxLine, int boxRow) {
		maze.getMaze()[boxLine][boxRow] = new WBox("W", boxLine, boxRow, maze);
		initModelFromMaze();
		saved = false;
	}

	public void setEBox(int boxLine, int boxRow) {
		maze.getMaze()[boxLine][boxRow] = new EBox("E", boxLine, boxRow, maze);
		initModelFromMaze();
		saved = false;
	}

	public void setDBox(int boxLine, int boxRow) {
		if (departure != null) {
			int departureRow = ((EBox) departure).getX();
			int departureLine = ((EBox) departure).getY();
			maze.getMaze()[departureLine][departureRow] =
				new EBox("E", departureLine, departureRow, maze);
		}
		maze.getMaze()[boxLine][boxRow] = new DBox("D", boxLine, boxRow, maze);
		initModelFromMaze();
		saved = false;
	}

	public void setABox(int boxLine, int boxRow) {
		if (arrival != null) {
			int arrivalRow = ((EBox) arrival).getX();
			int arrivalLine = ((EBox) arrival).getY();
			maze.getMaze()[arrivalLine][arrivalRow] =
				new EBox("E", arrivalLine, arrivalRow, maze);
		}
		maze.getMaze()[boxLine][boxRow] = new ABox("A", boxLine, boxRow, maze);
		initModelFromMaze();
		saved = false;
	}

	public VertexInterface getDeparture() {
		return departure;
	}

	public void newClearMaze(int numberOfLines, int numberOfRows) {

		Box[][] newClearMaze = new Box[numberOfLines][numberOfRows];
		
		for (int line = 0; line < numberOfLines; line++) {
			for (int row = 0; row < numberOfRows; row++) {
				newClearMaze[line][row] = new EBox("E", line, row, this.maze);
			}
		}
		this.maze = new Maze(newClearMaze);
		initModelFromMaze();
		saved = false;
	}

	public void addEmptyColumn() {

		Box[][] previousMaze  = maze.getMaze();
		int numberOfLines = maze.getDimensionY();
		int numberOfRows = maze.getDimensionX()+1;

		newClearMaze(numberOfLines, numberOfRows);

		for (int line = 0; line < numberOfLines; line++) {
			for (int row = 0; row < numberOfRows; row++) {
				if (row == numberOfRows-1) {
					maze.getMaze()[line][row] = new EBox("E", line, row, maze);
				} else {
					maze.getMaze()[line][row] = previousMaze[line][row];
				}
			}
		}

		initModelFromMaze();
		saved = false;
	}

	public void addEmptyLine() {

		Box[][] previousMaze  = maze.getMaze();
		int numberOfLines = maze.getDimensionY()+1;
		int numberOfRows = maze.getDimensionX();

		newClearMaze(numberOfLines, numberOfRows);

		for (int line = 0; line < numberOfLines; line++) {
			for (int row = 0; row < numberOfRows; row++) {
				if (line == numberOfLines-1) {
					maze.getMaze()[line][row] = new EBox("E", line, row, maze);
				} else {
					maze.getMaze()[line][row] = previousMaze[line][row];
				}
			}
		}

		initModelFromMaze();
		saved = false;
	}
}
