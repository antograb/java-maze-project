package maze;

/**
 * A <code>WBox</code> is a <code>Box</code>
 * that can't be walked through (a wall).
 */

public class WBox extends Box {

	public WBox (String label, int x, int y, Maze maze) {
		super(label, x, y, maze);
	}

	@Override
	public boolean isWalkable() {
		return false;
	}
}
