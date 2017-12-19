package ui;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.MazeAppModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveMazeMenuItem extends JMenuItem
				implements ActionListener {

	private final MazeApp mazeApp;

	public SaveMazeMenuItem(MazeApp mazeApp) {

		super("Save maze");
		this.mazeApp = mazeApp;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent evt) {

		MazeAppModel mazeAppModel = mazeApp.getMazeAppModel();
		JFileChooser fileChooser = new JFileChooser();

		FileNameExtensionFilter filter =
			new FileNameExtensionFilter("Text files", "txt");
		fileChooser.setFileFilter(filter);

		int returnVal = fileChooser.showOpenDialog(mazeApp);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to save to this file: " +
				fileChooser.getSelectedFile().getName());
		}

		String filename = fileChooser.getSelectedFile().getPath();
		mazeApp.getMazeAppModel().saveToFile(filename);
	}
}
