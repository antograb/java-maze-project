package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class AddRowMenuItem extends JMenuItem
				implements ActionListener {

	private final MazeApp mazeApp;
	private final int rowNum;

	public AddRowMenuItem(MazeApp mazeApp) {

		super("Add row");
		this.mazeApp = mazeApp;
		rowNum = -1;
		addActionListener(this);
	}

	public AddRowMenuItem(MazeApp mazeApp, int rowNum) {

		super("Add row");
		this.mazeApp = mazeApp;
		this.rowNum = rowNum;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (rowNum == -1) {
			mazeApp.addEmptyColumn();
		}
		else {
			mazeApp.addEmptyColumn(rowNum);
		}
	}
}
