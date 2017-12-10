package maze;

/**
 * A <code>DBox</code> is the <code>Box</code> on which the user is first in the <code>Maze</code>.
 */

public class DBox extends Box{

	public DBox (String label, int x, int y, Maze maze) {
		
		super(label,x,y,maze) ;
		
	}
	
	@Override
	public boolean isDeparture() {
		return true ;
	}
	
}
