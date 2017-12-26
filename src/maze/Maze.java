package maze;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import dijkstra.GraphInterface;
import dijkstra.VertexInterface;

/** <h1> Maze class </h1>
 * <p>
 * This class describes the maze as a matrix of Boxes ({@link maze.Box}).
 * 
 * @author Antonin Godard
 * @author Sylvain Rager
 *
 */
public class Maze 
		implements GraphInterface {

	private int dimensionX ;
	private int dimensionY ;
	private Box[][] maze ;
	
	public Maze(String filename) {
		
		initFromTextFile(filename) ;
		
	}
	
	public Maze(Maze maze) {

		this.dimensionX = maze.getDimensionX();
		this.dimensionY = maze.getDimensionY();
		this.maze = matrixDeepCopy(maze.getMaze());
	}

	public Maze(int numberOfLines, int numberOfRows, Box[][] boxes) {
		this.dimensionY = numberOfLines;
		this.dimensionX = numberOfRows;
		this.maze = matrixDeepCopy(boxes);
	}

	private Box[][] matrixDeepCopy (Box[][] original) {
		int numberOfLines = 0;
		int numberOfRows = 0;
		Box[][] copy;
		/*
		 * We first get the dimensions of the matrix
		 */
		for (Box[] lineBoxes: original) {
			numberOfLines++;
		}
		if (numberOfLines > 0) {
			for (Box box: original[0]) {
				numberOfRows++;
			}
		}
		copy = new Box[numberOfLines][numberOfRows];

		/*
		 * Now we proceed to the deep copy of each box
		 */
		for (int line = 0; line < numberOfLines; line++) {
			for (int row = 0; row < numberOfRows; row++) {
				Box box = original[line][row];
				if (box.isWalkable()) {
					if (box.isDeparture()) {
						copy[line][row] = new DBox(box.getLabel(), box.getY(), box.getX(), this);
					}
					else if (box.isArrival()) {
						copy[line][row] = new ABox(box.getLabel(), box.getY(), box.getX(), this);
					}
					else {
						copy[line][row] = new EBox(box.getLabel(), box.getY(), box.getX(), this);
					}
				}
				else {
					copy[line][row] = new WBox(box.getLabel(), box.getY(), box.getX(), this);
				}
			}
		}
		return copy;
	}

	public static Maze emptyMaze(int dimension) {
		Box[][] maze = new Box[dimension][dimension];
		for (int line = 0; line < dimension; line++) {
			for (int row = 0; row < dimension; row++) {
				maze[line][row] = new EBox("E", line, row, null);
			}
		}
		return new Maze(dimension, dimension, maze);
	}

	public static Maze emptyMaze(int numberOfLines, int numberOfRows) {
		Box[][] maze = new Box[numberOfLines][numberOfRows];
		for (int line = 0; line < numberOfLines; line++) {
			for (int row = 0; row < numberOfRows; row++) {
				maze[line][row] = new EBox("E", line, row, null);
			}
		}
		return new Maze(numberOfLines, numberOfRows, maze);
	}

	public static Maze wallMaze(int dimension) {
		Box[][] maze = new Box[dimension][dimension];
		for (int line = 0; line < dimension; line++) {
			for (int row = 0; row < dimension; row++) {
				maze[line][row] = new WBox("W", line, row, null);
			}
		}
		return new Maze(dimension, dimension, maze);
	}

	public static Maze wallMaze(int numberOfLines, int numberOfRows) {
		Box[][] maze = new Box[numberOfLines][numberOfRows];
		for (int line = 0; line < numberOfLines; line++) {
			for (int row = 0; row < numberOfRows; row++) {
				maze[line][row] = new WBox("W", line, row, null);
			}
		}
		return new Maze(numberOfLines, numberOfRows, maze);
	}

	public int getCost(VertexInterface start, VertexInterface end) {
		
		Box startBox = (Box) start ;
		Box endBox = (Box) end ;

		return startBox.getNeighbourList().contains(endBox) ? 1 : 0 ;
	}
	
	public int getVertexNumber() {
		return dimensionX*dimensionY ;
	}

	public ArrayList<VertexInterface> getVertexes() {
		ArrayList<VertexInterface> vertexList = new ArrayList<VertexInterface>() ;
		for (int line = 0 ; line < dimensionY; line++) {
			for (int row = 0 ; row < dimensionX; row++) {
				if (maze[line][row].isWalkable()) {
					vertexList.add(maze[line][row]) ;
				}
			}
		}
		return vertexList ;
	}
	
	/**Method to determine the departure in the maze. Returns null if the departure is non-existent.
	 * @return Departure vertex
	 */
	public VertexInterface getDeparture() {

		ArrayList<VertexInterface> vertexes = getVertexes();
		for (VertexInterface vertex: vertexes) {
			if (((Box) vertex).isDeparture()) {
				return vertex;
			}
		}
		return null;
	}

	/**Method to determine the arrival in the maze. Returns null if the arrival is non-existent.
	 * @return Arrival vertex
	 */
	public VertexInterface getArrival() {

		ArrayList<VertexInterface> vertexes = getVertexes();
		for (VertexInterface vertex: vertexes) {
			if (((Box) vertex).isArrival()) {
			       return vertex;
			}
		}
		return null;
	}

	public ArrayList<VertexInterface> generateNeighbours(VertexInterface vertex) {
		
		Box box = (Box)vertex ;
		//System.out.println(vertex);
		int x = box.getX() ;
		int y = box.getY() ;
		ArrayList<VertexInterface> neighbourList = new ArrayList<VertexInterface>() ;

		// Always returns an empty list if the departure box is a wall
		if (! box.isWalkable()) {
			return neighbourList ;
		}

		if (x > 0 && maze[y][x-1].isWalkable()) {
			neighbourList.add(maze[y][x-1]) ;
		}
		
		if (x < dimensionX-1 && maze[y][x+1].isWalkable()) {
			neighbourList.add(maze[y][x+1]) ;
		}
		
		if (y > 0 && maze[y-1][x].isWalkable()) {
			neighbourList.add(maze[y-1][x]) ;
		}
		
		if (y < dimensionY-1 && maze[y+1][x].isWalkable()) {
			neighbourList.add(maze[y+1][x]) ;
		}
		
		//System.out.println("  "+ neighbourList);
		return neighbourList ;
		
	}
	
	/**Constructs the maze from a text file, according to each single character on each line. 
	 * @param filename
	 * 	The path to the file containing the representation of the maze.
	 */
	
	private final void initFromTextFile(String filename) {
		
		FileReader     fr = null ;
		BufferedReader br = null ;

		try {
			fr = new FileReader(filename) ;
			br = new BufferedReader(fr) ;
			while (br.readLine() != null) dimensionY++;
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

			this.maze = new Box[dimensionY][dimensionX] ;

			int compteurLigne = 0 ;
			while (currentLine != null)
			{
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
			
			for (int line = 0; line < dimensionY ; line++) {
				for (int row = 0 ; row < dimensionX ; row++) {	
					
					pw.print(maze[line][row].getLabel());
					
				}
				pw.print("\n");
			}
		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { pw.close();} catch(Exception e) {}
		}
	}

	public String toString() {

		String mazeStr = "";
		for (int line = 0; line < dimensionY; line++) {
			for (int row = 0; row < dimensionX; row++) {
				mazeStr += maze[line][row].getLabel();
			}
			mazeStr += "\n";
		}
		return mazeStr;
	}

	public int getDimensionX() {
		return dimensionX;
	}

	public int getDimensionY() {
		return dimensionY;
	}

	public Box[][] getMaze() {
		return maze;
	}

}
