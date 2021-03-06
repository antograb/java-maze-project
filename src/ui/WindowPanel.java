package ui;

import javax.swing.*;
import maze.Maze;
import java.awt.*;

public final class WindowPanel extends JPanel {

	private final MazeDrawing drawingPanelGrid;

	public WindowPanel(MazeApp mazeApp) {

		setLayout(new BorderLayout());

		add(drawingPanelGrid = new MazeDrawing(mazeApp), BorderLayout.CENTER);
	}

	public void notifyForUpdates(Object param) {
		drawingPanelGrid.notifyForUpdates(param);
	}

	public void paintShortestPath() {
		drawingPanelGrid.paintShortestPath();
	}

	public void clearShortestPath() {
		drawingPanelGrid.clearShortestPath();
	}

	public void animateShortestPath() {

		drawingPanelGrid.animateShortestPath();
	}
}
