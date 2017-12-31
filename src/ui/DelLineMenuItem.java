package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class DelLineMenuItem extends JMenuItem
				implements ActionListener {

	private final MazeApp mazeApp;
	private final int lineNum;

	public DelLineMenuItem(MazeApp mazeApp) {

		super("Delete line");
		this.mazeApp = mazeApp;
		lineNum = -1;
		addActionListener(this);
	}

	public DelLineMenuItem(MazeApp mazeApp, int lineNum) {
		super("Delete line");
		this.mazeApp = mazeApp;
		this.lineNum = lineNum;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (lineNum == -1) {
			mazeApp.delLine();
		}
		else {
			mazeApp.delLine(lineNum);
		}
	}
}
