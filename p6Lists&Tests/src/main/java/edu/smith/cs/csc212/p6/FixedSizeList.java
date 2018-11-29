package edu.smith.cs.csc212.p6;

import java.util.Iterator;

import edu.smith.cs.csc212.p6.errors.BadIndexError;
import edu.smith.cs.csc212.p6.errors.EmptyListError;
import edu.smith.cs.csc212.p6.errors.RanOutOfSpaceError;

public class FixedSizeList<T> implements P6List<T> {
	private Object[] array;
	/**
	 * the number of elements in the array/fixed list
	 */
	private int fill;
	
	public FixedSizeList(int maximumSize) {
		this.array = new Object[maximumSize];
		this.fill = 0;
	}

	//O(1)
	@Override
	public T removeFront() {
		// remove the first element
		return removeIndex(0);
	}

	//O(1)
	@Override
	public T removeBack() {
		
		// check index
		if (this.size() == 0) {
			throw new EmptyListError();
		}
		/**
		 * value of the last element
		 */
		T value = this.getIndex(fill-1);
		// remove it
		this.array[fill-1] = null;
		// the size decreases by one
		fill--;
		
		return value;
	}
	
	//O(n)
	@Override
	public T removeIndex(int index) {
		
		if (this.size() == 0) {
			throw new EmptyListError();
		}
		/**
		 * value of the removed item
		 */
		T removed = this.getIndex(index);
		fill--;
		for (int i=index; i<fill; i++) {
			this.array[i] = this.array[i+1];
		}
		this.array[fill] = null;
		return removed;
	}

	//O(1)
	@Override
	public void addFront(T item) {
		addIndex(item, 0);		
	}

	//O(1)
	@Override
	public void addBack(T item) {
		if (fill < array.length) {
			array[fill++] = item;
		} else {
			throw new RanOutOfSpaceError();
		}
	}

	//O(1)
	@Override
	public void addIndex(T item, int index) {
		if (fill >= array.length) {
			throw new RanOutOfSpaceError();
		}
		// loop backwards, shifting items to the right.
		for (int j=fill; j>index; j--) {
			array[j] = array[j-1];
		}
		array[index] = item;
		fill++;		
	}

	/**
	 * Do not allow unchecked warnings in any other method.
	 * Keep the "guessing" the objects are actually a T here.
	 * Do that by calling this method instead of using the array directly.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T getIndex(int index) {  	//O(1)
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
		return this.fill == 0;
	}

	//O(1)
	@Override
	public T getFront() {
		if (this.isEmpty()) {
			throw new EmptyListError();
		}
		return this.getIndex(0);
	}

	//O(1)
	@Override
	public T getBack() {
		if (this.isEmpty()) {
			throw new EmptyListError();
		}
		return this.getIndex(this.size()-1);
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
		FixedSizeList<T> list;
		
		/**
		 * size of the input array
		 */
		int size;
		
		/**
		 * This constructor details where to start, given a list.
		 * @param list - the SinglyLinkedList to iterate or loop over.
		 */
		public Iter(FixedSizeList<T> list) {
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
