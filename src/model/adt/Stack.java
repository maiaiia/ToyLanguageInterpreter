package model.adt;

import exception.StackEmptyException;

import java.util.EmptyStackException;

public class Stack<T> implements IStack<T> {
    private final java.util.Stack<T> stack = new java.util.Stack<>();

    @Override
    public void push(T element) {
        stack.push(element);
    }

    @Override
    public T pop() {
        try {
            return stack.pop();
        } catch (EmptyStackException e) {
            throw new StackEmptyException();
        }
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString(){
        return stack.toString();
    }
}
