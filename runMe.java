package ubrah;

public class runMe {
	
	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Program start");
//		MazeSolver solver = new MazeSolver("C:\\Users\\Nizar\\JAVAworkspace\\ubrah\\src\\ubrah\\input.txt");
		MazeSolver solver = new MazeSolver("C:\\Users\\Nizar\\JAVAworkspace\\ubrah\\src\\ubrah\\Samples\\medium_input.txt");
		solver.solveTheMaze();
	}

}
