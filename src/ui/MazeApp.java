package ui;

import javax.swing.*;
import maze.Maze;
import model.MazeAppModel;

import java.util.Observable;
import java.util.Observer;

public class MazeApp extends JFrame 
				implements Observer {
	
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

	@Override
	public void update(Observable obs, Object param) {

		windowPanel.notifyForUpdates(param);
	}
}
