package ui;

import javax.swing.*;
import maze.Maze;

public class MazeApp extends JFrame {
	
	private final MazeMenuBar mazeMenuBar;
	private final WindowPanel windowPanel;
	private       Maze maze;
	
	public MazeApp(Maze maze) {
		
		super("Maze Solving Application");
		
		setJMenuBar(mazeMenuBar = new MazeMenuBar(this));
		setContentPane(windowPanel = new WindowPanel(this, maze)) ;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pack();
		setVisible(true);
		
	}

}
