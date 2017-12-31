package ui;

import javax.swing.JMenu;

public class MazeMenu extends JMenu {

	private final MazeApp mazeApp;
	private final DrawShortestPathMenuItem drawMenuItem;
	private final ClearShortestPathMenuItem clearMenuItem;
	private final NewClearMazeMenuItem newClearMazeMenuItem;
	private final ClearMazeMenuItem clearMazeMenuItem;
	private final AddRowMenuItem addRowMenuItem;
	private final AddLineMenuItem addLineMenuItem;
	private final AnimatePathMenuItem animatePathMenuItem;

	public MazeMenu(MazeApp mazeApp) {

		super("Maze");
		this.mazeApp = mazeApp;

		add(drawMenuItem = new DrawShortestPathMenuItem(mazeApp));
		add(animatePathMenuItem = new AnimatePathMenuItem(mazeApp));
		add(clearMenuItem = new ClearShortestPathMenuItem(mazeApp));
		add(newClearMazeMenuItem = new NewClearMazeMenuItem(mazeApp));
		add(clearMazeMenuItem = new ClearMazeMenuItem(mazeApp));
		add(addRowMenuItem = new AddRowMenuItem(mazeApp));
		add(addLineMenuItem = new AddLineMenuItem(mazeApp));
	}
}
