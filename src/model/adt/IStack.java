package model.adt;

public interface IStack<T> {
    void  push(T element);
    T pop();
    boolean isEmpty();
    public String toString();
}
