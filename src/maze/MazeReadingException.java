package maze;

public class MazeReadingException 
			extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public MazeReadingException (String filename) {
		
		super("Invalid maze format : " + filename) ;
		
	}
	
}
