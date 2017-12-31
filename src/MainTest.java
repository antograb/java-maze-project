import java.util.ArrayList;

import dijkstra.*;
import maze.*;
import ui.*;

public class MainTest {

	public static void main(String[] args) {

		Maze mazeTest = new Maze("data/labyrinthe.txt") ;
		System.out.println(mazeTest);
		mazeTest.saveToTextFile("data/labyOutput.txt");
		VertexInterface departure = mazeTest.getDeparture();
		VertexInterface arrival = mazeTest.getArrival();

		Previous previous = (Previous) Dijkstra.dijkstra(mazeTest, departure);

		ArrayList<VertexInterface> shortest = previous.getShortestPathTo(arrival);

		//Print shortest path to arrival
		System.out.println(arrival);
		for (VertexInterface vertex : shortest) {
			System.out.println(vertex);
		}

		MazeApp app = new MazeApp();

	}

}
