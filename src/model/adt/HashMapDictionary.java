package model.adt;

import exception.KeyNotInDictionaryException;

import java.util.HashMap;

public class HashMapDictionary<K, V> implements IDictionary<K, V> {
    private final HashMap<K, V> map = new HashMap<K, V>();

    @Override
    public void add(K key, V value) {
        map.put(key, value);
    }

    @Override
    public boolean contains(K key) {
        return map.containsKey(key);
    }

    @Override
    public void remove(K key) {
        if (!map.containsKey(key)) {
            throw new KeyNotInDictionaryException("Key not in dictionary");
        }
        map.remove(key);
    }

    @Override
    public V search(K key) throws KeyNotInDictionaryException {
        V v = map.get(key);
        if (v == null){
            throw new KeyNotInDictionaryException();
        }
        return v;
    }

    @Override
    public V get(K key) {
        return map.get(key);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public String toString(){
        return map.toString();
    }

}
