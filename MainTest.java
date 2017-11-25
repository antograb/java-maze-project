
public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Box[][] box = new Box[2][2] ;
		Maze mazeTest = new Maze(box) ;
		mazeTest.initFromTextFile("data/labyrinthe.txt");

	}

}
