package model;

import maze.Maze;

public class MazeAppModel {

	private Maze maze = new Maze("data/labyrinthe.txt");

	public void setMaze(Maze maze) {
		this.maze = maze;
	}

	public Maze getMaze() {
		return maze;
	}
}