package ui;

import javax.swing.*;
import maze.Maze;
import model.MazeAppModel;

public class MazeApp extends JFrame {

	private final MazeMenuBar  mazeMenuBar;
	private final WindowPanel  windowPanel;
	private       MazeAppModel mazeAppModel = new MazeAppModel();

	public MazeApp() {

		super("Maze Solving Application");

		setJMenuBar(mazeMenuBar = new MazeMenuBar(this));
		setContentPane(windowPanel = new WindowPanel(this)) ;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();
		setVisible(true);
	}

	public MazeAppModel getMazeAppModel() {
		return mazeAppModel;
	}
}
