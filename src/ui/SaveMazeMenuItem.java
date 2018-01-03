package ui;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;
import java.util.Observable;

import model.MazeAppModel;

public class SaveMazeMenuItem extends JMenuItem
				implements ActionListener, Observer {

	private final MazeApp mazeApp;

	public SaveMazeMenuItem(MazeApp mazeApp) {

		super("Save maze");
		this.mazeApp = mazeApp;
		this.setAccelerator(KeyStroke.getKeyStroke("control S"));
		mazeApp.getMazeAppModel().addObserver(this);
		if (mazeApp.getMazeAppModel().getFilename() == null) {
			this.setEnabled(false);
		}
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent evt) {

		MazeAppModel mazeAppModel = mazeApp.getMazeAppModel();

		if (mazeAppModel.getFilename() != null) {
			mazeAppModel.saveToFile();
		}
	}

	@Override
	public void update(Observable obs, Object param) {
		if (mazeApp.getMazeAppModel().getFilename() != null) {
			this.setEnabled(true);
		}
		else {
			this.setEnabled(false);
		}
	}
}
