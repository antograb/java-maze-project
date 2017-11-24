
public class DBox extends Box{

	public DBox (String label, int x, int y, Maze maze) {
		
		super(label,x,y,maze) ;
		
	}
	
	@Override
	public boolean isDeparture() {
		return true ;
	}
	
}
