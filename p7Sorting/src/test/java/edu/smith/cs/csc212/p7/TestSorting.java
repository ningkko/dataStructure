package edu.smith.cs.csc212.p7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TestSorting {
	/**
	 * This is useful for testing whether sort algorithms work!
	 * @param items - the list of integers.
	 * @return true if it is sorted, false if not.
	 */
	private static boolean checkSorted(List<Integer> items) {
		for (int i=0; i<items.size()-1; i++) {
			if (items.get(i) > items.get(i+1)) {
				System.err.println("items out of order: "+items.get(i)+", "+items.get(i+1) + " at index="+i);
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Here's some data!
	 */

	private static int[] data = {9,6,7,1,4,5,5,4,8,3,8,6,4,21,7,2,1};
	
	@Test
	public void testBubbleSort() {
		// See if the data can be reversed:
		ArrayList<Integer> sortMe = new ArrayList<>();
		for (int y : data) {
			sortMe.add(y);
		}
		BubbleSort.bubbleSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
		
		// For good measure, let's shuffle it and sort it again to see if that works, too.
		Collections.shuffle(sortMe);
		BubbleSort.bubbleSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
	}
	
	@Test
	public void testSelectionSort() {
		// See if the data can be reversed:
		ArrayList<Integer> sortMe = new ArrayList<>();
		for (int y : data) {
			sortMe.add(y);
		}
		SelectionSort.selectionSort(sortMe);
		//SelectionSort.visualize(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
		
		// For good measure, let's shuffle it and sort it again to see if that works, too.
		Collections.shuffle(sortMe);
		SelectionSort.selectionSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
	}
	
	@Test
	public void testInsertionSort() {
		// See if the data can be reversed:
		ArrayList<Integer> sortMe = new ArrayList<>();
		for (int y : data) {
			sortMe.add(y);
		}
		InsertionSort.insertionSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
		
		// For good measure, let's shuffle it and sort it again to see if that works, too.
		Collections.shuffle(sortMe);
		InsertionSort.insertionSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
	}
	
	@Test
	public void testRecursiveMergeSort() {
		// See if the data can be reversed:
		ArrayList<Integer> sortMe = new ArrayList<>();
		for (int y : data) {
			sortMe.add(y);
		}
		MergeSort.recursiveMergeSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
		
		// For good measure, let's shuffle it and sort it again to see if that works, too.
		Collections.shuffle(sortMe);
		MergeSort.recursiveMergeSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
	}
	
	
	@Test
	public void testIterativeMergeSort() {
		// See if the data can be reversed:
		ArrayList<Integer> sortMe = new ArrayList<>();
		for (int y : data) {
			sortMe.add(y);
		}
		IterativeMergeSort.iterativeMergeSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
		
		// For good measure, let's shuffle it and sort it again to see if that works, too.
		Collections.shuffle(sortMe);
		IterativeMergeSort.iterativeMergeSort(sortMe);
		Assert.assertTrue(checkSorted(sortMe));
	}

	@Test
	public void DoublyLinkedMergeSort() {
		//private static int[] data = {9,8,7,6,5,4,3,2,1};
		ArrayList<Integer> sortMe = new ArrayList<>();
		for (int y : data) {
			sortMe.add(y);
		}
		
		DoublyLinkedList lst=new DoublyLinkedList();
		for(int i:sortMe) {
			lst.addBack(i);
		}


		lst.head=lst.mergeSort();
		//lst.visualize(lst.head);

		ArrayList<Integer> sorted = lst.convertToArrayList();
		Assert.assertTrue(checkSorted(sorted));	
		
	}

}
