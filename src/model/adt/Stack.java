package model.adt;

import exception.StackEmptyException;

import java.util.EmptyStackException;

public class Stack<T> implements IStack<T> {
    private final java.util.Stack<T> stack = new java.util.Stack<>(); //stack is thread safe

    @Override
    public void push(T element) {
        stack.push(element);
    }

    @Override
    public T pop() throws StackEmptyException {
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
        String result = "";
        for (T element : stack.reversed()) {
            result += element.toString() + "\n";
        }
        if (result.length() != 0) {result = result.substring(0, result.length()-1);}
        return result;
    }
}
