package ui;

import javax.swing.*;

import dijkstra.VertexInterface;
import maze.Maze;
import model.MazeAppModel;
import maze.EBox;
import maze.MazeReadingException;
import maze.MazeSavingException;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public final class MazeApp extends JFrame
	implements Observer, WindowListener {

	private final MazeMenuBar  mazeMenuBar;
	private final WindowPanel  windowPanel;
	private       MazeAppModel mazeAppModel;

	public MazeApp() {

		super("Maze Solving Application");

		this.mazeAppModel = new MazeAppModel();
		mazeAppModel.addObserver(this);

		addWindowListener(this);
		setJMenuBar(mazeMenuBar = new MazeMenuBar(this));
		setContentPane(windowPanel = new WindowPanel(this));

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		pack();
		setVisible(true);
	}

	public MazeAppModel getMazeAppModel() {
		return mazeAppModel;
	}

	@Override
	public void update(Observable obs, Object param) {
		windowPanel.notifyForUpdates(param);
	}

	/**
	 * Exit the application, asking if the user wants to save an unsaved
	 * maze or not
	 */
	public void windowClosing(WindowEvent e) {
		if (! mazeAppModel.isSaved()) {
			if (! SaveBox.promptAndContinue(null, this, "Quit application")) {
				return;
			}
		}
		System.exit(0);
	}

	public void windowDeactivated(WindowEvent e) {
		return;
	}

	public void windowActivated(WindowEvent e) {
		return;
	}

	public void windowDeiconified(WindowEvent e) {
		return;
	}

	public void windowIconified(WindowEvent e) {
		return;
	}

	public void windowClosed(WindowEvent e) {
		return;
	}

	public void windowOpened(WindowEvent e) {
		JOptionPane.showMessageDialog(this,
			"Welcome to this maze solving application propelled by"
			+ " Dijkstra's algorithm.\n\n"
			+ "White boxes are walls, black boxes are empty and walkable.\n"
			+ "Feel free to edit boxes type by right clicking on them.\n\n"
			+ "You can find an example maze in data/labyrinthe.txt.",
			"Welcome",
			JOptionPane.INFORMATION_MESSAGE);
		return;
	}

	/** Draw the full path and update its state in the model */
	public void drawShortestPath() {
		windowPanel.paintShortestPath();
		mazeAppModel.setPathDrawn(2);
	}

	/**
	 * Trigger the animation of path drawing and update its state in
	 * the model
	 */
	public void animateShortestPath() {
		windowPanel.animateShortestPath();
		mazeAppModel.setPathDrawn(1);
	}

	/** Clear the shortest path drawn and update its state in the model */
	public void clearShortestPath() {
		windowPanel.clearShortestPath();
		mazeAppModel.setPathDrawn(0);
		this.repaint();
	}

	/** @see model.MazeAppModel */
	public void addEmptyColumn(int posColumn) {
		mazeAppModel.addEmptyColumn(posColumn);
	}

	/** @see model.MazeAppModel */
	public void addEmptyColumn() {
		mazeAppModel.addEmptyColumn();
	}

	/** @see model.MazeAppModel */
	public void delColumn(int posColumn) {
		mazeAppModel.delColumn(posColumn);
	}

	/** @see model.MazeAppModel */
	public void delColumn() {
		mazeAppModel.delColumn();
	}

	/** @see model.MazeAppModel */
	public void addEmptyLine(int posLine) {
		mazeAppModel.addEmptyLine(posLine);
	}

	/** @see model.MazeAppModel */
	public void addEmptyLine() {
		mazeAppModel.addEmptyLine();
	}

	/** @see model.MazeAppModel */
	public void delLine(int posLine) {
		mazeAppModel.delLine(posLine);
	}

	/** @see model.MazeAppModel */
	public void delLine() {
		mazeAppModel.delLine();
	}

	/** @see model.MazeAppModel */
	public void clearMaze() {
		mazeAppModel.clearMaze();
		this.repaint();
	}

	/** @see model.MazeAppModel */
	public boolean isModelSaved( ) {
		return this.mazeAppModel.isSaved();
	}

	/** @see model.MazeAppModel */
	public void loadModelMaze(String filename) throws MazeReadingException {
		this.mazeAppModel.loadMaze(filename);
	}

	/** @see model.MazeAppModel */
	public maze.Box getModelBox(int boxLine, int boxRow) {
		return this.mazeAppModel.getBox(boxLine, boxRow);
	}

	/** @see model.MazeAppModel */
	public void saveModelMaze(String filename) throws MazeSavingException {
		mazeAppModel.saveToFile(filename);
	}

	/** @see model.MazeAppModel */
	public void saveModelMaze() throws MazeSavingException {
		mazeAppModel.saveToFile();
	}

	/** @see model.MazeAppModel */
	public String getModelFilename() {
		return mazeAppModel.getFilename();
	}

	/** @see model.MazeAppModel */
	public void setModelABox(int boxLine, int boxRow) {
		this.mazeAppModel.setABox(boxLine, boxRow);
	}

	/** @see model.MazeAppModel */
	public void setModelDBox(int boxLine, int boxRow) {
		this.mazeAppModel.setDBox(boxLine, boxRow);
	}

	/** @see model.MazeAppModel */
	public void setModelEBox(int boxLine, int boxRow) {
		this.mazeAppModel.setEBox(boxLine, boxRow);
	}

	/** @see model.MazeAppModel */
	public void setModelWBox(int boxLine, int boxRow) {
		this.mazeAppModel.setWBox(boxLine, boxRow);
	}

	/** @see model.MazeAppModel */
	public int isPathDrawn() {
		return this.mazeAppModel.isPathDrawn();
	}

	/** @see model.MazeAppModel */
	public Maze getModelMaze() {
		return this.mazeAppModel.getMaze();
	}

	/** @see model.MazeAppModel */
	public ArrayList<VertexInterface> getModelShortest() {
		return this.mazeAppModel.getShortest();
	}

	/** @see model.MazeAppModel */
	public int getModelDimensionX() {
		return this.mazeAppModel.getDimensionX();
	}

	/** @see model.MazeAppModel */
	public int getModelDimensionY() {
		return this.mazeAppModel.getDimensionY();
	}

	/** @see model.MazeAppModel */
	public void setPathDrawn(int i) {
		this.mazeAppModel.setPathDrawn(i);
	}

	/** @see model.MazeAppModel */
	public void toggleModelBox(int boxLine, int boxRow) {
		this.mazeAppModel.toggleBox(boxLine, boxRow);
	}
}
