package maze;

public class ABox extends EBox{
	
	public ABox (String label, int x, int y, Maze maze) {
		
		super(label,x,y,maze) ;
		
	}
	
	@Override
	public boolean isArrival() {
		return true ;
	}

}
