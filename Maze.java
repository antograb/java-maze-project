import java.util.Iterator;
import java.util.List;

public class Maze 
		implements GraphInterface{

	int dimensionX ;
	int dimensionY ;
	int[][] maze ;
	
	public Maze(int dimensionX, int dimensionY, int[][] maze) {
		
		this.maze = maze ;
		this.dimensionX = maze.length ;
		this.dimensionY = maze[0].length ;
		
	}
	
	public int getCost(Box start, Box end) {
		
	}
	
	public int getVertexNumber() {
		
	}

	public int getDimensionX() {
		return dimensionX;
	}

	public void setDimensionX(int dimensionX) {
		this.dimensionX = dimensionX;
	}

	public int getDimensionY() {
		return dimensionY;
	}

	public void setDimensionY(int dimensionY) {
		this.dimensionY = dimensionY;
	}

	public int[][] getMaze() {
		return maze;
	}

	public void setMaze(int[][] maze) {
		this.maze = maze;
	}
	
	
	
	
}
