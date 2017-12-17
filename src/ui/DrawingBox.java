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
		
		setOpaque(true);
		setPreferredSize(new Dimension(50,50));
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		setBackground(color);
	}

	public void notifyForUpdates(Object param) {
		this.repaint();
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
