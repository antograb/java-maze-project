package ui;

import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import maze.Box;

public class BoxTypeEditionMenu extends JPopupMenu {
	JMenuItem setWBox;
	JMenuItem setEBox;
	JMenuItem setDBox;
	JMenuItem setABox;
	JMenuItem addRow;
	JMenuItem addLine;
	JMenuItem delRow;
	JMenuItem delLine;

	public BoxTypeEditionMenu(MazeApp mazeApp, int boxLine, int boxRow) {
		add(setEBox = new SetEBoxMenuItem(mazeApp, boxLine, boxRow));
		add(setWBox = new SetWBoxMenuItem(mazeApp, boxLine, boxRow));
		add(setDBox = new SetDBoxMenuItem(mazeApp, boxLine, boxRow));
		add(setABox = new SetABoxMenuItem(mazeApp, boxLine, boxRow));
		Box box = (Box) mazeApp.getMazeAppModel().getMaze().getMaze()[boxLine][boxRow];
		setEBox.setEnabled((box.isWalkable() && (box.isDeparture() ||
			box.isArrival())) || ! box.isWalkable());
		setWBox.setEnabled(box.isWalkable());
		setDBox.setEnabled(! box.isDeparture());
		setABox.setEnabled(! box.isArrival());

		add(addRow = new AddRowMenuItem(mazeApp, boxRow));
		add(addLine = new AddLineMenuItem(mazeApp, boxLine));
		add(delRow = new DelRowMenuItem(mazeApp, boxRow));
		add(delLine = new DelLineMenuItem(mazeApp, boxLine));
	}
}
