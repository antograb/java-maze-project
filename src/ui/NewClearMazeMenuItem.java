package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class NewClearMazeMenuItem extends JMenuItem
				implements ActionListener {

	private final MazeApp mazeApp;
	private NewMazeSpinnerBox spinnerBox;

	public NewClearMazeMenuItem(MazeApp mazeApp) {

		super("New clear maze");
		this.mazeApp = mazeApp;
		this.spinnerBox = new NewMazeSpinnerBox(mazeApp);
		this.setAccelerator(KeyStroke.getKeyStroke("control N"));
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		spinnerBox.createAndShowGUI();
	}
}
