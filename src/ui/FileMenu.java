package ui;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;
import java.util.Observable;
import javax.swing.JOptionPane;

import model.MazeAppModelMessage;

public final class FileMenu extends JMenu {

	private final MazeApp mazeApp;
	private final LoadMazeMenuItem loadMazeMenuItem;
	private final SaveMazeMenuItem saveMazeMenuItem;
	private final SaveMazeAsMenuItem saveMazeAsMenuItem;
	private final QuitMenuItem quitMenuItem;

	public FileMenu(MazeApp mazeApp) {

		super("File");
		this.mazeApp = mazeApp;

		add(loadMazeMenuItem = new LoadMazeMenuItem(mazeApp));
		add(saveMazeMenuItem = new SaveMazeMenuItem(mazeApp));
		add(saveMazeAsMenuItem = new SaveMazeAsMenuItem(mazeApp));
		add(quitMenuItem = new QuitMenuItem(mazeApp));
	}


	public final class LoadMazeMenuItem extends JMenuItem
			implements ActionListener {

		private final MazeApp mazeApp;

		public LoadMazeMenuItem(MazeApp mazeApp) {

			super("Load maze");
			this.mazeApp = mazeApp;
			this.setAccelerator(KeyStroke.getKeyStroke("control O"));
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent evt) {

			if (! mazeApp.isModelSaved()) {
				if (! SaveBox.promptAndContinue(this, mazeApp, "Load maze")){
					return;
				}
			}
			String filename = FileBox.load(mazeApp);
			if (filename != null) {
				mazeApp.loadModelMaze(filename);
			}
		}
	}


	public final class SaveMazeMenuItem extends JMenuItem
			implements ActionListener, Observer {

		private final MazeApp mazeApp;

		public SaveMazeMenuItem(MazeApp mazeApp) {

			super("Save maze");
			this.mazeApp = mazeApp;
			this.setAccelerator(KeyStroke.getKeyStroke("control S"));
			mazeApp.getMazeAppModel().addObserver(this);
			if (mazeApp.getMazeAppModel().getFilename() == null) {
				this.setEnabled(false);
			}
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent evt) {
			if (mazeApp.getModelFilename() != null) {
				mazeApp.saveModelMaze();
			}
		}

		@Override
		public void update(Observable obs, Object param) {
			if (param == MazeAppModelMessage.MazeRenewal &&
					mazeApp.getModelFilename() != null) {
				this.setEnabled(true);
			}
			else {
				this.setEnabled(false);
			}
		}
	}


	public final class SaveMazeAsMenuItem extends JMenuItem
			implements ActionListener {

		private final MazeApp mazeApp;

		public SaveMazeAsMenuItem(MazeApp mazeApp) {

			super("Save maze as");
			this.mazeApp = mazeApp;
			this.setAccelerator(KeyStroke.getKeyStroke("control shift S"));
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent evt) {
			String filename = FileBox.save(mazeApp);
			if (filename != null) {
				mazeApp.saveModelMaze(filename);
			}
		}
	}


	public final class QuitMenuItem extends JMenuItem
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
}
