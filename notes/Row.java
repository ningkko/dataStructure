

public class Row<KeyType,ValueType> implements Map.Entry<KeyType,ValueType>{

    private KeyType key;
    public ValueType value;


    public static void main(String[] args) {
        Map<String,String> items=new HashMap<String,String>();
        item.put("java","1");
        item.put("java","2");

        item.put("python","1");

        Set<Map.Entry<String,String>> allRows=items.entrySet();
    }



    public KeyType getKey(){
        return this.key;
    }

    public ValueType getValue(){
        return this.value;
    }

    //?
    public boolean setValue(ValueType value){
        return this.value=value;
    }
}
