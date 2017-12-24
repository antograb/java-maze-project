package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import javax.swing.JPanel;
import maze.Maze;
import maze.EBox;
import dijkstra.VertexInterface;

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

	private void paintShortestPath(Graphics2D g, int width, int height) {

		g.setColor(Color.ORANGE);
		int dimensionX = mazeApp.getMazeAppModel().getMaze().getDimensionX();
		int dimensionY = mazeApp.getMazeAppModel().getMaze().getDimensionY();
		int boxWidth = width / dimensionX;
		int boxHeight = height / dimensionY;
		for (VertexInterface vertex : mazeApp.getMazeAppModel().getShortest()) {
			EBox box = (EBox) vertex;
			g.fillOval(box.getX()*boxWidth, box.getY()*boxHeight, boxWidth, boxHeight);
		}
	}

	public void paintShortestPath() {
		paintShortestPath((Graphics2D) getGraphics(), getWidth(), getHeight());
	}
}
