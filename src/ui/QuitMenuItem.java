package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import model.MazeAppModel;

public class QuitMenuItem extends JMenuItem
				implements ActionListener {

	private final MazeApp mazeApp;

	public QuitMenuItem(MazeApp mazeApp) {

		super("Quit");
		this.mazeApp = mazeApp;
		this.setAccelerator(KeyStroke.getKeyStroke("control Q"));
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent evt) {

		if (! mazeApp.isModelSaved()) {
			if (! SaveBox.promptAndContinue(this, mazeApp, "Quit application")) {
				return;
			}
		}
		System.exit(0);
	}
}
