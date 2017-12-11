package maze;

/**
 * An <code>EBox</code> is a <code>Box</code>
 * that can be walked through (an empty box).
 */

public class EBox extends Box{

	public EBox (String label, int x, int y, Maze maze) {
		super(label, x, y, maze);
	}
}
