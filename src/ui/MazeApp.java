package ui;

import javax.swing.*;
import maze.Maze;
import model.MazeAppModel;

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
}
