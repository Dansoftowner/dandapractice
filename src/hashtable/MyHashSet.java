package hashtable;

public class MyHashSet<T> {

    private static final Object PLACE_HOLDER = new Object();

    private final MyHashtable<T, Object> table;

    public MyHashSet() {
        table = new MyHashtable<>(10);
    }

    public boolean add(T value) {
        return table.put(value, PLACE_HOLDER) == null;
    }

    public boolean remove(T value) {
        return table.remove(value) != null;
    }

    public boolean contains(T value) {
        return table.get(value) != null;
    }
}
