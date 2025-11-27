package model.adt;

import exception.OutOfBoundsIndexException;
import exception.ValueNotFoundException;

public interface IList<T> {
    int size();
    boolean isEmpty();
    T get(int index) throws OutOfBoundsIndexException;
    T set(int index, T value) throws OutOfBoundsIndexException;
    void append(T value);
    void insert(int index, T value) throws OutOfBoundsIndexException;
    T remove(int index) throws OutOfBoundsIndexException;
    int find(T value) throws ValueNotFoundException;
    String toString();
}
