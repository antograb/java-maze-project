package ui;

import javax.swing.*;
import maze.Maze;
import java.awt.*;

public class WindowPanel extends JPanel
{
    private final DrawingPanelGrid drawingPanelGrid;
    private       Maze             maze;
    
	public WindowPanel(MazeApp mazeApp, Maze maze)
	{
		setLayout(new BorderLayout());
		
		add(drawingPanelGrid = new DrawingPanelGrid(mazeApp, maze), BorderLayout.CENTER);
	}
	
}
