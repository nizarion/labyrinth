package ubrah;
import java.io.*;
import java.util.Scanner; 
import java.util.Arrays;
/**
 * TODO Put here a description of what this class does.
 *
 * @author Nizar.
 *         Created 15 Jan 2019.
 */
public class MazeSolver {

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param args
	 */
	public static void myFileReader(String FILENAME) {
		try (Scanner sc = new Scanner(new FileReader(FILENAME))) {

			String[] sCurrentLine;
			sCurrentLine = sc.nextLine().trim().split(" ");
			final int WIDTH = Integer.parseInt(sCurrentLine[0]);
			final int HEIGHT = Integer.parseInt(sCurrentLine[1]);
			System.out.println("The maze has "+WIDTH+" width and "+HEIGHT+" height.");
			
			sCurrentLine = sc.nextLine().trim().split(" ");
			final int START_X = Integer.parseInt(sCurrentLine[0]);
			final int START_Y = Integer.parseInt(sCurrentLine[1]);
			System.out.println("The starting point is ("+START_X+", "+START_Y+").");
			
			sCurrentLine = sc.nextLine().trim().split(" ");
			final int END_X = Integer.parseInt(sCurrentLine[0]);
			final int END_Y = Integer.parseInt(sCurrentLine[1]);
			System.out.println("The finishing point is ("+END_X+", "+END_Y+").");
			
			System.out.println("-------------MAZE-------------");
			int [][] myMazeArray = new int[WIDTH][HEIGHT];
			while(sc.hasNextLine()) {
				for (int i=0; i<myMazeArray.length; i++) {
					String[] line = sc.nextLine().trim().split(" ");
					for (int j=0; j<line.length; j++) {
						myMazeArray[i][j] = Integer.parseInt(line[j]);
					}
				}
			}
			printMaze(myMazeArray);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param args
	 */
	public static void printMaze(int some2DArray[][]) {
		for (int[] row : some2DArray) {
			System.out.println(Arrays.toString(row).replaceAll(", ", "").replaceAll("\\[|\\]", "").replaceAll("0", " ").replaceAll("1", "#").replaceAll("2", "S").replaceAll("3", "E"));
		}
	}
	
	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param args
	 */
	public static void printMazeOutput(int maze2DArray[][]) {
		for (int[] row : maze2DArray) {
			for (int x : row) {
				
				System.out.print(x);
			}
			System.out.println("");
		}
	}
	
	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub.
		System.out.println("Program start");
		myFileReader("C:\\Users\\Nizar\\JAVAworkspace\\ubrah\\src\\ubrah\\input.txt");

	}

}
