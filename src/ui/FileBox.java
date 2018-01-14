package ui;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public final class FileBox {
	private static JFileChooser fileChooser = new JFileChooser();
	private static FileNameExtensionFilter filter =
				new FileNameExtensionFilter("Text files", "txt");

	public static String load(MazeApp mazeApp) {
		fileChooser.setFileFilter(filter);

		int returnVal = fileChooser.showOpenDialog(mazeApp);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
				String filename = fileChooser.getSelectedFile().getPath();
				return filename;
		}
		return null;
	}

	public static String save(MazeApp mazeApp) {
		fileChooser.setFileFilter(filter);

		int returnVal = fileChooser.showSaveDialog(mazeApp);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			String filename = fileChooser.getSelectedFile().getPath();
			return filename;
		}
		return null;
	}
}
