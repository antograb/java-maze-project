package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class SetDBoxMenuItem extends JMenuItem
				implements ActionListener {

	private final MazeApp mazeApp;
	private final int boxLine;
	private final int boxRow;

	public SetDBoxMenuItem(MazeApp mazeApp, int boxLine, int boxRow) {
		super("Set Departure Box");
		this.mazeApp = mazeApp;
		this.boxLine = boxLine;
		this.boxRow = boxRow;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent evt) {
		mazeApp.getMazeAppModel().setDBox(boxLine, boxRow);
	}
}
