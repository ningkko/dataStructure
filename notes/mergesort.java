// Sorting

// bubble sort :=  works by repeatedly swapping the adjacent elements if they are in wrong order.

//      after things are sorted it go thru the list for one more time to make surebthe list is sorted
//      backwards
void bubbleSort(List<Integer> items){
    
    int n=items.size(-1);
       
        while(true){
            
            boolean isSorted = true;
            
            for (int i =0;i<n i++; ) {
                if (items.get(i)>itmes.get(i+1)) {
                    swap(items,i,i+1);
                    isSorted=false;
                }
            }

            n--;
            if (isSorted) breaj;
        }
}


// Selection sort
// from the left, scan to the right, find the minimum and move left, remove it from the todo list
// does not have memory among loops, doesn't remember what is the minimum


// selection sort and bubble sort are both sorting in place

// insertion sort: build a new list.
//      linearly chec O(n^2)
//      use binary search O(nlgn)


// combine two sorted array 
// compare each ith item in one list to the ith item in the otehr list
// put the smaller/larger one as wanted into the new list first
// -> psedocode
//      while both lists have next => isEmpty()
//          take the smaller one
//      put to remaining from the surviving list into the result

// What should be the input list for this algorithm?
-> SinglyLinkedList //bad at addback()
    this.isEmpty(){
        return this.start==null;
    }
    //O(1)
    this.addFront(Node newStart){
        temp=this.start;
        this.start=newStart;
        this.start.next=temp;
    }
    this.addBack(Node newBack){
        // loop and find the last one
        // O(n)
        }

-> DoublyLinekdList
    this.isEmpty()// O(1)
    this.addFront()// O(1)
    this.addback()// O(1)

-> ArrayList // bad at addFront()
    this.addFront(Node newNode){
        // O(n)
    }
    this.isEmpty(){
        return this.length==0;
    }// O(1)
    this.addBack(Node newNode){
        this.add(newNode);
    }// O(1)


// doublylinked is prefered because of its addback()
// it does poorly in between but this alg does not add things inside

