package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JMenuItem;

public class AddRowMenuItem extends JMenuItem
				implements ActionListener {

	private final MazeApp mazeApp;

	public AddRowMenuItem(MazeApp mazeApp) {

		super("Add row");
		this.mazeApp = mazeApp;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		this.mazeApp.getMazeAppModel().addEmptyColumn();
	}
}
