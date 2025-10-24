package model.adt;

public interface IDictionary<K, V> {
    void add(K key, V value);
    void remove(K key);
    V search(K key);
    boolean contains(K key);
    int size();
    boolean isEmpty();
}
