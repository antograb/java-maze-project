package ui;

import javax.swing.JMenuBar;

public class MazeMenuBar extends JMenuBar {

	private final MazeApp mazeApp;
	private final FileMenu fileMenu;

	public MazeMenuBar(MazeApp mazeApp) {

		super();
		this.mazeApp = mazeApp;

		add(fileMenu = new FileMenu(mazeApp));
	}
}
