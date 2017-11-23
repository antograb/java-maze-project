
public class WBox extends Box {
	
	
	
	public WBox (String label, int x, int y, Maze maze) {
		
		super(label,x,y,maze) ;
		
		
	}
	
	@Override
	public boolean isWalkable() {
		return false ;
	}	
	
}
