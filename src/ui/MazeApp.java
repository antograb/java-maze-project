package ui;

import javax.swing.*;
import maze.Maze;
import model.MazeAppModel;
import maze.EBox;

import java.util.Observable;
import java.util.Observer;

public class MazeApp extends JFrame
				implements Observer {

	private final MazeMenuBar  mazeMenuBar;
	private final WindowPanel  windowPanel;
	private       MazeAppModel mazeAppModel;

	public MazeApp() {

		super("Maze Solving Application");

		this.mazeAppModel = new MazeAppModel();
		mazeAppModel.addObserver(this);

		setJMenuBar(mazeMenuBar = new MazeMenuBar(this));
		setContentPane(windowPanel = new WindowPanel(this));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();
		setVisible(true);
	}

	public MazeAppModel getMazeAppModel() {
		return mazeAppModel;
	}

	@Override
	public void update(Observable obs, Object param) {

		System.out.println("New maze detected");
		windowPanel.notifyForUpdates(param);
	}

	public void drawShortestPath() {
		windowPanel.paintShortestPath();
	}
}
