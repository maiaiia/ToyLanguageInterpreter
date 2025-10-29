package model.adt;

public interface IList<T> {
    int size();
    boolean isEmpty();
    T get(int index);
    T set(int index, T value);
    void append(T value);
    void insert(int index, T value);
    T remove(int index);
    int find(T value);
    String toString();
}
