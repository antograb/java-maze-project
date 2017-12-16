package ui;

import java.awt.Color;
import java.awt.Graphics;

public class EmptyDrawingBox extends DrawingBox {

	public EmptyDrawingBox(MazeApp mazeApp, Color color) {
		super(mazeApp, color);
	}

	@Override
	protected final void paintComponent(Graphics g) {

		super.paintComponent(g);
		setBackground(Color.WHITE);
	}
}
