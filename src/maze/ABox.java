package maze;

/**
 * <code>ABox</code> is an arrival <code>Box</code>. It's the last <code>Box</code> to be walked through and it's an exit to the <code>Maze</code>.
 */
public class ABox extends EBox{
	
	public ABox (String label, int x, int y, Maze maze) {
		
		super(label,x,y,maze) ;
		
	}
	
	@Override
	public boolean isArrival() {
		return true ;
	}

}
