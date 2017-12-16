package ui;

import javax.swing.*;
import maze.Maze;
import java.awt.*;

public class WindowPanel extends JPanel
{
    private final DrawingPanelGrid drawingPanelGrid;

	public WindowPanel(MazeApp mazeApp) {

		setLayout(new BorderLayout());

		add(drawingPanelGrid = new DrawingPanelGrid(mazeApp), BorderLayout.CENTER);
	}
}
