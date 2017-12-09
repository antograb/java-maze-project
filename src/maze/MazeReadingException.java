package maze;

public class MazeReadingException 
			extends Exception {
	
	private String filename ;
	
	public MazeReadingException (String filename) {
		
		super("Invalid maze format : " + filename) ;
		this.filename = filename ;
		
	}
	
}
