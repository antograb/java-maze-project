package maze;

/**
 * An <code>EBox</code> is a <code>Box</code>
 * that can be walked through (an empty box).
 */

public class EBox extends Box{

	public EBox (String label, int y, int x, Maze maze) {
		super(label, y, x, maze);
	}
}
