package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.BorderFactory;

public abstract class DrawingBox extends JPanel {

	private final MazeApp mazeApp;
	private       Color   color;

	public DrawingBox(MazeApp mazeApp, Color color) {

		this.mazeApp = mazeApp;
		this.color   = color;

		setBackground(color);
		setPreferredSize(new Dimension(40,40));
		setBorder(BorderFactory.createLineBorder(Color.orange));
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		setBackground(color);
	}

	public void notifyForUpdates(Object param) {

		repaint();
	}
}
