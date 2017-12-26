package ui;

import javax.swing.JOptionPane;
import java.awt.Component;

import model.MazeAppModel;

public class SaveBox {
	private static String message = "Maze not saved. Save it ?";

	public static boolean promptAndContinue(Component parent,
						MazeApp mazeApp,
						String title) {
		MazeAppModel mazeAppModel = mazeApp.getMazeAppModel();
		int response = JOptionPane.showInternalOptionDialog(parent,
			message,
			title,
			JOptionPane.YES_NO_CANCEL_OPTION,
			JOptionPane.WARNING_MESSAGE,
			null, null, null);
		switch (response) {
		case JOptionPane.CANCEL_OPTION:
			return false;
		case JOptionPane.OK_OPTION:
			if (mazeAppModel.getFilename() != null) {
				mazeAppModel.saveToFile();
			}
			else {
				String filename = FileBox.save(mazeApp);
				if (filename != null) {
					mazeApp.getMazeAppModel().saveToFile(filename);
				}
				else {
					return promptAndContinue(null, mazeApp, title);
				}
			}
			break;
		case JOptionPane.NO_OPTION:
			break;
		}
	return true;
	}
}
