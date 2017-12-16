package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import maze.Maze;

public class DrawingPanelGrid extends JPanel {

	private final MazeApp        mazeApp;
	private       Maze           maze;
	private       DrawingBox[][] drawingBoxMatrix;
	private       int            dimensionX;
	private       int            dimensionY;
	
	public DrawingPanelGrid(MazeApp mazeApp) {
		
		this.mazeApp    = mazeApp;
		this.maze       = mazeApp.getMazeAppModel().getMaze();
		this.dimensionX = maze.getDimensionX();
		this.dimensionY = maze.getDimensionY();
		this.drawingBoxMatrix = new DrawingBox[dimensionX][dimensionY];
		
		setLayout(new GridLayout(this.dimensionX, this.dimensionY));
		initFromMaze(maze);
		
		setPreferredSize(new Dimension(256, 256));
	}
	
	private void initFromMaze(Maze maze) {

		for (int i = 0; i < dimensionX; i++) {
			for (int j = 0; j < dimensionY; j++) {
				if (maze.getMaze()[j][i].isWalkable()) {
					if (maze.getMaze()[j][i].isDeparture()) {
						add(new DepartureDrawingBox(mazeApp, Color.GREEN));
						drawingBoxMatrix[j][i] = new DepartureDrawingBox(mazeApp, Color.GREEN);
					}
					else if (maze.getMaze()[j][i].isArrival()) {
						add(new ArrivalDrawingBox(mazeApp, Color.RED));
						drawingBoxMatrix[j][i] = new ArrivalDrawingBox(mazeApp, Color.RED);
					}
					else {
						add(new EmptyDrawingBox(mazeApp, Color.BLACK));
						drawingBoxMatrix[j][i] = new EmptyDrawingBox(mazeApp, Color.BLACK);
					}
				}
				else {
					add(new WallDrawingBox(mazeApp, Color.WHITE));
					drawingBoxMatrix[j][i] = new WallDrawingBox(mazeApp, Color.WHITE);
				}
			}
		}
	}

	public void notifyForUpdates(Object param) {

		for (int i = 0; i < dimensionX; i++) {
			for (int j = 0; j < dimensionY; j++) {
				if (maze.getMaze()[j][i].isWalkable()) {
					if (maze.getMaze()[j][i].isDeparture()) {
						drawingBoxMatrix[j][i].notifyForUpdates();
					}
					else if (maze.getMaze()[j][i].isArrival()) {
						drawingBoxMatrix[j][i].notifyForUpdates();
					}
					else {
						drawingBoxMatrix[j][i].notifyForUpdates();
					}
				}
				else {
					drawingBoxMatrix[j][i].notifyForUpdates();
				}
			}
		}
	}
}
