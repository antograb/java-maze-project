package ui;

import javax.swing.JMenuItem;

public class QuitMenuItem extends JMenuItem {

	private final MazeApp mazeApp;
	
	public QuitMenuItem(MazeApp mazeApp) {

		super("Quit");
		this.mazeApp = mazeApp;
	}
	
}
