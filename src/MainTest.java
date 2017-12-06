import dijkstra.Dijkstra;
import maze.Maze;

public class MainTest {

	public static void main(String[] args) {
		
		Maze mazeTest = new Maze("data/labyrinthe.txt") ;
		
		Dijkstra.dijkstra(mazeTest, mazeTest.getMaze()[1][1]) ;
		
		mazeTest.saveToTextFile("data/labyOutput.txt");

	}

}
