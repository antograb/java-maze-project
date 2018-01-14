package maze;

/**
 * <code>ABox</code> is an arrival <code>Box</code>.
 * It's the last <code>Box</code> to be walked through and
 * it's an exit to the <code>Maze</code>.
 */
public final class ABox extends EBox{

	public ABox(String label, int y, int x, Maze maze) {
		super(label, y, x, maze) ;
	}

	@Override
	public boolean isArrival() {
		return true;
	}
}
