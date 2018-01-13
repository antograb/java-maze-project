package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public final class AddLineMenuItem extends JMenuItem
				implements ActionListener {

	private final MazeApp mazeApp;
	private final int lineNum;

	public AddLineMenuItem(MazeApp mazeApp) {

		super("Add line");
		this.mazeApp = mazeApp;
		lineNum = -1;
		addActionListener(this);
	}

	public AddLineMenuItem(MazeApp mazeApp, int lineNum) {
		super("Add line");
		this.mazeApp = mazeApp;
		this.lineNum = lineNum;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (lineNum == -1) {
			mazeApp.addEmptyLine();
		}
		else {
			mazeApp.addEmptyLine(lineNum);
		}
	}
}
