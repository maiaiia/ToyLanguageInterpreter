package model.adt;

public interface IDictionary<K, V> {
    void add(K key, V value);
    void remove(K key);
    V search(K key);
    V get(K key); //no checking
    boolean contains(K key);
    int size();
    boolean isEmpty();
}
