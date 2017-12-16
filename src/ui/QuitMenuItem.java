package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import model.MazeAppModel;

public class QuitMenuItem extends JMenuItem
				implements ActionListener {

	private final MazeApp mazeApp;

	public QuitMenuItem(MazeApp mazeApp) {

		super("Quit");
		this.mazeApp = mazeApp;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent evt) {

	      MazeAppModel mazeAppModel = mazeApp.getMazeAppModel();

	      if (mazeAppModel.isModified()) {
	    	  int response = JOptionPane.showInternalOptionDialog(this,
	                                                             "Maze not saved. Save it ?",
	                                                             "Quit application",
	                                                             JOptionPane.YES_NO_CANCEL_OPTION,
	                                                             JOptionPane.WARNING_MESSAGE,
	                                                             null,null,null);
	    	  switch (response) {
			  	case JOptionPane.CANCEL_OPTION:
			  		return;
			  	case JOptionPane.OK_OPTION:
			  		mazeAppModel.saveToFile();
			  		break;
			  	case JOptionPane.NO_OPTION:
			  		break;
			  }
		  }
	      System.exit(0);
	}
}
