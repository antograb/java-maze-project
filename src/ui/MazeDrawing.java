package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import javax.swing.JPanel;
import maze.Maze;

public class MazeDrawing extends JPanel {

	private final MazeApp mazeApp;

	public MazeDrawing(MazeApp mazeApp) {

		this.mazeApp    = mazeApp;
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(500,500)) ;
		
	}

	@Override
	protected final void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g2d);

		int width = getWidth();
		int height = getHeight();

		mazeApp.getMazeAppModel().paintMaze(g2d, width, height);
	}

	public void notifyForUpdates(Object param) {

		if (param instanceof Maze) {
			this.repaint();
		}
	}
}
