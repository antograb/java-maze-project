package ui;

import javax.swing.JMenu;

public class FileMenu extends JMenu {

	private final MazeApp mazeApp;
	private final QuitMenuItem quitMenuItem;
	private final LoadMazeMenuItem loadMazeMenuItem;
	private final SaveMazeMenuItem saveMazeMenuItem;

	public FileMenu(MazeApp mazeApp) {

		super("File");
		this.mazeApp = mazeApp;

		add(quitMenuItem = new QuitMenuItem(mazeApp));
		add(loadMazeMenuItem = new LoadMazeMenuItem(mazeApp));
		add(saveMazeMenuItem = new SaveMazeMenuItem(mazeApp));
	}
}
