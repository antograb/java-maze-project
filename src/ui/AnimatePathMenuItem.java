package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JMenuItem;

public class AnimatePathMenuItem extends JMenuItem
				implements ActionListener {

	private final MazeApp mazeApp;

	public AnimatePathMenuItem(MazeApp mazeApp) {

		super("Animate shortest path");
		this.mazeApp = mazeApp;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		mazeApp.animateShortestPath();
	}
}
