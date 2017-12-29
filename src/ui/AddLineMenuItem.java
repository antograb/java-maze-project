package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class AddLineMenuItem extends JMenuItem
				implements ActionListener {

	private final MazeApp mazeApp;

	public AddLineMenuItem(MazeApp mazeApp) {

		super("Add line");
		this.mazeApp = mazeApp;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		this.mazeApp.getMazeAppModel().addEmptyLine();
	}
}
