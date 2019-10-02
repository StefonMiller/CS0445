/*
 * Stefon Miller
 * CS445 Tuesday 9:30Am
 * Karin Cox Recitation Tuesday 4PM
 * Assig 5 tree class with a binarynode inner class
 */
import java.util.*;
public class BinaryTree 
{
	private BinaryNode<Character> root;	
	private int nodes;
	String[][] table;	//Conversion table
	/**
	 * Default Constructor
	 * @param s Scanner for file input
	 * @param t conversion table
	 */
	public BinaryTree(Scanner s, String[][] t)
	{
		String str = "";
		char[] c = null;
		table = t;
		//Create every node
		root = createNode(c,s,str);
		StringBuilder sb = new StringBuilder("");
		//Create the table
		encode(root,sb);
	}
	
	public BinaryNode<Character> createNode(char[] c, Scanner s, String str)
	{
		//Read in a new line and figure out what to do based on it
		BinaryNode<Character> r = null;
		str = s.nextLine();
		c = str.toCharArray();
		//If it is an interior node, recurse
		if(c[0] == 'I')
		{
			r = new BinaryNode<Character>('\0');
			r.leftChild = createNode(c,s,str);
			r.rightChild = createNode(c,s,str);
			
		}
		//If it is a leaf, add it and go back
		else
		{
			r = new BinaryNode<Character>(c[2]);
		}
		return r;
	}
	/**
	 * Creates the conversion table
	 * @param r root node
	 * @param s stringbuilder for codes
	 */
	public void encode(BinaryNode<Character> r, StringBuilder s)
	{
		//Add a zero if you have to go left
		 if(r.leftChild != null)
		 {
			 s.append("0");
			 encode(r.leftChild,s);
		 }
		 //If you get to a leaf, add its code to the table
		 if(r.isLeaf())
		 {
			 int loc = ((int)(r.data) - 65);
			 table[1][loc] = s.toString();
		 }
		 //Append a 1 if you have to go right
		 if(r.rightChild != null)
		 {
			 s.append("1");
			 encode(r.rightChild,s);
		 }
		 //If we backtrack, delete the last char
		 if(s.length() != 0)
		 {
			 s.deleteCharAt(s.length() - 1);
		 }
	}
	//Print out the conversion table
	public void displayTable()
	{
		System.out.println("\nYour encoding table is:");
		for(int i = 0; i < 26; i++)
		{
			if(table[1][i] == null)
			{
				
			}
			else
			{
				char c = (char)(i+65);
				System.out.println(c + ": "  + table[1][i]);
			}
		}
	}
	//Show the user the available letters for encoding
	public void displayLetters()
	{
		System.out.println("\nAvailable letters are:");
		for(int i = 0; i < 26; i++)
		{
			if(table[1][i] == null)
			{
				
			}
			else
			{
				char c = (char)(i+65);
				System.out.print(c + " " );
			}
		}
	}
	/**
	 * Takes a user input string and makes it into a code via the table
	 * @param s string entered
	 * @return converted code
	 */
	public String encodeString(String s)
	{
		
		StringBuilder sb = new StringBuilder("");
		char[] c = s.toCharArray();
		//For every character in the string, append its code to a stringbuilder
		for(int i = 0; i < c.length; i++)
		{
			int loc = ((int)c[i] - 65);
			if(table[1][loc] == null)
			{
				return null;
			}
			else
			{
				sb.append(table[1][loc]);
				sb.append("\n");
			}
		}
		return sb.toString();
		
	}
	/**
	 * Driver method for recursive decoding method
	 * @param l string entered
	 * @return decoded string
	 */
	public StringBuilder decodeString(String l)
	{
		StringBuilder s = new StringBuilder("");
		char[] c = l.toCharArray();
		//Make sure the user didn't enter 2's, 3's, etc.
		for(int i = 0; i < c.length; i++)
		{
			if(c[i] != '1' && c[i] != '0')
			{
				s.setLength(0);
				return s;
			}
		}
		StringBuilder sb = (decode(s,c,0, root));
		return sb;
	}
	/**
	 * Decodes a series of 1's and 0's into a string
	 * @param s stringbuilder
	 * @param c array of 1's and 0's
	 * @param loc current location
	 * @param r root
	 * @return converted string
	 */
	private StringBuilder decode(StringBuilder s, char[] c, int loc, BinaryNode<Character> r)
	{
		//Base case, we are at the last letter
		if(loc == c.length)
		{
			//If we are on an interior node and we are done, the string entered is invalid
			if(r.data == '\0')
			{
				s.setLength(0);
				return s;
			}
			//If we get to the end on a leaf node, the string is valid
			else
			{
				s.append(r.data);
			}
		}
		//If we are on an interior node, recurse
		else if(r.data == '\0')
		{
			if(c[loc] == '1')
			{
				loc++;
				s = decode(s,c,loc,r.rightChild);
			}
			else
			{
				loc++;
				decode(s,c,loc,r.leftChild);
			}
		}
		//If we are not on an interior node, append the data and recurse
		else
		{
			s.append(r.data);
			decode(s,c,loc,root);
		}
		
		return s;
	}
	/*
	 * Private inner class taken from the author's BinaryNode class
	 */
	private class BinaryNode<T>
	{
	   private T             data;
	   private BinaryNode<T> leftChild;  // Reference to left child
	   private BinaryNode<T> rightChild; // Reference to right child

	   public BinaryNode()
	   {
	      this(null); // Call next constructor
	   } // end default constructor

	   public BinaryNode(T dataPortion)
	   {
	      this(dataPortion, null, null); // Call next constructor
	   } // end constructor

	   public BinaryNode(T dataPortion, BinaryNode<T> newLeftChild,
	                                    BinaryNode<T> newRightChild)
	   {
	      data = dataPortion;
	      leftChild = newLeftChild;
	      rightChild = newRightChild;
	   } // end constructor

	  
	   /** Detects whether this node is a leaf.
	       @return  True if the node is a leaf. */
	   public boolean isLeaf()
	   {
	      return (leftChild == null) && (rightChild == null);
	   } // end isLeaf

	   /** Counts the nodes in the subtree rooted at this node.
	       @return  The number of nodes in the subtree rooted at this node. */
	   public int getNumberOfNodes()
	   {
	      int leftNumber = 0;
	      int rightNumber = 0;
	      
	      if (leftChild != null)
	         leftNumber = leftChild.getNumberOfNodes();
	      
	      if (rightChild != null)
	         rightNumber = rightChild.getNumberOfNodes();
	      
	      return 1 + leftNumber + rightNumber;
	   } 
	}
}
