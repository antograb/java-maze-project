package ui;

import javax.swing.JOptionPane;
import java.awt.Component;

public final class SaveBox {
	private static String message = "Maze not saved. Save it ?";

	public static boolean promptAndContinue(Component parent,
						MazeApp mazeApp,
						String title) {
		int response = JOptionPane.showOptionDialog(parent,
			message,
			title,
			JOptionPane.YES_NO_CANCEL_OPTION,
			JOptionPane.WARNING_MESSAGE,
			null, null, null);
		switch (response) {
		case JOptionPane.CANCEL_OPTION:
			return false;
		case JOptionPane.OK_OPTION:
			if (mazeApp.getModelFilename() != null) {
				mazeApp.saveModelMaze();
			}
			else {
				String filename = FileBox.save(mazeApp);
				if (filename != null) {
					mazeApp.saveModelMaze(filename);
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
