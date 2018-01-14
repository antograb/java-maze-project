package ui;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Collections;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import maze.Maze;
import model.MazeAppModelMessage;
import maze.Box;
import dijkstra.VertexInterface;

public final class MazeDrawing extends JPanel implements MouseListener {

	private final MazeApp mazeApp;
	private ArrayList<VertexInterface> shortestPath;
	private ArrayList<VertexInterface> finalPath;
	private int pathCounter;

	public MazeDrawing(MazeApp mazeApp) {

		this.mazeApp    = mazeApp;
		this.shortestPath = new ArrayList<VertexInterface>();
		this.finalPath = null;
		this.pathCounter = -1;
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(500,500));
		addMouseListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g2d);

		int width = getWidth();
		int height = getHeight();

		paintMaze(g2d, width, height);
		if (mazeApp.isPathDrawn() == 1) {
			animateShortestPath(g2d, width, height);
		} else if (mazeApp.isPathDrawn() == 2) {
			paintShortestPath(g2d, width, height);
		}
	}

	/** Paint (each box of) the maze in the current JPanel */
	private void paintMaze(Graphics2D g, int width, int height) {

		Maze maze =  mazeApp.getModelMaze();
		int dimensionX = maze.getDimensionX();
		int dimensionY = maze.getDimensionY();
		int boxWidth = width / dimensionX;
		int boxHeight = height / dimensionY;

		for (int line = 0; line < dimensionY; line++) {
			for (int row = 0; row < dimensionX; row++) {
				if (maze.getBox(line, row).isWalkable()) {
					if (maze.getBox(line, row).isDeparture()) {
						g.setColor(Color.GREEN);
					}
					else if (maze.getBox(line, row).isArrival()) {
						g.setColor(Color.RED);
					}
					else {
						g.setColor(Color.BLACK);
					}
				}
				else {
					g.setColor(Color.WHITE);
				}
				g.fill3DRect(row * boxWidth,
					line * boxHeight,
					boxWidth,
					boxHeight,
					true);
			}
		}
	}

	/**
	 * This method is responsible for repainting the maze when needed
	 * @param param
	 *	The message about what happened to the maze.
	 *	The maze will be re-painted only if it changed
	 *	(by user edition or by loading a new maze from file).
	 * 	@see model.MazeAppModelMessage
	 */
	public void notifyForUpdates(Object param) {

		if (param == MazeAppModelMessage.MazeRenewal ||
				param == MazeAppModelMessage.MazeLoaded) {
			if (this.mazeApp.getModelShortest() != null) {
				finalPath = new ArrayList<VertexInterface>(mazeApp.getModelShortest());
				Collections.reverse(finalPath);
			}
			this.repaint();
		}
	}

	/**
	 * This method is reponsible for painting the shortest path on top
	 * of the maze. It takes all the boxes belonging to the shortest path
	 * and display on dot on them all at once.
	 */
	private void paintShortestPath(Graphics2D g, int width, int height) {

		g.setColor(Color.WHITE);
		int dimensionX = mazeApp.getModelDimensionX();
		int dimensionY = mazeApp.getModelDimensionY();
		int boxWidth = width / dimensionX;
		int boxHeight = height / dimensionY;
		int factorWidth = boxWidth / 3;
		int factorHeight = boxHeight / 3;
		ArrayList<VertexInterface> shortest =
			mazeApp.getModelShortest();
		if (shortest == null && mazeApp.isPathDrawn() == 0) {
			mazeApp.clearShortestPath();
			JOptionPane.showMessageDialog(this,
				"Check that your graph contains a departure and an arrival,"
				+ " and that a path exists between them.",
				"No shortest path",
				JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if (shortest == null) {
			mazeApp.clearShortestPath();
			return;
		}
		for (VertexInterface vertex : shortest) {
			Box box = (Box) vertex;
			g.fillOval(box.getX() * boxWidth + factorWidth,
					   box.getY()*boxHeight + factorHeight,
					   boxWidth - 2 * factorWidth,
					   boxHeight - 2 * factorHeight);
		}
	}

	/**
	 * This method displays the next box in the shortest path at each call.
	 * When the last box in the shortest path has been displayed, it falls
	 * back to calling drawShortestPath.
	 */
	private void animateShortestPath(Graphics2D g, int width, int height) {

		g.setColor(Color.WHITE);
		int dimensionX = mazeApp.getModelDimensionX();
		int dimensionY = mazeApp.getModelDimensionY();
		int boxWidth = width / dimensionX;
		int boxHeight = height / dimensionY;
		int factorWidth = boxWidth / 3;
		int factorHeight = boxHeight / 3;

		if (this.finalPath == null && mazeApp.isPathDrawn() == 0) {
			mazeApp.clearShortestPath();
			JOptionPane.showMessageDialog(this,
				"Check that your graph contains a departure and an arrival,"
				+ " and that a path exists between them.",
				"No shortest path",
				JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if (this.finalPath == null) {
			mazeApp.clearShortestPath();
			return;
		}

		if (pathCounter == -1) {
			JOptionPane.showMessageDialog(this,
					"Click on the maze to see the shortest path evolve");
			pathCounter = 0;
		}

		for (VertexInterface vertex : shortestPath) {
			Box box = (Box) vertex;
			g.fillOval(box.getX() * boxWidth + factorWidth,
					   box.getY() * boxHeight + factorHeight,
					   boxWidth - 2 * factorWidth,
					   boxHeight - 2 * factorHeight);
		}

		if (pathCounter >= finalPath.size()) {
			mazeApp.setPathDrawn(2);
		}
	}

	/**
	 * The method called when the user clicked on the panel.
	 * It will trigger events such as box edition or shortest path animation.
	 * @param e
	 * 	If in animation mode, trigger the displaying of the next box
	 *  in the shortest pas. If not, catch right clicks to display a context menu
	 *  and left clicks to toggle boxes type.
	 */
	public void mouseClicked(MouseEvent e) {
		int boxRow = getBoxRow(e.getX());
		int boxLine = getBoxLine(e.getY());
		if (mazeApp.isPathDrawn() == 0) {
			// Don't do anything if there is no box where the mouse is
			if (boxRow >= mazeApp.getModelDimensionX()
					|| boxLine >= mazeApp.getModelDimensionY()) {
				return;
			}
			// Toggle box's type if the user clicked the left button,
			// print a menu allowing to choose between different actions
			// if the user clicked the right button.
			if (e.getButton() == MouseEvent.BUTTON3) {
				BoxTypeEditionMenu boxTypeEditionMenu
						= new BoxTypeEditionMenu(mazeApp, boxLine, boxRow);
				boxTypeEditionMenu.show(e.getComponent(), e.getX(), e.getY());
			}
			else if (e.getButton() == MouseEvent.BUTTON1) {
				mazeApp.toggleModelBox(boxLine, boxRow);
			}
		} else {
			shortestPath.add(this.finalPath.get(pathCounter));
			repaint();
			pathCounter += 1;
			return;
		}
		return;
	}

	public void paintShortestPath() {
		paintShortestPath((Graphics2D) getGraphics(), getWidth(), getHeight());
	}

	public void animateShortestPath() {
		animateShortestPath((Graphics2D) getGraphics(), getWidth(), getHeight());
	}

	public void mousePressed(MouseEvent e) {
		return;
	}

	/**
	 * Reponsible for calculating the row number of a box
	 * based on the panel size and the box's width
	 */
	private int getBoxRow(int x) {
		int dimensionX = mazeApp.getModelDimensionX();
		int boxWidth = getWidth() / dimensionX;
		return x / boxWidth;
	}

	/**
	 * Reponsible for calculating the line number of a box
	 * based on the panel size and the box's height
	 */
	private int getBoxLine(int y) {
		int dimensionY = mazeApp.getModelDimensionY();
		int boxHeight = getHeight() / dimensionY;
		return y / boxHeight;
	}

	public void mouseReleased(MouseEvent e) {
		return;
	}

	public void mouseExited(MouseEvent e) {
		return;
	}

	public void mouseEntered(MouseEvent e) {
		return;
	}

	/** Clear the list of boxes in the local shortest path */
	public void clearShortestPath() {
		this.pathCounter = -1;
	}
}
