package model.adt;

public interface IStack<T> {
    void  push(T element);
    T pop();
    T topElement();
    boolean isEmpty();
}
//TODO question - should this return a boolean value?
