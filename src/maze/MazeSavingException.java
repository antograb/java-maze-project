package maze;

/**<h1> MazeSavingException exception </h1>
 * <p>
 * This class is used to throw exception when reading and
 * initiating the maze from a text file.
 */
public final class MazeSavingException
			extends Exception {

	private static final long serialVersionUID = 1L;

	public MazeSavingException(String filename) {
		super("Can't save to file " + filename + ".");
	}
}
