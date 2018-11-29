package edu.smith.cs.csc212.p7;

import java.util.List;

public class SelectionSort {

	// in place
	// O(n^2)
	
	public static void selectionSort(List<Integer> lst) 
    {   
        // loop thru the list
        for (int i = 0; i < lst.size()-1; i++) 
        { 
            // find the min element in the unsorted part by comparing
            int min = i; 
            for (int j = i+1; j < lst.size(); j++) 
                if (lst.get(j) < lst.get(min)) 
                    min = j; 
  
            // Swap the min element and the first element
            int temp = lst.get(min); 
            lst.set(min, lst.get(i));
            lst.set(i,temp);
        } 
        
        //visualize(lst);

    } 
	
	public static void visualize(List<Integer> lst) {
        for(Integer i: lst) {
        	System.out.println(i);
        }
	}

}
