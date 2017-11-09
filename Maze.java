
public class Maze 
		implements GraphInterface{

	int dimensionX ;
	int dimensionY ;
	int[][] maze ;
	int[][] adjMatrix ;
	
	public Maze(int dimensionX, int dimensionY, int[][] adjmatrix) {
		
		this.dimensionX = dimensionX ;
		this.dimensionY = dimensionY ;
		this.maze = new int[dimensionX][dimensionY] ;
		this.adjMatrix = adjMatrix ;
		
	}
	
	public int getCost(VertexInterface start, VertexInterface end) {
		
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
