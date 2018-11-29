package edu.smith.cs.csc212.p7;

import java.util.ArrayList;
import java.util.List;

public class IterativeMergeSort {


    // a non-recursive version
    // O(nlgn)
    public static void iterativeMergeSort(List<Integer> lst) {
    	
    	/**
    	 * start and end positions of the list
    	 */
    	int start=0;
    	int end=lst.size()-1;
    	

    	// for size==1,2,4,8...merge them
    	for(int size=1;size<=end;size=size*2) {
    		    		
    		for(int left=start;left<end;left+=2*size) {
        		
    			/**
    			 * middle index
    			 */
    			int middle=left+size-1;
    			/**
    			 * right index, make sure not to run out of bound
    			 */
    			int right=Math.min(left+size*2-1,end);
    			
    			IterativeMergeSort.merge(lst, left, middle ,right);

    		}			
    	}
    	

    }
    public static void merge(List<Integer> input, int low, int mid, int high) {
		/**
		 * left sublist
		 */
		List<Integer> left = new ArrayList<>();
		/*
		 * right sublist
		 */
		List<Integer> right = new ArrayList<>();
		
		// put elements into sublists
		for(int i=low;i<=mid;i++) {
			left.add(input.get(i));
		}
		for(int i=mid+1;i<=high;i++) {
			right.add(input.get(i));
		}
		
		/**
		 * pointer in the left sublist
		 */
		int indexL = 0;
		/**
		 * pointer in the right sublist
		 */
		int indexR = 0;
		/**
		 * pointer in the merged list
		 */
		int indexI = low;	
		
		// while not reaching the end of either sublist
		// Compare each element of them
		// add the smaller one into the merged list
		while(indexL<left.size()&&indexR<right.size()) {
			
			if(left.get(indexL)<=right.get(indexR)) {
				input.set(indexI, left.get(indexL));
				indexL++;
			}else {				
				input.set(indexI, right.get(indexR));
				indexR++;
			}
			indexI++;
		}
		
		// add the whole left list if one list is longer than the other.
		while(indexL<left.size()) {
			input.set(indexI, left.get(indexL));
			indexI++;
			indexL++;
		}
		while(indexR<right.size()) {
			input.set(indexI, right.get(indexR));
			indexR++;
			indexI++;
		}
		
		
	}

}
