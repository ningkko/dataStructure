// Heaps and heapsort

//called priority queue

import java.util,PriorityQueue<T>;

//Application: 
//Dijkstra's algorithm
//Top-K search

// Difference btw heaps and Binary Search Trees
// -heaps have the biggest item at the top and all nodes are bigger than their children
// BST have a complete order, all smaller goes left and bigger goes right

// -> heaps are not complete because nodes are not in order
//  the order is only in each branch

// heap structure
// always lean to the left
// if there are leaves slpit across two levels, the deepest leaves

// Trees fit in arrays, and people aleays use this for heaps, so no extra space needed to..

//To insert a thing:
//insert it at a blank space
//then heapify

//O(logn)
private void siftUp(int index){

    // move up till top
    while(!isRoot(index)){

        int parent=this,parent(index);
        //if the parent is samller,swap it
        if (heap,get(parent)<heap.get(inxdex)) {
            swap(parent,index);
            index=parent;
            continue;
        }
        else{
            break;
        }
    }
}

//Remove
//Take the best out
//swap it with the rightmost and deepest item
//heapify

//O(logn)
private void siftDown(int index){
    while(leftChild(index)<heap.size()){
        // get indices of children
        int left =leftChild(index);
        int right =rightChild(index);

        //find max of left, right if exsist of a parent
        int localMax=index;
        if (heap.get(left)>heap.get(localMax)) {
            localMax=left;        
        }
        if (right<heap.size()&&heap.get(right)>heap.get(localMax)) {
            localMax=right;
        }
        if (localMax==index) {
            return;
        }
        else{
            swap(localMax,index);
            index=localMax;
            continue;
        }
    }
}

//• heapify(siftDown) = O(n)
//• Remove-Max(siftDown) = O(log(n)) 
//• n removeMaxes = O(n log(n))
//• total = n log(n) + n = O(n log(n))