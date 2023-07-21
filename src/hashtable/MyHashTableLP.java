package hashtable;

import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

public class MyHashTableLP<K, V> {
    private static class Entry<K, V> {
        private K key;
        private V value;
        private int i;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private static final double C = Math.E;

    private final Entry<K, V>[] table;
    private int size;

    public MyHashTableLP() {
        this(11);
    }

    @SuppressWarnings("unchecked")
    public MyHashTableLP(int capacity) {
        this.table = new Entry[capacity];
    }

    public V put(K key, V value) {
        if (isFull())
            throw new IllegalStateException();

        int index = getIndex(key, entry -> entry == null || entry.key.equals(key));
        Entry<K, V> previousEntry = table[index];
        if (previousEntry != null) {
            var previous = previousEntry.value;
            previousEntry.value = value;
            return previous;
        }
        table[index] = new Entry<>(key, value);
        return null;
    }

    public V get(K key) {
        int index = getIndex(key, entry -> entry != null && entry.key.equals(key));
        if (index == -1)
            return null;
        return table[index].value;
    }

    public V remove(K key) {
        int index = getIndex(key, entry -> entry.key.equals(key));
        if (index == -1)
            return null;

        var removedEntry = table[index];
        table[index] = null;
        return removedEntry.value;
    }

    public int size() {
        return size;
    }

    private int getIndex(K key, Predicate<Entry<K, V>> stopPredicate) {
        int hash = hash(key);

        int i = 0;
        while (i < table.length) {
            var current = table[(hash + i) % table.length];
            if (stopPredicate.test(current))
                return hash + i;
            i++;
        }

        return -1;
    }

    private boolean isFull() {
        return table.length == size;
    }

    private int hash(K key) {
        return key.hashCode() % table.length;
        // return (int) (table.length * (C * Math.abs(key.hashCode()) % 1));
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        for (Entry<K, V> entry : table) {
            if (entry == null) {
                continue;
            }
            sb.append("%s=%s".formatted(entry.key, entry.value));
            sb.append(", ");
        }
        if (!sb.isEmpty()) sb.delete(sb.length() - 2, sb.length());
        sb.insert(0, "[");
        sb.append("]");
        return sb.toString();
    }
}
