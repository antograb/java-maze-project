package model;

import java.util.ArrayList;
import maze.Maze;
import maze.Box;
import maze.MazeReadingException;
import maze.MazeSavingException;
import dijkstra.VertexInterface;

public interface MazeModelInterface {
  public void loadMaze(String filename) throws MazeReadingException;
  public void saveToFile(String filename) throws MazeSavingException;
  public Maze getMaze();
  public Box getBox(int boxLine, int boxRow);
  public String getFilename();
  public boolean isSaved();
  public ArrayList<VertexInterface> getShortest();
  public void toggleBox(int boxLine, int boxRow);
  public void setABox(int boxLine, int boxRow);
  public void setEBox(int boxLine, int boxRow);
  public void setDBox(int boxLine, int boxRow);
  public void setWBox(int boxLine, int boxRow);
  public void clearMaze();
  public void addEmptyLine(int posLine);
  public void addEmptyColumn(int posRow);
  public void delLine(int posLine);
  public void delColumn(int posRow);
  public int getDimensionX();
  public int getDimensionY();
}
