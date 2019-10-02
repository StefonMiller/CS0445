/*
 * Stefon Miller
 * CS0445 Assig2B
 * Tuesday/Thursday 9:30AM, Lab Tuesday 4PM
 * This program times the operations of MyStringBuilder, STringBuilder, and String
 */
import java.io.*;
public class Assig3B 
{
	public static void main(String[] args)
	{
		//Variables for all types being tested
		MyStringBuilder2 msb2;
		MyStringBuilder msb;
		StringBuilder sb;
		String s;
		//Loop iterating 3 times testing each method for all types one by one
		for(int i = 0; i < 3; i++)
		{
			//Initialize variables
			msb2 = new MyStringBuilder2("");
			msb = new MyStringBuilder("");
			sb = new StringBuilder("");
			s = new String("");
			//Test fill, delete, and insert with each iteration of the loop respectively
			switch(i)
			{
				case 0:
					fill(msb2, args[0]);
					fill(msb, args[0]);
					fill(sb, args[0]);
					fill(s, args[0]);
					break;
				case 1:
					delete(msb2,args[0]);
					delete(msb, args[0]);
					delete(sb, args[0]);
					delete(s, args[0]);
					break;
				case 2:
					insert(msb2,args[0]);
					insert(msb, args[0]);
					insert(sb, args[0]);
					insert(s, args[0]);
					break;
			}
		}	
	}
	/**
	 * Fills array w/ Characters and times it
	 * @param s Type of variable being tested
	 * @param d File name used
	 */
	public static void fill(MyStringBuilder2 s, String d)
	{
		
		long time = 0;
		long t1 = 0;
		int character;
		int nc = 0;
		BufferedReader br = null;
		try 
		{
			br = new BufferedReader(new FileReader(d));
		} 
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		}
		try 
		{
			long st1 = System.nanoTime();
			nc = 0;
			while ((character = br.read()) != -1) 
			{
			    s.append(d); 
			    nc++;
			}
			long et1 = System.nanoTime();
			t1 = et1 - st1;
			time = t1/nc;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		//Print out times
		System.out.println("Average time for appending with MyStringBuilder2: " + time + "ns" + " for " + nc + " operations");
		System.out.println("Total time for appending with MyStringBuilder2: " + t1+ "ns" + " for " + nc + " operations");
	}
	/**
	 * Fills array w/ Characters and times it
	 * @param s Type of variable being tested
	 * @param d File name used
	 */
	public static void fill(String s, String d)
	{
		
		long time = 0;
		long t1 = 0;
		int character;
		int nc = 0;
		BufferedReader br = null;
		try 
		{
			br = new BufferedReader(new FileReader(d));
		} 
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		}
		try 
		{
			long st1 = System.nanoTime();
			nc = 0;
			while ((character = br.read()) != -1) 
			{
			    s = s + (char)character; 
			    nc++;
			}
			long et1 = System.nanoTime();
			t1 = et1 - st1;
			time = t1/nc;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		//Print out times
		System.out.println("Average time for appending with String: " + time + "ns" + " for " + nc + " operations");
		System.out.println("Total time for appending with String: " + t1+ "ns" + " for " + nc + " operations");
	}
	/**
	 * Fills array w/ Characters and times it
	 * @param s Type of variable being tested
	 * @param d File name used
	 */
	public static void fill(MyStringBuilder s, String d)
	{
		long time = 0;
		long t1 = 0;
		int character;
		BufferedReader br = null;
		int nc = 0;
		try 
		{
			br = new BufferedReader(new FileReader(d));
		} 
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		}
		try 
		{
			long st1 = System.nanoTime();
			nc = 0;
			while ((character = br.read()) != -1) 
			{
			    s.append((char)character);  
			    nc++;
			    
			}
			long et1 = System.nanoTime();
			t1 = et1 - st1;
			time = t1/nc;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		System.out.println("Average time for appending with MyStringBuilder: " + time+ "ns" + " for " + nc + " operations");
		System.out.println("Total time for appending with MyStringBuilder: " + t1+ "ns" + " for " + nc + " operations");
		
	}
	/**
	 * Fills array w/ Characters and times it
	 * @param s Type of variable being tested
	 * @param d File name used
	 */
	public static void fill(StringBuilder s, String d)
	{
		long time = 0;
		long t1 = 0;
		int character;
		BufferedReader br = null;
		int nc = 0;
		try 
		{
			br = new BufferedReader(new FileReader(d));
		} 
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		}
		try 
		{
			long st1 = System.nanoTime();
			nc = 0;
			while ((character = br.read()) != -1) 
			{
			    s.append((char)character);  
			    nc++;
			}
			long et1 = System.nanoTime();
			t1 = et1 - st1;
			time = t1/nc;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		System.out.println("Average time for appending with StringBuilder: " + time+ "ns" + " for " + nc + " operations");
		System.out.println("Total time for appending with StringBuilder: " + t1+ "ns" + " for " + nc + " operations");
	}
	/**
	 * Fills array w/ Characters and then times how long it takes to delete from the front
	 * @param s Type of variable being tested
	 * @param d File name used
	 */
	public static void delete(MyStringBuilder2 s, String d)
	{
		long time = 0;
		long t1 = 0;
		int character;
		BufferedReader br = null;
		try 
		{
			br = new BufferedReader(new FileReader(d));
		} 
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		}
		int nc = 0;
		try 
		{
			while ((character = br.read()) != -1) 
			{
			    s.append((char)character);  
			    nc++;
			}
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		long st1 = System.nanoTime();
		while(s.length() != 0)
		{
			s.delete(0, 1);
		}
		long et1 = System.nanoTime();
		t1 = et1-st1;
		time  = t1/nc;
		System.out.println("\n\nAverage time for deleting with MyStringBuilder2: " + time+ "ns" + " for " + nc + " operations");
		System.out.println("Total time for deleting with MyStringBuilder2: " + t1+ "ns" + " for " + nc + " operations");
	}
	/**
	 * Fills array w/ Characters and then times how long it takes to delete from the front
	 * @param s Type of variable being tested
	 * @param d File name used
	 */
	public static void delete(MyStringBuilder s, String d)
	{
		long time = 0;
		long t1 = 0;
		int character;
		BufferedReader br = null;
		try 
		{
			br = new BufferedReader(new FileReader(d));
		} 
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		}
		int nc = 0;
		try 
		{
			while ((character = br.read()) != -1) 
			{
			    s.append((char)character);  
			    nc++;
			}
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		long st1 = System.nanoTime();
		while(s.length() != 0)
		{
			s.delete(0, 1);
		}
		long et1 = System.nanoTime();
		t1 = et1-st1;
		time  = t1/nc;
		System.out.println("Average time for deleting with MyStringBuilder: " + time+ "ns" + " for " + nc + " operations");
		System.out.println("Total time for deleting with MyStringBuilder: " + t1+ "ns" + " for " + nc + " operations");
	}
	/**
	 * Fills array w/ Characters and then times how long it takes to delete from the front
	 * @param s Type of variable being tested
	 * @param d File name used
	 */
	public static void delete(StringBuilder s, String d)
	{
		long time = 0;
		long t1 = 0;
		int character;
		BufferedReader br = null;
		try 
		{
			br = new BufferedReader(new FileReader(d));
		} 
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		}
		int nc = 0;
		try 
		{
			while ((character = br.read()) != -1) 
			{
			    s.append((char)character);  
			    nc++;
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		long st1 = System.nanoTime();
		while(s.length() != 0)
		{
			s.delete(0,1);
		}
		long et1 = System.nanoTime();
		t1 = et1-st1;
		time  = t1/nc;
		System.out.println("Average time for deleting with StringBuilder: " + time+ "ns" + " for " + nc + " operations");
		System.out.println("Total time for deleting with StringBuilder: " + t1+ "ns" + " for " + nc + " operations");
	}
	/**
	 * Fills array w/ Characters and then times how long it takes to delete from the front
	 * @param s Type of variable being tested
	 * @param d File name used
	 */
	public static void delete(String s, String d)
	{
		long time = 0;
		long t1 = 0;
		int character;
		BufferedReader br = null;
		try 
		{
			br = new BufferedReader(new FileReader(d));
		} 
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		}
		int nc = 0;
		try 
		{
			while ((character = br.read()) != -1) 
			{
			    s = s + (char)character; 
			    nc++;
			}
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		long st1 = System.nanoTime();
		while(s.length() != 0)
		{
			s = s.substring(1, s.length());
		}
		long et1 = System.nanoTime();
		t1 = et1-st1;
		time  = t1/nc;
		System.out.println("Average time for deleting with String: " + time+ "ns" + " for " + nc + " operations");
		System.out.println("Total time for deleting with String: " + t1+ "ns" + " for " + nc + " operations");
	}
	/**
	 * Adds characters to the middle one by one and times how long it takes
	 * @param s Type of variable being tested
	 * @param d File name used
	 */
	public static void insert(MyStringBuilder2 s, String d)
	{
		long time = 0;
		long t1 = 0;
		int character;
		BufferedReader br = null;
		try 
		{
			br = new BufferedReader(new FileReader(d));
		} 
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		}
		int nc = 0;
		try 
		{
			long st1 = System.nanoTime();
			while ((character = br.read()) != -1) 
			{
				double index = s.length()/2;
			    s.insert((int)(index), (char)character);
			    nc++;
			}
			long et1 = System.nanoTime();
			t1 = et1-st1;
			time  = t1/nc;
		} 
		catch (IOException e) 
		{
			
			e.printStackTrace();
		}
		
		System.out.println("\n\nAverage time for inserting with MyStringBuilder2: " + time+ "ns" + " for " + nc + " operations");
		System.out.println("Total time for inserting with MyStringBuilder2: " + t1+ "ns" + " for " + nc + " operations");
	}
	/**
	 * Adds characters to the middle one by one and times how long it takes
	 * @param s Type of variable being tested
	 * @param d File name used
	 */
	public static void insert(MyStringBuilder s, String d)
	{
		long time = 0;
		long t1 = 0;
		int character;
		BufferedReader br = null;
		try 
		{
			br = new BufferedReader(new FileReader(d));
		} 
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		}
		int nc = 0;
		try 
		{
			long st1 = System.nanoTime();
			while ((character = br.read()) != -1) 
			{
				double index = s.length()/2;
			    s.insert((int)(index), (char)character);
			    nc++;
			}
			long et1 = System.nanoTime();
			t1 = et1-st1;
			time  = t1/nc;
		} 
		catch (IOException e) 
		{
			
			e.printStackTrace();
		}
		
