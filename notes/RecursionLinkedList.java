//fibonacci for loop 
public int fib(int n){
    int first =1;
    int current;
    int second=1;
    for (int i=0;i<n ;i++ ) {
        current=first+second;
        first=second;
        second=current;    
    }

    return current;
}


//recursive fibonacci 
public int fibonacci(int n){

    if (n==0) {
        return;       
    }
    if (n==1) {
        return 1;      
    }
    else if (n==2) {
        return 1;       
    }
    else{
        return fibonacci(n-1)+fibonacci(n-2);
    }
}


//recursive factorial
public static int factorial(int n){
    if (n==1) {
        //base case
        return 1;        
    }
    else{
        //inductive hypothesis
        return n*factorial(n-1);
    }
}

/**
Structual recursion

-> Linked List::=
    without branches, easier for the computer to find data

*/

// linked data
Fish f1=new Fish("f1",
                 new Fish("f2",
                        new Fish("f3",null)))

// Linked List

class Node<T>{

    Node next;
    T value;

    public Node(T value, Node next){
        this.next=next;
        this.value=value;
    }

    class LinkedList<T>{

        //head of the list
        // base of the reccusive structure
        Node<T> start=null;

    }

    public void printAll(){

        printForward(this.start);

    }


    private void printForward(Node<T> startNode){

        if (node==null) {
            return;            
        }else{
        
        System.out.System.out.print(node.value);  
        printForward(node.next);  
        
        }

    // if print after calling next node, then print backwards
    // backwards: 0->1->2->null

    private void printBackward(Node<T> startNode){

        if (node==null) {
            return;            
        }else{
        
        printBackward(node.next);  
        System.out.System.out.print(node.value);  
        
        }

    public int size(){
        return countNodes(this.start);
    }
    private int countNodes(Node<T> node);
        if (node.next==null) {
            return 1;
        }else{
            return 1+countNodes(node.next);
        }
    }
