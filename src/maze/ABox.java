package maze;

public class ABox extends Box{
	
	public ABox (String label, int x, int y, Maze maze) {
		
		super(label,x,y,maze) ;
		
	}
	
	@Override
	public boolean isArrival() {
		return true ;
	}

}
