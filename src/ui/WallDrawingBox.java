package ui;

import java.awt.Color;
import java.awt.Graphics;

public class WallDrawingBox extends DrawingBox {

	public WallDrawingBox(MazeApp mazeApp, Color color) {
		super(mazeApp, color);
	}

	@Override
	protected final void paintComponent(Graphics g) {

		super.paintComponent(g);
		setBackground(Color.BLACK);
	}
}
