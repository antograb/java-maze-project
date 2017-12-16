package ui;

import java.awt.Color;
import java.awt.Graphics;

public class DepartureDrawingBox extends DrawingBox {

	public DepartureDrawingBox(MazeApp mazeApp, Color color) {
		super(mazeApp, color);
	}

	@Override
	protected final void paintComponent(Graphics g) {

		super.paintComponent(g);
		setBackground(Color.GREEN);
	}
}
