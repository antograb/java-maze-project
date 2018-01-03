package maze;

/**
 * A <code>DBox</code> is the <code>Box</code> on which the user
 * is first in the <code>Maze</code>.
 */

public class DBox extends EBox{

	public DBox (String label, int y, int x, Maze maze) {
		super(label, y, x, maze);
	}

	@Override
	public boolean isDeparture() {
		return true;
	}
}
