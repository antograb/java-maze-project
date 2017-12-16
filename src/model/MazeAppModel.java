package model;

import java.util.Observable;

public class MazeAppModel extends Observable {

	private Maze maze = new Maze("data/labyrinthe.txt");
	private boolean modified = false;

	public void setMaze(Maze maze) {
		this.maze = maze;
		modified = true;
		setChanged();
		notifyObservers();
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
