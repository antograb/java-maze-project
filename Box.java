import java.util.List;

public abstract class Box
		implements VertexInterface {

	private int x ;
	private int y ;
	private String label ;
	private Maze maze ;
	private List<Box> neighbourList ;
	
	public Box (String label, int x, int y, Maze maze) {
		
		this.label = label ;
		this.x = x ;
		this.y = y ;
		this.maze = maze ;
		
	}
	
	public List<Box> generateNeighbors() {
		
		if (x != 0 && y != 0 && x != maze.getDimensionX() && y != maze.getDimensionY()) {
			this.neighbourList.add(maze.getMaze()[x+1][y]) ;
			this.neighbourList.add(maze.getMaze()[x-1][y]) ;
			this.neighbourList.add(maze.getMaze()[x][y+1]) ;
			this.neighbourList.add(maze.getMaze()[x][y-1]) ;
		}
		
		if (x == 0) {
			this.neighbourList.add(maze.getMaze()[x+1][y]) ;
			
			if (y == maze.getDimensionY()) {
				this.neighbourList.add(maze.getMaze()[x][y-1]) ;
			}
			else if (y == 0) {
				this.neighbourList.add(maze.getMaze()[x][y+1]) ;
			}
			else {
				this.neighbourList.add(maze.getMaze()[x][y-1]) ;
				this.neighbourList.add(maze.getMaze()[x][y+1]) ;
			}
		}
		
		if (y == 0) {
			this.neighbourList.add(maze.getMaze()[x][y+1]) ;
			
			if (x == maze.getDimensionX()) {
				this.neighbourList.add(maze.getMaze()[x-1][y]) ;
			}
			else if (x == 0) {
				this.neighbourList.add(maze.getMaze()[x+1][y]) ;
			}
			else {
				this.neighbourList.add(maze.getMaze()[x-1][y]) ;
				this.neighbourList.add(maze.getMaze()[x+1][y]) ;
			}
		}
		
		if (y == maze.getDimensionY()) {
			this.neighbourList.add(maze.getMaze()[x][y-1]) ;
			
			if (x == maze.getDimensionX()) {
				this.neighbourList.add(maze.getMaze()[x-1][y]) ;
			}
			else if (x == 0) {
				this.neighbourList.add(maze.getMaze()[x+1][y]) ;
			}
			else {
				this.neighbourList.add(maze.getMaze()[x-1][y]) ;
				this.neighbourList.add(maze.getMaze()[x+1][y]) ;
			}
		}
		
		if (x == maze.getDimensionX()) {
			this.neighbourList.add(maze.getMaze()[x-1][y]) ;
			
			if (y == maze.getDimensionY()) {
				this.neighbourList.add(maze.getMaze()[x][y-1]) ;
			}
			else if (y == 0) {
				this.neighbourList.add(maze.getMaze()[x][y+1]) ;
			}
			else {
				this.neighbourList.add(maze.getMaze()[x][y-1]) ;
				this.neighbourList.add(maze.getMaze()[x][y+1]) ;
			}
		}
		
		return neighbourList ;
	}
	
	public boolean compareTo(VertexInterface vertex) {
		if (this.getLabel() == vertex.getLabel()) {
			return true ;
		}
		return false ;
	}
	
	public boolean isWalkable() {
		return true ;
	}
	
	public boolean isArrival() {
		return false ;
	}
	
	public boolean isDeparture() {
		return false ;
	}
	
	public List<Box> getNeighbourList() {
		return neighbourList;
	}

	public void setNeighbourList(List<Box> neighbourList) {
		this.neighbourList = neighbourList;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Maze getMaze() {
		return maze;
	}

	public void setMaze(Maze maze) {
		this.maze = maze;
	}
	
	
	
}
