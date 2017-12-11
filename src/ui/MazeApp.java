package ui;

import javax.swing.*;

public class MazeApp extends JFrame {
	
	private final MazeMenuBar mazeMenuBar;
	
	public MazeApp() {
		
		super("Maze Solving Application");
		
		setJMenuBar(mazeMenuBar = new MazeMenuBar(this));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pack();
		setVisible(true);
		
	}

}
