import java.util.ArrayList;

public class Maze 
		implements GraphInterface{

	private int dimensionX ;
	private int dimensionY ;
	private Box[][] maze ;
	
	public Maze(Box[][] maze) {
		
		this.maze = maze ;
		this.dimensionX = maze.length ;
		this.dimensionY = maze[0].length ;
		
	}
	
	public int getCost(VertexInterface start, VertexInterface end) {
		
		Box startBox = (Box) start ;
		Box endBox = (Box) end ;
		
		if (startBox.getNeighbourList().contains(endBox)) {
			if (startBox.isWalkable() & endBox.isWalkable()) {
				return 1 ;
			}
			else {
				return 0 ;
			}
		}
		
		return 0 ;
	}
	
	public int getVertexNumber() {
		return dimensionX*dimensionY ;
	}

	public ArrayList<VertexInterface> getVertexes() {
		ArrayList<VertexInterface> vertexList = new ArrayList<VertexInterface>() ;
		for (int i = 0 ; i <= dimensionX ; i++) {
			for (int j = 0 ; j <= dimensionY ; j++) {
				vertexList.add(maze[i][j]) ;
			}
		}
		return vertexList ;
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

	public Box[][] getMaze() {
		return maze;
	}

	public void setMaze(Box[][] maze) {
		this.maze = maze;
	}
	
	
}
