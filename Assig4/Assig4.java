/*
 * Stefon Miller
 * CS0445
 * Tuesday/Thursday 9:30AM REC TUE 4PM
 * This is the driver program for Assig4
 */
import java.io.*;
import java.util.*;
public class Assig4 
{
	public static void main(String[] args)
	{
		//Create scanner for filename input by user
		Scanner fr = null;
		File f;
		String file = args[0];
		f = new File(file);
		Grid g;
       
        try
        {
        	fr = new Scanner(f);
        } 
        catch (FileNotFoundException e) 
        {
        	e.printStackTrace();
        }
       
        //Create a new grid, print it out, and find all solutions for each grid in the txt file
        while(fr.hasNext())
        {
        	g = new Grid(fr);
        	g.print();	
        	g.solve();
        }
	}
	
	
	
}
