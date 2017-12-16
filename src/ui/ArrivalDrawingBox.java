package ui;

import java.awt.Color;
import java.awt.Graphics;

public class ArrivalDrawingBox extends DrawingBox {

	public ArrivalDrawingBox(MazeApp mazeApp, Color color) {
		super(mazeApp, color);
	}
	
	@Override
	protected final void paintComponent(Graphics g) {

		super.paintComponent(g);
		setBackground(Color.RED);
	}
}
