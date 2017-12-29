package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class NewMazeSpinnerBox extends JPanel
				implements ActionListener,
						   ChangeListener {

	private final MazeApp mazeApp;
	private JDialog spinnerDialog;
	private int numberOfLines;
	private int numberOfRows;
	private SpinnerModel lineSpinnerModel;
	private SpinnerModel rowSpinnerModel;

	public NewMazeSpinnerBox(MazeApp mazeApp) {


		this.mazeApp = mazeApp;
		JPanel spinnerDialog = new JPanel(new SpringLayout());
		String[] labels = {"Number of lines :", "Number of rows :"};
		int len = labels.length;
		int initialValue = 3;
		int maximumValue = 200;

		this.lineSpinnerModel =
				new SpinnerNumberModel(initialValue, //initial value
									   initialValue, //min
									   maximumValue, //max
									   1);
		this.lineSpinnerModel.addChangeListener(this);
		JSpinner lineSpinner = addLabeledSpinner(this, labels[0], lineSpinnerModel);

		this.rowSpinnerModel =
				new SpinnerNumberModel(initialValue, //initial value
									   initialValue, //min
									   maximumValue, //max
									   1);
		this.rowSpinnerModel.addChangeListener(this);
		JSpinner rowSpinner = addLabeledSpinner(this, labels[0], rowSpinnerModel);
	}

	static protected JSpinner addLabeledSpinner(Container c,
												String label,
												SpinnerModel model) {

		JLabel l = new JLabel(label);
		c.add(l);

		JSpinner spinner = new JSpinner(model);
		l.setLabelFor(spinner);
		c.add(spinner);

		return spinner;
	}

	public void createAndShowGUI() {

		spinnerDialog = new JDialog(spinnerDialog, "Create a new empty maze");
		spinnerDialog.setLayout(new BorderLayout());
		spinnerDialog.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		spinnerDialog.add(new NewMazeSpinnerBox(this.mazeApp),
						  BorderLayout.CENTER);

		JButton OKButton = new JButton("OK");
		OKButton.addActionListener(this);
		spinnerDialog.add(OKButton, BorderLayout.SOUTH);

		spinnerDialog.pack();
		spinnerDialog.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		spinnerDialog.dispose();
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		this.mazeApp.getMazeAppModel()
				.newClearMaze((int) this.lineSpinnerModel.getValue(),
							  (int) this.rowSpinnerModel.getValue());
	}
}
