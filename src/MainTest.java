import maze.Maze;

public class MainTest {

	public static void main(String[] args) {
		
		Maze mazeTest = new Maze("data/labyrinthe.txt") ;

		System.out.println("\n" + mazeTest);
		
		mazeTest.saveToTextFile("data/labyOutput.txt");

	}

}
