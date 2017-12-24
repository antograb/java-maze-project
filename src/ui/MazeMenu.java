package ui;

import javax.swing.JMenu;

public class MazeMenu extends JMenu {

	private final MazeApp mazeApp;
	private final DrawShortestPathMenuItem drawMenuItem;

	public MazeMenu(MazeApp mazeApp) {

		super("Maze");
		this.mazeApp = mazeApp;

		add(drawMenuItem = new DrawShortestPathMenuItem(mazeApp));
	}
}
