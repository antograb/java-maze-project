package model;

/**
  * This class is used to transport messages about an event happening
  * to the model
  */
public abstract class MazeAppModelMessage {
  /** Indicates that the maze has been renewed
   *(a replacement of the maze -resizing, new maze, ...-
   * or a change on any box)
   */
  public final static String MazeRenewal = "Maze renewed";
  /** Indicates a new file has been selected for the maze to be saved in */
  public final static String FileChange = "File changed";
  /** Indicates the maze has just been saved */
  public final static String MazeSaved = "Maze saved";
  /** Indicates a maze has been loaded from file */
  public final static String MazeLoaded = "Maze loaded";
}
