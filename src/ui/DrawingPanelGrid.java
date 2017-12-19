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
	private       GridLayout     gridLayout;

	public DrawingPanelGrid(MazeApp mazeApp) {

		this.mazeApp    = mazeApp;
		this.maze       = mazeApp.getMazeAppModel().getMaze();
		this.dimensionX = maze.getDimensionX();
		this.dimensionY = maze.getDimensionY();
		this.drawingBoxMatrix = new DrawingBox[dimensionY][dimensionX];
		this.gridLayout = new GridLayout(this.dimensionY, this.dimensionX); //inverted coordinates

		setLayout(gridLayout);
		initFromMaze(maze);
	}

	private void initFromMaze(Maze maze) {

		for (int line = 0 ; line < dimensionY; line++) {
			for (int row = 0 ; row < dimensionX; row++) {
				if (maze.getMaze()[line][row].isWalkable()) {
					if (maze.getMaze()[line][row].isDeparture()) {
						drawingBoxMatrix[line][row] = new DepartureDrawingBox(mazeApp, Color.GREEN);
					}
					else if (maze.getMaze()[line][row].isArrival()) {
						drawingBoxMatrix[line][row] = new ArrivalDrawingBox(mazeApp, Color.RED);
					}
					else {
						drawingBoxMatrix[line][row] = new EmptyDrawingBox(mazeApp, Color.BLACK);
					}
				}
				else {
					drawingBoxMatrix[line][row] = new WallDrawingBox(mazeApp, Color.WHITE);
				}
				
				add(drawingBoxMatrix[line][row]);
			}
		}
	}

	public void notifyForUpdates(Object param) {

		if (param instanceof Maze) {
			// set all new parameters
			this.maze = (Maze) param;
			this.dimensionX = maze.getDimensionX();
			this.dimensionY = maze.getDimensionY();
			this.drawingBoxMatrix = new DrawingBox[dimensionY][dimensionX];
			this.gridLayout = new GridLayout(dimensionY, dimensionX);
			setLayout(this.gridLayout);

			for (int line = 0; line < dimensionY; line++) {
				for (int row = 0; row < dimensionX; row++) {
					if (maze.getMaze()[line][row].isWalkable()) {
						if (maze.getMaze()[line][row].isDeparture()) {
							drawingBoxMatrix[line][row] = new DepartureDrawingBox(mazeApp, Color.GREEN);
						}
						else if (maze.getMaze()[line][row].isArrival()) {
							drawingBoxMatrix[line][row] = new DepartureDrawingBox(mazeApp, Color.RED);
						}
						else {
							drawingBoxMatrix[line][row] = new DepartureDrawingBox(mazeApp, Color.BLACK);
						}
					}
					else {
						drawingBoxMatrix[line][row] = new DepartureDrawingBox(mazeApp, Color.GREEN);
					}
				}
			}
			repaint();
		}
	}
}
