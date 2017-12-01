import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

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
			System.out.println(currentLine);
			dimensionX = currentLine.length() ; //maze's width
			System.out.println("Line length :" + dimensionX);
			int currentLineLength ;
			int compteurLigne = 0 ;
			
			this.maze = new Box[dimensionX][dimensionY] ;
			
			while (currentLine != null)
			{
				
				//System.out.println(currentLine);
				currentLineLength = currentLine.length() ;
				System.out.println("Current line length :" + currentLineLength);
				
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
		
		System.out.println("dimX :" + dimensionX);
		System.out.println("dimY :" + dimensionY);

		
		//neighbour generation for each box in the maze
		for (int i = 0 ; i < dimensionX ; i++) {
			for (int j = 0 ; j < dimensionY ; j++) {				
				System.out.println(maze[i][j].getLabel());
				System.out.println(maze[i][j].getMaze());
				System.out.println("x : " + maze[i][j].getX() +" y : " + maze[i][j].getY());
				
				maze[i][j].generateNeighbors() ;
			}
			System.out.println();
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
