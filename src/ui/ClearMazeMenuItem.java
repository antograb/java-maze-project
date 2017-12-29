package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Icon;
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

		int numberOfLines = mazeApp.getMazeAppModel()
							.getMaze().getDimensionY();
		int numberOfRows = mazeApp.getMazeAppModel()
						   .getMaze().getDimensionX();
		this.mazeApp.getMazeAppModel()
				.newClearMaze(numberOfLines, numberOfRows);
		System.out.println("test");
	}
}
