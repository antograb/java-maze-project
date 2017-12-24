package ui;

import javax.swing.JMenuItem;

import model.MazeAppModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearShortestPathMenuItem extends JMenuItem
				implements ActionListener {

	private final MazeApp mazeApp;

	public ClearShortestPathMenuItem(MazeApp mazeApp) {

		super("Clear shortest path");
		this.mazeApp = mazeApp;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent evt) {
		mazeApp.clearShortestPath();
	}
}
