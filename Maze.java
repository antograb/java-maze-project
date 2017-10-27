
public class Maze {

	int dimensionX ;
	int dimensionY ;
	int[][] maze ;
	
	public Maze(int x, int y) {
		
		this.dimensionX = x ;
		this.dimensionY = y ;
		this.maze = new int[dimensionX][dimensionY] ;
		
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
