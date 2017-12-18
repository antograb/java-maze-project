package ui;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.MazeAppModel;
import model.Maze;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadMazeMenuItem extends JMenuItem
				implements ActionListener {

	private final MazeApp mazeApp;

	public LoadMazeMenuItem(MazeApp mazeApp) {

		super("Load maze");
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
			System.out.println("You chose to open this file: " +
				fileChooser.getSelectedFile().getName());
		}

		String filename = fileChooser.getSelectedFile().getPath();
		mazeApp.getMazeAppModel().setMaze(new Maze(filename));
	}
}
