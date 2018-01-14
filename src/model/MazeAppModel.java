package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import dijkstra.*;
import maze.Maze;
import maze.Box;
import maze.MazeReadingException;
import maze.MazeSavingException;

public class MazeAppModel
		extends Observable
		implements MazeModelInterface {
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
			if (arrival.getNeighbourList() == null) {
				shortest = null;
			}
			else {
				shortest = previous.getShortestPathTo(arrival);
			}
		}
	}

	/** Load a new maze from a file and send a notification to the view */
	public void loadMaze(String filename)
			throws MazeReadingException {
		maze = new Maze(filename);
		this.filename = filename;
		initModelFromMaze();
		saved = true;
		setChanged();
		notifyObservers(MazeAppModelMessage.MazeLoaded);
	}

	public Maze getMaze() {
		return maze;
	}

	/** Return the box at given coordinates */
	public Box getBox(int boxLine, int boxRow) {
		return this.maze.getBox(boxLine, boxRow);
	}

	/** Return the name of the file where the maze is currently being saved */
	public String getFilename() {
		return filename;
	}

	/** Tell whether the current maze has been saved or not */
	public boolean isSaved() {
		return saved;
	}

	/** Return the Dijkstra shortest path from departure to arrival */
	public ArrayList<VertexInterface> getShortest() {
		return shortest;
	}

	/** Indicate whether the path is drawn, currently being drawn, or neither */
	public int isPathDrawn() {
		return pathDrawn;
	}

	/** Set the state of the path drawing :
	 * 0 if not drawn
	 * 1 if being animated
	 * 2 if completely drawn
	 */
	public void setPathDrawn(int pathDrawn) {
		this.pathDrawn = pathDrawn;
	}

	/**
	 * Save the maze to the current file being used and notify observers
	 * (such as the "Save" button)
	 */
	public void saveToFile() throws MazeSavingException {
		maze.saveToTextFile(filename);
		saved = true;
		setChanged();
		notifyObservers(MazeAppModelMessage.MazeSaved);
	}

  /** Save the maze to the given file and set it to be the current file */
	public void saveToFile(String filename) throws MazeSavingException {
		maze.saveToTextFile(filename);
		this.filename = filename;
		saved = true;
		setChanged();
		notifyObservers(MazeAppModelMessage.FileChange);
	}

	/** Expose a way to toggle a box's type given its coordinates */
	public void toggleBox(int boxLine, int boxRow) {
		if (maze.getBox(boxLine, boxRow).isWalkable()) {
			setWBox(boxLine, boxRow);
		}
		else {
			setEBox(boxLine, boxRow);
		}
	}

	/** Set the box at given coordinates to be a wall box */
	public void setWBox(int boxLine, int boxRow) {
		maze.setWBox(boxLine, boxRow);
		initModelFromMaze();
		saved = false;
		setChanged();
		notifyObservers(MazeAppModelMessage.MazeRenewal);
	}

	/** Set the box at given coordinates to be an empty box */
	public void setEBox(int boxLine, int boxRow) {
		maze.setEBox(boxLine, boxRow);
		initModelFromMaze();
		saved = false;
		setChanged();
		notifyObservers(MazeAppModelMessage.MazeRenewal);
	}

	/** Set the box at given coordinates to be a departure box */
	public void setDBox(int boxLine, int boxRow) {
		maze.setDBox(boxLine, boxRow);
		initModelFromMaze();
		saved = false;
		setChanged();
		notifyObservers(MazeAppModelMessage.MazeRenewal);
	}

	/** Set the box at given coordinates to be an arrival box */
	public void setABox(int boxLine, int boxRow) {
		maze.setABox(boxLine, boxRow);
		initModelFromMaze();
		saved = false;
		setChanged();
		notifyObservers(MazeAppModelMessage.MazeRenewal);
	}

	/**
	 * Set the maze to be a new clear (full of empty boxes) maze of given
	 * dimensions
	 */
	public void newClearMaze(int numberOfLines, int numberOfRows) {

		maze = new Maze(Maze.emptyMaze(numberOfLines, numberOfRows));
		initModelFromMaze();
		saved = false;
		setChanged();
		notifyObservers(MazeAppModelMessage.MazeRenewal);
	}

	/** Add an empty column at the end of the maze */
	public void addEmptyColumn() {
		addEmptyColumn(maze.getDimensionX());
	}

	/** Add an empty column to the maze at the given position */
	public void addEmptyColumn(int posColumn) {
		maze.addEmptyColumn(posColumn);
		initModelFromMaze();
		saved = false;
		setChanged();
		notifyObservers(MazeAppModelMessage.MazeRenewal);
	}

	/** Delete a column from the maze at the given position */
	public void delColumn(int posColumn) {
		maze.delColumn(posColumn);
		initModelFromMaze();
		saved = false;
		setChanged();
		notifyObservers(MazeAppModelMessage.MazeRenewal);
	}

	/** Delete the last column of the maze */
	public void delColumn() {
		delColumn(maze.getDimensionX() - 1);
	}

	/** Add an empty line at the end of the maze */
	public void addEmptyLine() {
		addEmptyLine(maze.getDimensionY());
	}

	/** Add an empty line at the given position */
	public void addEmptyLine(int posLine) {
		maze.addEmptyLine(posLine);
		initModelFromMaze();
		saved = false;
		setChanged();
		notifyObservers(MazeAppModelMessage.MazeRenewal);
	}

	/** Delete a line from the maze at the given position */
	public void delLine(int posLine) {
		maze.delLine(posLine);
		initModelFromMaze();
		saved = false;
		setChanged();
		notifyObservers(MazeAppModelMessage.MazeRenewal);
	}

	/** Delete the last line of the maze */
	public void delLine() {
		delLine(maze.getDimensionY() - 1);
	}

	/**
	 * Get the departure box from the maze
	 * @return The departure box from the maze, if one exists, else null
	 */
	public VertexInterface getDeparture() {
		return departure;
	}

	/** Clear the maze : create a new one of the current dimensions */
	public void clearMaze() {
		newClearMaze(maze.getDimensionY(), maze.getDimensionX());
	}

	/** Get the number of rows in the maze */
	public int getDimensionX() {
		return maze.getDimensionX();
	}

	/** Get the number of lines in the maze */
	public int getDimensionY() {
		return maze.getDimensionY();
	}
}
