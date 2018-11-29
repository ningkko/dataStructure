package edu.smith.cs.csc212.p6;

import java.util.Iterator;

import edu.smith.cs.csc212.p6.errors.BadIndexError;
import edu.smith.cs.csc212.p6.errors.EmptyListError;

public class SinglyLinkedList<T> implements P6List<T>, Iterable<T> {
	/**
	 * The start of this list. Node is defined at the bottom of this file.
	 */
	Node<T> start;

	//O(1)
	@Override
	public T removeFront() {
		checkNotEmpty();
		
		/**
		 * The previous start node
		 */
		Node<T> previousStart = start;
		// set the next node of the previous start to the current start
		start = start.next;
		/**
		 * value of the previous start
		 */
		T value = previousStart.value;
		// set previous start to null
		previousStart = null;
		
		return value;
	
	}

	// O(n)
	@Override
	public T removeBack() {
		/**
		 * 2 pointers, one for tracking the last node
		 * the other one for tracking the second last node
		 */
		Node<T> temp= this.start;
		Node<T> prev = null;
		
		// if empty, throw exception
		if (this.start==null) {
			throw new EmptyListError();
		}
		// else if only one element, set it to null
		else if(this.start.next==null) {
			T value=start.value;
			start=null;
			return value;
			
		// else revise its pointers stored in its linked node
		}else {
			
			while(temp.next != null){
				// the previous node
				prev = temp;
				// the current node
				temp = temp.next;
			}
			
			// get the value of the current node
			T value= prev.next.value;
			// set the current node to nil
			prev.next = null;
			return value;

		}
	}

	// O(n)
	@Override
	public T removeIndex(int index) {
		
		this.checkNotEmpty();
		/**
		 * target node
		 */
		Node<T> target=this.getTargetNode(index);
		
		// if there was only one node, set it to nil
		if(target==start) {
			T value=this.start.value;
			this.start=null; 
			return value;
			
		// else check the pointers of its linked nodes
		}else {
			
			/**
			 * the previous node of the target node
			 */
			Node<T> targetPrevious=this.getTargetNode(index-1);
			
			/**
			 * value of the current node
			 */
			T value=targetPrevious.next.value;
			
			// set the next node of the previous node to the next node of the current node
			// -> skip the current node.
			targetPrevious.next=targetPrevious.next.next;			
			
			return value;
		}

	}

	// O(1)
	@Override
	public void addFront(T item) {
		/**
		 * the previous start node
		 */
		Node<T> previousStart=this.start;
		/**
		 * set the start node to the node node
		 */
		this.start = new Node<T>(item, start);
		// set the next node of the current start node to the previous start node
		this.start.next=previousStart;
	}

	//O(n)
	@Override
	public void addBack(T item) {
		/**
		 * new node
		 */
		Node<T> newNode=new Node<T>(item,null);
		
		// if empty, add as the start node
		if(this.start==null) {
			this.start=newNode;
		}
		// else add to the last node
		else {
			
			/**
			 * the last node
			 */
			Node<T> last=this.getTargetNode(this.size()-1);
			// attach the new node to it
			last.next=newNode;
		}
	}
	
	
	//O(n)
	@Override
	public void addIndex(T item, int index) {
		
		/**
		 * next node of the new node
		 */
				
		Node<T> nextNode=null;
		if(index<this.size()){
			nextNode=this.getTargetNode(index);
		}
		/**
		 * new node
		 */
		Node<T> newNode=new Node<T>(item, nextNode);
		/**
		 * target node
		 */
		Node<T> target=this.getTargetNode(index-1);
		
		// attach
		target.next=newNode;
	}

	//O(1)
	@Override
	public T getFront() {
		
		// get the start node value
		return start.value;
	}

	//O(n)
	@Override
	public T getBack() {
		
		// find the last node and return its value
		return this.getTargetNode(this.size()-1).value;
	}

	//O(n)
	public Node<T> getTargetNode(int index){
		
		/**
		 * counter
		 */
		int at = 0;
		
		// loop thru the list
		for (Node<T> current = start; current != null; current = current.next) {
			if (at == index) {
				//return if found
				return current;
			}
			at++;
		}
		// We couldn't find it, throw an exception!
		throw new BadIndexError();	
	}
	
	//O(n)
	@Override
	public T getIndex(int index) {
		return this.getTargetNode(index).value;
		
	}

	//O(n)
	@Override
	public int size() {
		/**
		 * counter
		 */
		int count = 0;
		// loop thru
		for (Node<T> n = this.start; n != null; n = n.next) {
			count++;
		}
		return count;
	}

	//O(1)
	@Override
	public boolean isEmpty() {
		return start==null;
	}

	/**
	 * Helper method to throw the right error for an empty state.
	 */
	//O(1)
	private void checkNotEmpty() {
		if (this.isEmpty()) {
			throw new EmptyListError();
		}
	}

	/**
	 * The node on any linked list should not be exposed. Static means we don't need
	 * a "this" of SinglyLinkedList to make a node.
	 * 
	 * @param <T> the type of the values stored.
	 */
	public static class Node<T> {
		/**
		 * What node comes after me?
		 */
		public Node<T> next;
		/**
		 * What value is stored in this node?
		 */
		public T value;

		/**
		 * Create a node with no friends.
		 * 
		 * @param value - the value to put in it.
		 */
		public Node(T value, Node<T> next) {
			this.value = value;
			this.next = next;
		}
	}

	/**
	 * I'm providing this class so that SinglyLinkedList can be used in a for loop
	 * for {@linkplain ChunkyLinkedList}. This Iterator type is what java uses for
	 * {@code for (T x : list) { }} lops.
	 * 
	 * @author jfoley
	 *
	 * @param <T>
	 */
	private static class Iter<T> implements Iterator<T> {
		/**
		 * This is the value that walks through the list.
		 */
		Node<T> current;

		/**
		 * This constructor details where to start, given a list.
		 * @param list - the SinglyLinkedList to iterate or loop over.
		 */
		public Iter(SinglyLinkedList<T> list) {
			this.current = list.start;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			T found = current.value;
			current = current.next;
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
