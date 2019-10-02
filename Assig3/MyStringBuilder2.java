

// CS 0445 Spring 2016
// Read this class and its comments very carefully to make sure you implement
// the class properly.  Note the items that are required and that cannot be
// altered!  Generally speaking you will implement your MyStringBuilder using
// a singly linked list of nodes.  See more comments below on the specific
// requirements for the class.

// For more details on the general functionality of most of these methods, 
// see the specifications of the similar method in the StringBuilder class.  
public class MyStringBuilder2
{
	
	private CNode firstC;	// reference to front of list.  
	private CNode lastC; 	// reference to last node of list.  
	private int length;  	// number of characters in the list

	// Create a new MyStringBuilder initialized with the chars in String s
	public MyStringBuilder2(String s)
	{
		if (s == null || s.length() == 0) // Special case for empty String
		{					 			  // or null reference
			firstC = null;
			lastC = null;
			length = 0;
		}
		else
		{
			createString(s,0);
		}
	}

	// Create a new MyStringBuilder initialized with the chars in array s
	public MyStringBuilder2(char [] s)
	{
		if(s.length == 0)
		{
			firstC = null;
			lastC = null;
			length = 0;
		}
		else
		{
			createString(s,0);
		}
	}

	// Create a new empty MyStringBuilder
	public MyStringBuilder2()
	{
		firstC = null;
		lastC = null;
		length = 0;
	}
	
	// Append MyStringBuilder b to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	/**
	 * Append a stringbuilder to another recursively
	 * @param b strinbuilder being appended
	 * @return the new appended MySB2
	 */
	public MyStringBuilder2 append(MyStringBuilder2 b)
	{
		if(b.length() == 0 || b.firstC == null)
		{
		}
		else if(firstC == null)
		{
			firstC = new CNode(b.firstC.data);
			lastC = firstC;
			length++;
			append1(b,b.firstC.next);
		}
		else
		{
			append1(b,b.firstC);
		}
		return this;
	}
	/**
	 * Appends a string to the strinbuilder recursively
	 * @param s the string
	 * @return MySB2 w/ appended string
	 */
	public MyStringBuilder2 append(String s)
	{
		//Check for special cases
		if(s == null || s.length() == 0)
		{
		}
		else if(firstC == null)
		{
			createString(s,0);	
		}
		else
		{
			append1(s,0);
		}
		return this;
	}
	
	/**
	 * Adds a char array to the end of MySB2
	 * @param c char array being a added
	 * @return	MySB w/ chars from the array at the end
	 */
	public MyStringBuilder2 append(char [] c)
	{
		//CHeck for special cases
		if(c.length < 0)
		{			
		}
		else if(firstC == null)
		{
			new MyStringBuilder2(c);			
		}
		else
		{
			append1(c,0);
		}
		return this;	
	}

	/**
	 * Adds a char to the end of the MySB2, no recursion needed
	 * @param c char being appended
	 * @return MySB w/ new char at the end
	 */
	public MyStringBuilder2 append(char c)
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
	/**
	 * returns char at index given
	 * @param index index of char wanted
	 * @return char at index
	 */
	public char charAt(int index)
	{
		char c;
		if(index >= length)
		{
			throw new IndexOutOfBoundsException("The location you are trying to acces is invalid");
		}
		else
		{
			c = getTo(index,0,firstC).data;
		}
		return c;
	}
	/**
	 * Delete characters from start to end
	 * @param start where to begin
	 * @param end where to end
	 * @return MySB2 w/ removed chars
	 */
	public MyStringBuilder2 delete(int start, int end)
	{
		//Check for special cases
		if(start >= length || end <= start)
		{
		}
		else if(end - 1 >= length)
		{
			CNode temp = getTo(start-1,0,firstC);
			temp.next = null;
			length = length - (length - start);
		}
		else if(start == 0)
		{
			CNode temp = getTo(end,0,firstC);
			firstC = temp;
			length = length - end;
		}
		else if(firstC == null)
		{
		}
		//Create 2 vars marking the start and end of what needs to be deleted, and then link temp.next to temp1 so the things that need to be deleted are no longer in the list
		else
		{
			CNode temp = getTo(start-1,0,firstC);
			CNode temp1 = getTo(end,0,firstC);
			temp.next = temp1;
			length = length - (((end) - start));
		}
		return this;
	}

