package edu.smith.cs.csc212.p6;

import java.util.Iterator;

import edu.smith.cs.csc212.p6.errors.BadIndexError;
import edu.smith.cs.csc212.p6.errors.EmptyListError;



public class DoublyLinkedList<T> implements P6List<T> {
	private Node<T> start;
	private Node<T> end;
	
	/**
	 * A doubly-linked list starts empty.
	 */
	public DoublyLinkedList() {
		/**
		 * first node in the list
		 */
		this.start = null;
		/**
		 * last node in the list
		 */
		this.end = null;
	}
	

	// O(1)
	@Override
	public T removeFront() {
		checkNotEmpty();
		
		/**
		 * value of the first element
		 */
		T value=this.start.value;
		
		// if there's only one element, remove it
		if(this.size()==1) {
			this.start=null;
			this.end=null;
		}
		// else also check the pointers of elements linked to it
		else {
			Node<T> second = this.start.after;
			second.before=null;
			this.start=second;
		}
		
		return value;
	}

	// O(1)
	@Override
	public T removeBack() {
		
		checkNotEmpty();
		/**
		 * value of the last element
		 */
		T value=this.end.value;
		
		// if there was only one element in the list, set it nul
		if(this.size()==1) {
			this.start=null;
			this.end=null;
		}
		// else also change pointers of elements lined to it
		else{
			Node<T> secondEnd = this.end.before;
			secondEnd.after=null;
			this.end=secondEnd;
		}
		return value;
		
	}

	// O(n)
	@Override
	public T removeIndex(int index) {
		checkNotEmpty();
		
		/**
		 * get the target node
		 */
		Node<T> target=this.getTartgetNode(index);
		/**
		 * value of the target node
		 */
		T value=target.value;

		// if the target node is the start node..
		if(target==start) {
			this.removeFront();
		
		// if the target element is the last element
		}
		else if(target==this.end){	
			this.removeBack();
		}
		// else check its related nodes
		else{
			
			Node<T> previous=target.before;
			Node<T> next=target.after;
			previous.after=target.after;
			next.before=target.before;
		}
			return value;
		
	}

	// O(1)
	@Override
	public void addFront(T item) {
		
		/**
		 * a node with the given value
		 */
		Node<T> newNode= new Node<T>(item);

		// if there's no node in the list, add the node in
		if(this.size()==0) {
			
			this.start=newNode;
			this.end=newNode;
			
		}
		// else add the node after the last node and before the first node.
		else {
			this.start.before=newNode;
			newNode.after=this.start;
			this.start=newNode;
		}
	}

	// O(1)
	@Override
	public void addBack(T item) {
		Node<T> newNode= new Node<T>(item);

		// if there's no node in the list, add the node in
		if(this.size()==0) {
			this.end=newNode;
			this.start=newNode;
		}
		// else add the node after the last node and before the first node.
		else {
			newNode.before=this.end;
			this.end.after=newNode;
			this.end=newNode;
		}
		
	}

	// O(n)
	@Override
	public void addIndex(T item, int index) {
		
		/**
		 * a node with the given value
		 */
		Node<T> newNode= new Node<T>(item);

		// the node at the index-1th place
		Node<T> previousNode = this.getTartgetNode(index-1);
		
		newNode.after=previousNode.after;
		previousNode.after=newNode;
		newNode.after.before=newNode;
		
	}

	// O(1)
	@Override
	public T getFront() {
		// get the value of the first node
		return this.start.value;
	}

	// O(1)
	@Override
	public T getBack() {
		// get the value of the last node
		return this.end.value;
	}
	
	// O(n)
	public Node<T> getTartgetNode(int index){
		
		this.checkNotEmpty();
		
		/**
		 * keep record of which element we are loooking at
		 */
		int at =0;
		
		// loop thru the list
		for (Node<T> current = start; current != null; current = current.after) {
			
			if (at == index) {
				return current;
			}
			at++;
		}
		// We couldn't find it, throw an exception!
		throw new BadIndexError();	
		
	}

	
	// O(n)	
	@Override
	public T getIndex(int index) {
		
		return this.getTartgetNode(index).value;		
		
	}

	// O(n)
	@Override
	public int size() {
		
		// if the start is null, then size==0
		if(this.start==null) {
			return 0;
		}else {
			
			/**
			 * counter
			 */
			int count=0;
			/**
			 * count from the first node
			 */
			Node<T> node=this.start;
			
			// count
			while(node!=null) {
				count++;
				node=node.after;
			}
			
			 return count;
		}
		
	}

	// O(1)
	@Override
	public boolean isEmpty() {
		
		// check if the first of the list is null
		return this.start==null;
	}
	
	// O(1)
	private void checkNotEmpty() {
		
		// if empty throw an exception.
		if (this.isEmpty()) {
			throw new EmptyListError();
		}
	}
	
	/**
	 * The node on any linked list should not be exposed.
	 * Static means we don't need a "this" of DoublyLinkedList to make a node.
	 * @param <T> the type of the values stored.
	 */
	private static class Node<T> {
		
		public Node<T> before;
		
		public Node<T> after;
		
		public T value;
		
		Node(T value) {
			this.value = value;
			this.before = null;
			this.after = null;
		}
	}
	
	private static class Iter<T> implements Iterator<T> {
		/**
		 * This is the value that walks through the list.
		 */
		Node<T> current;

		/**
		 * This constructor details where to start, given a list.
		 * @param list - the SinglyLinkedList to iterate or loop over.
		 */
		public Iter(DoublyLinkedList<T> list) {
			this.current = list.start;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			T found = current.value;
			current = current.after;
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
