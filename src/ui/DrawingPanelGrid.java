package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import maze.Maze;

public class DrawingPanelGrid extends JPanel {

	private final MazeApp mazeApp;
	private       Maze    maze;
	private       int     dimensionX;
	private       int     dimensionY;
	
	public DrawingPanelGrid(MazeApp mazeApp, Maze maze) {
		
		this.mazeApp = mazeApp;
		this.maze = maze;
		this.dimensionX = maze.getDimensionX();
		this.dimensionY = maze.getDimensionY();
		
		setLayout(new GridLayout(this.dimensionX, this.dimensionY));
		initFromMaze(maze);
		
		setPreferredSize(new Dimension(256, 256));
	}
	
	private void initFromMaze(Maze maze) {

		int dimensionX = maze.getDimensionX();
		int dimensionY = maze.getDimensionY();
		
		for (int i = 0; i < dimensionX; i++) {
			for (int j = 0; j < dimensionY; j++) {
				if (maze.getMaze()[j][i].isWalkable()) {
					if (maze.getMaze()[j][i].isDeparture()) {
						add(new DepartureDrawingBox(mazeApp, Color.GREEN));
					}
					else if (maze.getMaze()[j][i].isArrival()) {
						add(new ArrivalDrawingBox(mazeApp, Color.RED));
					}
					else {
						add(new EmptyDrawingBox(mazeApp, Color.BLACK));
					}
				}
				else {
					add(new WallDrawingBox(mazeApp, Color.WHITE));
				}
			}
		}
		
	}
}