	/**
	 * Deletes a char at a given index
	 * @param index index of char
	 * @return MySB w/ removeed char at index
	 */
	public MyStringBuilder2 deleteCharAt(int index)
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
			CNode prev = getTo(index-1,0,firstC);
			CNode next;
			temp = prev.next;
			next = temp.next;
			prev.next = next;
			length--;
		}
		return this;
		
	}
	/**
	 * Return the index of a string being searched for
	 * @param str string being searched for
	 * @return index of beginning of string in mySB2
	 */
	public int indexOf(String str)
	{
		//Var l used instead of calling length() method everytime
		int l = str.length();
		char[] c1 = new char[l];
		//Fill a char array with the string being searched for
		getString(c1,0,str);
		return index(l, c1,0);
		
	}
	
	// Insert String str into the current MyStringBuilder starting at index
	// "offset" and return the current MyStringBuilder.  if "offset" == 
	// length, this is the same as append.  If "offset" is invalid
	// do nothing.
	public MyStringBuilder2 insert(int offset, String str)
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
		//If offset == 0, add at front
		else if(offset == 0)
		{
			CNode temp = firstC;
			firstC = new CNode(str.charAt(0));
			CNode temp1 = firstC;
			insert1(temp1,str,1,temp);
			length = length + str.length();
		}
		//If the mystringbuilder is empty, just add to it
		else if(firstC == null)
		{
			new MyStringBuilder2(str);
		}
		//Go to offset, then set that node's next to the beginning of what you are inserting, then link the end of what is being inserted to offset's original 'next' node
		else
		{
			CNode temp = getTo(offset-1,0,firstC);
			CNode temp2 = temp.next;
			CNode newNode = null;
			insert1(temp,str,0,temp2);
			length = length + str.length();
		}
		return this;
	}
	// Insert character c into the current MyStringBuilder at index
		// "offset" and return the current MyStringBuilder.  If "offset" ==
		// length, this is the same as append.  If "offset" is invalid, 
		// do nothing.
		public MyStringBuilder2 insert(int offset, char c)
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
				getTo(offset-1,0,temp);
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
		public MyStringBuilder2 insert(int offset, char [] c)
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
				new MyStringBuilder2(c);
			}
			else if(offset == 0)
			{
				CNode temp = firstC;
				firstC = new CNode(c[0]);
				CNode temp1 = firstC;
				insert1(temp1,c,1,temp);
				length = length + c.length;
			}
			//Same insert method as the first
			else
			{
				CNode temp = getTo(offset-1,0,firstC);
				CNode temp2 = temp.next;
				CNode newNode = null;
				insert1(temp,c,0,temp2);
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
		public MyStringBuilder2 replace(int start, int end, String str)
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
				CNode temp = getTo(start-1,0,firstC);
				length = length - (length - start);
				replace1(temp,str,0);
				length = length + str.length();
			}
			//Go to the node before what you want to replace, set it's next variable to the beginning of what you want to replace it w/
			//then, set the end of what you want to replace to the node after the end of what you want to replace
			else
			{
				CNode temp = getTo(start-1,0,firstC);
				CNode temp1 = getTo(end,0,firstC);
				length = length - (end-start);
				replace1(temp,temp1,str,0);
				
				length = length + str.length();
			}
			return this;
		}
		// Return as a String the substring of characters from index "start" to
		// index "end" - 1 within the current MyStringBuilder
		public String substring(int start, int end)
		{
			//Copy the nodes you want into a char array and then return them as a string
			CNode temp = getTo(start,0,firstC);
			char[] c = new char[(end-start)];
			//I didn't know if I was able to use an array to store all nodes and then
			//acces their index in the array or if I had to traverse the list to get 
			//The nodes I wanted and then copy them into an array, so I just 
			//Played it safe and got to the nodes using a list and then copied them
			//Into an array
			substring1(c,temp,0);
			return new String(c);
		}

	/**
	 * Return current length of MySB2
	 * @return length of MySB2
	 */
	public int length()
	{
		return length;
	}

	/**
	 * Return entire contents as a string
	 * @return mySB2 as a string
	 */
	public String toString()
	{
		char [] c = new char[length];
	    getString(c, 0, firstC);
	    return (new String(c));
	}
	/**
	 * Return current contents as a string
	 * @param c char array from toSTring method
	 * @param pos position from recursion
	 * @param curr current node
	 */
	private void getString(char[] c, int pos, CNode curr)
	{
	      if (curr != null)
	      {
	            c[pos] = curr.data;
	            getString(c, pos+1, curr.next);
	      }
	}
	/**
	 * fill char array with chars from a string
	 * @param c char array being filled
	 * @param pos current position
	 * @param s string whose chars are being passed into c
	 */
	private void getString(char[] c, int pos, String s)
	{
	      if (pos != c.length)
	      {
	            c[pos] = s.charAt(pos);
	            getString(c, pos+1, s);
	      }
	}
	 /**
	  * Creates the MyStringBuilder from a String
	  * @param s the String
	  * @param i initial index
	  */
	private void createString(String s, int i)
	{
		//Recursive case if not at end
	      if (i < s.length()-1)
	      {
	            createString(s, i+1);
	            firstC = new CNode(s.charAt(i), firstC);
	            length++;
	      }
	      //Base case if at end
	      else
	      {
	            firstC = new CNode(s.charAt(i));
	            lastC = firstC;
	            length = 1;
	      }
	}
	/**
	 * Creates a MySB2 from a char array
	 * @param s char array
	 * @param i current index from recursion
	 */
	private void createString(char[] s, int i)
	{
		//Recursive case if not at end
		if(i < s.length-1)
		{
			createString(s, i+1);
			firstC = new CNode(s[i], firstC);
			length++;
		}
		//Base case if at end
		else
		{
			firstC = new CNode(s[i]);
			lastC = firstC;
			length = 1;
		}
	}
	/**
	 * Recursively append characters from s to the MySB2
	 * @param s string being appended
	 * @param i beginning index
	 */
	private void append1(MyStringBuilder2 s, CNode c)
	{
		//Create new node w/ data from the MySB2
		CNode curr = new CNode(c.data);
		//Base case if at end
		if(c == s.lastC)
		{
			lastC.next = curr;
			lastC = curr;
			length++;
		}
		//Recursive case if not
		else
		{
			lastC.next = curr;
			lastC = curr;
			length++;
			append1(s,c.next);
		}
	}
	/**
	 * Append a string to the end of MySB2
	 * @param s string appended
	 * @param i current index from recursion
	 */
	private void append1(String s, int i)
	{
		CNode curr = new CNode(s.charAt(i));
		//Base case if on last char
		if(i == s.length()-1)
		{
			lastC.next = curr;
			lastC = curr;
			length++;
		}
		//Recursive case if there are more chars to append, append the next one
		else
		{
			lastC.next = curr;
			lastC = curr;
			length++;
			append1(s, i+1);
		}
		
	}
	/**
	 * Append a char array to MySb2
	 * @param s charr array
	 * @param i current index form recursion
	 */
	private void append1(char[] s, int i)
	{
		//Base case if you're at the end
		if(i == s.length - 1)
		{
			CNode curr = new CNode(s[i]);
			lastC.next = curr;
			lastC = curr;
			length++;
		}
		//Recursive case if you're not
		else
		{
			CNode curr = new CNode(s[i]);
			lastC.next = curr;
			lastC = curr;
			length++;
			append1(s,i+1);
		}
	}
	/**
	 * Generic method to get to a given index of the linked list.  
	 * @param i index wanted
	 * @param s current index
	 * @param c current node
	 * @return CNode searched for
	 */
	private CNode getTo(int i, int s, CNode c)
	{
		//Base case if you're at the index
		if(s == i)
		{
			return c;
		}
		//Recursive case if you're not at the index yet
		else
		{
			return getTo(i,s+1,c.next);
		}
	}
	/**
	 * Tests a sequence of chars l long starting at 0 and offsetting by 1 everytime it fails
	 * @param l length of string
	 * @param c1 array of chars being searched for
	 * @param i current offset
	 * @return index of where it is found, -1 if not found
	 */
	private int index(int l, char[] c1, int i)
	{
		boolean flag = false;
		boolean[] b = new boolean[l];
		flag = testEq(c1,l,i,0,b);
		//Base case, there is no sequence matching
		if(i > length - l)
		{
			return -1;
		}
		//Recursive case, if the sequence matches, return true and if not iterate the offset by 1
		else
		{
			if(flag == true)
			{
				return i;
			}
			else
			{
				return index(l,c1,i+1);
			}
		}
	}
	/**
	 * Tests for equality of characters at an index and those in the array
	 * @param c char array of what is being searched for
	 * @param l length of array
	 * @param offset how far the index method is offset, dependent on number of failed searches
	 * @param z current character being checked
	 * @param b boolean array used to determine if each char maches
	 * @return if the sequence of characters matches the array
	 */
	private boolean testEq(char[] c, int l, int offset, int z, boolean[] b)
	{
		//Base case, string not found
		if(offset > length - l)
		{
			return false;
		}
		//Base case, the number of searches with this offset matches the length of the string
		else if(z == l)
		{
			//If every character matched(the array is all true) then return true, if not return false
			if(test(b,0,l) == true)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		//Recursive case, if the character matches, continue onto the next and make the boolean var at that index true, if not return false
		else
		{
			if(charAt(offset+z) == c[z])
			{
				b[z] = true; 
				return testEq(c,l,offset,z+1,b);
			}
			else
			{
				return false;
			}
		}
		
	}
	/**
	 * Tests if a boolean array is full of true statements
	 * @param b boolean array
	 * @param i current index
	 * @param l length of array
	 * @return
	 */
	private boolean test(boolean [] b, int i, int l)
	{
		if(i == l)
		{
			return true;
		}
		if(b[i] == true)
		{
			return test(b,i+1,l);
		}
		else
		{
			return false;
		}
	}

	/**
	 * Add chars to the linked list
	 * @param temp1 temp node
	 * @param str string being inserted
	 * @param i index
	 * @param temp reference to where it is being inserted
	 */
	public void insert1(CNode temp1, String str, int i, CNode temp)
	{
		//Recursive case, if there are more chars add them
		if(i < str.length() - 1)
		{
			temp1.next = new CNode(str.charAt(i));
			temp1 = temp1.next;
			insert1(temp1,str,i+1,temp);
		}
		//Base case, add last char and link it back to the list
		else
		{
			temp1.next = new CNode(str.charAt(i));
			temp1 = temp1.next;
			temp1.next = temp;
		}
	}
	/**
	 * Insert char array into the MySB2
	 * @param temp1 temporary node
	 * @param str char array
	 * @param i index
	 * @param temp reference to list
	 */
	public void insert1(CNode temp1, char[] str, int i, CNode temp)
	{
		//Recursive case, if there are more chars add them
		if(i < str.length - 1)
		{
			temp1.next = new CNode(str[i]);
			temp1 = temp1.next;
			insert1(temp1,str,i+1,temp);
		}
		//Base case, add last char and link it back to the list
		else
		{
			temp1.next = new CNode(str[i]);
			temp1 = temp1.next;
			temp1.next = temp;
		}
	}
	/**
	 * Replace chars from an index temp to the end of the list
	 * @param temp pointer to where to start
	 * @param str string overriding chars
	 * @param i index
	 */
	public void replace1(CNode temp, String str,int i)
	{
		if(i == str.length() - 1)
		{
			temp.next = new CNode(str.charAt(i));
			temp = temp.next;
		}
		else
		{
			temp.next = new CNode(str.charAt(i));
			temp = temp.next;
			replace1(temp,str,i+1);
		}
	}
	/**
	 * Creates a list of chars starting at temp and links it to temp1
	 * @param temp Beginning of replacement
	 * @param temp1 end of replacement 
	 * @param str string overriding previous chars
	 * @param i index
	 */
	public void replace1(CNode temp,CNode temp1, String str, int i)
	{
		//Base case, if there are no more chars, add the last one and link it back to the list
		if(i == str.length() - 1)
		{
			temp.next = new CNode(str.charAt(i));
			temp = temp.next;
			temp.next = temp1;
		}
		//Recursive case, if there are more chars, add them
		else
		{
			temp.next = new CNode(str.charAt(i));
			temp = temp.next;
			replace1(temp,temp1,str,i+1);
		}
	}
	public void substring1(char[] c, CNode temp, int i)
	{
		if(i >= c.length - 1)
		{
			c[i] = temp.data;
			temp = temp.next;
			
		}
		else
		{
			c[i] = temp.data;
			temp = temp.next;
			substring1(c,temp,i+1);
		}
	}
	//CNode class given
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
