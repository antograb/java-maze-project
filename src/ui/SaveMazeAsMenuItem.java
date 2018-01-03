package ui;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.MazeAppModel;

public class SaveMazeAsMenuItem extends JMenuItem
				implements ActionListener {

	private final MazeApp mazeApp;

	public SaveMazeAsMenuItem(MazeApp mazeApp) {

		super("Save maze as");
		this.mazeApp = mazeApp;
		this.setAccelerator(KeyStroke.getKeyStroke("control shift S"));
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent evt) {

		MazeAppModel mazeAppModel = mazeApp.getMazeAppModel();

		String filename = FileBox.save(mazeApp);
		if (filename != null) {
			mazeApp.getMazeAppModel().saveToFile(filename);
		}
	}
}
