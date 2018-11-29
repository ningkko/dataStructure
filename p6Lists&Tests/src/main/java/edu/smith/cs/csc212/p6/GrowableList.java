package edu.smith.cs.csc212.p6;

import java.util.Iterator;
import edu.smith.cs.csc212.p6.errors.BadIndexError;
import edu.smith.cs.csc212.p6.errors.EmptyListError;

public class GrowableList<T> implements P6List<T> {
	public static final int START_SIZE = 32;
	private Object[] array;
	private int fill;
	
	public GrowableList() {
		this.array = new Object[START_SIZE];
		this.fill = 0;
	}

	//O(1)
	@Override
	public T removeFront() {
		return removeIndex(0);		
	}

	//O(1)
	@Override
	public T removeBack() {
		if (this.size() == 0) {
			throw new EmptyListError();
		}
		T value = this.getIndex(fill-1);
		this.array[fill-1] = null;
		fill--;
		return value;
	}

	//O(n)
	@Override
	public T removeIndex(int index) {
		
		if (this.size() == 0) {
			throw new EmptyListError();
		}
		T removed = this.getIndex(index);
		fill--;
		for (int i=index; i<fill; i++) {
			this.array[i] = this.array[i+1];
		}
		this.array[fill] = null;
		return removed;
	}

	//O(n)
	@Override
	public void addFront(T item) {
		addIndex(item, 0);		
	}

	// O(n) if overflow
	// O(1) if not
	@Override
	public void addBack(T item) {
		if (fill < array.length) {
			array[fill++] = item;
		} else {
			addIndex(item, fill+1);
		}
	}

	//O(n)
	@Override
	public void addIndex(T item, int index) {
		
		// if adding the new element makes the array run out of range, create a new array
		if (fill >= array.length) {
			
			/**
			 * size of the new array
			 */
			int newSize=fill*2;
			/**
			 * new array
			 */
			Object[] newArray=new Object[newSize];
			
			// loop thru the old array and copy its value
			for(int i=0;i<array.length;i++) {
				newArray[i]=array[i];
			}
			// add the new element
			newArray[fill]=item;
			
			// #element+1 
			fill++;
			
		}
		// loop backwards, shifting items to the right.
		for (int j=fill; j>index; j--) {
			array[j] = array[j-1];
		}
		array[index] = item;
		fill++;		
	}
	
	//O(1)
	@Override
	public T getFront() {
		return this.getIndex(0);
	}

	//O(1)
	@Override
	public T getBack() {
		return this.getIndex(this.fill-1);
	}

	/**
	 * Do not allow unchecked warnings in any other method.
	 * Keep the "guessing" the objects are actually a T here.
	 * Do that by calling this method instead of using the array directly.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T getIndex(int index) { 	//O(1)

		if (index < 0 || index >= fill) {
			throw new BadIndexError();
		}
		return (T) this.array[index];
	}

	//O(1)
	@Override
	public int size() {
		return this.fill;
	}

	//O(1)
	@Override
	public boolean isEmpty() {
		return fill == 0;
	}
	
	
	private static class Iter<T> implements Iterator<T> {
		/**
		 * This is the value that walks through the list.
		 */
		T current;

		/**
		 * how many elements we have looked at
		 */
		int count;
		
		/**
		 * a copy of the array
		 */
		GrowableList<T> list;
		
		/**
		 * size of the input array
		 */
		int size;
		
		/**
		 * This constructor details where to start, given a list.
		 * @param list - the SinglyLinkedList to iterate or loop over.
		 */
		public Iter(GrowableList<T> list) {
			this.current = list.getIndex(0);
			this.size=list.size();
			this.list=list;
			this.count=0;
		}

		@Override
		public boolean hasNext() {
			return this.count<this.size;
		}

		@Override
		public T next() {
			T found = current;
			current = list.getIndex(count++);
			return found;
		}
	}
	
	/**
	 * Implement iterator() so that {@code SinglyLinkedList} can be used in a for loop.
	 * @return an object that understands "next()" and "hasNext()".
	 */
	public Iterator<T> iterator() {
		return new Iter<>(this);
	}

}
