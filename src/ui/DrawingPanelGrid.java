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

		for (int i = 0; i < dimensionY; i++) {
			for (int j = 0; j < dimensionX; j++) {
				if (maze.getMaze()[j][i].isWalkable()) {
					if (maze.getMaze()[j][i].isDeparture()) {
						drawingBoxMatrix[j][i] = new DepartureDrawingBox(mazeApp, Color.GREEN);
					}
					else if (maze.getMaze()[j][i].isArrival()) {
						drawingBoxMatrix[j][i] = new ArrivalDrawingBox(mazeApp, Color.RED);
					}
					else {
						drawingBoxMatrix[j][i] = new EmptyDrawingBox(mazeApp, Color.BLACK);
					}
				}
				else {
					drawingBoxMatrix[j][i] = new WallDrawingBox(mazeApp, Color.WHITE);
				}
				
				add(drawingBoxMatrix[j][i]);
			}
		}
	}

	public void notifyForUpdates(Object param) {

		if (param instanceof Maze) {
			this.maze = (Maze) param;
			this.dimensionX = maze.getDimensionX();
			this.dimensionY = maze.getDimensionY();
			this.drawingBoxMatrix = new DrawingBox[dimensionY][dimensionX];
			System.out.println("x : "+dimensionX+" y : "+dimensionY);
			this.gridLayout = new GridLayout(dimensionY, dimensionX);

			for (int line = 0; line < dimensionX; line++) {
				for (int row = 0; row < dimensionY; row++) {
					if (maze.getMaze()[line][row].isWalkable()) {
						if (maze.getMaze()[line][row].isDeparture()) {
							drawingBoxMatrix[row][line].setColor(Color.GREEN);
						}
						else if (maze.getMaze()[line][row].isArrival()) {
							drawingBoxMatrix[row][line].setColor(Color.RED);
						}
						else {
							drawingBoxMatrix[row][line].setColor(Color.BLACK);
						}
					}
					else {
						drawingBoxMatrix[row][line].setColor(Color.WHITE);
					}
				}
			}
			repaint();
		}
	}
}
