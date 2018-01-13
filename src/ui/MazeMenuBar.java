package ui;

import javax.swing.JMenuBar;

public final class MazeMenuBar extends JMenuBar {

	private final MazeApp mazeApp;
	private final FileMenu fileMenu;
	private final MazeMenu mazeMenu;

	public MazeMenuBar(MazeApp mazeApp) {

		super();
		this.mazeApp = mazeApp;

		add(fileMenu = new FileMenu(mazeApp));
		add(mazeMenu = new MazeMenu(mazeApp));
	}
}
