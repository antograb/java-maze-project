package ui;

import javax.swing.JMenu;

public class FileMenu extends JMenu {

	private final MazeApp mazeApp;
	private final LoadMazeMenuItem loadMazeMenuItem;
	private final SaveMazeMenuItem saveMazeMenuItem;
	private final SaveMazeAsMenuItem saveMazeAsMenuItem;
	private final QuitMenuItem quitMenuItem;

	public FileMenu(MazeApp mazeApp) {

		super("File");
		this.mazeApp = mazeApp;

		add(loadMazeMenuItem = new LoadMazeMenuItem(mazeApp));
		add(saveMazeMenuItem = new SaveMazeMenuItem(mazeApp));
		add(saveMazeAsMenuItem = new SaveMazeAsMenuItem(mazeApp));
		add(quitMenuItem = new QuitMenuItem(mazeApp));
	}
}
