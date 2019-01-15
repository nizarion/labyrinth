package ubrah;
import java.io.*;
import java.util.Scanner; 
import java.util.Arrays;
import java.util.LinkedList; 
import java.util.Queue; 
/**
 * TODO Put here a description of what this class does.
 *
 * @author Nizar.
 *         Created 15 Jan 2019.
 */
public class MazeSolver {
	
	Maze inMaze;
	Queue<Integer> Cords_x = new LinkedList<>();
	Queue<Integer> Cords_y = new LinkedList<>();
	int[][][] parents;
	boolean foundEnd;
	int currNode_x;
	int currNode_y;
	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param args
	 */
	public MazeSolver(String FILENAME) {
		try (Scanner sc = new Scanner(new FileReader(FILENAME))) {

			String[] sCurrentLine;
			
			//Parse Maze size
			sCurrentLine = sc.nextLine().trim().split(" ");
			final int WIDTH = Integer.parseInt(sCurrentLine[0]);
			final int HEIGHT = Integer.parseInt(sCurrentLine[1]);
			System.out.println("The maze has "+WIDTH+" width and "+HEIGHT+" height.");
			
			this.inMaze = new Maze(WIDTH, HEIGHT);
			
			//Parse Maze starting and finishing point
			sCurrentLine = sc.nextLine().trim().split(" ");
			final int START_X = Integer.parseInt(sCurrentLine[0]);
			final int START_Y = Integer.parseInt(sCurrentLine[1]);
			System.out.println("The starting point is ("+START_X+", "+START_Y+").");
			
			sCurrentLine = sc.nextLine().trim().split(" ");
			final int END_X = Integer.parseInt(sCurrentLine[0]);
			final int END_Y = Integer.parseInt(sCurrentLine[1]);
			System.out.println("The finishing point is ("+END_X+", "+END_Y+").");
			
			this.inMaze.startAndFinish(START_X, START_Y, END_X, END_Y);
			
			//Parse the maze itself
			System.out.println("-------------MAZE-------------");
			int [][] myMazeArray = new int[HEIGHT][WIDTH];
			while(sc.hasNextLine()) {
				for (int i=0; i<myMazeArray.length; i++) {
					String[] line = sc.nextLine().trim().split(" ");
					for (int j=0; j<line.length; j++) {
						int temp = Integer.parseInt(line[j]);
						myMazeArray[i][j] = temp;
						inMaze.importMazeChar(i, j, temp);
					}
				}
			}
			this.inMaze.importMazeInt(myMazeArray);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private void addToQueue(Queue<Integer> queue_x, Queue<Integer> queue_y, int x, int y) {
		queue_x.add(x);
		queue_y.add(y);
		inMaze.charMaze[y][x] = '.';
	}
	
	private void pollFromQueue(Queue<Integer> queue_x, Queue<Integer> queue_y, int x, int y) {
		
	}
	
	public void discoverPossibleMoves(int x, int y) {
		
		int temp_x = x+1;
		int temp_y = y;
		
		if (inMaze.charMaze[temp_y][temp_x] == ' ') {
			addToQueue(Cords_x, Cords_y, temp_x, temp_y);
			addToParrents(temp_x, temp_y);
		}
		if (inMaze.charMaze[y][x-1] == ' ') {
			addToQueue(Cords_x, Cords_y, x-1, y);
			parents[y][x-1][0] = y;
			parents[y][x-1][1] = x;
		}
		if (inMaze.charMaze[y+1][x] == ' ') {
			addToQueue(Cords_x, Cords_y, x, y+1);
			parents[y+1][x][0] = y;
			parents[y+1][x][1] = x;
		}
		if (inMaze.charMaze[y-1][x] == ' ') {
			addToQueue(Cords_x, Cords_y, x, y-1);
			parents[y-1][x][0] = y;
			parents[y-1][x][1] = x;
		}
		if (inMaze.charMaze[y][x+1] == 'E') {
			parents[y][x+1][0] = y;
			parents[y][x+1][1] = x;
			foundEnd = true;
		}
		if (inMaze.charMaze[y][x-1] == 'E') {
			parents[y][x-1][0] = y;
			parents[y][x-1][1] = x;
			foundEnd = true;
		}
		if (inMaze.charMaze[y+1][x] == 'E') {
			parents[y+1][x][0] = y;
			parents[y+1][x][1] = x;
			foundEnd = true;
		}
		if (inMaze.charMaze[y-1][x] == 'E') {
			parents[y-1][x][0] = y;
			parents[y-1][x][1] = x;
			foundEnd = true;
		}
	}
	
	public void addToParrents(int x, int y) {
		parents[y][x][0] = currNode_y;
		parents[y][x][1] = currNode_x;
	}
	
	public void solveTheMaze() {
//		inMaze.printMaze(inMaze.myMazeArray);
		parents = new int[inMaze.HEIGHT][inMaze.WIDTH][3];
		for (int i=0; i<inMaze.HEIGHT; i++) {
			for (int j=0; j<inMaze.WIDTH; j++) {
				parents[i][j][0] = -1;
				parents[i][j][1] = -1;
				parents[i][j][2] = 99999;
			}
		}
		addToQueue(Cords_x, Cords_y, inMaze.START_X, inMaze.START_Y);
		inMaze.markStartEnd();
		inMaze.printMazeChar();
		foundEnd = false;
		while (Cords_x.size() != 0) {
			
			//Dequeue from both queues
			currNode_x = Cords_x.poll();
			currNode_y = Cords_y.poll();
			discoverPossibleMoves(currNode_x, currNode_y);
			if (foundEnd == true) {
				break;
			}
			inMaze.printMazeChar();
		}
//		int[][] next = new int[1][1];
//		next[0][0] =
		int next_x = inMaze.END_X;
		int next_y = inMaze.END_Y;
		while (inMaze.charMaze[next_y][next_x] != 'S') {
			next_y = parents[next_y][next_x][0];
			next_x = parents[next_y][next_x][1];
			inMaze.charMaze[next_y][next_x] = 'X';
			System.out.println(next_y+"  "+next_x);
			inMaze.printMazeChar();
			
		}
		inMaze.printMazeChar();
	}


}


