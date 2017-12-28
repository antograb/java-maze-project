package maze;

/**
 * A <code>WBox</code> is a <code>Box</code> that can't be walked through (a wall).
 */

public class WBox extends Box {

	public WBox (String label, int y, int x, Maze maze) {

		super(label, y, x, maze) ;


	}
	
	@Override
	public boolean isWalkable() {
		return false ;
	}	
	
}
