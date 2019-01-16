package ubrah;

import java.util.Arrays;

/**
 * @author Nizar
 * This class creates and stores a Maze instance.
 * It is also responsible to print the maze to the console.
 * Maze outputs are written to the console as per the "Maze input/output formats" guidelines
 * Arbitrary sized mazes can be handled
 * 
 *
 */
public class Maze {
	int WIDTH;
	int HEIGHT;
	int START_X;
	int START_Y;
	int END_X;
	int END_Y;
	int[][] myMazeArray;
	char[][] charMaze; //this array is used for main computation

	/**
	 * Constructs a MAze instance given its size
	 * @param arrWidth
	 * @param arrHeight
	 */
	public Maze(int arrWidth, int arrHeight) {
		this.WIDTH = arrWidth;
		this.HEIGHT = arrHeight;
		this.myMazeArray = new int[this.HEIGHT][this.WIDTH];
		this.charMaze = new char[this.HEIGHT][this.WIDTH];
	}

	/** 
	 * Import  starting and finishing points (x,y)
	 * @param start_x
	 * @param start_y
	 * @param end_x
	 * @param end_y
	 */
	public void startAndFinish(int start_x, int start_y, int end_x, int end_y) {
		this.START_X = start_x;
		this.START_Y = start_y;
		this.END_X = end_x;
		this.END_Y = end_y;
	}

	public void importMazeInt(int[][] some2DArray) {
		this.myMazeArray = some2DArray;
	}

	/**
	 * Input 0 or 1 and (x,y) point and created a 2D character array.
	 * 
	 * @param y
	 * @param x
	 * @param num
	 */
	public void importMazeChar(int y, int x, int num) {
		if (num == 0) {
			charMaze[y][x] = ' ';
		} else if (num == 1) {
			charMaze[y][x] = '#';
		} else {
			System.out.println("Something wrong happened with conversion");
		}
	}

	/**
	 * Must be called to mark the staring and ending of the maze
	 */
	public void markStartEnd() {
		charMaze[START_Y][START_X] = 'S';
		charMaze[END_Y][END_X] = 'E';
	}

	/**
	 * Prints the given array of integers to the console
	 * 
	 * @param some2DArray
	 */
	public void printMazeInt(int[][] some2DArray) {
		//TODO make this so it can be used even if the maze is not constructed 
		// start position [marked with 2]
		some2DArray[START_Y][START_X] = 2;
		// end position [marked with 3]
		some2DArray[END_Y][END_X] = 3;
		for (int[] row : some2DArray) {
			System.out.println(Arrays.toString(row).replaceAll(", ", "").replaceAll("\\[|\\]", ""));
		}
	}

	/**
	 * Prints the charMaze array, if removeDots is true replaces the dots with passages (space)
	 * charMaze has the right output form
	 * 
	 * @param removeDots
	 */
	public void printMazeChar(boolean removeDots) {
		// start position [marked with S]
		// end position [marked with E]
		// path/trace [marked with X]
		// discovered position [marked with .]
		System.out.println("\n-------------MAZE-------------");
		if (removeDots) {
			for (char[] row : charMaze) {
				System.out.println(
						Arrays.toString(row).replaceAll(", ", "").replaceAll("\\[|\\]", "").replaceAll("\\.", " "));
			}
		} else {
			for (char[] row : charMaze) {
				System.out.println(Arrays.toString(row).replaceAll(", ", "").replaceAll("\\[|\\]", ""));
			}
		}
		System.out.println("------------------------------\n");

	}

}