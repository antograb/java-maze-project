package maze;

/**<h1> MazeReadingException exception </h1>
 * <p>
 * This class is used to throw exception when reading and
 * initiating the maze from a text file.
 *
 *
 */

public final class MazeReadingException
			extends Exception {

	private static final long serialVersionUID = 1L;

	public MazeReadingException (String filename) {
		super("Invalid maze format : " + filename);
	}
}
