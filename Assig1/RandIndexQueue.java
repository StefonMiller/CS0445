import java.util.*;
public class RandIndexQueue<T> implements MyQ<T>, Shufflable, Indexable<T>
{
	private T t;
	private T[] array;
	private int nItems;
	public RandIndexQueue(int n)
	{
		T[] temp = (T[])new Object[n];
		array = temp;
		nItems = 0;
	}
	// Get and return the value located at logical location i in the implementing
	// collection, where location 0 is the logical beginning of the collection.
	// If the collection has fewer than (i+1) items, throw an IndexOutOfBoundsException 
	public T get(int i) 
	{
		if(nItems < (i+1))
		{
			throw new IndexOutOfBoundsException();
		}
		else
		{
			return array[i];
		}
	}

	// Assign item to logical location i in the implementing collection, where location
	// 0 is the logical beginning of the collection.  If the collection has fewer than
	// (i+1) items, throw an IndexOutOfBoundsException
	public void set(int i, T item) 
	{
		if(nItems < (i+1))
		{
			throw new IndexOutOfBoundsException();
		}
		else
		{
			array[i] = item;
		}
	}

	// Reorganize the items in the object in a pseudo-random way.  The exact
	// way is up to you but it should utilize a Random object (see Random in 
	// the Java API)
	public void shuffle() 
	{
		Random r = new Random();
		for(int i = 0; i < nItems; i++)
		{
			int index = r.nextInt(nItems);
			T a = array[index];
		    array[index] = array[i];
		    array[i] = a;
		}
	}

	// Add a new Object to the MyQ in the next available location.  If
	// all goes well, return true.  If there is no room in the MyQ for
	// the new item, return false (you do NOT have to resize it)
	public boolean addItem(T item) 
	{
		if(nItems == array.length)
		{
			return false;
		}
		else
		{
			array[nItems] = item;
			nItems++;
			return true;
		}
		
	}

	// Remove and return the "oldest" item in the MyQ.  If the MyQ
	// is empty, return null
	public T removeItem() 
	{
		if(nItems == 0)
		{
			return null;
		}
		else
		{
			T temp = array[0];
			for(int i = 0; i < nItems - 1; i ++)
			{
				array[i] = array[i + 1];
			}
			nItems--;
			return temp;
			
		}
		
	}

	// Return true if the MyQ is full, and false otherwise
	public boolean full() 
	{
		if(nItems == array.length)
		{
			return true;
		}
		else
		{
			return false;
		}
	}


	// Return true if the MyQ is empty, and false otherwise
	public boolean empty() 
	{
		if(nItems == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	// Return the number of items currently in the MyQ
	public int size() 
	{
		return nItems;
	}

	// Reset the MyQ to empty status by reinitializing the variables
	// appropriately
	public void clear() 
	{
		for(int i = 0; i < nItems; i++)
		{
			array[i] = null;
		}
		nItems = 0;
	}
	public String toString()
	{
		StringBuilder S = new StringBuilder();
		S.append("Contents: ");
		for(int i = 0; i < nItems; i++)
		{
			S.append(array[i]);
			S.append(" ");
		}
		return S.toString();
	}

}
