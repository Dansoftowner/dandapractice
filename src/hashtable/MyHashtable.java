package hashtable;

import java.util.Objects;

public class MyHashtable<K, V> {

    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            var sb = new StringBuilder();
            sb.append("[%s=%s]".formatted(key, value));
            if (next != null) {
                sb.append(" -> ");
                sb.append(next);
            }
            return sb.toString();
        }
    }

    private Entry<K, V>[] table;

    @SuppressWarnings("unchecked")
    public MyHashtable(int capacity) {
        this.table = new Entry[capacity];
    }

    public V put(K key, V value) {
        int h = hash(key);
        var entry = table[h];
        if (entry == null) {
            table[h] = new Entry<>(key, value);
        } else {
            while (entry.next != null && !entry.key.equals(key))
                entry = entry.next;
            if (entry.key.equals(key))
                return entry.value = value;
            else
                entry.next = new Entry<>(key, value);
        }
        return null;
    }

    public V get(K key) {
        int h = hash(key);
        var entry = table[h];
        while (entry != null && !entry.key.equals(key)) {
            entry = entry.next;
        }
        return entry == null ? null : entry.value;
    }

    public V remove(K key) {
        int h = hash(key);
        var entry = table[h];

        if (entry == null)
            return null;
        if (entry.key.equals(key))
            table[h] = entry.next;

        var next = entry.next;
        while (next != null && !next.key.equals(key)) {
            entry = next;
            next = next.next;
        }
        if (next == null)
            return null;

        entry.next = next.next;
        return next.value;
    }

    private int hash(Object key) {
        Objects.requireNonNull(key, "Key must not be null");
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % table.length;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        for (var entry : table) {
            while (entry != null) {
                sb.append("%s=%s".formatted(entry.key, entry.value));
                sb.append(", ");
                entry = entry.next;
            }
        }
        if (!sb.isEmpty()) sb.delete(sb.length() - 2, sb.length());
        sb.insert(0, "[");
        sb.append("]");
        return sb.toString();
    }
}
