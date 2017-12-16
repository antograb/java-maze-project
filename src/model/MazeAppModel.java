package model;

import maze.Maze;

public class MazeAppModel {

	private Maze maze = new Maze("data/labyrinthe.txt");
	private boolean modified = false;

	public void setMaze(Maze maze) {
		this.maze = maze;
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