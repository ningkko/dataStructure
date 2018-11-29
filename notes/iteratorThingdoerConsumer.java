// Things doer

// To hide the detail of loops

interface ThingDoer<T>{

    void doingThing(T item);

}

public void loop(ThingDoer<T> what){
    for(int i=0;i<fill;i++){
        what.doThing(this.getIndex(i));
    }
}

public static void main(String[] args) {
    List<T> items=...
}
//print
item.loop(new ThingDoer(){
    void doThing(T item){
        System.out.print("item");
    }
});

// ThingDoer is called consumer in java8

//Abstract loop
Iterator<T> iter=...;
while(iter.next()!=null){
    T it =iter.next();
    ...
}

// Index/Array-Based Iterator
class IndexListIterator<T> implements Iterator<T>{
    int i=0;
    List<T> data;

    public IndexListIterator(List<T> data){this.data=data;}

    @Override public boolean hasNext(){
        T current=data.get(i);
        i++
        return current;
    }
}

// LinkedList.Node-based Iterator
class LLIter<T> implements Iterator{
    Node<T> current;

    public LLIter(SList<T> data){
        this.current=data.start;
    }

    @Override 
    public boolean hasNext(){
        return current!= null;
    }
    @Override
    public T next(){
        T val=current.value;// which is a fixed linked list object
        current= current.next;// move to the next
        return val;
    }
}


// P6, in SinglyLinkedList, we defined an iterator
// loop thru fixedlists inside linkedlists
private static class Iter<T> implements Iterator<T> { Node<T> current;
  public Iter(SinglyLinkedList<T> list) {
    this.current = list.start;
}

  @Override
  public boolean hasNext() {
    return current != null;
}


public class SinglyLinkedList<T> implements P6List<T>, Iterable<T>{
    ...
    /**
    * Implement iterator() so that {@code SinglyLinkedList} can be used in a for loop.
    * @return an object that understands "next()" and "hasNext()". */

    public Iterator<T> iterator(){
        return new Iter<>(this);
    }
}

// inside chunkyLinkedList
@Override
public int size(){

    int total=0;

    for (FixedSizeList<T> chunk: this.chunks) {
        total+=chunk.size();        
    }
    return total;
}

// In java there is a list iterator, but usually we don't use it.
