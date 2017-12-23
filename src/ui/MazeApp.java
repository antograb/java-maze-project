package ui;

import javax.swing.*;
import maze.Maze;
import model.MazeAppModel;
import maze.EBox;

import java.util.Observable;
import java.util.Observer;

public class MazeApp extends JFrame
				implements Observer {

	private final MazeMenuBar  mazeMenuBar;
	private final WindowPanel  windowPanel;
	private       MazeAppModel mazeAppModel;

	public MazeApp() {

		super("Maze Solving Application");

		this.mazeAppModel = new MazeAppModel();
		mazeAppModel.addObserver(this);

		setJMenuBar(mazeMenuBar = new MazeMenuBar(this));
		setContentPane(windowPanel = new WindowPanel(this));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();
		setVisible(true);
	}

	public MazeAppModel getMazeAppModel() {
		return mazeAppModel;
	}

	@Override
	public void update(Observable obs, Object param) {

		System.out.println("New maze detected");
		windowPanel.notifyForUpdates(param);
	}

	public void paintShortestPath(Graphics2D g, int width, int height) {

		g.setColor(Color.ORANGE);
		int dimensionX = maze.getDimensionX();
		int dimensionY = maze.getDimensionY();
		int boxWidth = width/dimensionX;
		int boxHeight = height/dimensionY;
		for (VertexInterface vertex : shortest) {
			EBox box = (EBox) vertex;
			g.fillOval(box.getX()*boxHeight, box.getY()*boxWidth, boxWidth, boxHeight);
		}
	}
}
