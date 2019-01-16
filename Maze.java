package ubrah;

import java.util.Arrays;

public class Maze {
	int WIDTH;
	int HEIGHT;
	int START_X;
	int START_Y;
	int END_X;
	int END_Y;
	int[][] myMazeArray;
	char[][] charMaze;

	public Maze(int arrWidth, int arrHeight) {
		this.WIDTH = arrWidth;
		this.HEIGHT = arrHeight;
		this.myMazeArray = new int[this.HEIGHT][this.WIDTH];
		this.charMaze = new char[this.HEIGHT][this.WIDTH];
	}

	public void startAndFinish(int start_x, int start_y, int end_x, int end_y) {
		this.START_X = start_x;
		this.START_Y = start_y;
		this.END_X = end_x;
		this.END_Y = end_y;
	}

	public void importMazeInt(int[][] some2DArray) {
		this.myMazeArray = some2DArray;
	}

	public void importMazeChar(int y, int x, int num) {
		if (num == 0) {
			charMaze[y][x] = ' ';
		} else if (num == 1) {
			charMaze[y][x] = '#';
		} else {
			System.out.println("Something wrong happened with conversion");
		}
	}

	public void markStartEnd() {
		charMaze[START_Y][START_X] = 'S';
		charMaze[END_Y][END_X] = 'E';
	}

	public void printMaze(int[][] some2DArray) {
		// start position [marked with 2]
		some2DArray[START_Y][START_X] = 2;
		// end position [marked with 3]
		some2DArray[END_Y][END_X] = 3;
		for (int[] row : some2DArray) {
			System.out.println(Arrays.toString(row).replaceAll(", ", "").replaceAll("\\[|\\]", ""));
		}
	}

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