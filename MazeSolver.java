package ubrah;

import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class solves a 2D maze.
 * 
 * The constructor reads a file and creates an instance of type Maze. The Maze
 * class should be used for this software to run properly.
 * Please refer to README.txt for input/output formats.
 * 
 * @author Nizar. Created 15 Jan 2019.
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
     * Main function runs.
     * Name and location of the input file are selectable at runtime
     * Full path to the file is sufficient, examples:
     * C:\\Users\\Nizar\\JAVAworkspace\\ubrah\\src\\ubrah\\Samples\\input.txt
     * C:\\Users\\Nizar\\JAVAworkspace\\ubrah\\src\\ubrah\\Samples\\large_input.txt
     * C:\\Users\\Nizar\\JAVAworkspace\\ubrah\\src\\ubrah\\Samples\\small_wrap_input.txt
     * @param args
     */
    public static void main(String[] args) 
    {   
       Scanner input_file = new Scanner(System.in); 
       System.out.println("Welcome to Mazesolver!");   
       System.out.println("Enter a filename please ");   
       // C:\\Users\\Nizar\\JAVAworkspace\\ubrah\\src\\ubrah\\Samples\\large_input.txt
       String filename = input_file.nextLine();
       System.out.println("Opening " + filename);
       MazeSolver solver = new MazeSolver(filename);
       solver.solveTheMaze();
    }

	/**
	 * This constructor parses a file.
	 * Loads the maze and the information provided from the file.
	 * It is assumed that the input file will always follow the 
	 * guidelines provided in the "Maze input/output formats" section
	 *
	 * @param String: FILENAME
	 */
	public MazeSolver(String FILENAME) {
		try (Scanner sc = new Scanner(new FileReader(FILENAME))) {

			String[] sCurrentLine;

			// Parse Maze size
			sCurrentLine = sc.nextLine().trim().split(" ");
			final int WIDTH = Integer.parseInt(sCurrentLine[0]);
			final int HEIGHT = Integer.parseInt(sCurrentLine[1]);
			System.out.println("The maze has " + WIDTH + " width and " + HEIGHT + " height.");

			this.inMaze = new Maze(WIDTH, HEIGHT);

			// Parse Maze starting and finishing point
			sCurrentLine = sc.nextLine().trim().split(" ");
			final int START_X = Integer.parseInt(sCurrentLine[0]);
			final int START_Y = Integer.parseInt(sCurrentLine[1]);
			System.out.println("The starting point is (" + START_X + ", " + START_Y + ").");

			sCurrentLine = sc.nextLine().trim().split(" ");
			final int END_X = Integer.parseInt(sCurrentLine[0]);
			final int END_Y = Integer.parseInt(sCurrentLine[1]);
			System.out.println("The finishing point is (" + END_X + ", " + END_Y + ").");
			
			this.inMaze.startAndFinish(START_X, START_Y, END_X, END_Y);

			// Parse the maze itself
			while (sc.hasNextLine()) {
				for (int i = 0; i < HEIGHT; i++) {
					String[] line = sc.nextLine().trim().split(" ");
					for (int j = 0; j < line.length; j++) {
						int temp = Integer.parseInt(line[j]);
						inMaze.importMazeChar(i, j, temp);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Given a point in the maze (x,y) looks around to find possible next moves.
	 * Calls checkIfWall to check if possible move, is a wall
	 * Valid moves are N, S, E, W only, with no diagonal movement allowed. 
	 * Moves in any of these directions will be blocked by maze walls, which are
	 * identified as 1's in the input file. The edges of the grid should not be 
	 * considered as walls unless specified, and allow for wrapping movement.
	 * 
	 * Also if end point is found it informs the main code to stop searching and draw the path
	 * 
	 * @param x
	 * @param y
	 */
	private void discoverPossibleMoves(int x, int y) {
		
		// if on side
		int temp_x;
		int temp_y;
		if (x < 1) {
			temp_x = inMaze.WIDTH - 1;
			temp_y = y;
			if (checkIfWall(temp_x, temp_y)) {
			//	addToParrents(temp_x, temp_y, x, y);
			}
			checkIfWall(x + 1, y);
			checkIfWall(x, y + 1);
			checkIfWall(x, y - 1);
		} else if (x > inMaze.WIDTH - 2) {
			temp_x = 0 + 1;
			temp_y = y;
			if (checkIfWall(temp_x, temp_y)) {
			//	addToParrents(temp_x, temp_y, x, y);
			}
			checkIfWall(x - 1, y);
			checkIfWall(x, y + 1);
			checkIfWall(x, y - 1);
		} else if (y < 1) {
			temp_x = x;
			temp_y = inMaze.HEIGHT - 1;
			if (checkIfWall(temp_x, temp_y)) {
			//	addToParrents(temp_x, temp_y, x, y);
			}
			checkIfWall(x + 1, y);
			checkIfWall(x - 1, y);
			checkIfWall(x, y + 1);
		} else if (y > inMaze.HEIGHT - 2) {
			temp_x = x;
			temp_y = inMaze.HEIGHT - 1;
			if (checkIfWall(temp_x, temp_y)) {
				//addToParrents(temp_x, temp_y, x, y);
			}
			checkIfWall(x + 1, y);
			checkIfWall(x - 1, y);
			checkIfWall(x, y - 1);

		} else {
			// inside the labirinth

			checkIfWall(x + 1, y);
			checkIfWall(x - 1, y);
			checkIfWall(x, y + 1);
			checkIfWall(x, y - 1);

			// find the end
			if (inMaze.charMaze[y][x + 1] == 'E') {
				parents[y][x + 1][0] = y;
				parents[y][x + 1][1] = x;
				foundEnd = true;
			}
			if (inMaze.charMaze[y][x - 1] == 'E') {
				parents[y][x - 1][0] = y;
				parents[y][x - 1][1] = x;
				foundEnd = true;
			}
			if (inMaze.charMaze[y + 1][x] == 'E') {
				parents[y + 1][x][0] = y;
				parents[y + 1][x][1] = x;
				foundEnd = true;
			}
			if (inMaze.charMaze[y - 1][x] == 'E') {
				parents[y - 1][x][0] = y;
				parents[y - 1][x][1] = x;
				foundEnd = true;
			}
		}
	}

	/**
	 * Checks if the give point is a passage and add it to queue used by BFS, by calling addToQueue() 
	 * also calls addToParrents()
	 * 
	 * @param ch_x
	 * @param ch_y
	 * @return true if did found a passage in this position
	 */
	private boolean checkIfWall(int ch_x, int ch_y) {
		if (inMaze.charMaze[ch_y][ch_x] == ' ') {
			addToQueue(Cords_x, Cords_y, ch_x, ch_y);
			addToParrents(ch_x, ch_y, currNode_x, currNode_y);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * The 2 queues must allways be called together as the provide pairs of (x,y) (points 
	 * in the maze.
	 * Also marks the point with a dot which means the point is discovered
	 * 
	 * @param queue_x queue for x coordinates
	 * @param queue_y queue for y coordinates
	 * @param x x coordinates
	 * @param y y coordinates
	 */
	private void addToQueue(Queue<Integer> queue_x, Queue<Integer> queue_y, int x, int y) {
		queue_x.add(x);
		queue_y.add(y);
		inMaze.charMaze[y][x] = '.';
	}

	/**
	 * Add the previous point that lead to the starting point
	 * Is used to create final path after BFS is finished
	 * 
	 * @param next_x child x
	 * @param next_y child y
	 * @param toAdd_x parent x
	 * @param toAdd_y parent y
	 */
	private void addToParrents(int next_x, int next_y, int toAdd_x, int toAdd_y) {
		// if (parents[y][x][3] )
		parents[next_y][next_x][0] = toAdd_y;
		parents[next_y][next_x][1] = toAdd_x;

	}

	/**
	 * Main code. Contains the algorithm used to find end of the Maze.
	 * Uses BFS (Breadth-first search) which  is an algorithm for traversing 
	 * or searching tree or graph data structures. It starts at the tree root 
	 * and explores all of the neighbor nodes.
	 * Keeps track of each discovered point and the way we should go to find the 
	 * starting point.
	 * After end is discovered if traces back from end to start and prints the 
	 * output.
	 * 
	 */
	public void solveTheMaze() {
		parents = new int[inMaze.HEIGHT][inMaze.WIDTH][3];
		for (int i = 0; i < inMaze.HEIGHT; i++) {
			for (int j = 0; j < inMaze.WIDTH; j++) {
				parents[i][j][0] = -1;
				parents[i][j][1] = -1;
				parents[i][j][2] = 0; // distance from start
			}
		}
		addToQueue(Cords_x, Cords_y, inMaze.START_X, inMaze.START_Y);
		inMaze.markStartEnd();
		inMaze.printMazeChar(false);
		foundEnd = false;
		System.out.println("Exploring the Maze...");
		while (Cords_x.size() != 0) {

			// Dequeue from both queues
			currNode_x = Cords_x.poll();
			currNode_y = Cords_y.poll();
			discoverPossibleMoves(currNode_x, currNode_y);
			if (foundEnd == true) {
				break;
			}
			// inMaze.printMazeChar(false);
		}

		int next_x = inMaze.END_X;
		int next_y = inMaze.END_Y;

		if (foundEnd) {
			System.out.println("Exploration finished, end of the maze was found.");
			System.out.println("The dot (.) shows the explored points.");
			inMaze.printMazeChar(false);
			next_y = parents[next_y][next_x][0];
			next_x = parents[next_y][next_x][1];
			while (next_y > -1 && next_x > -1 && !(inMaze.charMaze[next_y][next_x] == 'S')) {
				inMaze.charMaze[next_y][next_x] = 'X';
				// Debug messages (2)
				// System.out.println(next_y + " " + next_x);
				// inMaze.printMazeChar(true);
				int temp_y = parents[next_y][next_x][0];
				int temp_x = parents[next_y][next_x][1];
				next_y = temp_y;
				next_x = temp_x;
			}
			inMaze.printMazeChar(true);
			System.out.println("Last known position: (" + next_y + ",  " + next_x + ")");
		} else {
			System.out.println("So unfortunate! We could not find the end of the maze!");
			System.out.println("This is how far we got! (where we looked)");
			inMaze.printMazeChar(false);
		}
		System.out.println("\nProgram finish!");
	}

}
