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
	private boolean                    saved = true;
	private boolean                    pathDrawn = false;

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

	public void newClearMaze(int numberOfLines, int numberOfRows) {

		maze = new Maze(Maze.emptyMaze(numberOfLines, numberOfRows));
		initModelFromMaze();
		saved = false;
	}

	public void addEmptyColumn() {
		addEmptyColumn(maze.getDimensionX());
	}

	public void addEmptyColumn(int posColumn) {
		maze.addEmptyColumn(posColumn);
		initModelFromMaze();
		saved = false;
	}

	public void delColumn(int posColumn) {
		maze.delColumn(posColumn);
		initModelFromMaze();
		saved = false;
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
	}

	public void delLine(int posLine) {
		maze.delLine(posLine);
		initModelFromMaze();
		saved = false;
	}

	public void delLine() {
		delLine(maze.getDimensionY() - 1);
	}

	public void clearMaze() {
		newClearMaze(maze.getDimensionY(), maze.getDimensionX());
	}
}
