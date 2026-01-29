package model.adt;

import exception.KeyNotInDictionaryException;

import java.util.Collection;
import java.util.Set;

public interface IDictionary<K, V> {
    void put(K key, V value);
    void remove(K key) throws KeyNotInDictionaryException;
    V search(K key) throws KeyNotInDictionaryException;
    V get(K key); //no checking
    boolean contains(K key);
    int size();
    boolean isEmpty();
    String toString();
    Set<K> keySet();
    Collection<V> values();

    IDictionary<K, V> copy();
}
