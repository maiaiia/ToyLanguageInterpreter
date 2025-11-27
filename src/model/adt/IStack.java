package model.adt;

import exception.StackEmptyException;

public interface IStack<T> {
    void  push(T element);
    T pop() throws StackEmptyException;
    boolean isEmpty();
    public String toString();
}
