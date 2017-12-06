package maze;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import dijkstra.GraphInterface;
import dijkstra.VertexInterface;

public class Maze 
		implements GraphInterface {

	private int dimensionX ;
	private int dimensionY ;
	private Box[][] maze ;
	
	public Maze(String filename) {
		
		initFromTextFile(filename) ;
		
	}
	
	public int getCost(VertexInterface start, VertexInterface end) {
		
		Box startBox = (Box) start ;
		Box endBox = (Box) end ;
		
		if (startBox.getNeighbourList().contains(endBox)) {
			if (startBox.isWalkable() && endBox.isWalkable()) {
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
		for (int i = 0 ; i < dimensionX ; i++) {
			for (int j = 0 ; j < dimensionY ; j++) {
				if (maze[i][j].isWalkable()) {
					vertexList.add(maze[i][j]) ;
				}
			}
		}
		return vertexList ;
	}
	
	public ArrayList<VertexInterface> generateNeighbours(VertexInterface vertex) {
		
		Box box = (Box)vertex ;
		int x = box.getX() ;
		int y = box.getY() ;
		ArrayList<VertexInterface> neighbourList = new ArrayList<VertexInterface>() ;
		
		if (x > 0 && maze[x-1][y].isWalkable()) {
			neighbourList.add(maze[x-1][y]) ;
		}
		
		if (x < dimensionX-1 && maze[x+1][y].isWalkable()) {
			neighbourList.add(maze[x+1][y]) ;
		}
		
		if (y > 0 && maze[x][y-1].isWalkable()) {
			neighbourList.add(maze[x][y-1]) ;
		}
		
		if (y < dimensionY-1 && maze[x][y+1].isWalkable()) {
			neighbourList.add(maze[x][y+1]) ;
		}
		
		return neighbourList ;
		
	}
	
	public final void initFromTextFile(String filename) {
		
		FileReader     fr = null ;
		BufferedReader br = null ;
		
		try { //Determines the height of the maze and stores it into lineCount
			fr = new FileReader(filename) ;
			br = new BufferedReader(fr) ;
			while (br.readLine() != null) dimensionY++;
			//System.out.println("Line count :" + lineCount);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { br.close() ;} catch (Exception e) {}
		}
		
		try { //generates the maze
			fr = new FileReader(filename) ;
			br = new BufferedReader(fr) ;
			
			String currentLine = null ;
			
			currentLine = br.readLine() ;
			dimensionX = currentLine.length() ; //maze's width
			int currentLineLength ;
			int compteurLigne = 0 ;
			
			this.maze = new Box[dimensionX][dimensionY] ;
			
			while (currentLine != null)
			{
				
				//System.out.println(currentLine);
				currentLineLength = currentLine.length() ;
				
				for (int compteurColonne = 0 ; compteurColonne < currentLineLength ; compteurColonne++) {
					
					char currentChar = currentLine.charAt(compteurColonne) ;
					
					switch(currentChar) {
						
					case 'W' : maze[compteurLigne][compteurColonne] = new WBox("W", compteurLigne, compteurColonne, this) ;
						break;
					
					case 'E' : maze[compteurLigne][compteurColonne] = new EBox("E", compteurLigne, compteurColonne, this) ;
						break;
					
					case 'D' : maze[compteurLigne][compteurColonne] = new DBox("D", compteurLigne, compteurColonne, this) ;
						break;
					
					case 'A' : maze[compteurLigne][compteurColonne] = new ABox("A", compteurLigne, compteurColonne, this) ;
						break;
					
					default : throw new MazeReadingException(filename) ;
					
					}
				}
				
				if (currentLineLength != dimensionX) {
					throw new MazeReadingException(filename) ;
				}
				
				currentLine = br.readLine() ;
				compteurLigne++ ;
			}
		} catch (MazeReadingException mre) { 
			mre.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { br.close() ;} catch (Exception e) {}
		}
		
	}
	
	public final void saveToTextFile(String fileName) {
		
		FileOutputStream fos = null ;
		PrintWriter      pw  = null ;
		
		try {
		
			fos = new FileOutputStream(fileName) ;
			pw  = new PrintWriter(fos) ;
			
			for (int i = 0; i < dimensionX ; i++) {
				for (int j = 0 ; j < dimensionY ; j++) {	
					
					pw.print(maze[i][j].getLabel());
					
				}
				pw.print("\n");
			}
		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { pw.close();} catch(Exception e) {}
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
