package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class ClearMazeMenuItem extends JMenuItem
				implements ActionListener {

	private final MazeApp mazeApp;

	public ClearMazeMenuItem(MazeApp mazeApp) {

		super("Clear maze");
		this.mazeApp = mazeApp;
		this.setAccelerator(KeyStroke.getKeyStroke("control D"));
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mazeApp.clearMaze();
	}
}
