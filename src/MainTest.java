import java.util.ArrayList;

import dijkstra.*;
import maze.*;

public class MainTest {

	public static void main(String[] args) {
		
		Maze mazeTest = new Maze("data/labyrinthe.txt") ;
		
		Previous previous = (Previous) Dijkstra.dijkstra(mazeTest, mazeTest.getMaze()[1][1]);
		
		ArrayList<VertexInterface> shortest = previous.getShortestPathTo(mazeTest.getMaze()[5][5]);
	    
		for (VertexInterface vertex : shortest) {
			System.out.println(vertex);
		}
		
		mazeTest.saveToTextFile("data/labyOutput.txt");

	}

}