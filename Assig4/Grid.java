/*
 * Stefon Miller
 * CS0445
 * TUE/THUR 9:30 REC TUE 4PM
 * This is a class for the grids read in from the txt file
 */
import java.util.*;
public class Grid 
{
	int startX;						//Starting x coord from the txt file
	int startY;						//Starting y coord from the txt file
	private String[][] board;		//The board itself
	int[] coordX;					//X coordinates of the path we took
	int[] coordY;					//Y coordinates of the path we took
	private int rows;
	private int cols;				
	boolean[][] beenTo;				//The 'path' we are taking on the maze
	private int xs;					//# of segments
	private int sols;				//# of solutions
	private long calls;				//# of recursive calls
	private String[][] reference;	//A copy of the board used to change the xs back to numbers
	private Solution[] solutions;	//Array of solutions for multiple-solution grids
	
	//Constructor
	public Grid(Scanner s)
	{
		//Get dimensions of board, as well as starting coords, and fill it
		String [] dimensions = (s.nextLine()).split(" ");	
 		rows = Integer.parseInt(dimensions[0]);
 		cols = Integer.parseInt(dimensions[1]);	
 		String [] start = (s.nextLine()).split(" ");
 		startX = Integer.parseInt(start[0]);
 		startY = Integer.parseInt(start[1]);
 		
 		//initialize all arrays needed 
 		board = new String[rows][cols];
 		reference = new String[rows][cols];
 		beenTo = new boolean[rows][cols];
 		//Max path is rows * cols
 		solutions = new Solution[rows * cols];
 		coordX = new int[rows * cols];
 		coordY = new int[rows * cols];
 		sols = 0;
 		//Fill the reference, board, and path arrays
 		for (int i = 0; i < rows; i++)
 		{
 			String rowString = s.nextLine();
 			String[] ints = (rowString.split(" "));
 			for (int j = 0; j < ints.length; j++)
 			{
 				board[i][j] = ints[j];
 				reference[i][j] = ints[j];
 				//When we visit a coord, the boolean var at that location will be false
 				beenTo[i][j] = true;
 			}
 		}
 		
	}
	
	public void solve()
	{
		//Set segments to 0 everytime we try to get a solution
		xs = 0;
		boolean b = false;
		//Recursively solve the maze
		b = solveG(startX,startY,0);
		
		//Show the results of solveG
		if(!b && sols == 0)
		{
			System.out.println("\nNo path found");
			showResults();
		}
		else
		{
			showResults();
		}
	}
	
	public boolean solveG(int sX, int sY, int loc)
	{
		// Check boundary conditions.  Only really needed for the initial call as I implement the same if statement later in the method
		calls++;
		if (sX >= board.length || sX < 0 || sY >= board[0].length || sY < 0 || beenTo[sX][sY] == false || Integer.parseInt(reference[sX][sY]) == 1)
		{
			return false;
		}
		else  	// current character matches
		{
			//Replace the location we are at with an x and add it to the path lists
			boolean answer;
			beenTo[sX][sY] = false;
			board[sX][sY] = "x";  // Change it to an x
			coordX[loc] = sX;
			coordY[loc] = sY;
			xs++;
			
			//If the location is a '2', we have a solution
			if (Integer.parseInt(reference[sX][sY]) == 2)
			{
				board[sX][sY] = "2";
				//Add the solution to our solution array
				Solution s = new Solution(coordX,coordY,xs,board, rows, cols);
				System.out.println("\n\nSolution found with " + s.segs  + " segments");
				s.print();
				//Resize the array if needed
				if(sols >= solutions.length)
				{
					Solution[] temp = new Solution[2 * solutions.length];
					for(int i = 0; i < solutions.length;i++)
					{
						temp[i] = solutions[i];
					}
					solutions = temp;
				}
				solutions[sols] = s;
				sols++;
				answer = true;
			}
			
			//These if statements check the validity of the next step in the path before making a recursive call.
			if (sX >= board.length || sX < 0 || (sY + 1) >= board[0].length || (sY + 1) < 0 || beenTo[sX][sY + 1] == false || Integer.parseInt(reference[sX][sY + 1]) == 1)
			{
				answer = false;
			}
			else
			{
				answer = solveG(sX,sY+1,loc+1);  // Right
			}
			if (!answer)
			{
				if ((sX + 1) >= board.length || (sX + 1) < 0 || sY >= board[0].length || sY < 0 || beenTo[sX + 1][sY] == false || Integer.parseInt(reference[sX + 1][sY]) == 1)
				{
					
				}
				else
				{
					answer = solveG(sX+1,sY,loc+1);  // Down
				}
			}
			if (!answer)
			{
				if (sX >= board.length || sX < 0 || (sY - 1) >= board[0].length || (sY - 1) < 0 || beenTo[sX][sY - 1] == false || Integer.parseInt(reference[sX][sY - 1]) == 1)
				{
					
				}
				else
				{
					answer = solveG(sX,sY-1,loc+1);  // Left
				}
			}
			if (!answer)
			{
				if ((sX - 1) >= board.length || (sX - 1) < 0 || sY >= board[0].length || sY < 0 || beenTo[sX - 1][sY] == false || Integer.parseInt(reference[sX - 1][sY]) == 1)
				{
					
				}
				else
				{
					answer = solveG(sX-1,sY,loc+1);  // Up
				}
			}
			//If no answer is found, reset the variables
			if (!answer)
			{
				board[sX][sY] = reference[sX][sY];
				coordX[loc] = 0;
				coordY[loc] = 0;
				beenTo[sX][sY] = true;
				xs--;
			}
			return answer;
		}
	}
	
	//Prints the grid
	public void print()
	{
		System.out.println("--------------------------------------------------\nNew grid is:\n");
 		for (int i = 0; i < rows; i++)
 		{
 			for (int j = 0; j < cols; j++)
 			{
 				System.out.print(board[i][j] + " ");
 			}
 			System.out.println();
 		}
	}
	
	//Shows the final results
	public void showResults()
	{
		int min = rows * cols;
		int index = 0;
		if(sols == 0)
		{
			System.out.println("\nThere were a total of " + sols + " solutions found.");
			System.out.println("A total of " + calls  + " recursive calls were made");
		}
		else
		{
			for(int i = 0; i < sols; i++)
			{
				if(solutions[i].segs < min)
				{
					min = solutions[i].segs;
					index = i;
				}
			}
			System.out.println("\nThere were a total of " + sols + " solutions found.");
			System.out.println("A total of " + calls + " recursive calls were made");
			System.out.println("The shortest solution has " + solutions[index].segs + " segments");
			solutions[index].print();		
		}
		
		
	}
}
