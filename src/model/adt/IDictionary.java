package model.adt;

public interface IDictionary<K, V> {
    void add(K key, V value);
    void remove(K key);
    void search(K key);
    int size();
    boolean isEmpty();
}
