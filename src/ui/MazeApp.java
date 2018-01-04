package ui;

import javax.swing.*;

import dijkstra.VertexInterface;
import maze.Maze;
import model.MazeAppModel;
import maze.EBox;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MazeApp extends JFrame
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
		System.out.println("New maze detected");
		windowPanel.notifyForUpdates(param);
	}

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
		return;
	}

	public void drawShortestPath() {
		windowPanel.paintShortestPath();
		mazeAppModel.setPathDrawn(2);
	}

	public void animateShortestPath() {
		windowPanel.animateShortestPath();
		mazeAppModel.setPathDrawn(1);
	}

	public void clearShortestPath() {
		windowPanel.clearShortestPath();
		mazeAppModel.setPathDrawn(0);
		this.repaint();
	}

	public void addEmptyColumn(int posColumn) {
		mazeAppModel.addEmptyColumn(posColumn);
	}

	public void addEmptyColumn() {
		mazeAppModel.addEmptyColumn();
	}

	public void delColumn(int posColumn) {
		mazeAppModel.delColumn(posColumn);
	}

	public void delColumn() {
		mazeAppModel.delColumn();
	}

	public void addEmptyLine(int posLine) {
		mazeAppModel.addEmptyLine(posLine);
	}

	public void addEmptyLine() {
		mazeAppModel.addEmptyLine();
	}

	public void delLine(int posLine) {
		mazeAppModel.delLine(posLine);
	}

	public void delLine() {
		mazeAppModel.delLine();
	}

	public void clearMaze() {
		mazeAppModel.clearMaze();
		this.repaint();
	}

	public boolean isModelSaved( ) {
		return this.mazeAppModel.isSaved();
	}

	public void loadModelMaze(String filename) {
		this.mazeAppModel.loadMaze(filename);
	}

	public maze.Box getModelBox(int boxLine, int boxRow) {
		return this.mazeAppModel.getBox(boxLine, boxRow);
	}

	public void setModelABox(int boxLine, int boxRow) {
		this.mazeAppModel.setABox(boxLine, boxRow);
	}

	public void setModelDBox(int boxLine, int boxRow) {
		this.mazeAppModel.setDBox(boxLine, boxRow);
	}

	public void setModelEBox(int boxLine, int boxRow) {
		this.mazeAppModel.setEBox(boxLine, boxRow);
	}

	public void setModelWBox(int boxLine, int boxRow) {
		this.mazeAppModel.setWBox(boxLine, boxRow);
	}

	public int isPathDrawn() {
		return this.mazeAppModel.isPathDrawn();
	}

	public Maze getModelMaze() {
		return this.mazeAppModel.getMaze();
	}

	public ArrayList<VertexInterface> getModelShortest() {
		return this.mazeAppModel.getShortest();
	}

	public ArrayList<VertexInterface> getModelShortestDeepCopy() {
		return this.mazeAppModel.getShortestDeepCopy();
	}

	public int getModelDimensionX() {
		return this.mazeAppModel.getDimensionX();
	}

	public int getModelDimensionY() {
		return this.mazeAppModel.getDimensionY();
	}

	public void setPathDrawn(int i) {
		this.mazeAppModel.setPathDrawn(i);
	}

	public void toggleModelBox(int boxLine, int boxRow) {
		this.mazeAppModel.toggleBox(boxLine, boxRow);
	}
}