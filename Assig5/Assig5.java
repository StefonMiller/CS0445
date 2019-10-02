/*
 * Stefon Miller
 * CS445 Tuesday 9:30Am
 * Karin Cox Recitation Tuesday 4PM
 * Assig 5 main program
 */
import java.io.*;
import java.util.*;
public class Assig5
{
	public static void main(String[] args)
	{
		//Create file and scanner to read in tree
		File f = new File(args[0]);
		Scanner s = null;
		BinaryTree b = null;	
		String[][] table = new String[2][26];	//Table of conversion codes
		int selection = 0;
		Scanner keyboard = new Scanner(System.in);
		//Read in characters for the conversion table
		for(int i = 0; i < 26; i++)
		{
			table[0][i] = ((Character)(char)(i+65)).toString();
		}
		try 
		{
			s = new Scanner(f);
			b = new BinaryTree(s, table);
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		//Main program loop
		while(!(selection==3))
		{
			selection = showOptions(keyboard);
			
			//Professor Ramirez's favorite control structure!
			switch(selection)
			{
				case 1:
					boolean eFlag = true;
					String enc = null;
					while(eFlag)
					{
						b.displayLetters();
						System.out.println("\nEnter a string to encode");
						enc = keyboard.nextLine();
						//Make sure the user doesn't enter a number
						try
						{
							int test = Integer.parseInt(enc);
							System.out.println("What you entered is invalid, try again");
						}
						catch(NumberFormatException ex)
						{
							eFlag = false;
						}
					}
					//Convert to upper case to make things easier for the user
					String str = b.encodeString(enc.toUpperCase());
					if(str == null)
					{
						System.out.println("The string you entered is invalid, try again.");
					}
					else
					{
						System.out.println("Your encoded string is\n" + str);
					}
					break;
				case 2:
					boolean dFlag = true;
					String dec = null;
					long decN = 2;
					while(dFlag)
					{
						b.displayTable();
						System.out.println("Enter a code to decode");
						dec = keyboard.nextLine();
						//Make sure the user didn't enter a String, basically the opposite of case 1
						try
						{
							decN = Long.parseLong(dec);
							dFlag = false;
						}
						catch(NumberFormatException ex)
						{
							System.out.println("What you entered is invalid, try again");
						}
					}
					//Make sure the string is valid
					StringBuilder decoded = b.decodeString(dec);
					if(decoded.length() == 0)
					{
						System.out.println("What you entered is invalid\n");
					}
					else
					{
						System.out.println("Your decoded string is\n" + decoded.toString());
					}
					break;
			}	
			
		}
		System.out.println("Thank you for using my program.");
	}
	/**
	 * basic method to display options to the user
	 * @param s scanner for keyboard input
	 * @return the user's choice
	 */
	public static int showOptions(Scanner s)
	{
		boolean flag = true;
		int choice = 0;
		while(flag || choice > 3 || choice < 1)
		{
			System.out.println("Please select an operation:\n" + "1) Encode a string\n"
					+ "2) Decode a string\n" + "3) Quit");
			String str = s.nextLine();
			
			try
			{
				choice = Integer.parseInt(str);
				flag = false;
			}
			catch(NumberFormatException e)
			{
				System.err.println("The number you entered is invalid, try again");
			}
			
			
		}
		return choice;
	}
}
