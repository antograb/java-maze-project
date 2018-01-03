package ui;

import javax.swing.JMenuItem;

import model.MazeAppModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawShortestPathMenuItem extends JMenuItem
				implements ActionListener {

	private final MazeApp mazeApp;

	public DrawShortestPathMenuItem(MazeApp mazeApp) {

		super("Draw shortest path");
		this.mazeApp = mazeApp;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent evt) {
		mazeApp.drawShortestPath();
	}
}
