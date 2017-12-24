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

public class MazeAppModel extends Observable {

	private Maze                       maze          = new Maze("data/labyrinthe.txt");
	private VertexInterface            departure     = maze.getDeparture();
	private VertexInterface            arrival       = maze.getArrival();
	private Previous                   previous      = (Previous) Dijkstra.dijkstra(maze, departure);
	private ArrayList<VertexInterface> shortest      = previous.getShortestPathTo(arrival);
	//private ArrayList<VertexInterface> boxList       = maze.getVertexes();
	private Boolean                    modified      = false;
	private boolean                    pathDrawn     = false;

	public void setMaze(Maze maze) {
		this.maze = new Maze(maze);
		this.departure = maze.getDeparture();
		this.arrival = maze.getArrival();
		this.previous = (Previous) Dijkstra.dijkstra(maze, departure);
		this.shortest = previous.getShortestPathTo(arrival);
		modified = true;
		setChanged();
		notifyObservers(maze);
	}

	public Maze getMaze() {
		return maze;
	}

	public ArrayList<VertexInterface> getShortest() {
		return shortest;
	}

	public boolean isModified() {
		return modified;
	}

	public boolean isPathDrawn() {
		return pathDrawn;
	}

	public void setPathDrawn(boolean pathDrawn) {
		this.pathDrawn = pathDrawn;
	}

	public void saveToFile() {
		maze.saveToTextFile("data/labyOutput.txt");
	}

	public void saveToFile(String filename) {
		maze.saveToTextFile(filename);
	}

	public void paintMaze(Graphics2D g, int width, int height) {

		int dimensionX = maze.getDimensionX();
		int dimensionY = maze.getDimensionY();
		int boxWidth = width/dimensionX;
		int boxHeight = height/dimensionY;

		for (int line = 0; line < dimensionY; line++) {
			for (int row = 0; row < dimensionX; row++) {
				if (maze.getMaze()[line][row].isWalkable()) {
					if (maze.getMaze()[line][row].isDeparture()) {
						g.setColor(Color.GREEN);
					}
					else if (maze.getMaze()[line][row].isArrival()) {
						g.setColor(Color.RED);
					}
					else {
						g.setColor(Color.BLACK);
					}
				}
				else {
					g.setColor(Color.WHITE);
				}
				g.fill3DRect(row*boxWidth, line*boxHeight,
						boxWidth, boxHeight,
						true);
			}
		}
	}
}
