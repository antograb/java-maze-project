package ui;

import javax.swing.JMenu;

public class FileMenu extends JMenu {

	private final MazeApp mazeApp;
	private final QuitMenuItem quitMenuItem;
	
	public FileMenu(MazeApp mazeApp) {
		
		super("File");
		this.mazeApp = mazeApp;
		
		add(quitMenuItem = new QuitMenuItem(mazeApp));
		
	}

}
