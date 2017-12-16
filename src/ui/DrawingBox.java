package ui;

import java.awt.Color;
import java.awt.Dimension;
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
}
