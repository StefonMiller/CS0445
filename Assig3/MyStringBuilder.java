/*
 * Stefon Miller
 * CS0445 Assig2
 * Tuesday/Thursday 9:30AM
 * Tuesday 4PM lab
 */
public class MyStringBuilder
{
	
	
	private CNode firstC;	//First node
	private CNode lastC; 	//Last node
	private int length;  	//All nodes

	/**
	 * Creates a new MyStringBuilder from a string 
	 * @param s String that will be converted
	 */
	public MyStringBuilder(String s)
	{
		if (s == null || s.length() == 0) // Special case for empty String
		{					 			  // or null reference
			firstC = null;
			lastC = null;
			length = 0;
		}
		else
		{
			// Create front node to get started
			firstC = new CNode(s.charAt(0));
			length = 1;
			CNode currNode = firstC;
			// Create remaining nodes, copying from String. 
			for (int i = 1; i < s.length(); i++)
			{
				CNode newNode = new CNode(s.charAt(i));
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
			lastC = currNode;
		}
	}
	

	// Create a new MyStringBuilder initialized with the chars in array s
	public MyStringBuilder(char [] s)
	{
		if(s.length == 0)
		{
			firstC = null;
			lastC = null;
			length = 0;
		}
		else
		{
			firstC = new CNode(s[0]);
			length = 1;
			CNode currNode = firstC;
			for(int i = 1; i < s.length; i++)
			{
				CNode newNode = new CNode(s[i]);
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
			lastC = currNode;
		}
		
			
	}

	// Create a new empty MyStringBuilder
	public MyStringBuilder()
	{
		firstC = null;
		lastC = null;
		length = 0;
	}

	// Append MyStringBuilder b to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder. 
	public MyStringBuilder append(MyStringBuilder b)
	{
		//Check to see if there are characters in the stringbuilder
		if(b.length() == 0 || b.firstC == null)
		{
		}
		//See if this stringbuilder is empty, if it is create the first and continue normally
		else if(firstC == null)
		{

			CNode temp = b.firstC;
			char c = b.firstC.data;
			firstC = new CNode(c);
			CNode currNode;
			CNode newNode;
			lastC = firstC;
			temp = temp.next;
			c = temp.data;
			currNode = lastC;
			newNode = new CNode(c);
			while(temp.next != null)
			{
				currNode.next = newNode;
				currNode = newNode;
				c = temp.next.data;
				newNode = new CNode(c);
				temp = temp.next;
				length++;
			}
			currNode.next = newNode;
			currNode = newNode;
			c = temp.next.data;
			newNode = new CNode(c);
			temp = temp.next;
			length++;
			lastC = currNode;
		}
		else
		{
			//Add nodes to the end of the end of the list by traversing the list and setting lastC to next node in the mystringbuilder
			CNode currNode;
			CNode newNode;
			currNode = lastC;
			char c = b.firstC.data;
			newNode = new CNode(c);
			CNode temp = b.firstC;
			while(temp.next != null)
			{
				currNode.next = newNode;
				currNode = newNode;
				c = temp.next.data;
				newNode = new CNode(c);
				temp = temp.next;
				length++;
			}
			currNode.next = newNode;
			currNode = newNode;
			newNode = new CNode(c);
			temp = temp.next;
			length++;
			lastC = currNode;
			
		}
		return this;
	}


	// Append String s to the end of the current MyStringBuilder, and return
	// the current MyStringBuilder. 
	public MyStringBuilder append(String s)
	{
		//Check for special cases
		if(s == null || s.length() == 0)
		{
		}
		else if(firstC == null)
		{
			new MyStringBuilder(s);
			
		}
		//Add a new CNode to the end one at a time
		else
		{
			CNode currNode = lastC;
			CNode newNode;
			for(int i = 0; i < s.length(); i++)
			{
				newNode = new CNode(s.charAt(i));
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
			lastC = currNode;
		}
		return this;
	}

	// Append char array c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder. 
	public MyStringBuilder append(char [] c)
	{
		//CHeck for special cases
		if(c.length < 0)
		{
			
		}
		else if(firstC == null)
		{
			new MyStringBuilder(c);
			
		}
		//Append a new CNode to the end of the list one by one from the array, same as a string just with char array
		else
		{
			CNode currNode = lastC;
			CNode newNode;
			for(int i = 0; i < c.length; i++)
			{
				newNode = new CNode(c[i]);
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
			lastC = currNode;
		}
		return this;
	}
	// Append char c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  
	public MyStringBuilder append(char c)
	{
		//Check for special cases
		if(firstC == null)
		{
			firstC = new CNode(c);
			lastC = firstC;
			length++;
		}
		//Append one character to the end of the list
		else
		{

			CNode newNode = new CNode(c);
			lastC.next = newNode;
			lastC = newNode;
			length++;
		}
		return this;
	}
	// Return the character at location "index" in the current MyStringBuilder.
	// If index is invalid, throw an IndexOutOfBoundsException.
	public char charAt(int index)
	{
		if(index >= length)
		{
			throw new IndexOutOfBoundsException("The location you are trying to acces is invalid");
		}
		//Traverse list to index i and set a temp variable to the node, then return that var
		else
		{
			CNode temp = firstC;
			for(int i = 0; i < index; i ++)
			{
				temp = temp.next;
			}
			return temp.data;
		}
	}
	// Delete the characters from index "start" to index "end" - 1 in the
	// current MyStringBuilder, and return the current MyStringBuilder.
	// If "start" is invalid or "end" <= "start" do nothing (just return the
	// MyStringBuilder as is).  If "end" is past the end of the MyStringBuilder, 
	// only remove up until the end of the MyStringBuilder. 
	public MyStringBuilder delete(int start, int end)
	{
		//Check for special cases
		if(start >= length || end <= start)
		{
		}
		else if(end - 1 >= length)
		{
			CNode temp = firstC;
			for(int i = 0; i < start; i ++)
			{
				temp = temp.next;
			}
			temp.next = null;
			length = length - (length - start);
		}
		else if(start == 0)
		{
			CNode temp = firstC;
			for(int i = 0; i < end; i++)
			{
				temp = temp.next;
			}
			firstC = temp;
			length = length - end;
		}
		else if(firstC == null)
		{
			
		}
		//Create 2 vars marking the start and end of what needs to be deleted, and then link temp.next to temp1 so the things that need to be deleted are no longer in the list
		else
		{
			CNode temp = firstC;
			CNode temp1 = firstC;
			for(int i = 0; i < (start - 1); i++)
			{
				temp = temp.next;
			}
			for(int i = 0; i < end; i++)
			{
				temp1 = temp1.next;
			}
			temp.next = temp1;
			length = length - (((end) - start));
		}
		return this;
	}
	// Delete the character at location "index" from the current
	// MyStringBuilder and return the current MyStringBuilder.  If "index" is
	// invalid, do nothing (just return the MyStringBuilder as is).
	public MyStringBuilder deleteCharAt(int index)
	{
		//Special cases
		if(index >= length)
		{
		}
		else if(index == 0)
		{
			firstC = firstC.next;
			length--;
		}
		else if(firstC == null)
		{
			
		}
		//Store a variable pointing to the node 1 before index i and link it to the node after i
		else
		{
			CNode temp = firstC;
			CNode prev = firstC;
			CNode next;
			for(int i = 0; i < index - 1; i++)
			{
				prev = prev.next;
			}
			temp = prev.next;
			next = temp.next;
			prev.next = next;
			length--;
		}
		return this;
	}
	// Find and return the index within the current MyStringBuilder where
	// String str first matches a sequence of characters within the current
	// MyStringBuilder.  If str does not match any sequence of characters
	// within the current MyStringBuilder, return -1.  
	public int indexOf(String str)
	{
		int l = str.length();
		char[] c1 = new char[l];
		//Fill a char array with the string being searched for
		for(int i = 0; i < l; i++)
		{
			c1[i] = str.charAt(i);
		}
		/*
		 * Test a random sequence of characters equal to the length of the string
		 * starting at the beginning of the nodes.  For example, if the word is 
		 * 'whoville' and the list has characters 'zwhovillezzz', the list will 
		 * check 'zwhovill', then 'whoville'
		 */
		boolean flag = false;
		//Nested loop, first one creates an offset that grows as more character sequences fail
		//Second one checks for equality for all characters in the array and list starting at
		//Offset i
		for(int i = 0; i <= length - l; i++)
		{
			//Outer marking in case one character doesn't match
			outer:
			for(int j = 0; j < l; j++)
			{
				if(charAt(i+j) == c1[j])
				{
					flag = true;
				}
				else
				{
					flag = false;
					break outer;
				}
			}
			//If all chars match, return the index the beginning of the word was at(i)
			if(flag == true)
			{
				return i;
			}
			else
			{
				
			}
		}
		return -1;
	}
	// Insert String str into the current MyStringBuilder starting at index
	// "offset" and return the current MyStringBuilder.  if "offset" == 
	// length, this is the same as append.  If "offset" is invalid
	// do nothing.
	public MyStringBuilder insert(int offset, String str)
	{
		//Test for sepcial cases
		if(offset == length)
		{
			this.append(str);
		}
		else if(offset < 0)
		{
			
		}
		else if(str == null)
		{
			
		}
		//If offset == 0, att at front
		else if(offset == 0)
		{
			CNode temp = firstC;
			firstC = new CNode(str.charAt(0));
			CNode temp1 = firstC;
			for(int i = 1; i < str.length();i++)
			{
				temp1.next = new CNode(str.charAt(i));
				temp1 = temp1.next;
			}
			temp1.next = temp;
			length = length + str.length();
		}
		//If the mystringbuilder is empty, just add to it
		else if(firstC == null)
		{
			firstC = new CNode(str.charAt(0));
			lastC = firstC;
			CNode temp = firstC;
			length++;
			for(int i = 1; i < str.length(); i++)
			{
				temp.next = new CNode(str.charAt(i));
				temp = temp.next;
				length++;
			}
			lastC = temp;
		}
		//Go to offset, then set that node's next to the beginning of what you are inserting, then link the end of what is being inserted to offset's original 'next' node
		else
		{
			
			CNode temp = firstC;
			for(int i = 0; i < offset - 1; i++)
			{
				temp = temp.next;
			}
			CNode temp2 = temp.next;
			CNode newNode;
			for(int i = 0; i < str.length(); i++)
			{
				newNode = new CNode(str.charAt(i));
				temp.next = newNode;
				temp = temp.next;
			}
			temp.next = temp2;
			length = length + str.length();
		}
		return this;
	}

	// Insert character c into the current MyStringBuilder at index
	// "offset" and return the current MyStringBuilder.  If "offset" ==
	// length, this is the same as append.  If "offset" is invalid, 
	// do nothing.
	public MyStringBuilder insert(int offset, char c)
	{
		//Special cases
		if(offset == length)
		{
			this.append(c);
		}
		else if(offset < 0)
		{
			
		}
		else if(offset == 0)
		{
			CNode temp = firstC;
			firstC = new CNode(c);
			firstC.next = temp;
			length++;
		}
		else if(firstC == null)
		{
			firstC = new CNode(c);
			lastC = firstC;
			length++;
		}
		//Same insert method as the first
		else
		{
			CNode temp = firstC;
			for(int i = 0; i < offset - 1; i++)
			{
				temp = temp.next;
			}
			CNode temp2 = temp.next;
			CNode newNode;
			newNode = new CNode(c);
			temp.next = newNode;
			temp = temp.next;
			temp.next = temp2;
			length++;
		}
		return this;
	}

	// Insert char array c into the current MyStringBuilder starting at index
	// index "offset" and return the current MyStringBuilder.  If "offset" is
	// invalid, do nothing.
	public MyStringBuilder insert(int offset, char [] c)
	{
		//Special cases(same as before)
		if(offset == length)
		{
			this.append(c);
		}
		else if(offset < 0)
		{
			
		}
		else if(c == null)
		{
			
		}
		else if(firstC == null)
		{
			firstC = new CNode(c[0]);
			lastC = firstC;
			CNode temp = firstC;
			length++;
			for(int i = 1; i < c.length; i++)
			{
				temp.next = new CNode(c[i]);
				temp = temp.next;
				length++;
			}
			lastC = temp;
		}
		else if(offset == 0)
		{
			CNode temp = firstC;
			firstC = new CNode(c[0]);
			CNode temp1 = firstC;
			for(int i = 1; i < c.length;i++)
			{
				temp1.next = new CNode(c[i]);
				temp1 = temp1.next;
			}
			temp1.next = temp;
			length = length + c.length;
		}
		//Same insert method as the first
		else
		{
			CNode temp = firstC;
			for(int i = 0; i < offset - 1; i++)
			{
				temp = temp.next;
			}
			CNode temp2 = temp.next;
			CNode newNode;
			for(int i = 0; i < c.length; i++)
			{
				newNode = new CNode(c[i]);
				temp.next = newNode;
				temp = temp.next;
			}
			temp.next = temp2;
			length = length + c.length;
		}
		
		return this;
	}
	// Delete the substring from "start" to "end" - 1 in the current
	// MyStringBuilder, then insert String "str" into the current
	// MyStringBuilder starting at index "start", then return the current
	// MyStringBuilder.  If "start" is invalid or "end" <= "start", do nothing.
	// If "end" is past the end of the MyStringBuilder, only delete until the
	// end of the MyStringBuilder, then insert.
	public MyStringBuilder replace(int start, int end, String str)
	{
		//Special cases
		if(start < 0)
		{
		}
		else if(end <= start)
		{
		}
		else if(end > length)
		{
			CNode temp = firstC;
			for(int i = 0; i < start - 1; i++)
			{
				temp = temp.next;
			}
			length = length - (length - start);
			for(int i = 0; i < str.length(); i++)
			{
				temp.next = new CNode(str.charAt(i));
				temp = temp.next;
			}
			length = length + str.length();
		}
		//Go to the node before what you want to replace, set it's next variable to the beginning of what you want to replace it w/
		//then, set the end of what you want to replace to the node after the end of what you want to replace
		else
		{
			CNode temp = firstC;
			CNode temp1 = firstC;
			for(int i = 0; i < start - 1; i++)
			{
				temp = temp.next;
			}
			for(int i = 0; i < end; i++)
			{
				temp1 = temp1.next;
			}
			length = length - (end-start);
			for(int i = 0; i < str.length(); i++)
			{
				temp.next = new CNode(str.charAt(i));
				temp = temp.next;
			}
			temp.next = temp1;
			length = length + str.length();
		}
		return this;
	}
	// Return as a String the substring of characters from index "start" to
	// index "end" - 1 within the current MyStringBuilder
	public String substring(int start, int end)
	{
		//Copy the nodes you want into a char array and then return them as a string
		CNode temp = firstC;
		char[] c = new char[(end-start)];
		//I didn't know if I was able to use an array to store all nodes and then
		//acces their index in the array or if I had to traverse the list to get 
		//The nodes I wanted and then copy them into an array, so I just 
		//Played it safe and got to the nodes using a list and then copied them
		//Into an array
		for(int i = 0; i < start; i++)
		{
			temp = temp.next;
		}
		for(int i = 0; i < (end-start); i++)
		{
			c[i] = temp.data;
			temp = temp.next;
		}
		
		return new String(c);
	}
	
	
	// Return the entire contents of the current MyStringBuilder as a String
	public String toString()
	{
		//Copy all nodes into a char array and then return the char array as a string
		char[] c = new char[length];
		CNode currNode = firstC;
		for(int i = 0; i < length; i++)
		{
			c[i] = currNode.data;
			currNode = currNode.next;
		}
		
		
		return new String(c);
	}
	// Return the length of the current MyStringBuilder
	public int length()
	{
		//basically a getter for the length
		return length;
	}
	
	// You must use this inner class exactly as specified below.  Note that
	// since it is an inner class, the MyStringBuilder class MAY access the
	// data and next fields directly.
	private class CNode
	{
		private char data;
		private CNode next;

		public CNode(char c)
		{
			data = c;
			next = null;
		}

		public CNode(char c, CNode n)
		{
			data = c;
			next = n;
		}
	}
}
