package adt;

import exception.StackEmptyException;

class Node<T>{
    T data;
    Node<T> next;

    Node (T data){
        this.data = data;
        this.next = null;
    }
}

public class SLLStack<T> implements IStack<T> {
    private Node<T> head = null;

    SLLStack(){
        head = null;
    }
    @Override
    public void push(T element) {
        Node<T> newNode = new Node<T>(element);
        newNode.next = head;
        head = newNode;
    }

    @Override
    public T pop() {
        if  (head == null) {
            throw new StackEmptyException();
        }
        Node<T> temp = head;
        head = head.next;
        return temp.data;
    }

    @Override
    public T topElement() {
        if  (head == null) {
            throw new StackEmptyException();
        }
        return head.data;
    }

    @Override
    public boolean empty() {
        return head == null;
    }
}
