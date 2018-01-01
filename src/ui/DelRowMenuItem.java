package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class DelRowMenuItem extends JMenuItem
				implements ActionListener {

	private final MazeApp mazeApp;
	private final int rowNum;

	public DelRowMenuItem(MazeApp mazeApp) {
		super("Delete row");
		this.mazeApp = mazeApp;
		rowNum = -1;
		addActionListener(this);
	}

	public DelRowMenuItem(MazeApp mazeApp, int rowNum) {
		super("Delete row");
		this.mazeApp = mazeApp;
		this.rowNum = rowNum;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (rowNum == -1) {
			mazeApp.delColumn();
		}
		else {
			mazeApp.delColumn(rowNum);
		}
	}
}
