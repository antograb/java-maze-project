package maze;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
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
public final class Maze
		implements GraphInterface {

	private int dimensionX;
	private int dimensionY;
	private Box[][] maze;

	public Maze(String filename)
			throws MazeReadingException {
		initFromTextFile(filename);
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

	public Maze(Box[][] newMaze) {
		this.dimensionY = newMaze.length;
		if (dimensionY > 0) {
			this.dimensionX = newMaze[0].length;
		}
		else {
			this.dimensionX = 0;
		}
		this.maze = matrixDeepCopy(newMaze);
	}

	/**
	 * matrixDeepCopy methods allow to deep copy a matrix of Box
	 * (as stored in a Maze) or only a part of it inside a new matrix of Box.
	 * The new matrix is therefore a truly new matrix with new boxes, which
	 * are not references to a previously used object.
	 */
	private Box[][] matrixDeepCopy(Box[][] original,
				       Box[][] destination,
				       int numberOfLines,
				       int numberOfRows,
				       int lineOffset,
				       int rowOffset)
	{
		if (destination == null) {
			destination = new Box[numberOfLines][numberOfRows];
		}
		if (original.length < 1) {
			return destination;
		}
		for (int line = lineOffset; line < lineOffset + numberOfLines; line++) {
			for (int row = rowOffset; row < rowOffset + numberOfRows; row++) {
				Box box = original[line][row];
				if (box.isWalkable()) {
					if (box.isDeparture()) {
						destination[line][row] = new DBox(box.getLabel(), box.getY(), box.getX(), this);
					}
					else if (box.isArrival()) {
						destination[line][row] = new ABox(box.getLabel(), box.getY(), box.getX(), this);
					}
					else {
						destination[line][row] = new EBox(box.getLabel(), box.getY(), box.getX(), this);
					}
				}
				else {
					destination[line][row] = new WBox(box.getLabel(), box.getY(), box.getX(), this);
				}
			}
		}
		return destination;
	}

	private Box[][] matrixDeepCopy(Box[][] original,
				       int numberOfLines,
				       int numberOfRows,
				       int lineOffset,
				       int rowOffset,
				       int numberOfDestLines,
				       int numberOfDestRows) {
		return matrixDeepCopy(original,
				      new Box[numberOfDestLines][numberOfDestRows],
				      numberOfLines,
				      numberOfRows,
				      lineOffset,
				      rowOffset);
	}

	private Box[][] matrixDeepCopy(Box [][] original,
				       int numberOfLines,
				       int numberOfRows,
				       int lineOffset,
				       int rowOffset) {
		return matrixDeepCopy(original,
							numberOfLines,
				      numberOfRows,
				      lineOffset,
				      rowOffset,
							numberOfLines,
							numberOfRows);
	}

	private Box[][] matrixDeepCopy(Box[][] original, int numberOfLines, int numberOfRows) {
		return matrixDeepCopy(original, numberOfLines, numberOfRows, 0, 0);
	}

	private Box[][] matrixDeepCopy(Box[][] original) {
		if (original.length < 1) {
			return null;
		}
		return matrixDeepCopy(original, original.length, original[0].length);
	}

	/** Create a square maze of empty boxes of the given dimension */
	public static Maze emptyMaze(int dimension) {
		Box[][] maze = new Box[dimension][dimension];
		for (int line = 0; line < dimension; line++) {
			for (int row = 0; row < dimension; row++) {
				maze[line][row] = new EBox("E", line, row, null);
			}
		}
		return new Maze(dimension, dimension, maze);
	}

	/** Create a rectangle maze of empty boxes of the given dimensions */
	public static Maze emptyMaze(int numberOfLines, int numberOfRows) {
		Box[][] maze = new Box[numberOfLines][numberOfRows];
		for (int line = 0; line < numberOfLines; line++) {
			for (int row = 0; row < numberOfRows; row++) {
				maze[line][row] = new EBox("E", line, row, null);
			}
		}
		return new Maze(numberOfLines, numberOfRows, maze);
	}

	/** Create a square maze of wall boxes of the given dimension */
	public static Maze wallMaze(int dimension) {
		Box[][] maze = new Box[dimension][dimension];
		for (int line = 0; line < dimension; line++) {
			for (int row = 0; row < dimension; row++) {
				maze[line][row] = new WBox("W", line, row, null);
			}
		}
		return new Maze(dimension, dimension, maze);
	}

	/** Create a rectangle maze of wall boxes of the given dimensions */
	public static Maze wallMaze(int numberOfLines, int numberOfRows) {
		Box[][] maze = new Box[numberOfLines][numberOfRows];
		for (int line = 0; line < numberOfLines; line++) {
			for (int row = 0; row < numberOfRows; row++) {
				maze[line][row] = new WBox("W", line, row, null);
			}
		}
		return new Maze(numberOfLines, numberOfRows, maze);
	}

	/** Add a column of empty boxes at the given position */
	public void addEmptyColumn(int posColumn) {
		Box[][] newMaze = matrixDeepCopy(maze,
						 dimensionY,
						 posColumn,
						 0,
						 0,
						 dimensionY,
						 dimensionX + 1);
		for (int line = 0; line < dimensionY; line++) {
			newMaze[line][posColumn] = new EBox("E", line, posColumn, this);
		}
		for (int line = 0; line < dimensionY; line++) {
			for (int row = posColumn; row < dimensionX; row++) {
				Box box = maze[line][row];
				if (box.isWalkable()) {
					if (box.isDeparture()) {
						newMaze[line][row + 1] = new DBox(box.getLabel(), box.getY(), box.getX() + 1, this);
					}
					else if (box.isArrival()) {
						newMaze[line][row + 1] = new ABox(box.getLabel(), box.getY(), box.getX() + 1, this);
					}
					else {
						newMaze[line][row + 1] = new EBox(box.getLabel(), box.getY(), box.getX() + 1, this);
					}
				}
				else {
					newMaze[line][row + 1] = new WBox(box.getLabel(), box.getY(), box.getX() + 1, this);
				}
			}
		}
		maze = newMaze;
		dimensionX = dimensionX + 1;
	}

	public void delColumn(int posColumn) {
		Box[][] newMaze = matrixDeepCopy(maze,
						 dimensionY,
						 posColumn,
						 0,
						 0,
						 dimensionY,
						 dimensionX - 1);
		for (int line = 0; line < dimensionY; line++) {
			for (int row = posColumn + 1; row < dimensionX; row++) {
				Box box = maze[line][row];
				if (box.isWalkable()) {
					if (box.isDeparture()) {
						newMaze[line][row - 1] = new DBox(box.getLabel(), box.getY(), box.getX() - 1, this);
					}
					else if (box.isArrival()) {
						newMaze[line][row - 1] = new ABox(box.getLabel(), box.getY(), box.getX() - 1, this);
					}
					else {
						newMaze[line][row - 1] = new EBox(box.getLabel(), box.getY(), box.getX() - 1, this);
					}
				}
				else {
					newMaze[line][row - 1] = new WBox(box.getLabel(), box.getY(), box.getX() - 1, this);
				}
			}
		}
		maze = newMaze;
		dimensionX = dimensionX - 1;
	}

	public void addEmptyLine(int posLine) {
		Box[][] newMaze = matrixDeepCopy(maze,
						 posLine,
						 dimensionX,
						 0,
						 0,
						 dimensionY + 1,
						 dimensionX);
		for (int row = 0; row < dimensionX; row++) {
			newMaze[posLine][row] = new EBox("E", posLine, row, this);
		}
		for (int line = posLine; line < dimensionY; line++) {
			for (int row = 0; row < dimensionX; row++) {
				Box box = maze[line][row];
				if (box.isWalkable()) {
					if (box.isDeparture()) {
						newMaze[line + 1][row] = new DBox(box.getLabel(), box.getY() + 1, box.getX(), this);
					}
					else if (box.isArrival()) {
						newMaze[line + 1][row] = new ABox(box.getLabel(), box.getY() + 1, box.getX(), this);
					}
					else {
						newMaze[line + 1][row] = new EBox(box.getLabel(), box.getY() + 1, box.getX(), this);
					}
				}
				else {
					newMaze[line + 1][row] = new WBox(box.getLabel(), box.getY() + 1, box.getX(), this);
				}
			}
		}
		dimensionY = dimensionY + 1;
		maze = newMaze;
	}

	public void delLine(int posLine) {
		Box[][] newMaze = matrixDeepCopy(maze,
						 posLine,
						 dimensionX,
						 0,
						 0,
						 dimensionY - 1,
						 dimensionX);
		for (int line = posLine + 1; line < dimensionY; line++) {
			for (int row = 0; row < dimensionX; row++) {
				Box box = maze[line][row];
				if (box.isWalkable()) {
					if (box.isDeparture()) {
						newMaze[line - 1][row] = new DBox(box.getLabel(), box.getY() - 1, box.getX(), this);
					}
					else if (box.isArrival()) {
						newMaze[line - 1][row] = new ABox(box.getLabel(), box.getY() - 1, box.getX(), this);
					}
					else {
						newMaze[line - 1][row] = new EBox(box.getLabel(), box.getY() - 1, box.getX(), this);
					}
				}
				else {
					newMaze[line - 1][row] = new WBox(box.getLabel(), box.getY() - 1, box.getX(), this);
				}
			}
		}
		maze = newMaze;
		dimensionY = dimensionY - 1;
	}

	/** Return the box at given coordinates */
	public Box getBox(int boxLine, int boxRow) {
		return this.maze[boxLine][boxRow];
	}

	/**
	 * Set the box at given coordinates to an arrival box. If an arrival box
	 * was already in the maze, set it to an empty box.
	 */
	public void setABox(int boxLine, int boxRow) {
		if (getArrival() != null) {
			setEBox(((Box) getArrival()).getY(), ((Box) getArrival()).getX());
		}
		this.maze[boxLine][boxRow] = new ABox("A", boxLine, boxRow, this);
		resetNeighbourLists();
	}

	/**
	 * Set the box at given coordinates to a departure box. If a departure box
	 * was already in the maze, set it to an empty box.
	 */
	public void setDBox(int boxLine, int boxRow) {
		if (getDeparture() != null) {
			setEBox(((Box) getDeparture()).getY(), ((Box) getDeparture()).getX());
		}
		this.maze[boxLine][boxRow] = new DBox("D", boxLine, boxRow, this);
		resetNeighbourLists();
	}

	/** Set the box at given coordinates to an empty box */
	public void setEBox(int boxLine, int boxRow) {
		this.maze[boxLine][boxRow] = new EBox("E", boxLine, boxRow, this);
		resetNeighbourLists();
	}

	/** Set the box at given coordinates to a wall box */
	public void setWBox(int boxLine, int boxRow) {
		this.maze[boxLine][boxRow] = new WBox("W", boxLine, boxRow, this);
		resetNeighbourLists();
	}

	public int getCost(VertexInterface start, VertexInterface end) {

		Box startBox = (Box) start;
		Box endBox = (Box) end;

		return generateNeighbours(startBox).contains(endBox) ? 1 : 0;
	}

	public int getVertexNumber() {
		return dimensionX * dimensionY;
	}

	public ArrayList<VertexInterface> getVertexes() {
		ArrayList<VertexInterface> vertexList = new ArrayList<VertexInterface>();
		for (int line = 0 ; line < dimensionY; line++) {
			for (int row = 0 ; row < dimensionX; row++) {
				if (maze[line][row].isWalkable()) {
					vertexList.add(maze[line][row]);
				}
			}
		}
		return vertexList;
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

		Box box = (Box) vertex;
		int x = box.getX();
		int y = box.getY();
		ArrayList<VertexInterface> neighbourList = new ArrayList<VertexInterface>();

		// Always returns an empty list if the departure box is a wall
		if (! box.isWalkable()) {
			return neighbourList;
		}

		if (x > 0 && maze[y][x-1].isWalkable()) {
			neighbourList.add(maze[y][x-1]);
		}

		if (x < dimensionX-1 && maze[y][x+1].isWalkable()) {
			neighbourList.add(maze[y][x+1]);
		}

		if (y > 0 && maze[y-1][x].isWalkable()) {
			neighbourList.add(maze[y-1][x]);
		}

		if (y < dimensionY-1 && maze[y+1][x].isWalkable()) {
			neighbourList.add(maze[y+1][x]);
		}

		return neighbourList;
	}

	/**Constructs the maze from a text file, according to each single character on each line.
	 * @param filename
	 * 	The path to the file containing the representation of the maze.
	 */
	private void initFromTextFile(String filename)
			throws MazeReadingException {

		FileReader     fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader(filename);
			br = new BufferedReader(fr);
			while (br.readLine() != null) dimensionY++;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { br.close(); } catch (Exception e) {}
		}

		try { //generates the maze
			fr = new FileReader(filename);
			br = new BufferedReader(fr);

			String currentLine = null;

			currentLine = br.readLine();
			dimensionX = currentLine.length(); //maze's width
			int currentLineLength;

			this.maze = new Box[dimensionY][dimensionX] ;

			int compteurLigne = 0;
			while (currentLine != null)
			{
				currentLineLength = currentLine.length();

				for (int compteurColonne = 0;
						 compteurColonne < currentLineLength;
						 compteurColonne++) {

					char currentChar = currentLine.charAt(compteurColonne);

					switch(currentChar) {

					case 'W':
						maze[compteurLigne][compteurColonne] =
						   	new WBox("W", compteurLigne, compteurColonne, this) ;
						break;
					case 'E':
						maze[compteurLigne][compteurColonne] =
							new EBox("E", compteurLigne, compteurColonne, this) ;
						break;
					case 'D':
						maze[compteurLigne][compteurColonne] =
							new DBox("D", compteurLigne, compteurColonne, this) ;
						break;
					case 'A':
						maze[compteurLigne][compteurColonne] =
							new ABox("A", compteurLigne, compteurColonne, this) ;
						break;

					default:
						throw new MazeReadingException(filename);

					}
				}

				if (currentLineLength != dimensionX) {
					throw new MazeReadingException(filename);
				}

				currentLine = br.readLine();
				compteurLigne++;
			}
		} catch (IOException e) {
			throw new MazeReadingException(filename);
		} finally {
			try { br.close(); } catch (Exception e) {}
		}
	}

  /**
	 * Save the current maze to a text file.
	 * @param fileName The name of the file in which the maze will be saved
	 */
	public void saveToTextFile(String fileName)
			throws MazeSavingException {

		FileOutputStream fos = null;
		PrintWriter      pw  = null;

		try {

			fos = new FileOutputStream(fileName);
			pw  = new PrintWriter(fos);

			for (int line = 0; line < dimensionY; line++) {
				for (int row = 0; row < dimensionX; row++) {
					pw.print(maze[line][row].getLabel());
				}
				pw.print("\n");
			}
		} catch(IOException e) {
			throw new MazeSavingException(fileName);
		} finally {
			try { pw.close(); } catch(Exception e) {}
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

	/** Gives the number of rows in the maze */
	public int getDimensionX() {
		return dimensionX;
	}

	/** Gives the number of lines in the maze */
	public int getDimensionY() {
		return dimensionY;
	}

	protected Box[][] getMaze() {
		return maze;
	}

	private void resetNeighbourLists() {
		for (Box[] line: maze) {
			for (Box box: line) {
				box.resetNeighbourList();
			}
		}
	}
}
