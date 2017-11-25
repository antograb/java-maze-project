import java.io.BufferedReader;
import java.io.FileReader;
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
	
	public final void initFromTextFile(String filename) {
		
		FileReader fr = null ;
		BufferedReader br = null ;
		
		try {
			fr  = new FileReader(filename) ;
			br = new BufferedReader(fr) ;

			
			int lineLength = br.readLine().length() - 1 ;
			int currentLineLength ;
			int cInt = br.read() ;
			
			while (cInt != -1)
			{
				currentLineLength = br.readLine().length() ;
				if (currentLineLength != lineLength) {
					throw new MazeReadingException(filename) ;
				}
				cInt = br.read() ;
			}
		} catch (MazeReadingException mre) { 
			mre.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { br.close() ;} catch (Exception e) {}
		}
		
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
