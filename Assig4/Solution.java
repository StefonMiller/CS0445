/*
 * Stefon Miller
 * CS0445
 * TUE/THUR 9:30 REC TUE 4PM
 * This is a class for the solutions to the grids
 */
public class Solution 
{
	private int[] pathX;		//X coords of the path
	private int[] pathY;		//Y coords of the path
	int segs;					
	private String[][] board;	//Board, used for the print method
	private int rows;			
	private int cols;
	
	//Constructor
	public Solution(int[] pX, int[] pY, int s, String[][] b, int r, int c)
	{
		//Create a copy of the paths
		pathX = new int[pX.length];
		for(int i = 0;i < pX.length; i++)
		{
			pathX[i] = pX[i];
		}
		pathY = new int[pY.length];
		for(int i = 0;i < pY.length; i++)
		{
			pathY[i] = pY[i];
		}
		segs = s;
		board = new String[r][c];
		//Fill copy of board
		for (int i = 0; i < r; i++)
 		{
 			for (int j = 0; j < c; j++)
 			{
 				board[i][j] = b[i][j];
 			}
 		}
		rows = r;
		cols = c;
	}
	//Print out the solution
	public void print()
	{
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("\nPath taken: ");
		for(int i = 0; i < segs; i++)
		{
			System.out.print("(" + pathX[i] + "," + pathY[i] + ") ");
		}
		System.out.println();
	}
	

}
