package model.adt;

import exception.StackEmptyException;

import java.util.EmptyStackException;

public class Stack<T> implements IStack<T> {
    java.util.Stack<T> stack;

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
    public T topElement() {
        try{
            return stack.peek();
        } catch (EmptyStackException e) {
            throw new StackEmptyException();
        }
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
