package edu.smith.cs.csc212.p7;

import java.util.List;

public class MergeSort {

    // Additional space is required for merge-sort.
    // O(nlgn)
    public static void recursiveMergeSort(List<Integer> lst){
    	
    	if(lst.size()==0||lst.size()==1) {
    		return;
    	}
    	else {
	        // call the helper function, sort from the beginning to the end
    		recursiveMergeSort(lst,0,lst.size()-1);
    	}
    	
        //visualize(lst);
    }

    // a recursive method to sort the list by
    // sorting the left half and right half of a given list separately
    public static void recursiveMergeSort(List<Integer> lst, int left, int right){
        
        if (left>=right) {
            return;
        }

        int middle=(left+right)/2;
        // sort left-half
        recursiveMergeSort(lst,left,middle);
        // sort right-half
        recursiveMergeSort(lst,middle+1,right);
        // reorder them
        merge(lst,left,right);

    }
    

    
    // merges sub-list together in order
    public static void merge(List<Integer> lst, int left, int right) {
      
    		int middle=(left+right)/2;
    		/**
    		 * length of the first sub-list
    		 */
    		int l1=middle-left+1;
    		/**
    		 * length of the second sub-list
    		 */
    		int l2=right-middle;
            // copy the lst into 2 sublists
            int[] lst1=new int[l1];
            int[] lst2=new int[l2];

            for (int i=0; i<l1; i++) { 
                lst1[i] = lst.get(left + i); 
            }
            for (int j=0; j<l2; j++){
                lst2[j] = lst.get(middle+1+j); 
            }
      
            /**
             * pointers for sub-lists
             */
            int leftPointer = 0;
            int rightPointer = 0; 
      
            /**
             * pointer for the final list, start from the beginning
             */
            int mergedPointer = left; 
            
            while (leftPointer<l1&&rightPointer<l2) 
            { 
            	// if the element in the left sub-list is smaller than the one in the right
            	// put the left sub-list element into the current merged list position
                if (lst1[leftPointer] <= lst2[rightPointer]){ 
                    lst.set(mergedPointer, lst1[leftPointer]); 
                    leftPointer++; 
                } 
                // else put the one in the right sub-list into the merged list
                else
                { 
                    lst.set(mergedPointer, lst2[rightPointer]); 
                    rightPointer++; 
                } 
                
                // move to the next position in the final merged list
                mergedPointer++;
            } 
      
            // if we run out of one of the sub-lists, just put the rest of the other one into the final list
            while (leftPointer < l1){
           
            	lst.set(mergedPointer, lst1[leftPointer]);
                leftPointer++;
                mergedPointer++;
            } 
            while (rightPointer < l2){
            	
            	lst.set(mergedPointer, lst2[rightPointer]);
            	rightPointer++;
                mergedPointer++;

            } 
        } 
    
	 	public static void visualize(List<Integer> lst) {
	         for(Integer i: lst) {
	         	System.out.println(i);
	         }
 	}
    }


