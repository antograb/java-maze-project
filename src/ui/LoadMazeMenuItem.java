package ui;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.MazeAppModel;

public class LoadMazeMenuItem extends JMenuItem
				implements ActionListener {

	private final MazeApp mazeApp;

	public LoadMazeMenuItem(MazeApp mazeApp) {

		super("Load maze");
		this.mazeApp = mazeApp;
		this.setAccelerator(KeyStroke.getKeyStroke("control O"));
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent evt) {

		MazeAppModel mazeAppModel = mazeApp.getMazeAppModel();
		if (! mazeAppModel.isSaved()) {
			if (! SaveBox.promptAndContinue(this, mazeApp, "Load maze")){
				return;
			}
		}
		String filename = FileBox.load(mazeApp);
		if (filename != null) {
			mazeApp.getMazeAppModel().loadMaze(filename);
		}
	}
}