		System.out.println("Average time for inserting with MyStringBuilder: " + time+ "ns" + " for " + nc + " operations");
		System.out.println("Total time for inserting with MyStringBuilder: " + t1+ "ns" + " for " + nc + " operations");
	}
	/**
	 * Adds characters to the middle one by one and times how long it takes
	 * @param s Type of variable being tested
	 * @param d File name used
	 */
	public static void insert(StringBuilder s, String d)
	{
		long time = 0;
		long t1 = 0;
		int character;
		BufferedReader br = null;
		try 
		{
			br = new BufferedReader(new FileReader(d));
		} 
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		}
		int nc = 0;
		try 
		{
			long st1 = System.nanoTime();
			while ((character = br.read()) != -1) 
			{
				double index = s.length()/2;
			    s.insert((int)(index), (char)character);  
			    nc++;
			}
			long et1 = System.nanoTime();
			t1 = et1-st1;
			time  = t1/nc;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		System.out.println("Average time for inserting with StringBuilder: " + time+ "ns" + " for " + nc + " operations");
		System.out.println("Total time for inserting with StringBuilder: " + t1+ "ns" + " for " + nc + " operations");
	}
	/**
	 * Adds characters to the middle one by one and times how long it takes
	 * @param s Type of variable being tested
	 * @param d File name used
	 */
	public static void insert(String s, String d)
	{
		long time = 0;
		long t1 = 0;
		int character;
		int nc = 0;
		BufferedReader br = null;
		try 
		{
			br = new BufferedReader(new FileReader(d));
		} 
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		}
		try 
		{
			nc = 0;
			long st1 = System.nanoTime();
			while ((character = br.read()) != -1) 
			{
				double index = s.length()/2;
			   s = s.substring(0, (int)index) + (char)character + s.substring((int)index, s.length()); 
			   nc++;
			}
			long et1 = System.nanoTime();
			t1 = et1-st1;
			time = t1/nc;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		System.out.println("Average time for inserting with String: " + time+ "ns" + " for " + nc + " operations");
		System.out.println("Total time for inserting with String: " + t1+ "ns" + " for " + nc + " operations");
	}
}
