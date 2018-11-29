
/* Kind::=indicates the class needs parameters
* usually written in T
*
* A class can only "implement" an interface. A class only "extends" a class. 
* Likewise, an interface can extend another interface.
*
* A class can only extend one other class. A class can implement several interfaces.
*


-> extends: A derived class can extend a base class. You may redefine the behaviour of an established relation. Derived class "is a" base class type

-> implements: You are implementing a contract. The class implementing the interface "has a" capability.
*/


public class setFromList<Kind> extends AbstractSet<Kind>{

    private List<Kind> items;

    //constructor
    public setFromList(){
        this.items=new ArrayList();
    }


    //------------------ MAIN -------------------------------
    public static void main(String[] args) {
        List<String> withDuplicates=new ArrayList<>();
        withDuplicates.add("a");
        withDuplicates.add("b");
        withDuplicates.add("b");  
        setFromList<String> result=new setFromList<>();
        for(String item : withDuplicates){
            result.add(item);
        }

        for (String item:result) {
            System.out.print(item);
        }

    }

    //------------------------- Override -------------------------------
    
   
    // add only if not repeated
    public boolean add(Kind e){

        if (this.item.contains(e)){
            return false;
        }
        this.items.add(e);
        return true;
    }


    public int size(){
        return this.items.size();
    }


    public boolean isEmpty(){
        return this.items.isEmpty();
    }


    public Iterator<Kind> iterator(){
        return this.items.iterator;
    }


    public boolean remove(Kind e){
        return this.items.remove(e);
    }


}