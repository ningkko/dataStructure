package edu.smith.cs.csc212.p7;

import java.util.ArrayList;

public class DoublyLinkedList {

	/**
	 * thefirst node in the list
	 */
	Node head;
	Node back;
	
	class Node{
		
		int value;
		Node next;
		Node previous;
		

		// constructor with input value
		Node(int value){
			this.value=value;
			this.next=null;
			this.previous=null;
		}
	}
	
	public DoublyLinkedList() {
		
		head=null;
		back=null;
		
	}
	
	public DoublyLinkedList(int value) {
		
		head=new Node(value);
		
	}
	
	public boolean addBack(int value) {
		/**
		 * new node
		 */
		Node newNode=new Node(value);
		// set the new node to be head if no head yet
		if(this.head==null) {
			this.head=newNode;
			this.back=newNode;
		}else {
		
		this.back.next=newNode;
		newNode.previous=this.back;
		this.back=newNode;
		}
		return true;

	
	}
	
	public Node split(Node head) {
		
		/**
		 * counter to loop thru the list
		 */
		Node counter=head;
		/**
		 * index of the middle element in the list
		 */
		Node middle=head;
		// counter / 2 = middle
		while(counter.next!=null&&counter.next.next!=null) {
			counter=counter.next.next;
			middle=middle.next;
		}
			
		Node lst2StartNode= middle.next;
		middle.next=null;
		return lst2StartNode;
	}
	
	public Node mergeSort() {
		return this.mergeSort(this.head);
	}
	
	public Node mergeSort(Node node) {
		
		// return if reaches the end of the list
		if(node==null||node.next==null) {
			return node;
		}
		
		/**
		 * split from the given node
		 */
		Node lst2head=split(node);
		
		// the given node is the head of the first list
		// recur for left and right sublists
		node=mergeSort(node);
		lst2head=mergeSort(lst2head);
		
		// merge the result
		return merge(node,lst2head);
		
	}
	
	public Node merge(Node node1, Node node2) {
		
		//return if the other node is null
		if(node1==null) {
			return node2;
		}
		
		else if(node2==null) {
			return node1;
		}
		
		// if node1.value < node2.value, take node 1 out and merge what's left
		else if(node1.value<node2.value) {
			// merge what's left
			node1.next=merge(node1.next,node2);
			// set the previous node of the second node(after merging) to be the first node
			node1.next.previous=node1;
			// set the previous node to be null
			node1.previous=null;
			// return the first node
			return node1;
		}
		// otherwise do the same thing with the other node
		else {
			// merge what's left
			node2.next=merge(node1,node2.next);
			// set the previous node of the second node(after merging) to be the first node
			node2.next.previous=node2;
			// set the previous node to be null
			node2.previous=null;
			// return the first node
			return node2;
		}
		
		
	}
	
	public ArrayList<Integer> convertToArrayList(){
		
		ArrayList<Integer> newList = new ArrayList<>();
		
		Node last=this.head;
		
		while(last.next!=null) {
			newList.add(last.value);
			last=last.next;
		}
		
		return newList;
	}
	
	public void visualize(Node node) {
		/**
		 * pointer to loop thru the list
		 */
        while (node != null) { 
            System.out.print(node.value + "\n"); 
            node = node.next;   
        }
	}

}

