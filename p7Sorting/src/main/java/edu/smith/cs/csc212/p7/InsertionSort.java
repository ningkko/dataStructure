package edu.smith.cs.csc212.p7;

import java.util.List;

public class InsertionSort {
	
	// IN PLACE
	// O(n^2)
    public static void insertionSort( List<Integer> lst){
    	
    	// loop thru the list
        for (int i=1; i<lst.size(); ++i) 
        { 
            int current = lst.get(i); 
            /**
             * pointer of items before CURRENT
             */
            int j = i-1; 
  
            // Move elements greater than CURRENT rightward 
            while (j>=0 && lst.get(j)>current) 
            { 
                lst.set(j+1,lst.get(j));
            	j--;
            } 
            lst.set(j+1, current);
        } 
        
        //visualize(lst);
    } 
    
    @SuppressWarnings("unused")
	private static void visualize(List<Integer> lst) {
        for(Integer i: lst) {
        	System.out.println(i);
        }
	}
}
